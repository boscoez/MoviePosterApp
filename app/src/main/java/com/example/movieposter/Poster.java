package com.example.movieposter;
/**
 * The Poster class represents a movie poster with details like title, creator, story, image, selection status, and rating.
 */
public class Poster {
    /** The name of the movie. */
    String name;
    /** The creator or director of the movie. */
    String createdBy;
    /** A brief story or description of the movie's plot. */
    String story;
    /** Resource ID for the movie's poster image. */
    int image;
    /** Boolean indicating if the poster is selected. Default is false. */
    Boolean isSelected = false;
    /** The rating of the movie, typically out of 5. */
    float rating;
}
