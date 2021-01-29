package icia.kotlin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView mv) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		mv.addObject("welcome", "어서오세요~ 환영합니다." );
		mv.addObject("serverTime", formattedDate );
		
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value = "/LoginForm", method = {RequestMethod.GET, RequestMethod.POST})
	
	public ModelAndView logInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView logIn(@ModelAttribute Member m) {
							//@RequestParam("memberInfo") String[] member
							  
	ModelAndView mav = new ModelAndView();
	mav.addObject("mId", m.getMId());
	mav.addObject("mPw", m.getMPw());
	
//	mav.addObject("memberId",member[0]);
//	mav.addObject("memberPw",member[1]);
	
	mav.setViewName("loginForm");
	return mav;
		
	}
	
}
