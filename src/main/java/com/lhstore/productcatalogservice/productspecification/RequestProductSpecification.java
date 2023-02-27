package com.lhstore.productcatalogservice.productspecification;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestProductSpecification {

    private long productId;
    @NotEmpty(message = "The product specification name is mandatory")
    private String specificationName;
    @NotEmpty(message = "The product specification value is mandatory")
    private String specificationValue;
}
