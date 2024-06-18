package com.example.application.views.main;

import com.example.application.entities.Address;
import com.example.application.entities.EducationDetails;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Registration-Form")
@Route(value = "/application-form")
@RouteAlias(value = "")
public class MainView extends HorizontalLayout {

  private Binder<User> binder = new Binder<>(User.class);
  private Binder<Address> addressBinder = new Binder<>(Address.class);
  private Binder<EducationDetails> educationBinder10 = new Binder<>(EducationDetails.class);
  private Binder<EducationDetails> educationBinder12 = new Binder<>(EducationDetails.class);
  private Binder<EducationDetails> educationBinderGraduation = new Binder<>(EducationDetails.class);


  @Autowired
  private UserRepository userRepository;

  TextField firstNameField = new TextField("First Name");
  TextField lastNameField = new TextField("Last Name");
  RadioButtonGroup<String> genderRadioGroup = new RadioButtonGroup<>("Gender");
  DatePicker dobPicker = new DatePicker("Date of Birth");
  EmailField emailField = new EmailField("Email");
  private final TextField contactNumberField = new TextField("Contact Number");
  ComboBox<String> bloodGroupComboBox = new ComboBox<>("Blood Group");
  RadioButtonGroup<String> maritalStatus = new RadioButtonGroup<>("Marital Status");


  TextField streetField = new TextField("Street");
  TextField cityField = new TextField("City");
  TextField stateField = new TextField("State");
  TextField countryField = new TextField("Country");
  IntegerField zipcodeField = new IntegerField("Zip code");


  TextField collegeName10Field = new TextField("College Name");
  TextField percentage10Field = new TextField("Percentage");
  DatePicker yearOfPassing10Picker = new DatePicker("Year of Passing");

  TextField collegeName12Field = new TextField("College Name");
  TextField percentage12Field = new TextField("Percentage");
  DatePicker yearOfPassing12Picker = new DatePicker("Year of Passing");
  TextField graduationCollegeNameField = new TextField("College Name");
  TextField graduationBranchField = new TextField("Branch");
  TextField graduationPercentageField = new TextField("Percentage");
  DatePicker yearOfPassingGraduationPicker = new DatePicker("Year of Passing");

  Button submitButton = new Button("Submit");

