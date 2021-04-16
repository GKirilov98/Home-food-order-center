package dp.home_food_order_center.server.data.view.category;

import dp.home_food_order_center.server.data.view.subcategory.SubcategorySelectView;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/1/2021
 */
public class CategorySelectView {
    private Long id;
    private String name;
    private SubcategorySelectView[] subcategories;

    public CategorySelectView() {
    }

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

    public SubcategorySelectView[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(SubcategorySelectView[] subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorySelectView that = (CategorySelectView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Arrays.equals(subcategories, that.subcategories);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name);
        result = 31 * result + Arrays.hashCode(subcategories);
        return result;
    }

    @Override
    public String toString() {
        return "CategorySelectView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subcategories=" + Arrays.toString(subcategories) +
                '}';
    }
}
