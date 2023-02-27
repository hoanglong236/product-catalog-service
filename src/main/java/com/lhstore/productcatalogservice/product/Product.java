package com.lhstore.productcatalogservice.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "_product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "name")
    @NotEmpty(message = "")
    private String name;

    @Column(name = "main_image_path")
    @NotEmpty(message = "")
    private String mainImagePath;

    @Column(name = "price")
    @Min(value = 0, message = "")
    private long price;

    @Column(name = "quantity")
    @Min(value = 0, message = "")
    private int quantity;

    @Column(name = "description_file_path")
    private String descriptionFilePath;

    @Column(name = "status")
    private ProductStatus productStatus;

    @Column(name = "delete_flag")
    private boolean deleteFlag;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
