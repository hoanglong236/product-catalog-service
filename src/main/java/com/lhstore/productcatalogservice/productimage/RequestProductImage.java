package com.lhstore.productcatalogservice.productimage;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestProductImage {

    private long productId;
    @NotEmpty(message = "The product image is mandatory")
    private String imagePath;
}
