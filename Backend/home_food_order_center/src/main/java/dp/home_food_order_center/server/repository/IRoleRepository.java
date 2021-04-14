package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.RoleEntity;
import dp.home_food_order_center.server.data.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleType roleUser);
}
