package dp.home_food_order_center.service.impl;

import dp.home_food_order_center.data.entity.UserEntity;
import dp.home_food_order_center.data.model.user.UserModel;
import dp.home_food_order_center.data.view.auth.LoginReturnedUserView;
import dp.home_food_order_center.error.GlobalServiceException;
import dp.home_food_order_center.repository.IUserRepository;
import dp.home_food_order_center.service.IUserService;
import dp.home_food_order_center.util.AuthenticationUtil;
import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private IUserRepository repository;
    @Autowired
    private AuthenticationUtil authentication;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder cryptPass;

    @Override
    public List<LoginReturnedUserView> login(String username, String password) throws GlobalServiceException {
        String logId = authentication.getLogId();
        //Проверки на входните данни
        if (username == null || username.trim().length() == 0) {
            logger.error(String.format("%s: Username is null!", logId));
            throw new GlobalServiceException(logId, "Username няма подадена стойност!", "Username is null!");
        }else if (password == null || password.trim().length() == 0) {
            logger.error(String.format("%s: Password is null!", logId));
            throw new GlobalServiceException(logId, "Парола няма подадена стойност!", "Password is null!");
        }


        //Проверка с базата
        try {
            logger.info(String.format("%s: Starting login service!", logId));

            List<UserEntity> entityList = this.repository.findAllByUsername(username);
            List<LoginReturnedUserView> loggedUser = entityList.stream()
                    .filter(e -> cryptPass.matches(password, e.getPassword()))
                    .map(e -> this.modelMapper.map(e, LoginReturnedUserView.class))
                    .collect(Collectors.toList());
            if (loggedUser.size() == 1){
                UserEntity userEntity = entityList.get(0);
                String token = this.authentication.getLogId();
                userEntity.setLoggedId(token);
                this.repository.saveAndFlush(userEntity);
                loggedUser.get(0).setToken(token);
            }

            return loggedUser;
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected login service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished login service!", logId));
        }
    }

    /**
     * insertOne - Insert new record in the table
     *
     * @param model - data to add
     * @throws GlobalServiceException
     */
    @Override
    public void insertOne(UserModel model) throws GlobalServiceException {
        String logId = authentication.getLogId();
        //Проверки на входните данни
        if (model.getUsername() == null || model.getUsername().trim().length() == 0) {
            logger.error(String.format("%s: Username is null!", logId));
            throw new GlobalServiceException(logId, "Username няма подадена стойност!", "Username is null!");
        } else if (model.getfName() == null || model.getfName().trim().length() == 0) {
            logger.error(String.format("%s: FirstName is null!", logId));
            throw new GlobalServiceException(logId, "Първо име няма подадена стойност!", "FirstName is null!");
        } else if (model.getlName() == null || model.getlName().trim().length() == 0) {
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

        //Записване в базата
        try {
            logger.info(String.format("%s: Starting insertOne service!", logId));
            UserEntity entity = this.modelMapper.map(model, UserEntity.class);
            String encode = cryptPass.encode(model.getPassword());
            entity.setPassword(encode);
            entity.setDateRegistration(new Timestamp(System.currentTimeMillis()));
            this.repository.saveAndFlush(entity);
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished insertOne service!", logId));
        }
    }

    @Override
    public void logout(LoginReturnedUserView params) throws GlobalServiceException {
        String logId = this.authentication.getLogId();
        if (params.getUsername() == null || params.getUsername().trim().length() == 0) {
            logger.error(String.format("%s: Username is null!", logId));
            throw new GlobalServiceException(logId, "Username няма подадена стойност!", "Username is null!");
        } else if (params.getId() == null) {
            logger.error(String.format("%s: Id is null!", logId));
            throw new GlobalServiceException(logId, "Id няма подадена стойност!", "Id is null!");
        }


        try {
            logger.info(String.format("%s: Starting logout service!", logId));
            UserEntity entity = this.repository.findByIdAndUsername(params.getId(), params.getUsername());
            entity.setLoggedId(null);
            this.repository.saveAndFlush(entity);
        } catch (Exception e) {
            logger.error(String.format("%s: Unexpected service error!", logId), e);
            throw new GlobalServiceException(logId, "Грешка при работа на сървиса!", "Unexpected service error!");
        } finally {
            logger.info(String.format("%s: Finished logout service!", logId));
        }
    }
}
