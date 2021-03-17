package dp.home_food_order_center.data.entity;

import dp.home_food_order_center.data.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "roles")
public class RoleEntity extends BaseEntity {
    private String name;
    private String description;
    private String code;
    private Timestamp dateRegistration;
    private Set<UserEntity> users;

    public RoleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Timestamp getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Timestamp dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
}
