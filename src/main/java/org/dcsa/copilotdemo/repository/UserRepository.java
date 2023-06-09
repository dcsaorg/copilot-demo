package org.dcsa.copilotdemo.repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.dcsa.copilotdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // implement missing interface jpa methods required by UserService.java
    Optional<User> findById(UUID id);
    User save(User user);
    void deleteById(UUID id);
    List<User> findAll();
}
