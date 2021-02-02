package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Movie;

public interface ReservationIf {
	public ArrayList<Movie> getMovieList();
	public Movie movieSelectDate(Movie movie);
}
