package org.example.finalrmicallbacks;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.finalrmicallbacks.client.ChatPageController;

import java.io.File;
import java.io.IOException;

public class LoginPageController
{
    @javafx.fxml.FXML
    private TextField usernameField;

    @javafx.fxml.FXML
    private ImageView profilePic;


    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public void setProfilePic(ImageView profilePic) {
        this.profilePic = profilePic;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public ImageView getProfilePic() {
        return profilePic;
    }

    @javafx.fxml.FXML
    public void initialize(){

    }

    public void handleSignUp(ActionEvent actionEvent) {
        String username = usernameField.getText().trim();
        if(username==""|| username==null){
            System.out.println("Please enter a username");
            usernameField.setStyle("-fx-border-color: red");
        }
        else{
            try {
                FXMLLoader chatScreen = new FXMLLoader(LoginPageController.class.getResource("chatPageView.fxml"));
                Parent root = chatScreen.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Deprecated
    public void changeProfilePic(Event event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profilePic.setImage(new Image(image.getUrl()));
        }
    }
}