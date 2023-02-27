package com.lhstore.productcatalogservice.category;

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

    private Integer parentCategoryId;
    @NotEmpty(message = "Category name is mandatory")
    private String name;
    private String iconPath;
}
