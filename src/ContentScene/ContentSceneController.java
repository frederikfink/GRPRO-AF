package ContentScene;

import UserMVC.Users;
import controller.ContentController;
import controller.SuperController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Content;
import model.Movie;
import model.Show;

import java.io.IOException;

public class ContentSceneController {
    @FXML
    private ImageView movieSceneImageView;

    @FXML
    private Label contentSceneTitleLabel;

    @FXML
    private Label contentSceneGenresLabel;

    @FXML
    private Label contentSceneReleaseYearLabel;

    @FXML
    private Button movieSceneLogOut;

    @FXML
    private Button movieSceneBackButton;

    @FXML
    private Button contentSceneAddToMyListButton;

    @FXML
    private Label contentSceneMessageLabel;

    @FXML
    private Label contentSceneRatingLabel;

    @FXML
    private Label contentSceneSeasonsLabel;

    private SuperController sC = new SuperController();

    private ContentController cC = ContentController.getInstanceOf();

    private Users brugere = Users.getInstanceOf();

    private Content selectedContent;


    public ContentSceneController() throws IOException {
    }


    public void logOutClicked(){
        sC.goToLogIn(movieSceneLogOut);
    }

    public void backClicked() {
        sC.goToStartScene(movieSceneBackButton);
    }

    public void addToMyListClicked(){
        if (isInFavorites(selectedContent) != true){
            brugere.getSelectedUser().getSelectedProfile().addContent(selectedContent); //tilføjer filmen til ens liste hvis man klikker på den
            contentSceneMessageLabel.setText(" Added to favorites");
            contentSceneAddToMyListButton.setText("Remove from my list");
        } else {
            brugere.getSelectedUser().getSelectedProfile().removeContent(selectedContent);
            contentSceneMessageLabel.setText(" Removed from favorites");
            contentSceneAddToMyListButton.setText("Add to my list");
        }
    }



    public boolean isInFavorites(Content content){
        for (Content c : brugere.getSelectedUser().getSelectedProfile().getFavorites()){
            if (c == content){
                return true;
            }
        }
        return false;
    }


    public void initialize(){
        selectedContent = cC.getSelectedContent();
        contentSceneMessageLabel.setText("");
        contentSceneTitleLabel.setText("Title: " + selectedContent.getTitle());
        String s = new String();
        int i = 0;
        for (String string : selectedContent.getGenre()){
            s += string;
            i++;
            if (i < selectedContent.getGenre().length){
                s += ", ";
            }
        }
        contentSceneGenresLabel.setText("Genres: " + s);
        String rating = "" + selectedContent.getRating();
        contentSceneRatingLabel.setText("Rating: " + rating);
        if (selectedContent instanceof Movie) {
            Movie a = (Movie) selectedContent;
            contentSceneReleaseYearLabel.setText("Release year: " + ((Movie) selectedContent).getYear());
            contentSceneSeasonsLabel.setText("");
        }
        if (selectedContent instanceof Show) {
            Show a = (Show) selectedContent;
            contentSceneReleaseYearLabel.setText("Run time: " + a.getRuntime());
            contentSceneSeasonsLabel.setText("Seasons " + a.getSeasons());
        }

        if (isInFavorites(selectedContent) != true){
            contentSceneAddToMyListButton.setText("Add to my list");
        } else {
            contentSceneAddToMyListButton.setText("Remove from my list");
        }

        Image selectedImage = selectedContent.getCover();
        movieSceneImageView.setImage(selectedImage);
    }
}
