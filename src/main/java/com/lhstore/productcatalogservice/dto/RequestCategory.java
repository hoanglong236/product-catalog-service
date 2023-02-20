package com.lhstore.productcatalogservice.dto;

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
public class RequestCategory {

    @Min(value = 0, message = "Category id must be positive")
    private Integer parentCategoryId;
    @NotEmpty(message = "Category name is mandatory")
    private String name;
    private String iconPath;
}
