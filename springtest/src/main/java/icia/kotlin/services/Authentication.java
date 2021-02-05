package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
<<<<<<< HEAD
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
		
		switch(m.getSCode()) {
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
					System.out.println("ë¡œê·¸ì¸ ì„±ê³µ!");
					mav.addObject("member", this.getMemberInfo(m));
					/* TRANSACTION ì²˜ë¦¬ë¥¼ ìœ„í•œ ë©”ì„œë“œ 1 : ST INSERT */
					m.setMId("tranHIJ2");
					m.setMPwd("1234");
					m.setMName("íŠ¸ëžœ");
					m.setMPhone("01012341212");
					this.insCustomer(m);
					System.out.println("insert ì„±ê³µ");
					
					/* TRANSACTION ì²˜ë¦¬ë¥¼ ìœ„í•œ ë©”ì„œë“œ 2 : MV INSERT */
//					Movie movie = new Movie();
//					movie.setMvCode("40004");
//					movie.setMvName("í”¼ì•„ë‹ˆìŠ¤íŠ¸");
//					movie.setMvGrade("T");
//					movie.setMvStatus("S");
//					movie.setMvImage("40004.JPG");
//					movie.setMvComments("ë“œë¼ë§ˆ,ì „ìŸ|í”„ëž‘ìŠ¤,ë…ì¼,í´ëž€ë“œ,ì˜êµ­,ë„¤ëœëž€ë“œ|148ë¶„");
//					this.insMovie(movie);
//					System.out.println("insert ì„±ê³µ");1
					tran.commit(status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback(status);
			System.out.println("rollback ì™„ë£Œ!");
		}
		mav.setViewName("loginForm");
		
		return mav;
	}

	private boolean convertToBoolean(int value) {
		return value==1? true: false;
	}
	
	/* Member ì—¬ë¶€ í™•ì¸ */
	private boolean isMember(Member member) {
		return convertToBoolean(mapper.isMember(member));
	}

	/* Access ê°€ëŠ¥ ì—¬ë¶€ : password ì¼ì¹˜ ì—¬ë¶€ */
	private boolean isAccess(Member member) {
		return convertToBoolean(mapper.isAccess(member));
	}

	/* íšŒì›ì •ë³´ ê°€ì ¸ì˜¤ê¸°  */
	private Member getMemberInfo(Member member) {
		
		return mapper.getMemberInfo(member);
	}
	/* SPRING FRAMEWORKì—ì„œì˜ TRANSACTION  
	* 1. @Transactionalì„ ì´ìš©í•œ Transaction
	* 2. AOPë¥¼ ì´ìš©í•œ Transaction
	* 3. Programmatic Transaction (í•„ë“œì—ì„œ ë§Žì´ ì‚¬ìš©, ê¶Œìž¥!)
	* */
	
	/* TRANSACTION ì²˜ë¦¬ë¥¼ ìœ„í•œ ë©”ì„œë“œ 1  */
	private int insCustomer(Member member) {
		return mapper.insCustomer(member);
	}
	/* TRANSACTION ì²˜ë¦¬ë¥¼ ìœ„í•œ ë©”ì„œë“œ 2  */
	private int insMovie(Movie movie) {
		return mapper.insMovie(movie);
	}
}
=======
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
      
      switch(m.getServiceCode()) {
      case "A":
         mav = this.loginCtl(m);
         break;
      }
      return mav;
   }

   private ModelAndView loginCtl(Member m) {
      
      ModelAndView mav = new ModelAndView();
      
      TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
      
     
      
      try {
	      if(this.isMember(m)) {
	         if(this.isAccess(m)) {
	            System.out.println("·Î±×ÀÎ ¼º°ø!");
	            mav.addObject("member", this.getMemberInfo(m));
	            
	            tran.commit(status);
	         }
	         
	      }
	      
      }catch(Exception e) {
    	  tran.rollback(status);
      }
      //mav.addObject("mId", m.getMId());
      //mav.addObject("mPwd", m.getMPw());
      mav.setViewName("loginForm");
      
      return mav;
   }
   /*@Transactional ´Ü ¸ðµç ¸Þ¼­µå¸¦ publicÀ¸·Î ¹Ù²ã¾ß ÇÑ´Ù
   (SPRING FRAMEWORK¿¡¼­ÀÇ TRANSACTION 1¹ø)
   */
   
   private boolean convertToBoolean(int value) {
      return value==1? true: false;
   }
   
   /* Member ¿©ºÎ È®ÀÎ */
   private boolean isMember(Member member) {
      return convertToBoolean(mapper.isMember(member));
   }

   /* Access °¡´É ¿©ºÎ : password ÀÏÄ¡ ¿©ºÎ */
   private boolean isAccess(Member member) {
      return convertToBoolean(mapper.isAccess(member));
   }

   /* È¸¿øÁ¤º¸ °¡Á®¿À±â  */
   private Member getMemberInfo(Member member) {
      return mapper.getMemberInfo(member);
   }
   

}
>>>>>>> refs/remotes/master/Hyeon
