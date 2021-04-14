package dp.home_food_order_center.server.repository;

import dp.home_food_order_center.server.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>, IUserRepositoryCustom {
    List<UserEntity> findAllByUsernameAndPassword(String username, String password);

    List<UserEntity> findAllByUsername(String username);

    UserEntity findByIdAndUsername(Long id, String username);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
