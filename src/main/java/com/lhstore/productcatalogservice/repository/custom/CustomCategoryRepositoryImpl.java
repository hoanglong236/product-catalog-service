package com.lhstore.productcatalogservice.repository.custom;

import com.lhstore.productcatalogservice.repository.sql.CategorySql;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteCategoriesRecursive(Integer categoryId) {
        final Query query = this.entityManager.createNativeQuery(CategorySql.generateSqlToDeleteCategoryTree());
        query.setParameter("id", categoryId);
        query.executeUpdate();
    }
}
