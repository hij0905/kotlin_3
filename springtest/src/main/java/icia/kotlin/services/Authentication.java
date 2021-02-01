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
            System.out.println("�α��� ����!");
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