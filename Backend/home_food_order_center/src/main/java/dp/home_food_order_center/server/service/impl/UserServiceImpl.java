package dp.home_food_order_center.server.service.impl;

import dp.home_food_order_center.security.jwt.UserDetailsImpl;
import dp.home_food_order_center.security.model.AuthenticateResponseModel;
import dp.home_food_order_center.security.service.IAuthenticateService;
import dp.home_food_order_center.server.data.entity.RoleEntity;
import dp.home_food_order_center.server.data.entity.RoleType;
import dp.home_food_order_center.server.data.entity.UserEntity;
import dp.home_food_order_center.server.data.model.user.UserAuthRegistrationModel;
import dp.home_food_order_center.server.data.model.user.UserListModel;
import dp.home_food_order_center.server.data.model.user.UserModel;
import dp.home_food_order_center.server.data.view.auth.RegisterUserView;
import dp.home_food_order_center.server.data.view.user.UserEditView;
import dp.home_food_order_center.server.error.GlobalServiceException;
import dp.home_food_order_center.server.repository.IRoleRepository;
import dp.home_food_order_center.server.repository.IUserRepository;
import dp.home_food_order_center.server.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String DEFAULT_IMAGE_URL = "https://res.cloudinary.com/drb3wiwvh/image/upload/v1617196067/Default%20User/084f3b94-9222-11eb-a8b3-0242ac130003.png";
    private static final String DEFAULT_IMAGE_PUBLIC_ID = "084f3b94-9222-11eb-a8b3-0242ac130003";

    private final IAuthenticateService authenticateService;
    private final ModelMapper modelMapper;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRespository;

    public UserServiceImpl(IAuthenticateService authenticateService, ModelMapper modelMapper, IUserRepository userRepository, IRoleRepository roleRespository) {
        this.authenticateService = authenticateService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRespository = roleRespository;
    }


    @Override
    public List<AuthenticateResponseModel> login(String username, String password) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверки на входните данни
        if (username == null || username.trim().length() == 0) {
            logger.error(String.format("%s: Username is null!", logId));
            throw new GlobalServiceException(logId, "Username няма подадена стойност!", "Username is null!");
        } else if (password == null || password.trim().length() == 0) {
            logger.error(String.format("%s: Password is null!", logId));
            throw new GlobalServiceException(logId, "Парола няма подадена стойност!", "Password is null!");
        }


        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting login service!", logId));
            List<AuthenticateResponseModel> res = new ArrayList<>();
            res.add(authenticateService.loginUser(username, password));
            return res;
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected login service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished login service!", logId));
        }
    }

    /**
     * Adding bearer token in black list
     *
     * @param token bearer
     * @return success message
     */
    @Override
    public String logout(String token) {
        return this.authenticateService.logoutUser(token);
    }

    /**
     * insertOne - Insert new record in the table
     *
     * @param model - data to add
     * @throws GlobalServiceException
     */
    @Override
    public String insertOne(RegisterUserView model) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверки на входните данни
        if (model.getUsername() == null || model.getUsername().trim().length() == 0) {
            logger.error(String.format("%s: Username is null!", logId));
            throw new GlobalServiceException(logId, "Username няма подадена стойност!", "Username is null!");
        } else if (model.getFirstName() == null || model.getFirstName().trim().length() == 0) {
            logger.error(String.format("%s: FirstName is null!", logId));
            throw new GlobalServiceException(logId, "Първо име няма подадена стойност!", "FirstName is null!");
        } else if (model.getLastName() == null || model.getLastName().trim().length() == 0) {
            logger.error(String.format("%s: LastName is null!", logId));
            throw new GlobalServiceException(logId, "Поле фамилия няма подадена стойност!", "LastName is null!");
        } else if (model.getEmail() == null || model.getEmail().trim().length() == 0) {
            logger.error(String.format("%s: Email is null!", logId));
            throw new GlobalServiceException(logId, "Имейл няма подадена стойност!", "Email is null!");
        } else if (model.getPhoneNumber() == null || model.getPhoneNumber().trim().length() == 0) {
            logger.error(String.format("%s: Phone number is null!", logId));
            throw new GlobalServiceException(logId, "Телефонен номер няма подадена стойност!", "Phone number is null!");
        } else if (model.getPassword() == null || model.getPassword().trim().length() == 0) {
            logger.error(String.format("%s: Password is null!", logId));
            throw new GlobalServiceException(logId, "Парола няма подадена стойност!", "Password is null!");
        } else if (model.getConfirmPassword() == null || model.getConfirmPassword().trim().length() == 0) {
            logger.error(String.format("%s: Confirm password is null!", logId));
            throw new GlobalServiceException(logId, "Потвърдената парола няма подадена стойност!", "Confirm password is null!");
        } else if (!model.getPassword().equals(model.getConfirmPassword())) {
            logger.error(String.format("%s: Password and Confirm password doesn't match!", logId));
            throw new GlobalServiceException(logId, "Паролите не съвпадат!", "Password and Confirm password doesn't match!");
        }

        //Слагане на defaults стойности
        if (model.getImageUrl() == null || model.getImagePublicId() == null) {
            model.setImageUrl(DEFAULT_IMAGE_URL);
            model.setImagePublicId(DEFAULT_IMAGE_PUBLIC_ID);
        }

        //Записване в базата
        try {
            logger.info(String.format("%s: Starting insertOne service!", logId));
            UserAuthRegistrationModel authUserModel = this.modelMapper.map(model, UserAuthRegistrationModel.class);
            authUserModel.setLogId(logId);
            authUserModel.setImagePublicIdentity(model.getImagePublicId());
            return this.authenticateService.registerUser(authUserModel);
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished insertOne service!", logId));
        }
    }

    @Override
    public List<UserModel> getUserByUsername(String username) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting getUserById service!", logId));
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this username! %s", logId, username));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with this username");
            }

            List<UserModel> models = new ArrayList<>();
            UserModel model = this.modelMapper.map(userEntity, UserModel.class);
            //Ако не е текущия логнат потребител или админ или бизнес потребител - се трият касовите бележки
            if (!loggedInUser.getName().equals(username)) {
                Set<String> loggedInRoles = loggedInUser.getAuthorities().stream()
                        .map(Object::toString)
                        .collect(Collectors.toSet());
                boolean contains = loggedInRoles.contains(RoleType.ROLE_ADMIN.toString()) ||
                        loggedInRoles.contains(RoleType.ROLE_BUSINESS.toString());
                if (!contains) {
                    model.getReceipts().clear();
                }
            }
            models.add(model);
            return models;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getUserById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getUserById service!", logId));
        }
    }

    @Override
    public List<UserModel> editUser(String username, UserEditView params) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting editUser service!", logId));;
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            if (!loggedInUser.getName().equals(username)) {
                boolean contains = loggedInUser.getAuthorities().stream()
                        .map(Object::toString)
                        .collect(Collectors.toSet())
                        .contains(RoleType.ROLE_ADMIN.toString());
                if (!contains) {
                    logger.error(String.format("%s: You do not have permission for this operation!", logId));
                    throw new GlobalServiceException(logId, "Нямате необходимите права за промяна", "You do not have permission for this operation!");
                }
            }

            UserEntity userEntity = this.userRepository.findByUsername(username).orElse(null);

            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this username! %s", logId, username));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това username", "Could not user with that username");
            }

            if (!userEntity.getEmail().equals(params.getEmail()) &&
                    this.userRepository.existsByEmail(params.getEmail())
            ) {
                logger.error(String.format("%s: This email is in use! %s", logId, params.getEmail()));
                throw new GlobalServiceException(logId, "Tози email e зает!", "This email is in use!");
            }

            userEntity.setImageUrl(params.getImageUrl());
            userEntity.setImagePublicId(params.getImagePublicId());
            userEntity.setEmail(params.getEmail());
            userEntity.setPhoneNumber(params.getPhoneNumber());
            userEntity.setFirstName(params.getFirstName());
            userEntity.setLastName(params.getLastName());
            List<UserModel> list = new ArrayList<>();
            UserEntity changedUser = this.userRepository.saveAndFlush(userEntity);
            list.add(this.modelMapper.map(changedUser, UserModel.class));
            return list;
        } catch (
                GlobalServiceException e) {
            throw e;
        } catch (
                Exception e) {
            logger.error(String.format("%s: Unexpected editUser service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished editUser service!", logId));
        }

    }

    //ADMIN>>>


    @Override
    public List<UserListModel> getAllUsers(String username, String email, String phoneNumber, String orderBY) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting getAllUsers service!", logId));
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetailsImpl currLoggedUser = (UserDetailsImpl) principal;

            if (username == null) {
                username = "";
            }

            if (email == null) {
                email = "";
            }

            if (phoneNumber == null) {
                phoneNumber = "";
            }

            if (orderBY == null) {
                orderBY = "";
            }

            String[] orderToken = orderBY.split(" ");
            Comparator<UserListModel> comparator = Comparator.comparing(UserListModel::getUsername);
            if (orderToken.length == 2) {
                if (orderToken[0].equalsIgnoreCase("phoneNumber")) {
                    comparator = Comparator.comparing(UserListModel::getPhoneNumber);
                } else if (orderToken[0].equalsIgnoreCase("email")) {
                    comparator = Comparator.comparing(UserListModel::getEmail);
                }
            }


            String finalUsername = username;
            String finalEmail = email;
            String finalPhoneNumber = phoneNumber;
            List<UserListModel> list = this.userRepository.findAll()
                    .stream()
                    .filter(e ->
                            !e.getUsername().equals(currLoggedUser.getUsername()) &&
                            e.getUsername().contains(finalUsername) &&
                                    e.getEmail().contains(finalEmail) &&
                                    e.getPhoneNumber().contains(finalPhoneNumber))
                    .map(e -> {
                        UserListModel model = this.modelMapper.map(e, UserListModel.class);
                        List<String> roles = e.getRoles().stream()
                                .map(RoleEntity::getCode)
                                .collect(Collectors.toList());
                        model.setRoles(roles);
                        return model;
                    })
                    .sorted(comparator)
                    .collect(Collectors.toList());
            if (orderToken.length == 2) {
                if (orderToken[1].equalsIgnoreCase("desc")) {
                    Collections.reverse(list);
                }
            }

            return list;
