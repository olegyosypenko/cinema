package ua.training.model.entity;


import java.util.List;

public class Film {
    private int id;
    private String name;
    private String genre;
    private String director;
    private float rate;
    private String description;
    private String imageLink;
    private List<Seance> seances;

    private Film(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.genre = builder.genre;
        this.director = builder.director;
        this.rate = builder.rate;
        this.description = builder.description;
        this.imageLink = builder.imageLink;
        this.seances = builder.seances;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public float getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public static class Builder {
        private int id;
        private String name;
        private String genre;
        private String director;
        private float rate;
        private String description;
        private String imageLink;
        private List<Seance> seances;

        public Builder() {

        }
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setDirector(String director) {
            this.director = director;
            return this;
        }

        public Builder setRate(float rate) {
            this.rate = rate;
            return this;
        }

        public Builder setImageLink(String imageLink) {
            this.imageLink = imageLink;
            return this;
        }
        public Builder setSeances(List<Seance> seances) {
            this.seances = seances;
            return this;
        }

        public Film buildFilm() {
            return new Film(this);
        }
    }
}