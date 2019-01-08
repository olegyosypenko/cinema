package ua.training.model.dto;


public class FilmDto {
    private String name;
    private String nameEN;
    private String genre;
    private String genreEN;
    private String director;
    private String directorEN;
    private float rate;
    private String description;
    private String descriptionEN;
    private String imageLink;
    private String imageLinkEN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenreEN() {
        return genreEN;
    }

    public void setGenreEN(String genreEN) {
        this.genreEN = genreEN;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectorEN() {
        return directorEN;
    }

    public void setDirectorEN(String directorEN) {
        this.directorEN = directorEN;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLinkEN() {
        return imageLinkEN;
    }

    public void setImageLinkEN(String imageLinkEN) {
        this.imageLinkEN = imageLinkEN;
    }
}