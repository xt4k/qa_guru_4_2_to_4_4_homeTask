package tests;

import helpers.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Student;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.openqa.selenium.Keys.*;

public class StudentRegistrationTest2 extends BaseTest {

    @Test
    @DisplayName("Register FAKE student")
    void testStudentRegistrationForm() {
        Student student = new Student();

        open("https://demoqa.com/automation-practice-form");
        $("#app").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(student.getFirstName());
        $("#lastName").setValue(student.getLastName());
        $("#userEmail").setValue(student.getEmail());

        String cssGender = format("#gender-radio-%s", student.getGender());
        $(cssGender).parent().click();
        String genderName = $(cssGender).getValue();
        $("#userNumber").setValue(student.getMobileNum());
        $("#dateOfBirthInput").sendKeys(chord(CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys(student.getBirthDateIn() + ENTER);
        $("#subjectsInput").setValue(student.getSubjects())
                .pressEnter();

        $(format("#hobbies-checkbox-%s", student.getHobbies())).parent()
                .click();
        File file = student.getIcon();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue(student.getAddress());
        $("#react-select-3-input").setValue(student.getState())
                .pressEnter();
        $("#react-select-4-input").setValue(student.getCity())
                .pressEnter();
        $("#submit").click();
        //Assert section
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']").parent().shouldHave(text(student.getFirstName() + " " + student.getLastName()));
        $x("//td[text()='Student Email']").parent().shouldHave(text(student.getEmail()));
        $x("//td[text()='Gender']").parent().shouldHave(text(genderName));
        $x("//td[text()='Mobile']").parent().shouldHave(text(student.getMobileNum()));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(student.getBirthDateOut()));
    }
}