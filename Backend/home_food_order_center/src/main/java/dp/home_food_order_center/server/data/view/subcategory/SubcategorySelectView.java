package dp.home_food_order_center.server.data.view.subcategory;

import java.io.Serializable;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
public class SubcategorySelectView implements Serializable {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
