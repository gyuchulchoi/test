package com.myspring.lotto.DAO;

import java.util.List;
import java.util.Map;

import com.myspring.lotto.bean.DataVO;
import com.myspring.lotto.bean.StoreVO;
import com.myspring.lotto.bean.RankVO;


public interface DataDAO {
	public List<Map<String,String>> recentData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showAllData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showRoundData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showDateData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showDateRoundData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showStoreData(StoreVO storeVO) throws Exception;
	public List<Map<String,String>> showPageData(DataVO dataVO) throws Exception;
	public List<Map<String,String>> showStorePageData(StoreVO storeVO) throws Exception;
	public List<Map<String,String>> showStoreOnlyPageData(StoreVO storeVO) throws Exception;
	public List<Map<String,String>> showRoundRankData(RankVO rankVO) throws Exception;
	public List<Map<String,String>> showDateRankData(RankVO rankVO) throws Exception;
	public List<Map<String,String>> showRankData(RankVO rankVO) throws Exception;
	public List<Map<String,String>> showStoreRankData(StoreVO storeVO) throws Exception;
	public void insertStoreData(StoreVO testBean);
	public void insertData(DataVO testBean);
	public void updateWinningAmount(DataVO testBean);
	public void insertRankData(RankVO testBean);
	
}