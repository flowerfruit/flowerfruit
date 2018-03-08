package com.mengxiang.producer;

import java.util.Date;

public class Message {
	public String username;
	public String order_num;
	public Date order_time;
	public String sex;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Message(String username, String order_num, Date order_time,
			String sex) {
		super();
		this.username = username;
		this.order_num = order_num;
		this.order_time = order_time;
		this.sex = sex;
	}
	public Message() {
		super();
	}
	
}
