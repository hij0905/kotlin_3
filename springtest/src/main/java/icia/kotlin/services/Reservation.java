package icia.kotlin.services;

<<<<<<< HEAD
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
		
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl(movie);
		}else {
			//mav = this.movieSelectDateCtl(movie);
			switch(movie.getSCode()) {
			case "Step2":
				mav = this.screeningDate(movie);
				break;
			case "Step3":
				mav = this.screeningTime(movie);
				break;
			case "Step4":
				mav = this.selectSeat(movie);
				break;
			}
		}
		return mav;
	}

	private ModelAndView selectSeat(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(movie.getSCode());
		System.out.println(movie.getMvCode());
		System.out.println(movie.getMvThCode());
		System.out.println(movie.getMvScreen());
		System.out.println(movie.getMvDateTime());
		
		mav.addObject("Access", this.getCurrentDate('f'));
		mav.setViewName("step4");
		
		return mav;
	}

	/* Screening Time*/
	private ModelAndView screeningTime(Movie movie) {
		ModelAndView mav = new ModelAndView();
			/*Start Date*/
			mav.addObject("Access", this.getCurrentDate('d'));
			
			/* Movie Info & Convert to JSON*/
			String jsonData = gson.toJson(this.getScreening(movie));
			System.out.println(jsonData);
			
			mav.addObject("ScreeningData", jsonData);
		return mav;
		}  
	
	private ArrayList<Movie> getScreening(Movie movie) {

		return mapper.getScreening(movie);
	}
	
	/* Screening Date*/
	private ModelAndView screeningDate(Movie movie) {
		ModelAndView mav = new ModelAndView();
			/*Start Date*/
			mav.addObject("Access", this.getCurrentDate('d'));
			
			/* Movie Info & Convert to JSON*/
			String jsonData = gson.toJson(this.getMovieList(movie));
			mav.addObject("movieData", jsonData);
			/* View*/
			mav.setViewName("step2");
		return mav;
		}



//private ModelAndView movieSelectDateCtl(Movie movie) {
//		ModelAndView mav = new ModelAndView();
//		Movie mv = new Movie();
//		Date date = new Date();
//		
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
//	    
//	    mav.addObject("movieDate", sdf.format(date));
//	    mav.addObject("selectMovie", this.movieSelectDate(movie));
//	    
//	    mav.setViewName("movieDate");	
//		return mav;
//	}
//
//private Movie movieSelectDate(Movie movie) {
//	return mapper.movieSelectDate(movie);
//}


private ModelAndView movieListCtl(Movie movie) {
	      ModelAndView mav = new ModelAndView();
	      //Date date = new Date();
	      //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
	      //mav.addObject("Access", sdf.format(date));
	      mav.addObject("Access", this.getCurrentDate('f'));
	      
	     // System.out.println(this.getMovieList().size());
	      
	      String jsonData = gson.toJson(this.getMovieList(movie));
	      mav.addObject("jsonData", jsonData);
	      
	      /* makeHTML*/
	      ArrayList<Movie> mList = new ArrayList<Movie>();
	      StringBuffer sBuffer = new StringBuffer();
	      mList = this.getMovieList(movie);
	      int index = 0;
	      
   		  for(Movie mv : mList) {
   			  index++;
   			  if(index%4 == 1 )  {// 1,5,9 ....
   				  sBuffer.append("<div>");
   			  }
		          sBuffer.append("<div id=\"movie_row\">");
		          sBuffer.append("<div onclick= \"step1('"+mv.getMvCode()+"')\"><img src=\"resources/img/"+ mv.getMvImage() +"\"/></div>");
		          sBuffer.append("<div>" + mv.getMvName() + "</div>");
		          sBuffer.append("</div>");
	          if(index%4 == 0) { // 4,8,12
	        	  sBuffer.append("</div>");  
	          }
	       }
	      
	      mav.addObject("mList", sBuffer.toString());
	      ///////////
	      
	      mav.setViewName("home");
	      return mav;
	   }

	private ArrayList<Movie> getMovieList(Movie movie) {

		return mapper.getMovieList(movie);
	}
	
	/* Current DataTime*/
	private String getCurrentDate(char dateType) {
		Date date = new Date();
		
		SimpleDateFormat sdf = (dateType=='f')? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일") :
			(dateType=='d')? new SimpleDateFormat("yyyy-MM-dd"):
				(dateType=='t')? new SimpleDateFormat("HH:mm E요일") : null;
				
		return sdf.format(date);		
	}
	
	
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.ReservationIf;

@Service
public class Reservation {
	   @Autowired
	   private ReservationIf mapper;
	   @Autowired
	   private PlatformTransactionManager tran;
	   
	   
	   
	   public ModelAndView entrance(Movie movie) {
	      ModelAndView mav = null;
	      
	      if(movie.getMvCode() == null) {
	    	  mav = this.movieListCtl();
	      }
	      
	      return mav;
	   }



	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		ArrayList<Movie> movieList = null;
		/* AccessTime */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E����");
		mav.addObject("Access", sdf.format(date));
		
		System.out.println(this.getMovieList().size());
		
		mav.setViewName("home");
		return mav;
	}
	
	private ArrayList<Movie> getMovieList(){
		return mapper.getMovieList();
	}
>>>>>>> refs/remotes/master/Hyeon
}
