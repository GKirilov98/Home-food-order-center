package dp.home_food_order_center.server.controller;

import dp.home_food_order_center.server.data.view.user.UserEditView;
import dp.home_food_order_center.server.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JsonUtils jsonUtils;

    @WithMockUser(value = "spring")
    @Test
    void getUserProfileById_200() throws Exception {
        mockMvc.perform(get("/user/profile/USER!@")
                .contentType("application/json"))
                .andExpect(status().isOk());

    }


    @Test
    void getUserProfileById_401() throws Exception {
        mockMvc.perform(get("user/profile/69")
                .contentType("application/json"))
                .andExpect(status().is4xxClientError());

    }

    @WithMockUser(username = "testUsername2", password = "passowrdTest")
    @Test
    void editUserById_200() throws Exception {
        UserEditView userEditView = new UserEditView();
        userEditView.setImageUrl("newImage");
        userEditView.setImagePublicId("newPublicId");
        userEditView.setFirstName("NewTestFirstName");
        userEditView.setLastName("NewTestLastName");
        userEditView.setEmail( "NewAasd2@tes.com");
        userEditView.setPhoneNumber("0888888888");


        mockMvc.perform(post("/user/edit/testUsername2")
                .contentType("application/json")
                .content(jsonUtils.convertObjectToJson(userEditView))
        )
                .andExpect(status().isOk());
    }
}