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
		}
		return mav;
		
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		
		
		ArrayList<Movie> mList = new ArrayList<Movie>();
		
		mList = this.getMovieList();
		
		
		// for(int i = 0; i)
		
		System.out.println("test get mv name :"+ mList.get(0).getMvName());
		
		System.out.println(this.getMovieList().size());
		mav.addObject("movieList", this.getMovieList());
		
		mav.setViewName("home");
		return mav;
	}

	private ArrayList<Movie> getMovieList() {
		return mapper.getMovieList();
	}
	
	
}
