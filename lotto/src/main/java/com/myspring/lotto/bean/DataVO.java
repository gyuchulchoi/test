package com.myspring.lotto.bean;

import java.sql.Date;
//dto

public class DataVO {
		
		private long winningAmount;
		private String drawDate;
		private int drawRound;
		private int winningNum1;
		private int winningNum2;
		private int winningNum3;
		private int winningNum4;
		private int winningNum5;
		private int winningNum6;
		private int bonusNum;
		private int winnerCount;
		private long winnerPrice;
		
		public int getWinnerCount() {
			return winnerCount;
		}
		public void setWinnerCount(int winnerCount) {
			this.winnerCount = winnerCount;
		}
		public long getWinnerPrice() {
			return winnerPrice;
		}
		public void setWinnerPrice(long winnerPrice) {
			this.winnerPrice = winnerPrice;
		}
		public long getWinningAmount() {
			return winningAmount;
		}
		public void setWinningAmount(long winningAmount) {
			this.winningAmount = winningAmount;
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
		public int getWinningNum1() {
			return winningNum1;
		}
		public void setWinningNum1(int winningNum1) {
			this.winningNum1 = winningNum1;
		}
		public int getWinningNum2() {
			return winningNum2;
		}
		public void setWinningNum2(int winningNum2) {
			this.winningNum2 = winningNum2;
		}
		public int getWinningNum3() {
			return winningNum3;
		}
		public void setWinningNum3(int winningNum3) {
			this.winningNum3 = winningNum3;
		}
		public int getWinningNum4() {
			return winningNum4;
		}
		public void setWinningNum4(int winningNum4) {
			this.winningNum4 = winningNum4;
		}
		public int getWinningNum5() {
			return winningNum5;
		}
		public void setWinningNum5(int winningNum5) {
			this.winningNum5 = winningNum5;
		}
		public int getWinningNum6() {
			return winningNum6;
		}
		public void setWinningNum6(int winningNum6) {
			this.winningNum6 = winningNum6;
		}
		public int getBonusNum() {
			return bonusNum;
		}
		public void setBonusNum(int bonusNum) {
			this.bonusNum = bonusNum;
		}
		
		
}
