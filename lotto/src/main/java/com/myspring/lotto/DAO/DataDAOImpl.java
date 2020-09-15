package com.myspring.lotto.DAO;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;



import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;



import com.myspring.lotto.bean.DataVO;
import com.myspring.lotto.bean.RankVO;
import com.myspring.lotto.bean.StoreVO;



@Repository
public class DataDAOImpl implements DataDAO {

	private static final String namespace="com.myspring.lotto.dataMapper";

	

	@Inject

	private SqlSession sqlSession;
	
	@Override
	public List<Map<String,String>> showDateRoundData(DataVO dataVO) throws Exception{
		return sqlSession.selectList(namespace+".showDateRoundData",dataVO);
	}
	@Override
	public List<Map<String,String>> showStoreRankData(StoreVO storeVO) throws Exception {
		return sqlSession.selectList(namespace + ".showStoreRankData", storeVO);
	}
	@Override
	public List<Map<String,String>> showRoundData(DataVO dataVO) throws Exception {
		return sqlSession.selectList(namespace+".showRoundData", dataVO);
	}
	@Override
	public List<Map<String,String>> showDateData(DataVO dataVO) throws Exception{
		return sqlSession.selectList(namespace+".showDateData", dataVO);
	}
	@Override
	public List<Map<String,String>> showAllData(DataVO dataVO) throws Exception{
		return sqlSession.selectList(namespace+".showAllData", dataVO);
	}
	@Override
	public List<Map<String,String>> showPageData(DataVO dataVO) throws Exception{
		return sqlSession.selectList(namespace+".showPageData", dataVO);
	}
	@Override
	public List<Map<String,String>> showStorePageData(StoreVO storeVO) throws Exception{
		return sqlSession.selectList(namespace + ".showStorePageData",storeVO);
	}
	@Override
	public List<Map<String,String>> showStoreOnlyPageData(StoreVO storeVO) throws Exception{
		return sqlSession.selectList(namespace+".showStoreOnlyPageData",storeVO);
	}
	@Override
	public List<Map<String,String>> showStoreData(StoreVO storeVO) throws Exception{
		return sqlSession.selectList(namespace+".showStoreData",storeVO);
	}
	@Override
	public List<Map<String,String>> showRankData(RankVO rankVO) throws Exception{
		return sqlSession.selectList(namespace + ".showRankData", rankVO);
	}
	@Override
	public List<Map<String,String>> showRoundRankData(RankVO rankVO) throws Exception {
		return sqlSession.selectList(namespace+".showRoundRankData", rankVO);
	}
	@Override
	public List<Map<String,String>> showDateRankData(RankVO rankVO) throws Exception {
		return sqlSession.selectList(namespace+".showDateRankData", rankVO);
	}
	@Override
	public List<Map<String,String>> recentData(DataVO dataVO) throws Exception{
		return sqlSession.selectList(namespace+".recentData", dataVO);
	}
	@Override
	public void insertStoreData(StoreVO testBean) {
		sqlSession.insert(namespace + ".insertStoreData", testBean);
	}
	@Override
	public void insertRankData(RankVO testBean) {
		sqlSession.insert(namespace + ".insertRankData",testBean);
	}
	@Override
	public void insertData(DataVO testBean) {
		
		sqlSession.insert(namespace + ".insertData", testBean);
	}
	@Override
	public void updateWinningAmount(DataVO testBean) {
		sqlSession.update(namespace + ".updateWinningAmount", testBean);
	}

}