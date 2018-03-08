package com.mengxiang.producer;

public class Behaviour {
	public String uuid;
	public String mac;
	public String uid;
	public String ip;
	public String dateStamp;
	public BehaviourData data;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDateStamp() {
		return dateStamp;
	}
	public void setDateStamp(String dateStamp) {
		this.dateStamp = dateStamp;
	}
	public BehaviourData getData() {
		return data;
	}
	public void setData(BehaviourData data) {
		this.data = data;
	}
	public Behaviour(String uuid, String mac, String uid, String ip,
			String dateStamp, BehaviourData data) {
		super();
		this.uuid = uuid;
		this.mac = mac;
		this.uid = uid;
		this.ip = ip;
		this.dateStamp = dateStamp;
		this.data = data;
	}
	public Behaviour() {
		super();
	}
	
	
	
	
}
