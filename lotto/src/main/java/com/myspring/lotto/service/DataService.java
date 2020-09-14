package com.myspring.lotto.service;

import java.util.List;
import java.util.Map;

import com.myspring.lotto.bean.DataVO;
import com.myspring.lotto.bean.StoreVO;

public interface DataService {
		public List<Map<String,String>> showAllData() throws Exception;
		public List<Map<String,String>> showRoundData(int drawRound) throws Exception;
		public List<Map<String,String>> showDateData (String drawDate) throws Exception;
		public List<Map<String,String>> showDateRoundData(String drawDate, int drawRound) throws Exception;
		public List<Map<String,String>> showRecentData() throws Exception;
		public List<Map<String,String>> showStoreData(String storeAddress) throws Exception;
		public List<Map<String,String>> showStorePageData(int drawRound, String storeAddress) throws Exception;
		public List<Map<String,String>> showStoreOnlyPageData(int drawRound) throws Exception;
		public List<Map<String,String>> showPageData (int drawRound) throws Exception;
		public List<Map<String,String>> showRoundRankData (int drawRound) throws Exception;
		public List<Map<String,String>> showDateRankData (String drawDate) throws Exception;
		public List<Map<String,String>> showRankData() throws Exception;
		public List<Map<String,String>> showStoreRankData() throws Exception;
		
}
