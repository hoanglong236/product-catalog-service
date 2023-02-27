package com.lhstore.productcatalogservice.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProduct {

    private long id;
    private String name;
    private int categoryId;
    private int brandId;
    private String mainImagePath;
    private long price;
    private int quantity;
    private String descriptionFilePath;
    private ProductStatus productStatus;
}
