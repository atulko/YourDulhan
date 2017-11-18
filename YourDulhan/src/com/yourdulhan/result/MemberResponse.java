package com.yourdulhan.result;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.yourdulhan.pojo.Member;


@XmlRootElement
public class MemberResponse {
	private String reason;
	private String status;
	private List<Member> listOfMember;
	private boolean result;
	private Member member;//has a relationship
	private int statusCode;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Member> getListOfMember() {
		return listOfMember;
	}
	public void setListOfMember(List<Member> listOfMember) {
		this.listOfMember = listOfMember;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	

}
