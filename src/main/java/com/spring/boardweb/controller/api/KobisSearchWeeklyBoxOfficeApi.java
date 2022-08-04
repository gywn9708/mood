package com.spring.boardweb.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
 
 
//특정 일자 상영작들의 박스오피스 정보를 영화구분(다양성영화,상업영화), 한국/외국 구분, 상영지역 등의 조건을 통해 조회
//조회하고 싶은 날짜targetDt로 요청해서 박스오피스 순위rank 받아오기
public class KobisSearchWeeklyBoxOfficeApi {
   
    private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
    private final String KEY = "1ed4c7c650525909f4840fee0d47b75b";
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");
 
    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
 
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
 
        return sb.toString();
    }
 
    // API요청
    public void requestAPI() {
        // 변수설정
        //   - 하루전 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
 
        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , KEY);                        		// 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        paramMap.put("weekGb" 		, "0");                             
        paramMap.put("repNationCd"  , "F");                             // K:한국영화, F:외국영화, Default:전체
 
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);
 
            // 박스오피스 목록 출력
            JSONArray weeklyBoxOfficeList = boxOfficeResult.getJSONArray("weeklyBoxOfficeList");
            Iterator<Object> iter = weeklyBoxOfficeList.iterator();
            while(iter.hasNext()) {
                JSONObject boxOffice = (JSONObject) iter.next();
                System.out.printf("  %s - %s\n", boxOffice.get("rnum"), boxOffice.get("movieNm"));
                System.out.println("영화코드 : " + boxOffice.get("movieCd") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        // API 객체 생성
    	KobisSearchWeeklyBoxOfficeApi api = new KobisSearchWeeklyBoxOfficeApi();
 
        // API 요청
        api.requestAPI();
    }
 
	
	
}

