package com.lhstore.productcatalogservice.category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteCategoryTree(int rootCategoryId) {
        final Query query = entityManager.createNativeQuery(CategorySql.generateSqlToDeleteCategoryTree());
        query.setParameter("id", rootCategoryId);
        query.executeUpdate();
    }
}
