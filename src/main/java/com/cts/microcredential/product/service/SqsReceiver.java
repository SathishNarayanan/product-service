package com.cts.microcredential.product.service;

import com.cts.microcredential.product.model.SKU;
import com.cts.microcredential.product.repository.ProductCacheRepository;
import com.cts.microcredential.product.repository.ProductSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SqsReceiver {

    @Autowired
    private ProductSearchRepository repo;

    @Autowired
    private ProductCacheRepository cacheRepository;

    @SqsListener("product-queue-details")
    public void listen(SKU sku) {
        if (sku != null) {
            log.info("Recieved the Sku ...{}", sku);
            cacheRepository.save(sku);
        } else {
            log.info("Recieved the SKU as null from the queue");
        }
    }
}
