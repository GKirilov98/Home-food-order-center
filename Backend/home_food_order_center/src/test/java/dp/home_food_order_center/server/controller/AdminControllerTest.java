package dp.home_food_order_center.server.controller;

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
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "spring", username = "admin", password = "admin", roles = "ADMIN")
    @Test
    void makeAdminByUserId() throws Exception {
        mockMvc.perform(post("/admin/makeAdmin/101")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring", username = "admin", password = "admin", roles = "ADMIN")
    @Test
    void removeAdminByUserId() throws Exception {
        mockMvc.perform(post("/admin/removeAdmin/101")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring", username = "admin", password = "admin", roles = "ADMIN")
    @Test
    void getAllReceiptWithFilter() throws Exception {
        mockMvc.perform(get("/admin/receipt/getAll")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void changeReceiptToPaid() {
    }

    @WithMockUser(value = "spring", username = "admin", password = "admin", roles = "ADMIN")
    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/admin/user/getAll")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editProductById() {
    }

    @Test
    void insertOne() {
    }

    @Test
    void deleteUserById() {
    }
}