package dp.home_food_order_center.server.data.entity;

import dp.home_food_order_center.server.data.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "roles")
public class RoleEntity extends BaseEntity {
    private RoleType name;
    private String description;
    private String code;
    private Timestamp dateRegistration;
    private Set<UserEntity> users;

    public RoleEntity() {
    }

    @Enumerated(EnumType.STRING)
    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
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
