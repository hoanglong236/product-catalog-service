package com.lhstore.productcatalogservice.repository;

import com.lhstore.productcatalogservice.repository.sql.CategorySql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteCategoriesRecursive(Integer categoryId) {
        final Query query = this.entityManager.createNativeQuery(CategorySql.generateSqlToDeleteCategoryTree());
        query.setParameter("id", categoryId);
        query.executeUpdate();
    }
}
