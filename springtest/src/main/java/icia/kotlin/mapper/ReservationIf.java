package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Movie;

public interface ReservationIf {
	public ArrayList<Movie> getMovieList(Movie movie);
	// public Movie movieSelectDate(Movie movie);

	public ArrayList<Movie> getScreening(Movie movie);
	
	
}
