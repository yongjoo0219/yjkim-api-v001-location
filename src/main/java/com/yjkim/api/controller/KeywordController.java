package com.yjkim.api.controller;

import com.yjkim.api.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * KeywordController.
 * API ID: yjkim-api-002 - 검색 키워드 목록.
 *
 * @author werbwerb@naver.com
 */
@RestController
public class KeywordController {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordController.class);

    public ApiResponse getKeywordRank() {
        return new ApiResponse();
    }
}