  public MainView(UserRepository userRepository) {
    this.userRepository = userRepository;

    bloodGroupComboBox.setItems("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

    genderRadioGroup.setLabel("Gender");
    genderRadioGroup.setItems("Male", "Female", "Others");

    maritalStatus.setLabel("Marital Status");
    maritalStatus.setItems("Single", "Married", "Divorced", "Others");
    dobPicker.setMax(LocalDate.now());
    yearOfPassing10Picker.setMax(LocalDate.now());
    yearOfPassing12Picker.setMax(LocalDate.now());
    yearOfPassingGraduationPicker.setMax(LocalDate.now());

    binder.forField(firstNameField)
        .asRequired("")
        .bind(User::getFirstName, User::setFirstName);
    binder.forField(lastNameField)
        .asRequired("")
        .bind(User::getLastName, User::setLastName);
    binder.forField(genderRadioGroup)
        .asRequired("")
        .bind(User::getGender, User::setGender);
    binder.forField(emailField)
        .asRequired("")
        .bind(User::getEmail, User::setEmail);
    binder.forField(contactNumberField)
        .withValidator(contact -> contact.length() == 10 && contact.matches("\\d+"), "Contact Number must be exactly 10 digits")
        .bind(user -> String.valueOf(user.getContactNumber()),
            (user, value) -> user.setContactNumber(Long.parseLong(value)));
    binder.forField(dobPicker)
        .asRequired("")
        .bind(User::getDob, User::setDob);
    binder.forField(bloodGroupComboBox)
        .asRequired("")
        .bind(User::getBloodGroup, User::setBloodGroup);
    binder.forField(maritalStatus)
        .asRequired("")
        .bind(User::getMaritalStatus, User::setMaritalStatus);

    addressBinder.forField(streetField)
        .asRequired("")
        .bind(Address::getStreet, Address::setStreet);
    addressBinder.forField(cityField)
        .asRequired("")
        .bind(Address::getCity, Address::setCity);
    addressBinder.forField(stateField)
        .asRequired("")
        .bind(Address::getState, Address::setState);
    addressBinder.forField(countryField)
        .asRequired("")
        .bind(Address::getCountry, Address::setCountry);
    addressBinder.forField(zipcodeField)
        .asRequired("")
        .bind(Address::getZipCode, Address::setZipCode);


    educationBinder10.forField(collegeName10Field)
        .asRequired("")
        .bind(EducationDetails::getCollegeName, EducationDetails::setCollegeName);
    educationBinder10.forField(percentage10Field)
        .asRequired("")
        .bind(EducationDetails::getPercentage, EducationDetails::setPercentage);
    educationBinder10.forField(yearOfPassing10Picker)
        .asRequired("")
        .bind(EducationDetails::getYearOfPassing, EducationDetails::setYearOfPassing);


    educationBinder12.forField(collegeName12Field)
        .asRequired("")
        .bind(EducationDetails::getCollegeName, EducationDetails::setCollegeName);
    educationBinder12.forField(percentage12Field)
        .asRequired("")
        .bind(EducationDetails::getPercentage, EducationDetails::setPercentage);
    educationBinder12.forField(yearOfPassing12Picker)
        .asRequired("")
        .bind(EducationDetails::getYearOfPassing, EducationDetails::setYearOfPassing);

    educationBinderGraduation.forField(graduationCollegeNameField)
        .asRequired("")
        .bind(EducationDetails::getCollegeName, EducationDetails::setCollegeName);
    educationBinderGraduation.forField(graduationPercentageField)
        .asRequired("")
        .bind(EducationDetails::getPercentage, EducationDetails::setPercentage);
    educationBinderGraduation.forField(yearOfPassingGraduationPicker)
        .asRequired("")
        .bind(EducationDetails::getYearOfPassing, EducationDetails::setYearOfPassing);
    educationBinderGraduation.forField(graduationBranchField)
        .asRequired()
        .bind(EducationDetails::getBranch, EducationDetails::setBranch);


    submitButton = new Button("Submit");
    submitButton.addClickListener(e -> {

      User user = new User();
      try {
        binder.writeBean(user);

        Address address = new Address();
        addressBinder.writeBean(address);
        address.setUser(user);
        user.setAddress(address);

        EducationDetails education10 = new EducationDetails();
        education10.setStandard("10th");
        educationBinder10.writeBean(education10);
        education10.setUser(user);

        EducationDetails education12 = new EducationDetails();
        education12.setStandard("12th");
        educationBinder12.writeBean(education12);
        education12.setUser(user);

        EducationDetails graduation = new EducationDetails();
        graduation.setStandard("Graduation");
        educationBinderGraduation.writeBean(graduation);
        graduation.setUser(user);

        user.getEducationDetails().add(education10);
        user.getEducationDetails().add(education12);
        user.getEducationDetails().add(graduation);

        userRepository.save(user);
        Notification.show("User registered successfully.");
        clearFormFields();
      } catch (ValidationException validationException) {
        Notification.show("Please fill all the details.");
      }
    });

    FormLayout personalInfoLayout = new FormLayout();
    H3 personalInfoHeader = new H3("Personal Information");
    personalInfoLayout.add(personalInfoHeader);
    personalInfoLayout.setColspan(personalInfoHeader, 2);
    personalInfoLayout.add(firstNameField, lastNameField, dobPicker, emailField, contactNumberField, bloodGroupComboBox, genderRadioGroup, maritalStatus);

    FormLayout educationDetails10 = new FormLayout();
    H3 educationHeader = new H3("Educational Details");
    educationDetails10.add(educationHeader);
    educationDetails10.setColspan(educationHeader, 2);
    H5 tenthHeader = new H5("10th Standard");
    educationDetails10.add(tenthHeader);
    educationDetails10.setColspan(tenthHeader, 2);
    educationDetails10.add(collegeName10Field, percentage10Field, yearOfPassing10Picker);

    FormLayout educationDetails12 = new FormLayout();
    H5 twelthHeader = new H5("12th Standard");
    educationDetails12.add(twelthHeader);
    educationDetails12.setColspan(twelthHeader, 2);
    educationDetails12.add(collegeName12Field, percentage12Field, yearOfPassing12Picker);

    FormLayout graduationDetails = new FormLayout();
    H5 graduationHeader = new H5("Graduation");
    graduationDetails.add(graduationHeader);
    graduationDetails.setColspan(graduationHeader, 2);
    graduationDetails.add(graduationCollegeNameField, graduationBranchField, graduationPercentageField, yearOfPassingGraduationPicker);

    FormLayout addressLayout = new FormLayout();
    H3 addressHeader = new H3("Residence Address");
    addressLayout.add(addressHeader);
    addressLayout.setColspan(addressHeader, 2);
    addressLayout.add(streetField, cityField, stateField, countryField, zipcodeField);

    VerticalLayout mainLayout = new VerticalLayout();
    mainLayout.add(new H1("Application Form"), personalInfoLayout,  educationDetails10, educationDetails12, graduationDetails, addressLayout, submitButton);
    mainLayout.setSpacing(true);
    mainLayout.setPadding(true);
    mainLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

    add(mainLayout);

    setMargin(true);
    setAlignItems(Alignment.CENTER);
  }

  private void clearFormFields() {
    firstNameField.clear();
    lastNameField.clear();
    emailField.clear();
    contactNumberField.clear();
    dobPicker.clear();
    bloodGroupComboBox.clear();
    genderRadioGroup.clear();
    maritalStatus.clear();
    streetField.clear();
    cityField.clear();
    stateField.clear();
    countryField.clear();
    zipcodeField.clear();
    collegeName10Field.clear();
    percentage10Field.clear();
    yearOfPassing10Picker.clear();
    collegeName12Field.clear();
    percentage12Field.clear();
    yearOfPassing12Picker.clear();
    graduationCollegeNameField.clear();
    graduationBranchField.clear();
    graduationPercentageField.clear();
    yearOfPassingGraduationPicker.clear();
  }
}
