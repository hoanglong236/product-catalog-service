package com.lhstore.productcatalogservice.brand;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {

    @NotEmpty(message = "Brand name is mandatory")
    private String name;
    @NotEmpty(message = "Logo path is mandatory")
    private String logoPath;
}
