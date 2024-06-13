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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Registration-Form")
@Route(value = "")
@RouteAlias(value = "")
public class MainView extends VerticalLayout {

  @Autowired
  private UserRepository userRepository;

  TextField firstNameField = new TextField("First Name");
  TextField lastNameField = new TextField("Last Name");
  EmailField emailField = new EmailField("Email");
  NumberField contactNumberField = new NumberField("Contact Number");
  NumberField ageField = new NumberField("Age");
  ComboBox<String> bloodGroupComboBox = new ComboBox<>("Blood Group");
  Button submitButton = new Button("Submit");

  public MainView(UserRepository userRepository){
    this.userRepository = userRepository;

    Header header = new Header();
    header.setText("Application Form");
    header.getStyle().set("font-size", "24px").set("margin-bottom", "20px");

    bloodGroupComboBox.setItems("A+","A-", "B+", "B-", "AB+", "AB-", "O+",  "O-");

    submitButton = new Button("Submit");
    submitButton.addClickListener(e -> {
      String firstname = firstNameField.getValue();
      String lastname = lastNameField.getValue();
      String email = emailField.getValue();
      long contactNumber = contactNumberField.getValue().longValue();
      int age = ageField.getValue().intValue();
      String bloodGroup = bloodGroupComboBox.getValue();

      User user = new User();
      user.setFirstname(firstname);
      user.setLastname(lastname);
      user.setEmail(email);
      user.setContactNumber(contactNumber);
      user.setAge(age);
      user.setBloodgroup(bloodGroup);

      userRepository.save(user);

      Notification.show("User registered successfully.");
      clearFormFields();

    });


    FormLayout formLayout = new FormLayout();
    formLayout.add(firstNameField, lastNameField, emailField, contactNumberField, ageField, bloodGroupComboBox, submitButton);
    add(formLayout);

    setMargin(true);
    setAlignItems(Alignment.CENTER);
  }

  private void clearFormFields(){
    firstNameField.clear();
    lastNameField.clear();
    emailField.clear();
    contactNumberField.clear();
    ageField.clear();
    bloodGroupComboBox.clear();
  }


}
