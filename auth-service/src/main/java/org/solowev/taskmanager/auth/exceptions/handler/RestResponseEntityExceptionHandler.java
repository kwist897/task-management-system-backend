package org.solowev.taskmanager.auth.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;
import org.solowev.taskmanager.base.model.ErrorDto;
import org.solowev.taskmanager.base.model.ResponseDto;
import org.solowev.taskmanager.base.model.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = TaskManagerException.class)
    public ResponseEntity<ResponseDto> handleInternalExceptions(TaskManagerException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ex.getErrorCode().toString());
        errorDto.setText(ex.getMessage());

        ResponseDto responseDto = ResponseDto.builder()
                .result(ResponseResult.FAIL)
                .error(errorDto)
                .build();
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(responseDto);
    }
}
