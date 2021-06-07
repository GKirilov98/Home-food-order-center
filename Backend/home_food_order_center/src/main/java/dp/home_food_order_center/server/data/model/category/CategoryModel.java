package dp.home_food_order_center.server.data.model.category;

import dp.home_food_order_center.server.data.model.base.BaseModel;
import dp.home_food_order_center.server.data.model.subcategory.SubcategoryModel;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 5/11/2021
 */
public class CategoryModel extends BaseModel {
    private String name;
    private List<SubcategoryModel> subcategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubcategoryModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryModel> subcategories) {
        this.subcategories = subcategories;
    }
}
