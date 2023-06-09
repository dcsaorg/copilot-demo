// you are an senior java spring boot developer
// write the a rest controller for endpoint /v1/api/ for the User object
// the controller should have the following methods:
// 1. GET /v1/api/users
// 2. GET /v1/api/users/{id}
// 3. POST /v1/api/users
// 4. PUT /v1/api/users/{id}
// 5. DELETE /v1/api/users/{id}
// the business logic is abtracted away in the service layer

package org.dcsa.copilotdemo.controller;

import java.util.List;
import java.util.UUID;

import org.dcsa.copilotdemo.model.User;
import org.dcsa.copilotdemo.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }    

    @GetMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.OK)
    public User getUserById(@PathVariable UUID id){
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.OK)
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}

