package dp.home_food_order_center.server.data.entity;

import dp.home_food_order_center.server.data.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
@Entity(name = "categories")
public class CategoryEntity extends BaseEntity {
    private String name;
    private Set<SubcategoryEntity> subcategories;

    public CategoryEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    public Set<SubcategoryEntity> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<SubcategoryEntity> subCategories) {
        this.subcategories = subCategories;
    }
}
