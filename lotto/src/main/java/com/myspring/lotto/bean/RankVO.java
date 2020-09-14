package com.myspring.lotto.bean;

public class RankVO {
	String winnerAmount;
	String winnerCount;
	String winnerAmountPerGame;
	String drawDate;
	int drawRound;
	public String getWinnerAmount() {
		return winnerAmount;
	}
	public void setWinnerAmount(String winnerAmount) {
		this.winnerAmount = winnerAmount;
	}
	public String getWinnerCount() {
		return winnerCount;
	}
	public void setWinnerCount(String winnerCount) {
		this.winnerCount = winnerCount;
	}
	public String getWinnerAmountPerGame() {
		return winnerAmountPerGame;
	}
	public void setWinnerAmountPerGame(String winnerAmountPerGame) {
		this.winnerAmountPerGame = winnerAmountPerGame;
	}
	public String getDrawDate() {
		return drawDate;
	}
	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}
	public int getDrawRound() {
		return drawRound;
	}
	public void setDrawRound(int drawRound) {
		this.drawRound = drawRound;
	}
}
