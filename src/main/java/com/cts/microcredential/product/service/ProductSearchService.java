package com.cts.microcredential.product.service;

import com.cts.microcredential.product.model.SKU;
import com.cts.microcredential.product.repository.ProductCacheRepository;
import com.cts.microcredential.product.repository.ProductSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductSearchService {

    @Autowired
    private ProductSearchRepository productSearchRepo;

    @Autowired
    private ProductCacheRepository cacheRepository;

    public List<SKU> getInHandProducts() {
        Map<String, SKU> allProducts = cacheRepository.findAll();
        List<SKU> products = new ArrayList<>();
        if (allProducts == null) {
            log.info("Fetching Products from DB");
            Iterable<SKU> skus = productSearchRepo.findAll();
            allProducts = new HashMap<String, SKU>();

            Iterator<SKU> skuIter = skus.iterator();
            while(skuIter.hasNext()) {
                SKU sku = skuIter.next();
                allProducts.put(sku.getSku(), sku);
                cacheRepository.save(sku);
            }
        }
        log.info("Products available in Cache {}", allProducts);
        products = allProducts.values().stream().filter(prod -> prod.getIsOutOfStock().equalsIgnoreCase("no")).collect(Collectors.toList());
        log.info("InHandProducts from Cache: {}", products);

        return products;
    }

    public SKU getProduct(String skuId) {
        SKU product =  cacheRepository.findById(skuId);

        if (product == null) {
            log.info("Fetching the productid {} from DB ", skuId);
            Optional<SKU> skuOptional = productSearchRepo.findById(skuId);
            if (!skuOptional.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, " Product ID: " +  skuId + " not found ");
            }
            product = skuOptional.get();
            cacheRepository.save(product);
        }

        return product;
    }

}
