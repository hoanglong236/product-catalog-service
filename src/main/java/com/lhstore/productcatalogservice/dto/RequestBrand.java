package com.lhstore.productcatalogservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBrand {

    @NotEmpty(message = "Brand name is mandatory")
    private String name;
    private String iconPath;
}
