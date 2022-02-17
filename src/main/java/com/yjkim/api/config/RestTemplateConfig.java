package com.yjkim.api.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * HttpClientConfig 활용한 RestTemplate 정의.
 * - source : https://howtodoinjava.com/spring-restful/resttemplate-httpclient-java-config/
 */
@Configuration
public class RestTemplateConfig {
    /**
     * HttpClient.
     */
    @Autowired
    CloseableHttpClient httpClient;

    /**
     * Rest Template.
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    /**
     * client http request factory.
     *
     * @return HttpComponentsClientHttpRequestFactory
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
