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


@Service
public class Authentication {

	@Autowired
	private MapperInterface mapper;
	@Autowired
	private PlatformTransactionManager tran;
	public Authentication() {}
	
	public ModelAndView entrance(Member m) {
		ModelAndView mav = null;
		
		switch(m.getServicecode()) {
		case "A":
			mav = this.loginCtl(m);
			break;
		}
		return mav;
	}

	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = null;

		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		
		mav = new ModelAndView();
		try {
			if (this.isMember(m)) {
				if (this.isAccess(m)) {
					System.out.println("로그인 성공!");
					mav.addObject("member", this.getMemberInfo(m));
					/* TRANSACTION 처리를 위한 메서드 1 : ST INSERT */
					m.setMId("tranHIJ2");
					m.setMPwd("1234");
					m.setMName("트랜");
					m.setMPhone("01012341212");
					this.insCustomer(m);
					System.out.println("insert 성공");
					
					/* TRANSACTION 처리를 위한 메서드 2 : MV INSERT */
//					Movie movie = new Movie();
//					movie.setMvCode("40004");
//					movie.setMvName("피아니스트");
//					movie.setMvGrade("T");
//					movie.setMvStatus("S");
//					movie.setMvImage("40004.JPG");
//					movie.setMvComments("드라마,전쟁|프랑스,독일,폴란드,영국,네덜란드|148분");
//					this.insMovie(movie);
//					System.out.println("insert 성공");1
					tran.commit(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback(status);
			System.out.println("rollback 완료!");
		}
		mav.setViewName("loginForm");
		
		return mav;
	}

	private boolean convertToBoolean(int value) {
		return value==1? true: false;
	}
	
	/* Member 여부 확인 */
	private boolean isMember(Member member) {
		return convertToBoolean(mapper.isMember(member));
	}

	/* Access 가능 여부 : password 일치 여부 */
	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}

	/* 회원정보 가져오기  */
	private Member getMemberInfo(Member member) {
		
		return mapper.getMemberInfo(member);
	}
	/* SPRING FRAMEWORK에서의 TRANSACTION  
	* 1. @Transactional을 이용한 Transaction
	* 2. AOP를 이용한 Transaction
	* 3. Programmatic Transaction (필드에서 많이 사용, 권장!)
	* */
	
	/* TRANSACTION 처리를 위한 메서드 1  */
	private int insCustomer(Member member) {
		return mapper.insCustomer(member);
	}
	/* TRANSACTION 처리를 위한 메서드 2  */
	private int insMovie(Movie movie) {
		return mapper.insMovie(movie);
	}
}
