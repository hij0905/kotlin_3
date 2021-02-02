package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.MapperInterface;
import lombok.Setter;

@Service
public class Authentication {
	@Autowired
	private MapperInterface mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
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
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition()); //기본설정방식이다.
		
		try {
		if(this.isMember(m)) {
			System.out.println("아이디 일치 (isMember)");
			if(this.isAccess(m)){
				System.out.println("로그인 성공 (아이디 패스워드 일치)");
				mav.addObject("member", getMemberInfo(m));
				
				// tran 처리 1 :: ST insert
				m.setMId("tranTestjj10");
				m.setMName("tranName");
				m.setMPhone("01099997281");
				this.insCustomer(m);
				// tran 처리 2 :: MV insert
				
				Movie mv = new Movie();
				mv.setMvCode("10005");
				mv.setMvName("tran Test 영화 이름");
				mv.setMvGrade("T");
				mv.setMvStatus("S");
				mv.setMvImage("100005.JPG");
				mv.setMvComments("대충 트랜젝션 테스트하는 내용");
				this.insMovie(mv);
				tran.commit(status);
			}
		}
		}catch (Exception e) {
			tran.rollback(status);
			e.printStackTrace();
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
	
	private int insCustomer(Member member) {
		return mapper.insCustomer(member);
	}
	private int insMovie(Movie movie) {
		return mapper.insMovie(movie);
	}
	
	private Boolean convertToBoolean(int val) {
		return val == 1? true: false;
	}

	
	
}
