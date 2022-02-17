package com.yjkim.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjkim.api.exception.CommonException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ObjectUtils.
 *
 * @author werbwerb@naver.com
 */
@Component
public class ObjectUtils {
    /**
     * ObjectMapper.
     *
     * @param json        Object String.
     * @param targetClass 매핑 클래스.
     * @param <T>         매핑 클래스.
     * @return 매핑 결과
     */
    public <T> T getObjectMapper(String json, Class<T> targetClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(targetClass));
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * ListMapper.
     *
     * @param jsonArr     List String.
     * @param targetClass 매핑 클래스.
     * @param <T>         매핑 클래스.
     * @return 매핑 결과
     */
    public <T> List<T> getListMapper(String jsonArr, Class<T> targetClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonArr, objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass));
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
