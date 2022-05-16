package org.solowev.taskmanager.base.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.solowev.taskmanager.base.aspect.message.LogDirection;
import org.solowev.taskmanager.base.aspect.message.LogMessage;
import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.model.ErrorDto;
import org.solowev.taskmanager.base.model.ResponseDto;
import org.solowev.taskmanager.base.model.ResponseResult;
import org.solowev.taskmanager.base.utils.SecurityUtils;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class WebCallPointCut {

    @Around(value = "@annotation(webCallJoinPoint)")
    public ResponseEntity<ResponseDto> controllerCall(ProceedingJoinPoint proceedingJoinPoint, WebCallJoinPoint webCallJoinPoint) {
        return proceedCall(proceedingJoinPoint);
    }

    private ResponseEntity<ResponseDto> proceedCall(ProceedingJoinPoint joinPoint) {
        LogMessage inMessage = createMessage(joinPoint, LogDirection.IN);

        log.info(inMessage.getMessage());

        ResponseEntity<ResponseDto> result;
        try {
            ResponseEntity<?> data = (ResponseEntity<?>) joinPoint.proceed();
            ResponseDto responseDto = ResponseDto.builder()
                    .result(ResponseResult.OK)
                    .data(data.getBody())
                    .build();
            result = ResponseEntity.ok(responseDto);
        } catch (Throwable t) {
            log.error(ExceptionUtils.getStackTrace(t));
            ErrorCode errorCode;
            String text;
            if (t instanceof TaskManagerException ex) {
                errorCode = ex.getErrorCode();
                text = ex.getMessage();
            } else if (t instanceof AccessDeniedException) {
                errorCode = ErrorCode.ACCESS_DENIED_EXCEPTION;
                text = ErrorCode.ACCESS_DENIED_EXCEPTION.getMessage();
            } else {
                errorCode = ErrorCode.INTERNAL_EXCEPTION;
                text = ExceptionUtils.getRootCauseMessage(t);
            }
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode(errorCode.toString());
            errorDto.setText(text);

            ResponseDto responseDto = ResponseDto.builder()
                    .result(ResponseResult.FAIL)
                    .error(errorDto)
                    .build();

            result = ResponseEntity.status(errorCode.getHttpStatus()).body(responseDto);
        }

        LogMessage outMessage = createMessage(joinPoint, LogDirection.OUT);
        log.info(outMessage.getMessage());

        return result;
    }

    private LogMessage createMessage(ProceedingJoinPoint joinPoint, LogDirection logDirection) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String username = SecurityUtils.getCurrentUser().getUsername();

        return new LogMessage(className, methodName, logDirection, username);
    }
}
