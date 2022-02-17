package com.yjkim.api.controller;

import com.yjkim.api.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 기본 Controller.
 *
 * @author werbwerb@naver.com
 */
@RestController
public class IndexController {
    /**
     * 인덱스 페이지.
     *
     * @return ApiResponse
     */
    @GetMapping(value = "/")
    public ApiResponse index() {
        return new ApiResponse();
    }
}
