package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperInterface;
import lombok.Setter;

@Service
public class Authentication {
	@Setter(onMethod_ = {@Autowired})
	private MapperInterface mapper;
	
	public Authentication() {}
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		switch (m.getServiceCode()) {
		case "A":
			mav = this.loginCtl(m);
			break;
		}
		return mav;
	}
	
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = new ModelAndView();
		if(this.isMember(m)) {
			System.out.println("아이디 일치 (isMember)");
			if(this.isAccess(m)){
				System.out.println("로그인 성공 (아이디 패스워드 일치)");
//				mav.addObject("mName", m.getMId());
//				mav.addObject("mName", this.getMemberInfo(m).getMName());
//				mav.addObject("mPhone", this.getMemberInfo(m).getMPhone());
				mav.addObject("member", getMemberInfo(m));
			}
		}
		mav.setViewName("loginForm");
		return mav;
	}
	
	//member 여부 확인.
	private boolean isMember(Member member) {
		return convertToBoolean(mapper.isMember(member));
	}
	
	// 패스워드 일치여부.
	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}
	
	
	// 회원정보가져오기.
	private Member getMemberInfo(Member member) {
		return mapper.getMemberInfo(member);
	}
	
	private Boolean convertToBoolean(int val) {
		return val == 1? true: false;
	}

	
	
}