//        } catch (GlobalServiceException e) {
//            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected getAllUsers service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished getAllUsers service!", logId));
        }

    }

    @Override
    public String deleteUserById(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting deleteUserById service!", logId));

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetailsImpl currLoggedUser = (UserDetailsImpl) principal;

            Set<String> authorities = currLoggedUser.getAuthorities().stream().map(Object::toString)
                    .collect(Collectors.toSet());
            if (!authorities.contains(RoleType.ROLE_ADMIN.toString())) {
                if (!currLoggedUser.getId().equals(id)) {
                    logger.error(String.format("%s: You do not have permission for this operation!", logId));
                    throw new GlobalServiceException(logId, "Нямате необходимите права за промяна", "You do not have permission for this operation!");
                }
            }

            UserEntity userEntity = this.userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this id! %d", logId, id));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with that id");
            }

            this.userRepository.deleteUser(userEntity);
            return "Successfully deleted user";
        } catch (
                GlobalServiceException e) {
            throw e;
        } catch (
                Exception e) {
            logger.error(String.format("%s: Unexpected deleteUserById service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished deleteUserById service!", logId));
        }

    }

    @Override
    public String makeBusinessByUserId(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting makeBusinessByUserId service!", logId));

            UserEntity userEntity = this.userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this id! %d", logId, id));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with that id");
            }

            RoleEntity roleEntity = this.roleRespository.findByName(RoleType.ROLE_BUSINESS).orElseThrow( () ->  new NoSuchElementException("Missing Role!"));
            userEntity.getRoles().add(roleEntity);
            this.userRepository.saveAndFlush(userEntity);

            return "Successfully added role \"BUSINESS\"!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected makeBusinessByUserId service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected makeBusinessByUserId service error!");
        } finally {
            logger.info(String.format("%s: Finished makeBusinessByUserId service!", logId));
        }
    }

    @Override
    public String removeBusinessByUserId(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting removeBusinessByUserId service!", logId));

            UserEntity userEntity = this.userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this id! %d", logId, id));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with that id");
            }

            RoleEntity roleAdmin = this.roleRespository.findByName(RoleType.ROLE_ADMIN).orElse(null);
            if (userEntity.getRoles().contains(roleAdmin)) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserDetailsImpl currLoggedUser = (UserDetailsImpl) principal;

                Set<String> authorities = currLoggedUser.getAuthorities().stream().map(Object::toString)
                        .collect(Collectors.toSet());
                if (!authorities.contains(RoleType.ROLE_ADMIN.toString())) {
                    if (!currLoggedUser.getId().equals(id)) {
                        logger.error(String.format("%s: You do not have permission for this operation!", logId));
                        throw new GlobalServiceException(logId, "Нямате необходимите права за промяна", "You do not have permission for this operation!");
                    }
                }
            }

            RoleEntity roleEntity = this.roleRespository.findByName(RoleType.ROLE_BUSINESS).orElseThrow( () ->  new NoSuchElementException("Missing Role!"));
            userEntity.getRoles().remove(roleEntity);
            this.userRepository.saveAndFlush(userEntity);

            return "Successfully removed role \"ADMIN\"!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected removeBusinessByUserId service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected removeBusinessByUserId service error!");
        } finally {
            logger.info(String.format("%s: Finished removeBusinessByUserId service!", logId));
        }
    }

    @Override
    public String makeAdminByUserId(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting makeAdminByUserId service!", logId));

            UserEntity userEntity = this.userRepository.findById(id).orElseThrow( () ->  new NoSuchElementException("Missing Role!"));
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this id! %d", logId, id));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with that id");
            }

            RoleEntity roleEntity = this.roleRespository.findByName(RoleType.ROLE_ADMIN).orElseThrow( () ->  new NoSuchElementException("Missing Role!"));
            userEntity.getRoles().add(roleEntity);
            this.userRepository.saveAndFlush(userEntity);

            return "Successfully added admin role!";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected makeAdminByUserId service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected makeAdminByUserId service error!");
        } finally {
            logger.info(String.format("%s: Finished makeAdminByUserId service!", logId));
        }
    }

    @Override
    public String removeAdminByUserId(Long id) throws GlobalServiceException {
        String logId = UUID.randomUUID().toString();
        try {
            logger.info(String.format("%s: Starting removeAdminByUserId service!", logId));

            UserEntity userEntity = this.userRepository.findById(id).orElse(null);
            if (userEntity == null) {
                logger.error(String.format("%s: Couldn't found user with this id! %d", logId, id));
                throw new GlobalServiceException(logId, "Не мога да намеря user  с това id", "Could not user with that id");
            }

            RoleEntity roleEntity = this.roleRespository.findByName(RoleType.ROLE_ADMIN).orElseThrow( () ->  new NoSuchElementException("Missing Role!"));
            userEntity.getRoles().remove(roleEntity);
            this.userRepository.saveAndFlush(userEntity);

            return "Successfully removed role \"ADMIN\" !";
        } catch (GlobalServiceException e) {
            throw e;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected removeAdminByUserId service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected removeAdminByUserId service error!");
        } finally {
            logger.info(String.format("%s: Finished removeAdminByUserId service!", logId));
        }
    }

}
