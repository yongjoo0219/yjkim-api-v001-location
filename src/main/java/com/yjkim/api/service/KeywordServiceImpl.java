package com.yjkim.api.service;

import com.yjkim.api.dao.KeywordDao;
import com.yjkim.api.model.KeywordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * KeywordServiceImpl.
 *
 * @author werbwerb@naver.com
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordServiceImpl.class);
    /**
     * KeywordDao.
     */
    @Autowired
    KeywordDao keywordDao;

    @Override
    public void addKeyword(String keyword) {
        List<KeywordVO> keywordList = keywordDao.getKeyword(keyword);
        if(keywordList.isEmpty()) {
            keywordDao.addKeyword(keyword);
        }
        if(!keywordList.isEmpty()) {
            LOGGER.debug("UPDATE >> ");
        }
    }
}
