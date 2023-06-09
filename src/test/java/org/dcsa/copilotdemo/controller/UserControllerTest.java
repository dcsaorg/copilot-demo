package org.dcsa.copilotdemo.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.dcsa.copilotdemo.model.User;
import org.dcsa.copilotdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

// write spring boot test for UserController.java
@WebMvcTest(UserController.class)
public class UserControllerTest {
    // autowire MockMvc
    // write test for each method in UserController.java
    // use Mockito to mock UserService.java
    // use MockMvc to test each method in UserController.java

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@gmail");
        user1.setPassword("john.doe.password");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@gmail");
        user2.setPassword("jane.doe.password");

        List<User> users = Arrays.asList(user1, user2);

        Mockito.when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/v1/api/users")) // which library is this from?
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[1].firstName", is("Jane")));
    }

    @Test
    public void testGetUserById() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail");
        user.setPassword("john.doe.password");

        Mockito.when(userService.findUserById(id)).thenReturn(user);

        mockMvc.perform(get("/v1/api/users/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Test
    public void testCreateUser() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail");
        user.setPassword("john.doe.password");

        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/v1/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail");
        user.setPassword("john.doe.password");

        Mockito.when(userService.updateUser(Mockito.any(UUID.class), Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(put("/v1/api/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andDo(System.out::println)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@gmail");

       //  Mockito.when(userService.deleteUserById(id)).thenReturn(user); // use do nothing to mock void method
       Mockito.doNothing().when(userService).deleteUser(id);

        mockMvc.perform(delete("/v1/api/users/" + id))
            .andExpect(status().isNoContent());
   
    }

}
