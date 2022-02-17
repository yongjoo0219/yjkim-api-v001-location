package com.yjkim.api.util;

import com.google.gson.Gson;
import com.yjkim.api.exception.ExternalApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplateUtil.
 *
 * @author werbwerb@naver.com
 */
@Component
public class RestTemplateUtil {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtil.class);
    /**
     * RestTemplate.
     */
    @Autowired
    RestTemplate restTemplate;

    public <T> T restTemplate(String url, HttpMethod httpMethod, HttpHeaders headers, Object body, String apiName, Class<T> responseType) {
        long trId = System.currentTimeMillis();
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        Gson gson = new Gson();
        String jsonBody = gson.toJson(body);
        LOGGER.debug("###[REST_REQUEST_INFO] >> " + apiName + " : trId={}, url={}, method={}, headers={}, body={}", trId, url, httpMethod, headers, jsonBody);
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, entity, responseType);
            LOGGER.debug("###[REST_RESPONSE_INFO] >> " + apiName + " : trId={}, statusCode={}, body={}", trId, responseEntity.getStatusCode(), responseEntity.getBody());
            return responseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            LOGGER.debug("###[REST_RESPONSE_ERROR_INFO] >> " + apiName + " : trId={}, exception={} ", trId, e);
            throw new ExternalApiException(e.getResponseBodyAsString());
        } catch (Exception e) {
            LOGGER.debug("###[REST_EXCEPTION] >> " + apiName + " : trId={}, exception={}", trId, e);
            throw e;
        }
    }
}
