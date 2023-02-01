package com.chetan.service.services;


import org.springframework.web.multipart.MultipartFile;
import com.chetan.service.model.ProductValue;

import java.util.List;

public interface ProductService {

    List<ProductValue> getProducts();

    ProductValue getProduct(long productId);

    ProductValue createProduct(ProductValue productValue);

    ProductValue updateProduct(ProductValue productValue);

    void deleteProductById(long productId);

    void uploadFile(MultipartFile multipartFile);
}
