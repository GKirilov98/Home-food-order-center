package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.data.view.auth.LoginUserView;
import dp.home_food_order_center.server.data.view.auth.RegisterUserView;
import dp.home_food_order_center.server.repository.IUserRepository;
import dp.home_food_order_center.server.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/16/2021
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonUtils jsonUtils;
    @Mock
    private IUserRepository userRepository;

    @Test
    void registerUser_Success() throws Exception {
        RegisterUserView registerUserView = new RegisterUserView();
//        registerUserView.setImagePublicId("DemoPublicId");
//        registerUserView.setImageUrl("DemoImageUrl");
        registerUserView.setUsername("asdasd2");
        registerUserView.setFirstName("TestFirstName");
        registerUserView.setLastName("TestLastName");
        registerUserView.setEmail( "aasd2@tes.com");
        registerUserView.setPhoneNumber("0888888888");
        registerUserView.setPassword("passowrdTest");
        registerUserView.setConfirmPassword("passowrdTest");

        String reqJson = jsonUtils.convertObjectToJson(registerUserView);

        Mockito.when(userRepository.saveAndFlush(Mockito.any())).thenReturn(null);
        mockMvc.perform(
                post("/auth/register")
                .contentType("application/json")
                .content(reqJson)
        )
                .andExpect(status().isOk());
    }

    @Test
    void login_ProviderNotFound() throws Exception {
        LoginUserView loginUserView = new LoginUserView();

        loginUserView.setUsername("testUsername");
        loginUserView.setPassword("passowrdTest");

        String reqJson = jsonUtils.convertObjectToJson(loginUserView);

        Mockito.when(userRepository.saveAndFlush(Mockito.any())).thenReturn(null);
        mockMvc.perform(
                post("/auth/login")
                        .contentType("application/json")
                        .content(reqJson)
        ).andExpect(status().is5xxServerError());
    }
}