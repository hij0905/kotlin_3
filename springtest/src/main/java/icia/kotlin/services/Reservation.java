package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.ReservationIf;
@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	
	public ModelAndView entrance(Movie movie) {
		ModelAndView mav = null;
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl();
		}else {
			mav = this.movieSelectDateCtl(movie);
		}
		return mav;
		
	}

	private ModelAndView movieSelectDateCtl(Movie movie) {
		ModelAndView mav = new ModelAndView();
		Movie mv = new Movie();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss E요일");
		mav.addObject("movieDate", sdf.format(date));
		mav.addObject("selectMovie", this.movieSelectDate(movie));
		
		mav.setViewName("movieDate");
		return mav;
	}

	private Movie movieSelectDate(Movie movie) {
		return mapper.movieSelectDate(movie);
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		
		ArrayList<Movie> mList = new ArrayList<Movie>();
		StringBuffer sBuffer = new StringBuffer();
		mList = this.getMovieList();
		
		System.out.println(this.getMovieList().size());
		for(Movie mv : mList) {
			sBuffer.append("<div>");
			sBuffer.append("<div id=\"movie_row\">");
			sBuffer.append("<div onclick= \"step2(\'"+ mv.getMvCode()+"\')\"><img src=\"resources/img/"+ mv.getMvImage() +"\"/></div>");
			sBuffer.append("<div>" + mv.getMvName() + "</div>");
			sBuffer.append("</div>");
		}
		
		mav.addObject("mList", sBuffer.toString());
		
		mav.setViewName("home");
		return mav;
	}

	private ArrayList<Movie> getMovieList() {
		return mapper.getMovieList();
	}
	
	
}
