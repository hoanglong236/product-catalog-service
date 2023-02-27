package com.lhstore.productcatalogservice.category;

public class CategorySql {

    private CategorySql() {}

    public static String generateSqlToCreateCategoryTreeCTE() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("WITH RECURSIVE category_tree AS (\n");
        stringBuilder.append("  SELECT * FROM _category WHERE _category.id = :id\n");
        stringBuilder.append("  UNION ALL\n");
        stringBuilder.append("  SELECT _category.* FROM category_tree\n");
        stringBuilder.append("  INNER JOIN _category ON _category.parent_id = category_tree.id\n");
        stringBuilder.append(")\n");

        return stringBuilder.toString();
    }

    public static String generateSqlToDeleteCategoryTree() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(generateSqlToCreateCategoryTreeCTE());
        stringBuilder.append("UPDATE _category SET deleted_flag = TRUE WHERE id IN (\n");
        stringBuilder.append("  SELECT category_tree.id FROM category_tree\n");
        stringBuilder.append("  WHERE category_tree.deleted_flag = FALSE\n");
        stringBuilder.append(")\n");

        return stringBuilder.toString();
    }
}
