package register;

import UserMVC.Users;
import UserMVC.invalidRegistration;
import controller.SuperController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;



public class RegisterController {

    @FXML
    private TextField registerNameField;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private TextField registerPasswordField;
    @FXML
    private TextField registerEmailField;
    @FXML
    private TextField registerAgeField;
    @FXML
    private Button submitButton;
    @FXML
    private GridPane gp;
    @FXML
    private Button registerGoBackButton;
    @FXML
    private Label registerErrorMessage;

    private SuperController sC = new SuperController();



    private Users brugere;


    public void submitPressed() {

        try {
            int age = Integer.parseInt(registerAgeField.getText());
            brugere.registerUser(registerNameField.getText(), registerUsernameField.getText(), registerPasswordField.getText(), registerEmailField.getText(), age);
            sC.goToLogIn(submitButton);
        } catch (IllegalArgumentException f) {
            if (f instanceof NumberFormatException) {
                registerErrorMessage.setText("All fields must be filled");
            } else {
                registerErrorMessage.setText(f.getMessage());
            }
        } catch (RuntimeException g) {
            System.out.println("s");
        }
    }

    public void goBackPressed(){
        sC.goToLogIn(registerGoBackButton);
    }


    public void initialize(){
        brugere = Users.getInstanceOf();
        registerErrorMessage.setText("All fields must be filled");
        registerAgeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    registerAgeField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
