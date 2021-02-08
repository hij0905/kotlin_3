package icia.kotlin.mapper;
 

import icia.kotlin.beans.Member;

import icia.kotlin.beans.Movie;

public interface MapperInterface {

	public int isMember(Member member);
	public int isAccess(Member member);
	public Member getMemberInfo(Member member);
	public int insCustomer(Member member);
	public int insMovie(Movie movie);

}
