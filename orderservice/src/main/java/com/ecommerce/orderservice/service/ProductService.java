package com.ecommerce.orderservice.service;


import com.ecommerce.orderservice.exception.BusinessException;
import com.ecommerce.orderservice.requests.PurchaseRequest;
import com.ecommerce.orderservice.responses.PurchaseResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requests,headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType =
                new ParameterizedTypeReference<List<PurchaseResponse>>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity =
                restTemplate.exchange(
                  productUrl+"/purchase",
                  HttpMethod.POST,
                  requestEntity,
                  responseType
                );

        if(responseEntity.getStatusCode().isError())
            throw new BusinessException("Product with Id not found");

        return responseEntity.getBody();
    }
}
