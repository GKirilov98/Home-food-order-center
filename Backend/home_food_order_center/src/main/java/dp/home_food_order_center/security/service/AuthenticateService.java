package dp.home_food_order_center.security.service;

import dp.home_food_order_center.server.data.entity.RoleEntity;
import dp.home_food_order_center.server.data.entity.RoleType;
import dp.home_food_order_center.server.data.entity.UserEntity;
import dp.home_food_order_center.server.data.model.user.UserAuthRegistrationModel;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IRoleRepository;
import dp.home_food_order_center.server.repository.IUserRepository;
import dp.home_food_order_center.security.jwt.JwtUtils;
import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.security.model.AuthenticateResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 3/28/2021
 */
@Service
public class AuthenticateService {
    private final Logger logger = LogManager.getLogger(AuthenticateService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ModelMapper modelMapper;

    public AuthenticateResponseModel loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new AuthenticateResponseModel(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public String registerUser(UserAuthRegistrationModel model) throws GlobalServiceException {

        if (userRepository.existsByUsername(model.getUsername())) {
            logger.error(String.format("%s: Username is already taken!", model.getLogId()));
            throw new GlobalServiceException(model.getLogId(), "Този username е зает!", "Username is already taken!");
        }

        if (userRepository.existsByEmail(model.getEmail())) {
            logger.error(String.format("%s: Email is already in use!", model.getLogId()));
            throw new GlobalServiceException(model.getLogId(), "Този email вече е регистриран!", "Email is already in use!");
        }

        int roleCount = this.roleRepository.findAll().size();
        if (roleCount == 0){
            for (RoleType value : RoleType.values()) {
                RoleEntity role = new RoleEntity();
                role.setDateRegistration(new Timestamp(System.currentTimeMillis()));
                role.setCode(value.name());
                role.setDescription(value.name());
                role.setName(value);

                this.roleRepository.saveAndFlush(role);
            }
        }


        // Create new user's account
        UserEntity entityUser = this.modelMapper.map(model, UserEntity.class);

        Set<String> strRoles = model.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equalsIgnoreCase(RoleType.ROLE_ADMIN.name())) {
                    RoleEntity adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    RoleEntity userRole = roleRepository.findByName(RoleType.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        entityUser.setRoles(roles);
        entityUser.setPassword(encoder.encode(model.getPassword()));
        entityUser.setImagePublicId(model.getImagePublicIdentity());
        entityUser.setDateRegistration(new Timestamp(System.currentTimeMillis()));
        userRepository.saveAndFlush(entityUser);

        return "User registered successfully!";
    }

    public String logoutUser(String token) {
        jwtUtils.addLogOutToken(token);
        return "Logout successfully!";
    }

}
