package com.mengxiang.producer;

public class BehaviourData {
	public String acive;
	public Message message;
	public String getAcive() {
		return acive;
	}
	public void setAcive(String acive) {
		this.acive = acive;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public BehaviourData(String acive, Message message) {
		super();
		this.acive = acive;
		this.message = message;
	}
	public BehaviourData() {
		super();
	}
	
	
}
