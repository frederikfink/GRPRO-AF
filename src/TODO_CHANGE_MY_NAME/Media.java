package TODO_CHANGE_MY_NAME;

import javafx.scene.image.Image;

public class Media{
    protected String title;
    protected String[] genre;
    protected double rating;
    protected Image cover;


    public Media (String title, String[] genre, double rating, Image cover)  {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.cover = cover;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String[] getGenre() {
        return genre;
    }

    public Image getCover() {
        return cover;
    }

}
