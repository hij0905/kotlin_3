package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Member;
import icia.kotlin.mapper.MapperInterface;


@Service
public class Authentication {

   @Autowired
   private MapperInterface mapper;
   
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
      
      if(this.isMember(m)) {
         if(this.isAccess(m)) {
            System.out.println("로그인 성공!");
            mav.addObject("member", this.getMemberInfo(m));
         }
      }
      //mav.addObject("mId", m.getMId());
      //mav.addObject("mPwd", m.getMPw());
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
}