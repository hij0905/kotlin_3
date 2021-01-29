package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;

@Service
public class Authentication {

	
	public Authentication() {
	}
	public ModelAndView entrance() {
		ModelAndView mav = null;
		
		return mav;
	}
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		switch(m.getServiceCode()) {
		case "A": 
			mav = this.loginCtl(m);
			break;
			}
		
		return mav;
	}
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = null;
		
		
		mav = new ModelAndView();
		mav.addObject("mId", m.getMId());
		mav.addObject("mPw", m.getMPw());
		
		
		mav.setViewName("loginForm");
		
		return mav;
	}
}
