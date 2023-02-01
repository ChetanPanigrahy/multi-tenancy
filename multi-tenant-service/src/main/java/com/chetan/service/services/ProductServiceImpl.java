package com.chetan.service.services;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.chetan.service.domain.entity.Product;
import com.chetan.service.model.ProductValue;
import com.chetan.service.multitenancy.util.BlobCloudContainer;
import com.chetan.service.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductValue> getProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductValue::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductValue getProduct(long productId) {
        return productRepository.findById(productId)
                .map(ProductValue::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Product " + productId + " not found"));
    }

    @Override
    @Transactional
    public ProductValue createProduct(ProductValue productValue) {
        Product product = Product.builder()
                .name(productValue.getName())
                .build();
        product = productRepository.save(product);
        return ProductValue.fromEntity(product);
    }

    @Override
    @Transactional
    public ProductValue updateProduct(ProductValue productValue) {
        Product product = productRepository.findById(productValue.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product " + productValue.getProductId() + " not found"));
        product.setName(productValue.getName());
        return ProductValue.fromEntity(product);
    }

    @Override
    @Transactional
    public void deleteProductById(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product " + productId + " not found"));
        productRepository.delete(product);
    }

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        CloudBlockBlob blob;
        try {
            blob = BlobCloudContainer.getBlobCloudContainer().getBlockBlobReference(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            blob.upload(multipartFile.getInputStream(), -1);
        } catch (URISyntaxException | StorageException | IOException e) {
            e.printStackTrace();
        }
    }
}
