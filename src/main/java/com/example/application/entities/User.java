package com.example.application.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.scheduling.support.SimpleTriggerContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_vaadin")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name", length = 25, nullable = false)
  private String firstName;


  @Column(name = "last_name", length = 25, nullable = false)
  private String lastName;
  @NonNull
  private String gender;

  @NonNull
  private int age;

  @Email(message = "Email-Id can not be blank")
  @NonNull
  private String email;

  @NonNull
  private long contactNumber;

  @NonNull
  private String bloodGroup;

  @NonNull
  private String address;

}
