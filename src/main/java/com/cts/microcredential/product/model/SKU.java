package com.cts.microcredential.product.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class SKU implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String sku;

    private String name;

    private String categoryId;

    private String subCategoryId;

    private String brand;

    private Long quantity;

    private String isOutOfStock;

    private String create_user;

    private String create_tsamp;

    private String modify_user;

    private String modify_tstamp;
}
