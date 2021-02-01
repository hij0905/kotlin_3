package icia.kotlin.beans;

import lombok.Data;

@Data // getter setter 안만들어도 lombok이 대신 만들어줌.
public class Member {
	
	private String mId;
	private String mPwd;
	private String mName;
	private String mPhone;
	private String servicecode;
}
