package com.bada.bazaar.enums;

public enum Category {
    SPORTS(SportsSubCategory.class),
    FASHION(FashionSubCategory.class),
    ELECTRONICS(ElectronicsSubCategory.class);

    private Class<? extends SubCategory> subCategoryClass;

    Category(Class<? extends SubCategory> subCategoryClass) {
        this.subCategoryClass = subCategoryClass;
    }

    public Class<? extends SubCategory> getSubCategoryClass() {
        return subCategoryClass;
    }
}
