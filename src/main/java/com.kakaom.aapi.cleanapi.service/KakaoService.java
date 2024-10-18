package com.kakaom.aapi.cleanapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {
    private static final String KAKAO_API_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

    public void sendKakaoMessage(String accessToken, String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        String requestBody = "template_object={\"object_type\":\"text\",\"text\":\"" + message + "\",\"link\":{\"web_url\":\"http://yourwebsite.com\"}}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(KAKAO_API_URL, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to send message: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while sending Kakao message: " + e.getMessage());
        }
    }
}
