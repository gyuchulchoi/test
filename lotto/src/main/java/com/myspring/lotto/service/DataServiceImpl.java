package com.myspring.lotto.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;



import org.springframework.stereotype.Service;



import com.myspring.lotto.DAO.DataDAO;

import com.myspring.lotto.bean.DataVO;
import com.myspring.lotto.bean.RankVO;
import com.myspring.lotto.bean.StoreVO;



@Service

public class DataServiceImpl implements DataService {

	@Inject

	private DataDAO dao;
	private DataVO dataVO;
	private StoreVO storeVO;
	private RankVO rankVO;
	@Override
	public List<Map<String,String>> showStoreRankData() throws Exception {
		//storeVO = new StoreVO();
		return dao.showStoreRankData(storeVO);
		
	}
	@Override
	public List<Map<String,String>> showDateRoundData(String drawDate, int drawRound) throws Exception {
		dataVO = new DataVO();
		dataVO.setDrawRound(drawRound);
		dataVO.setDrawDate(drawDate);
		return dao.showRoundData(dataVO);
	}
	@Override
	public List<Map<String,String>> showRecentData() throws Exception {
		return dao.recentData(dataVO);
	}
	@Override
	public List<Map<String,String>> showStoreData(String storeAddress) throws Exception {
		storeVO = new StoreVO();
		storeVO.setStoreAddress(storeAddress);
		return dao.showStoreData(storeVO);
	}
	@Override
	public List<Map<String,String>> showStoreOnlyPageData(int drawRound) throws Exception{
		storeVO = new StoreVO();
		storeVO.setDrawRound(drawRound);
		return dao.showStoreOnlyPageData(storeVO);
	}
	@Override
	public List<Map<String,String>> showRoundRankData (int drawRound) throws Exception {
		rankVO = new RankVO();
		rankVO.setDrawRound(drawRound);
		return dao.showRoundRankData(rankVO);
	}
	@Override
	public List<Map<String,String>> showDateRankData (String drawDate) throws Exception {
		rankVO = new RankVO();
		rankVO.setDrawDate(drawDate);
		return dao.showDateRankData(rankVO);
	}
	@Override
	public List<Map<String,String>> showRankData() throws Exception {
		return dao.showRankData(rankVO);
	}
	@Override
	public List<Map<String,String>> showPageData(int drawRound) throws Exception { 
		dataVO = new DataVO();
		dataVO.setDrawRound(drawRound);
		return dao.showPageData(dataVO);
	}
	
	@Override
	public List<Map<String,String>> showStorePageData(int drawRound, String storeAddress) throws Exception{
		storeVO = new StoreVO();
		storeVO.setDrawRound(drawRound);
		storeVO.setStoreAddress(storeAddress);
		return dao.showStorePageData(storeVO);
	}
	@Override
	public List<Map<String,String>> showAllData() throws Exception {
		// TODO Auto-generated method stub
		return dao.showAllData(dataVO);
	}
	
	@Override
	public List<Map<String,String>> showRoundData(int drawRound) throws Exception
	{
		dataVO = new DataVO();
		dataVO.setDrawRound(drawRound);
		return dao.showRoundData(dataVO);
	}
	@Override
	public List<Map<String,String>> showDateData (String drawDate) throws Exception
	{
		dataVO = new DataVO();
		dataVO.setDrawDate(drawDate);
		return dao.showDateData(dataVO);
	}
}