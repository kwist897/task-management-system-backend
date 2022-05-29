package org.solowev.taskmanager.base.client;

import org.solowev.taskmanager.base.model.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface ProfileClient {
    @GetMapping("/user/profile/{userId}")
    ResponseEntity<ResponseDto> getProfileByUserId(@PathVariable("userId") Long userId);
}
