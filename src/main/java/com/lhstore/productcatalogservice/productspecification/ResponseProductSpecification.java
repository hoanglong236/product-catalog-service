package com.lhstore.productcatalogservice.productspecification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProductSpecification {

    private long id;
    private long productId;
    private String specificationName;
    private String specificationValue;
}
