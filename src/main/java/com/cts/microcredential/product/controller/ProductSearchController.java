package com.cts.microcredential.product.controller;

import com.cts.microcredential.product.model.SKU;
import com.cts.microcredential.product.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/productsearch")
public class ProductSearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/products/inhand")
    public ResponseEntity<List<SKU>> getInHandProducts() {
        return ResponseEntity.ok().body(productSearchService.getInHandProducts());
    }

    @GetMapping("/product/{skuId}")
    public ResponseEntity<SKU> getProduct(@PathVariable(name="skuId") String skuId) {
        return ResponseEntity.ok().body(productSearchService.getProduct(skuId));
    }
}
