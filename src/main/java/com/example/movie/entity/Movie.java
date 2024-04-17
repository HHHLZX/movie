package com.example.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String movieName;
    private String movieImage;
    private String movieBoxOffice;
    private String movieLanguage;
    private Date movieDate;
    private int movieTimeTotal;
    private float movieScore;
    private String movieType;
    private String movieDesc;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", movieImage='" + movieImage + '\'' +
                ", movieBoxOffice='" + movieBoxOffice + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", movieDate=" + movieDate +
                ", movieTimeTotal=" + movieTimeTotal +
                ", movieScore=" + movieScore +
                ", movieType='" + movieType + '\'' +
                ", movieDesc='" + movieDesc + '\'' +
                '}';
    }

    public Movie(Integer id, String movieName, String movieImage, String movieBoxOffice, String movieLanguage, Date movieDate, int movieTimeTotal, float movieScore, String movieType, String movieDesc) {
        this.id = id;
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieBoxOffice = movieBoxOffice;
        this.movieLanguage = movieLanguage;
        this.movieDate = movieDate;
        this.movieTimeTotal = movieTimeTotal;
        this.movieScore = movieScore;
        this.movieType = movieType;
        this.movieDesc = movieDesc;
    }

    public Movie() {
    }

    public String getMovieBoxOffice() {
        return movieBoxOffice;
    }

    public void setMovieBoxOffice(String movieBoxOffice) {
        this.movieBoxOffice = movieBoxOffice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }

    public int getMovieTimeTotal() {
        return movieTimeTotal;
    }

    public void setMovieTimeTotal(int movieTimeTotal) {
        this.movieTimeTotal = movieTimeTotal;
    }

    public float getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(float movieScore) {
        this.movieScore = movieScore;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }
}
