package icia.kotlin.mapper;

import java.util.ArrayList;


import icia.kotlin.beans.Movie;

public interface ReservationIf {
	public ArrayList<Movie> getMovieList(Movie movie);
	public ArrayList<Movie> getScreening(Movie movie);
//	public ArrayList<Movie> getMovieList();
//	public Movie movieSelectDate(Movie movie);

}
