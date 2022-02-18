package com.yjkim.api.service;

import com.yjkim.api.dao.KeywordDao;
import com.yjkim.api.model.KeywordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public synchronized void addKeyword(String keyword) {
        String trimKeyword = keyword.replace(" ", "");

        List<KeywordVO> keywordList = keywordDao.getKeyword(trimKeyword);
        if (keywordList.isEmpty()) {
            keywordDao.addKeyword(trimKeyword);
        }
        if (!keywordList.isEmpty()) {
            LOGGER.debug("UPDATE >> word:{}, cnt:{}", keywordList.get(0).getWord()
                    , keywordList.get(0).getCnt());

            KeywordVO updateKeyword = new KeywordVO();
            updateKeyword.setWord(keywordList.get(0).getWord());
            updateKeyword.setCnt(keywordList.get(0).getCnt() + 1);
            keywordDao.modifyKeyword(updateKeyword);
        }
    }

    @Override
    public List<KeywordVO> getKeywordRank() {
        return keywordDao.getKeywordRank();
    }
}
