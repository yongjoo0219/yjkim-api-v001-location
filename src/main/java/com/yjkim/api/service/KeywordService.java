package com.yjkim.api.service;

import com.yjkim.api.model.KeywordVO;

import java.util.List;

/**
 * KeywordService.
 *
 * @author werbwerb@naver.com
 */
public interface KeywordService {
    /**
     * 키워드 저장.
     *
     * @param keyword 요청 키워드
     */
    void addKeyword(String keyword);

    /**
     * 키워드 Top10 조회.
     *
     * @return 조회된 키워드 리스트
     */
    List<KeywordVO> getKeywordRank();
}
