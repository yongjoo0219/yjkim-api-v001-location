package com.yjkim.api.dao;

import com.yjkim.api.model.KeywordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeywordDao {
    /**
     * JdbcTemplate.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 키워드 조회.
     *
     * @param keyword 키워드 값.
     * @return 조회 결과.
     */
    public List<KeywordVO> getKeyword(String keyword) {
        return jdbcTemplate.query(
                "SELECT WORD, CNT FROM yj_key_tb WHERE WORD = ?", new Object[]{keyword},
                (rs, rowNum) -> {
                    KeywordVO keywordVO = new KeywordVO();
                    keywordVO.setWord(rs.getString("word"));
                    keywordVO.setCnt(rs.getInt("cnt"));
                    return keywordVO;
                });
    }

    /**
     * 키워드 Top10 조회.
     *
     * @return 조회된 키워드 리스트
     */
    public List<KeywordVO> getKeywordRank() {
        return jdbcTemplate.query(
                "SELECT WORD, CNT FROM yj_key_tb ORDER BY CNT DESC LIMIT 10",
                (rs, rowNum) -> {
                    KeywordVO keywordVO = new KeywordVO();
                    keywordVO.setWord(rs.getString("word"));
                    keywordVO.setCnt(rs.getInt("cnt"));
                    return keywordVO;
                });
    }

    /**
     * 키워드 저장.
     *
     * @param keyword 요청 키워드.
     */
    public void addKeyword(String keyword) {
        jdbcTemplate.update("INSERT INTO yj_key_tb(WORD, CNT) VALUES(?, ?)", keyword, 1);
    }

    /**
     * 키워드 수정.
     *
     * @param keywordVO 키워드 정보
     */
    public void modifyKeyword(KeywordVO keywordVO) {
        jdbcTemplate.update("UPDATE yj_key_tb SET CNT = ? WHERE WORD = ?", keywordVO.getCnt(), keywordVO.getWord());
    }
}
