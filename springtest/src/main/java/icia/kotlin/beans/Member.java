package icia.kotlin.beans;

import lombok.Data;

@Data //롬북을 사용하기 위해선 반드시 적을 것+임포트
public class Member {

	private String mId;
	private String mPw;
	private String[] memberInfo;
//	public Object getMId() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public Object getMPw() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
