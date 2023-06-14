package org.dcsa.copilotdemo.model;
import java.util.Base64;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

// User object has attributes first name, last name, email, and password
// implement getters, setters, toString and hashCode
@Entity
@Table(name = "user", schema =  "public" )
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid password format")
    private String password;

  // implement getters, setters, toString and hashCode

  public UUID getId() {
      return id;
  }

  public void setId(UUID id) {
        this.id = id;
  }

  public String getFirstName() {
      return firstName;
  }
  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }
  public String getLastName() {
      return lastName;
  }
  public void setLastName(String lastName) {
      this.lastName = lastName;
  }
  public String getEmail() {
      return email;
  }
  public void setEmail(String email) {
      this.email = email;
  }
  public String getPassword() {
      //return password; // base64 enccode password
      return Base64.getEncoder().encodeToString(password.getBytes());
  }
  public void setPassword(String password) {
      this.password = password;
  }
  @Override
  public String toString() {
      return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
              + "]";
  }
  @Override
  public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result + ((password == null) ? 0 : password.hashCode());
      return result;
  }
  @Override
  public boolean equals(Object obj) {
      if (this == obj)
          return true;
      if (obj == null)
          return false;
      if (getClass() != obj.getClass())
          return false;
      User other = (User) obj;
      if (email == null) {
          if (other.email != null)
              return false;
      } else if (!email.equals(other.email))
          return false;
      if (firstName == null) {
          if (other.firstName != null)
              return false;
      } else if (!firstName.equals(other.firstName))
          return false;
      if (lastName == null) {
          if (other.lastName != null)
              return false;
      } else if (!lastName.equals(other.lastName))
          return false;
      if (password == null) {
          if (other.password != null)
              return false;
      } else if (!password.equals(other.password))
          return false;
      return true;
  }

}
