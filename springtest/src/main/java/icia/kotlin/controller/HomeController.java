package icia.kotlin.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private Authentication auth;
	@Autowired
	private Reservation reservation;
	
	ModelAndView mav = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		mav = reservation.entrance(movie);
		return mav;
	}
	
	@RequestMapping(value = "/LoginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView logInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	@RequestMapping(value = "/Login", method = {RequestMethod.POST})
	public ModelAndView logIn(@ModelAttribute Member m) {
		ModelAndView mav = null;
		return auth.entrance(m);
	}
	
	@RequestMapping(value = "/Step2", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView step2(@ModelAttribute Movie movie) {
		mav = reservation.entrance(movie);
		
		return mav;
	}
	
	@RequestMapping(value = "/Step3", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String step3(@ModelAttribute Movie movie) throws UnsupportedEncodingException {
		mav = reservation.entrance(movie);
		System.out.println(movie.getSCode());
		System.out.println(movie.getMvCode());
		System.out.println(movie.getMvDate());
		//mav.setViewName("movieTestPage");
		System.out.println(mav.getModel().get("ScreeningData"));
		return URLEncoder.encode(mav.getModel().get("ScreeningData").toString(),"UTF-8");
	}
	
}
