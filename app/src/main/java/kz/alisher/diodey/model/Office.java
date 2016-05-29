package kz.alisher.diodey.model;

/**
 * Created by Alikdemon on 29.05.2016.
 */
public class Office {
    private String name;
    private int image;

    public Office(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
