package startScene;

import UserMVC.Profiles;
import UserMVC.Users;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CreateProfileBox {

    private Users brugere = Users.getInstanceOf();
    private Boolean answer;

    public boolean display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create new profile");
        window.setMinWidth(300);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        Label usernameLabel = new Label("Username: ");
        GridPane.setConstraints(usernameLabel, 0 ,0);

        Label ageLabel = new Label("Age ");
        GridPane.setConstraints(ageLabel, 0, 1);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        GridPane.setConstraints(usernameTextField,1,0);

        TextField ageTextField = new TextField();
        ageTextField.setPromptText("Age");
        ageTextField.setOnAction(e -> {
            int age = Integer.parseInt(ageTextField.getText());
            Profiles newProfile = new Profiles(usernameTextField.getText(),age);
            brugere.getSelectedUser().addProfile(newProfile);
            brugere.getSelectedUser().setSelectedProfile(newProfile);
            window.close();
            answer = true;
        });
        GridPane.setConstraints(ageTextField,1,1);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        GridPane.setConstraints(cancelButton,0,2);

        Button createButton = new Button("Create profile");
        createButton.setOnAction(e -> {
            int age = Integer.parseInt(ageTextField.getText());
            Profiles newProfile = new Profiles(usernameTextField.getText(),age);
            brugere.getSelectedUser().addProfile(newProfile);
            brugere.getSelectedUser().setSelectedProfile(newProfile);
            window.close();
            answer = true;
        });
        GridPane.setConstraints(createButton,1,2);

        layout.getChildren().addAll(usernameLabel,ageLabel,usernameTextField,ageTextField,cancelButton,createButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
