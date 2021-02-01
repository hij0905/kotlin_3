package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
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
	            System.out.println("�α��� ����!");
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
   /*@Transactional �� ��� �޼��带 public���� �ٲ�� �Ѵ�
   (SPRING FRAMEWORK������ TRANSACTION 1��)
   */
   
   private boolean convertToBoolean(int value) {
      return value==1? true: false;
   }
   
   /* Member ���� Ȯ�� */
   private boolean isMember(Member member) {
      return convertToBoolean(mapper.isMember(member));
   }

   /* Access ���� ���� : password ��ġ ���� */
   private boolean isAccess(Member member) {
      return convertToBoolean(mapper.isAccess(member));
   }

   /* ȸ������ ��������  */
   private Member getMemberInfo(Member member) {
      return mapper.getMemberInfo(member);
   }
   

}