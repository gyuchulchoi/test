package com.myspring.lotto.bean;

public class StoreVO {
	private String storeName;
	private String storeAddress;
	private int drawRound;
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public int getDrawRound() {
		return drawRound;
	}
	public void setDrawRound(int drawRound) {
		this.drawRound = drawRound;
	}
}
