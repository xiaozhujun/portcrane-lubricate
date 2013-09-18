package com.botest.utils.smsutils;

public class Message {
	//地址ID
	private String addID;
	//对方号码
	private String phone;
	//短信中心
	private String smsc;
	//消息状态
	private String state;
	//消息内容
	private String message;
	
	private String time;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSmsc() {
		return smsc;
	}
	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddID() {
		return addID;
	}
	public void setAddID(String addID) {
		this.addID = addID;
	}
}
