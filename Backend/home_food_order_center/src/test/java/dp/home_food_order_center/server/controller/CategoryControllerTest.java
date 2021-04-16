package dp.home_food_order_center.server.controller;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project: home_food_order_center
 * Created by: G.Kirilov
 * On: 4/15/2021
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {WebSecurityConfig.class, ApplicationBeanConfiguration.class})
//@WebMvcTest(controllers = CategoryController.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllForSelect() throws Exception {
        mockMvc.perform(get("/category/getAllForSelect")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}