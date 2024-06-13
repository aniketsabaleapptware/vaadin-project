package com.example.application.views.main;

import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Registration-Form")
@Route(value = "")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {

  private Binder<User> binder = new Binder<>(User.class);
  @Autowired
  private UserRepository userRepository;

  TextField firstNameField = new TextField("First Name");
  TextField lastNameField = new TextField("Last Name");
  ComboBox<String> genderComboBox = new ComboBox<>("Gender");
  IntegerField ageField = new IntegerField("Age");
  EmailField emailField = new EmailField("Email");
  private final TextField contactNumberField = new TextField("Contact Number");
  ComboBox<String> bloodGroupComboBox = new ComboBox<>("Blood Group");

  TextField addressField = new TextField("Address");

  Button submitButton = new Button("Submit");

  public MainView(UserRepository userRepository){
    this.userRepository = userRepository;

    bloodGroupComboBox.setItems("A+","A-", "B+", "B-", "AB+", "AB-", "O+",  "O-");
    genderComboBox.setItems("Male", "Female", "Others");

    binder.forField(firstNameField)
        .asRequired("First Name is required")
        .bind(User::getFirstName, User::setFirstName);
    binder.forField(lastNameField)
        .asRequired("Last Name is required")
        .bind(User::getLastName, User::setLastName);
    binder.forField(genderComboBox)
        .asRequired("Gender is required")
        .bind(User::getGender, User::setGender);
    binder.forField(emailField)
        .asRequired("Email id required.")
        .bind(User::getEmail, User::setEmail);
    binder.forField(contactNumberField)
        .withValidator(contact -> contact.length() == 10 && contact.matches("\\d+"), "Contact Number must be exactly 10 digits")
        .bind(user -> String.valueOf(user.getContactNumber()),
            (user, value) -> user.setContactNumber(Long.parseLong(value)));
    binder.forField(ageField)
        .asRequired("Age is required")
        .bind(User::getAge, User::setAge);
    binder.forField(bloodGroupComboBox)
        .asRequired("Blood group is required")
        .bind(User::getBloodGroup, User::setBloodGroup);
    binder.forField(addressField)
        .asRequired("Address is required")
        .bind(User::getAddress, User::setAddress);

    submitButton = new Button("Submit");
    submitButton.addClickListener(e -> {

      User user = new User();
      try {
        binder.writeBean(user);
        userRepository.save(user);
        Notification.show("User registered successfully.");
        clearFormFields();
      } catch (ValidationException validationException) {
        Notification.show("Please fill all the details.");
      }
    });

    FormLayout formLayout = new FormLayout();
    formLayout.add(firstNameField, lastNameField, genderComboBox, ageField, emailField, contactNumberField, bloodGroupComboBox, addressField, submitButton);
    add(formLayout);

    setMargin(true);
    setAlignItems(Alignment.CENTER);
  }




  private void clearFormFields() {
    firstNameField.clear();
    lastNameField.clear();
    emailField.clear();
    contactNumberField.clear();
    ageField.clear();
    addressField.clear();
    bloodGroupComboBox.clear();
    genderComboBox.clear();
  }


}
