package com.myspring.lotto;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.myspring.lotto.DAO.DataDAO;
import com.myspring.lotto.bean.DataVO;
import com.myspring.lotto.bean.RankVO;
import com.myspring.lotto.bean.StoreVO;
import com.myspring.lotto.service.DataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private DataService service;
	@Inject
	private DataDAO dao;
	
	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch(MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch(IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try(BufferedReader lineReader = new BufferedReader(streamReader)) {
			
			StringBuilder responseBody = new StringBuilder();
			String line;
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}	

			return responseBody.toString();
		} 
		catch(IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
		}
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
			HttpURLConnection con = connect(apiUrl);
			try {
					con.setRequestMethod("GET");
					for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
						con.setRequestProperty(header.getKey(), header.getValue());
					}

					int responseCode = con.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
							return readBody(con.getInputStream());
					} 
					else { // 에러 발생
							return readBody(con.getErrorStream());
					}
				} 
			catch (IOException e) {
					throw new RuntimeException("API 요청과 응답 실패", e);
			} 
			finally {
					con.disconnect();
			}
	}
	@SuppressWarnings({"unchecked"})
	public static JSONObject convertMapToJson(Map<String,String> map) {
			
			JSONObject json = new JSONObject();
			JSONArray JsonArray1 = new JSONArray();
			Object num1=0;
			Object num2=0; 
			Object num3=0; 
			Object num4=0; 
			Object num5=0; 
			Object num6=0;
			Object num7=0;
			Object num8=0;
			Object num9=0;
			for(Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				System.out.println("키 "+key);
			
				if(key.equals("winningNum1"))
					continue;
				else if(key.equals("winningNum2"))
					continue;
				else if(key.equals("winningNum3"))
					continue;
				else if(key.equals("winningNum4"))
					continue;
				else if(key.equals("winningNum5"))
					continue;
				else if(key.equals("winningNum6"))
					continue;
				else if(key.equals("bonusNum"))
				{
					continue;
				}
				
				else
					json.put(key,value);
			}
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(key.equals("winningNum1"))
				num1 = value;
			else if(key.equals("winningNum2"))
				num2 = value;
			else if(key.equals("winningNum3"))
				num3 = value;
			else if(key.equals("winningNum4"))
				num4 = value;
			else if(key.equals("winningNum5"))
				num5 = value;
			else if(key.equals("winningNum6"))
				num6 = value;
			else if(key.equals("bonusNum"))
			{
				num7 = value;
			}
		}
			JsonArray1.add(num1);
			JsonArray1.add(num2);
			JsonArray1.add(num3);
			JsonArray1.add(num4);
			JsonArray1.add(num5);
			JsonArray1.add(num6);
			JsonArray1.add(num7);
			json.put("winningArray", JsonArray1);
			return json;
	}
	@SuppressWarnings({"unchecked"})
	public static JSONObject convertToJson(Map<String,String> map) {
		JSONObject json = new JSONObject();
		for(Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			json.put(key,value);
		}
		return json;
	}
	@ResponseBody
	 @RequestMapping(value = "/lottos", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String showSelectData(HttpServletRequest request) throws Exception {
		if(request.getParameter("page") != null)
		{
			String page = request.getParameter("page");
			int integerPage=Integer.parseInt(page);
			JSONArray jsonArray = new JSONArray();
			List<Map<String,String>> dataVO;
			if(integerPage ==1)
				dataVO = service.showPageData(0);
			else
				dataVO = service.showPageData((integerPage*10)-10);
			for(Map<String, String> map : dataVO) {
					jsonArray.add(convertMapToJson(map));
			}
			return jsonArray.toString();
		}
		else {
			String drawDate = request.getParameter("drawDate");
			String drawRound = request.getParameter("drawRound");
			List<Map<String,String>> dataVO;
			if(drawDate != null && drawRound != null)
				dataVO = service.showDateRoundData(drawDate, Integer.parseInt(request.getParameter("drawRound")));
			else if(drawRound == null && drawDate !=null)
				dataVO = service.showDateData(drawDate);
			else if(drawDate == null && drawRound != null)
				dataVO = service.showRoundData(Integer.parseInt(request.getParameter("drawRound")));
			else
				dataVO=service.showAllData();
			JSONArray jsonArray = new JSONArray();
		
			for(Map<String, String> map : dataVO) {
				jsonArray.add(convertMapToJson(map));
			}
			if(jsonArray.size() == 1)
				return jsonArray.get(0).toString();
			return jsonArray.toString();
		}
	}
	
	@ResponseBody
	 @RequestMapping(value = "/lottos/randomNum", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String randomNum(HttpServletRequest request) throws Exception {
		try{
	        //파일 객체 생성
	        File file = new File("C:/Users/82106/randomNumber.txt");
	         //입력 스트림 생성
	         FileReader file_reader = new FileReader(file);
	         int cur = 0;
	         String s ="";
	         while((cur = file_reader.read()) != -1){
	            System.out.print((char)cur);
	            s += (char)cur;
	         }
	         StringBuilder builder = new StringBuilder(s);
	         //StringBuilder builder = new StringBuilder(s);
	         //String replaceStr = new StringBuilder(builder.toString().replace("\"", "")).toString();
	         //String str = new StringBuilder(replaceStr).insert(2, "\"").insert(17, "\"").insert(47, "\"").insert(96, "\"").toString();
	         for(int i=0; i<s.length(); i++) {
	        	 if(i!=0 && s.charAt(i) == '[') {
	        		 builder.setCharAt(i-1, ' ');
	        	 }
	        	 else if(i!=s.length()-1 && s.charAt(i) == ']')
	        	 {
	        		 builder.setCharAt(i+1, ' ');
	        	 }
	        	 
	         }
	         
	         s = builder.toString().replaceAll(" ", "");
	         
	         //System.out.println(str);
	         //2, 17 49
	         //96
	         
	         file_reader.close();
	         return "["+s.trim()+ "]";
	        }catch (FileNotFoundException e) {
	            e.getStackTrace();
	        }catch(IOException e){
	            e.getStackTrace();
	        }
		//‪
			return "";
	}
	
	@ResponseBody
	 @RequestMapping(value = "lottos/ranks", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String showRankData(HttpServletRequest request) throws Exception {
		String drawDate = request.getParameter("drawDate");
		String drawRound = request.getParameter("drawRound");
		List<Map<String,String>> rankVO;
		if(drawDate == null && drawRound != null) 
			rankVO = service.showRoundRankData(Integer.parseInt(drawRound));
		else if(drawDate != null && drawRound == null)
			rankVO = service.showDateRankData(drawDate);
		else
			rankVO = service.showRankData();
		JSONArray jsonArray = new JSONArray();
		for(Map<String, String> map : rankVO) {
			jsonArray.add(convertToJson(map));
		}
		if(jsonArray.size() == 1)
			return jsonArray.get(0).toString();
		return jsonArray.toString();
		
    }
	@ResponseBody
	 @RequestMapping(value = "/lottos/recent", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String recentData(HttpServletRequest request) throws Exception {
			List<Map<String,String>> dataVO;
			dataVO = service.showRecentData();
			JSONArray jsonArray = new JSONArray();
			for(Map<String, String> map : dataVO) {
				jsonArray.add(convertMapToJson(map));
			}
			if(jsonArray.size() == 1)
				return jsonArray.get(0).toString();
			return jsonArray.toString();
	}
	
	@ResponseBody
	 @RequestMapping(value = "/shopsRank", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String storeRank(HttpServletRequest request) throws Exception {
		JSONArray jsonArray = new JSONArray();
		List<Map<String,String>> dataVO;
		dataVO = service.showStoreRankData();
		for(Map<String, String> map : dataVO) {
			jsonArray.add(convertToJson(map));		
		}
		return jsonArray.toString();
	}
	
	@ResponseBody
	 @RequestMapping(value = "/shops", method = RequestMethod.GET, produces="text/plain;charset=UTF-8") 
	 public String store(HttpServletRequest request) throws Exception {

			String storeAddress = request.getParameter("storeAddress");
			if(request.getParameter("page") != null && storeAddress != null) {
				String page = request.getParameter("page");
				int integerPage=Integer.parseInt(page);
				JSONArray jsonArray = new JSONArray();
				List<Map<String,String>> dataVO;
				dataVO = service.showStorePageData(integerPage,storeAddress);
				for(Map<String, String> map : dataVO) {
					jsonArray.add(convertToJson(map));		
				}
				
				return jsonArray.toString();
			}
			else if(request.getParameter("page") != null && storeAddress == null) {
				String page = request.getParameter("page");
				int integerPage=Integer.parseInt(page);
				JSONArray jsonArray = new JSONArray();
				List<Map<String,String>> dataVO;
				dataVO = service.showStoreOnlyPageData(integerPage);
				for(Map<String, String> map : dataVO) {
					jsonArray.add(convertToJson(map));		
				}
				
				return jsonArray.toString();
			}
			else {
				List<Map<String,String>> dataVO;
				dataVO = service.showStoreData(storeAddress);
				JSONArray jsonArray = new JSONArray();
				for(Map<String, String> map : dataVO) {
					jsonArray.add(convertToJson(map));
				}
				if(jsonArray.size() == 1)
					return jsonArray.get(0).toString();
				return jsonArray.toString();
			}
	}
	@ResponseBody
	 @RequestMapping(value = "/showAllData.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String showAllData(HttpServletRequest request) throws Exception {
			List<Map<String,String>> dataVO = service.showAllData();
			JSONArray jsonArray = new JSONArray();
			for(Map<String,String> map : dataVO) {
				jsonArray.add(convertMapToJson(map));
			}
			return jsonArray.toString();
	}
	
	@ResponseBody
	 @RequestMapping(value = "/lotto.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String lotto(HttpServletRequest request) throws ParseException {
			JSONArray dataArray = new JSONArray();
			JSONObject jsonData = new JSONObject();
			//String s = request.getParameter("drawRound");
			//int i = Integer.parseInt(s);
			for(int i=1; i<929; i++) {
			String apiURL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + i;
			Map<String, String> requestHeaders = new HashMap<>();
			String responseBody = get(apiURL,requestHeaders);
			JSONParser jsonParse = new JSONParser();
			
			JSONObject jsonObj = (JSONObject)jsonParse.parse(responseBody);
		
			String drawDate = jsonObj.get("drwNoDate").toString().replace("-", ""); //추첨일
			int drawRound = Integer.parseInt(jsonObj.get("drwNo").toString()); //회차
			long winningAmount = Long.parseLong(jsonObj.get("totSellamnt").toString());
			int winningNum1 = Integer.parseInt(jsonObj.get("drwtNo1").toString()); // 1
			int winningNum2 = Integer.parseInt(jsonObj.get("drwtNo2").toString()); // 2
			int winningNum3 = Integer.parseInt(jsonObj.get("drwtNo3").toString()); // 3
			int winningNum4 = Integer.parseInt(jsonObj.get("drwtNo4").toString()); // 4
			int winningNum5 = Integer.parseInt(jsonObj.get("drwtNo5").toString()); // 5
			int winningNum6 = Integer.parseInt(jsonObj.get("drwtNo6").toString()); // 6
			int bonusNum = Integer.parseInt(jsonObj.get("bnusNo").toString()); // 보너스
			long winnerPrice = Long.parseLong(jsonObj.get("firstWinamnt").toString()); // 1등 당첨금
			int winnerCount = Integer.parseInt(jsonObj.get("firstPrzwnerCo").toString());
			
			DataVO dataVO = new DataVO();
			
			dataVO.setWinningAmount(winningAmount);
			dataVO.setDrawDate(drawDate);
			dataVO.setDrawRound(drawRound);
			dataVO.setWinningNum1(winningNum1);
			dataVO.setWinningNum2(winningNum2);
			dataVO.setWinningNum3(winningNum3);
			dataVO.setWinningNum4(winningNum4);
			dataVO.setWinningNum5(winningNum5);
			dataVO.setWinningNum6(winningNum6);
			dataVO.setBonusNum(bonusNum);
			dataVO.setWinnerCount(winnerCount);
			dataVO.setWinnerPrice(winnerPrice);
			dao.insertData(dataVO);
			}
			return "";
	}
	
	@ResponseBody
	 @RequestMapping(value = "/shopsInsert", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String lottoStore(HttpServletRequest request) throws IOException {
			JSONArray dataArray = new JSONArray();
			//String s = request.getParameter("drawRound");
			//int i = Integer.parseInt(s);
			JSONObject jsonData = new JSONObject();
			for(int i=0; i<814; i++) {	
			Document doc = Jsoup.connect("https://pyony.com/lotto/win-stores/?page="+i).get();
			Elements ele = doc.select("body");
			Elements ele2 = ele.select("div.container");
			Elements ele3 = ele2.select("div.mt-3");
			Elements ele4 = ele3.select("table");
			Elements ele5 = ele4.select("tbody");
			Elements ele6 = ele5.select("tr");
			Elements ele7 = ele6.select("th");
			Elements ele8 = ele6.select("td");
			StoreVO storeVO = new StoreVO();
			for(int j=9; j<ele6.size(); j++) {
					jsonData.put("drawRound",Integer.parseInt(ele6.eq(j).select("th").text()));
					jsonData.put("storeName",ele6.eq(j).select("td").eq(0).text());
					jsonData.put("storeAddress", ele6.eq(j).select("td").eq(2).text());
					storeVO.setDrawRound(Integer.parseInt(ele6.eq(j).select("th").text()));
					storeVO.setStoreAddress(ele6.eq(j).select("td").eq(2).text());
					storeVO.setStoreName(ele6.eq(j).select("td").eq(0).text());
					dao.insertStoreData(storeVO);
					dataArray.add(jsonData);
			}
			}				
		return dataArray.toString();
	 }
	
	@ResponseBody
	 @RequestMapping(value = "lottos/insertRank", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	 public String InsertWinningAmount(HttpServletRequest request) throws IOException {
			//String drawRound= request.getParameter("drawRound");
			//String drawDate = request.getParameter("drawDate");
			RankVO rankVO = new RankVO();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			//String z = request.getParameter("drawRound");
			//int k = Integer.parseInt(z);
			for(int k=1;k<929; k++) {
			Document doc = Jsoup.connect("https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo="+k).get();
			Elements el = doc.select("div.win_result");
			Elements strong = el.select("h4");
			
			Elements el2 = el.select("p").eq(0);
			String drawDate = el2.text().replace("(","").replace(")","").replace("년", "").replace("월", "").replace("일", "").replace("추첨", "").replace(" ", "");
			
			Elements ele = doc.select("table");
			Elements ele2 = ele.select("tbody");
			Elements ele3 = ele2.select("tr");
			DataVO dataVO = new DataVO();
			String drawRound= strong.text().replace(" ", "").replace("회", "").replace("당첨결과","");
			//System.out.println(drawRound);
			//Long winnerAmountData;
			for(int i=0; i<5; i++) {
				jsonObject = new JSONObject();
				for(int j=1; j<4; j++) {
					String s = ele3.eq(i).select("td").eq(j).text();
					if(j==1) {
						jsonObject.put("winnerAmount", s);
						rankVO.setWinnerAmount(s);
					}
					else if(j==2) {
						jsonObject.put("winnerCount",s);
						rankVO.setWinnerCount(s);
					}
					else if(j==3) {
						jsonObject.put("winnerAmountPerGame", s);
						rankVO.setWinnerAmountPerGame(s);
					}
				}
				jsonObject.put("drawDate",drawDate);
				jsonObject.put("drawRound", Integer.parseInt(drawRound));
				
				rankVO.setDrawDate(drawDate);
				rankVO.setDrawRound(Integer.parseInt(drawRound));
				dao.insertRankData(rankVO);
				jsonArray.add(jsonObject);
			}
			}
			if(jsonArray.size() == 1)
				return jsonArray.get(0).toString();
			return jsonArray.toString();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
}
