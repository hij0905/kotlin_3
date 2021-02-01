package icia.kotlin.mapper;

import icia.kotlin.beans.Member;

public interface MapperInterface {
	public int isMember(Member member);
	public int isAccess(Member member);
	public Member getMemberInfo(Member member);
}
