package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.ReservationIf;

@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	@Autowired
	private PlatformTransactionManager tran;
	@Autowired
	private Gson gson;
	
	public ModelAndView entrance(Movie movie) {
		ModelAndView mav = null;
		if (movie.getMvCode() == null) {
			mav = this.movieListCtl(movie);
		} else {
			mav = this.screeningDate(movie);
			switch (movie.getSCode()) {
			case "Step2":

				break;

			case "Step3":
				System.out.println("step3 실행");
				mav = this.screeningTime(movie);
				break;
			}

			
			//
			
		}
		return mav;

	}

	private ModelAndView screeningTime(Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Access", this.getCurrentDate('d'));
		String jsonData = gson.toJson(this.getScreening(movie));
		// System.out.println(jsonData);
		mav.addObject("ScreeningData", jsonData);
		//mav.setViewName("step2");
		
		return mav;
	}
	
	private ArrayList<Movie> getScreening(Movie movie) {
		return mapper.getScreening(movie);
	}


	private ModelAndView screeningDate(Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Access", this.getCurrentDate('d'));
		// Movie info & Convert to json 
		String jsonData = gson.toJson(this.getMovieList(movie));
		mav.addObject("movieData", jsonData);
		// mav.addObject("selectMovie", this.movieSelectDate(movie));

		mav.setViewName("step2");
		return mav;
	}

	
	
	//private Movie movieSelectDate(Movie movie) {
		//return mapper.movieSelectDate(movie);
	//}

	private ModelAndView movieListCtl(Movie movie) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("Access", this.getCurrentDate('f'));
		String jsonData = gson.toJson(this.getMovieList(movie));
		System.out.println(jsonData + "jsonData");
		mav.addObject("movieData", jsonData);
		mav.setViewName("home");
		return mav;
	}

	private ArrayList<Movie> getMovieList(Movie movie) {
		return mapper.getMovieList(movie);
	}

	private String getCurrentDate(char dateType) {
		Date date = new Date();

		SimpleDateFormat sdf = (dateType == 'f') ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일")
				: (dateType == 'd') ? new SimpleDateFormat("yyyy-MM-dd")
						: (dateType == 't') ? new SimpleDateFormat("HH-mm E요일") : null;
		System.out.println("sdf.format(date):: " + sdf.format(date));
		return sdf.format(date);
	}

}
