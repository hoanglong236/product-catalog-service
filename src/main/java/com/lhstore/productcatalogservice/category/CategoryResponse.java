package com.lhstore.productcatalogservice.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private int id;
    private Integer parentId;
    private String name;
    private String iconPath;
}
