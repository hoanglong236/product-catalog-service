package com.lhstore.productcatalogservice.productimage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProductImage {

    private long id;
    private long productId;
    private String imagePath;
}
