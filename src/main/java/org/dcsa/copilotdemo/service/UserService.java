package org.dcsa.copilotdemo.service;
import java.util.List;
import java.util.UUID;

import org.dcsa.copilotdemo.model.User;
import org.dcsa.copilotdemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.dcsa.copilotdemo.exception.ResourceNotFoundException;


// implement missing java methods required by UserController.java
@Service
public class UserService {

private final UserRepository userRepository;

public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
}

public List<User> findAllUsers(){
    return userRepository.findAll();
}

public User findUserById(UUID id){
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
}

public User saveUser(User user){
    return userRepository.save(user);
}

public User updateUser(UUID id, User user){
    User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    existingUser.setPassword(user.getPassword());
    return userRepository.save(existingUser);
}

public void deleteUser(UUID id){
    userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    userRepository.deleteById(id);
}


}
