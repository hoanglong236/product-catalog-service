package com.lhstore.productcatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBrand {

    private Integer id;
    private String name;
    private String iconPath;
}
