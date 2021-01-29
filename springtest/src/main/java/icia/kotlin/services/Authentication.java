package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;

@Service
public class Authentication {
	public Authentication() {}
	public ModelAndView entrance(Member m) {
		ModelAndView mav = new ModelAndView();
		
		switch (m.getServiceCode()) {
		case "A":
			mav = this.loginCtl(m);
			break;

		}
		
		
		return mav;
	}
	
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = new ModelAndView();
		System.out.println("loginCtl 진입 성공 및 서비스코드는 " + m.getServiceCode());
		System.out.println("m.getMId():: " + m.getMId());
		System.out.println("m.getMPwd():: " + m.getMPwd());
		mav.addObject("mId", m.getMId());
		mav.addObject("mPwd", m.getMPwd());
		mav.setViewName("loginForm");
		return mav;
		
	}
}
