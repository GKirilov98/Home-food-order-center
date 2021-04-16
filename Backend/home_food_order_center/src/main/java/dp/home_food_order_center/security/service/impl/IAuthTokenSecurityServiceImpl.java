package dp.home_food_order_center.security.service.impl;

import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.security.service.IAuthTokenSecurityService;
import dp.home_food_order_center.server.data.entity.UserEntity;
import dp.home_food_order_center.server.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
@Component
public class IAuthTokenSecurityServiceImpl implements IAuthTokenSecurityService {
    private final IUserRepository repository;

    public IAuthTokenSecurityServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
