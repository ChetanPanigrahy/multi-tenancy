package com.chetan.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.chetan.service.model.ProductValue;
import com.chetan.service.services.ProductService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ProductApiController {

    private final ProductService productService;

    @GetMapping(value = "/products", produces = {ContentType.PRODUCTS_1_0})
    public ResponseEntity<List<ProductValue>> getProducts() {
        List<ProductValue> productValues = productService.getProducts();
        return new ResponseEntity<>(productValues, HttpStatus.OK);
    }

    @GetMapping(value = "/products/{productId}", produces = {ContentType.PRODUCT_1_0})
    public ResponseEntity<ProductValue> getProduct(@PathVariable("productId") long productId) {
        try {
            ProductValue branch = productService.getProduct(productId);
            return new ResponseEntity<>(branch, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping(value = "/products",
            consumes = {ContentType.PRODUCT_1_0},
            produces = {ContentType.PRODUCT_1_0})
    public ResponseEntity<ProductValue> createProduct(@Valid @RequestBody ProductValue productValue) {
        ProductValue product = productService.createProduct(productValue);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.LOCATION, "/products/" + product.getProductId());
        return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{productId}",
            consumes = {ContentType.PRODUCT_1_0},
            produces = {ContentType.PRODUCT_1_0})
    ResponseEntity<ProductValue> updateProduct(@PathVariable long productId, @Valid @RequestBody ProductValue productValue) {
        productValue.setProductId(productId);
        try {
            ProductValue product = productService.updateProduct(productValue);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PostMapping(value = "/upload",
            consumes = "multipart/form-data"
    )
    ResponseEntity uploadFile(@RequestParam MultipartFile multipartFile) {
        productService.uploadFile(multipartFile);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        try {
            productService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping(value = "/async/products", produces = {ContentType.PRODUCTS_1_0})
    public CompletableFuture<ResponseEntity<List<ProductValue>>> asyncGetProducts() {
        List<ProductValue> productValues = productService.getProducts();
        return CompletableFuture.completedFuture(new ResponseEntity<>(productValues, HttpStatus.OK));
    }

    @GetMapping(value = "/async/products/{productId}", produces = {ContentType.PRODUCT_1_0})
    public CompletableFuture<ResponseEntity<ProductValue>> asyncGetProduct(@PathVariable("productId") long productId) {
        return CompletableFuture.completedFuture(getProduct(productId));
    }

}
