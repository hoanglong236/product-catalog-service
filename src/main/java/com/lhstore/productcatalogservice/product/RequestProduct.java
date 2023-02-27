package com.lhstore.productcatalogservice.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestProduct {

    @NotEmpty(message = "The product name is mandatory")
    private String name;
    private int brandId;
    private int categoryId;
    @NotEmpty
    private String mainImagePath;
    @Min(value = 0, message = "The product price must be a non-negative number")
    private long price;
    @Min(value = 0, message = "The product quantity must be a non-negative number")
    private int quantity;
    private String descriptionFilePath;
    private ProductStatus productStatus;
}
