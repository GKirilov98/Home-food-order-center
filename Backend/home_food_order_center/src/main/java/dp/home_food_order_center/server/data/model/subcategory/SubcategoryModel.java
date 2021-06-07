package dp.home_food_order_center.server.data.model.subcategory;

import dp.home_food_order_center.server.data.model.base.BaseModel;
import dp.home_food_order_center.server.data.model.product.ProductModel;

import java.util.List;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 5/11/2021
 */
public class SubcategoryModel extends BaseModel {
    private String name;
    private List<ProductModel> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
