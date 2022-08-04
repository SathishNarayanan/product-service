package com.cts.microcredential.product.repository;

import com.cts.microcredential.product.model.SKU;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class ProductCacheRepository {

    private RedisTemplate<String, SKU> redisTemplate;

    private HashOperations hashOperations;

    public ProductCacheRepository(RedisTemplate<String, SKU> tmplate) {
        this.redisTemplate = tmplate;
        hashOperations = tmplate.opsForHash();
    }

    public void save(SKU sku) {
        hashOperations.put("inventorycache", sku.getSku(), sku);
    }

    public void delete(String sku) {
        hashOperations.delete(sku);
    }

    public Map<String, SKU> findAll() {
        return hashOperations.entries("inventorycache");
    }

    public SKU findById(String skuId) {
        return (SKU) hashOperations.get("inventorycache", skuId);
    }

}
