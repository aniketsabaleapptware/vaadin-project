package com.example.application.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
  private LocalDate dob;

  @Email(message = "Email-Id can not be blank")
  @NonNull
  private String email;

  @NonNull
  private long contactNumber;

  @NonNull
  private String bloodGroup;

  private String maritalStatus;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Address address;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<EducationDetails> educationDetails = new ArrayList<>();
}
