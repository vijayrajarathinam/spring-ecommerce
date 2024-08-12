package com.ecommerce.productservice.services;


import com.ecommerce.productservice.exception.ProductPurchaseException;
import com.ecommerce.productservice.mapper.ProductMapper;
import com.ecommerce.productservice.repositories.ProductRepository;
import com.ecommerce.productservice.requests.ProductPurchaseRequest;
import com.ecommerce.productservice.requests.ProductRequest;
import com.ecommerce.productservice.responses.ProductPurchaseResponse;
import com.ecommerce.productservice.responses.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds= requests.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size())
            throw new ProductPurchaseException("One or more products does not exists");

        var storeRequest = requests.stream()
            .sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++){
            var product= storedProducts.get(i);
            var productRequest = storeRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity())
                throw new ProductPurchaseException("Insufficient product quantity");

            product.setAvailableQuantity(product.getAvailableQuantity() - productRequest.quantity());
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("Product not found with ID ::"+productId)
                );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
