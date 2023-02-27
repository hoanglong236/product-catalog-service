package com.lhstore.productcatalogservice.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCategory {

    private int id;
    private Integer parentCategoryId;
    private String name;
    private String iconPath;
}
