package com.spring.boardweb.controller.api;


import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
 
import org.json.JSONArray;
import org.json.JSONObject;

public class KobisSearchMovieInfoApi {
	
	private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";
    private final String KEY = "1ed4c7c650525909f4840fee0d47b75b";
        
    //영화코드로 요청해서 영화정보를 담은 객체를 받아오는 메서드
    public JSONObject searchMovieInfo(String movieCode) {
    	System.out.println(movieCode);
    	JSONObject movieInfo = new JSONObject();
    	
        // 변수 설정 - 요청(Request) 인터페이스 Map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key", KEY);                        		
        paramMap.put("movieCd", movieCode);  
                                
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
	        JSONObject movieInfoResult = responseBody.getJSONObject("movieInfoResult");
	        movieInfo = movieInfoResult.getJSONObject("movieInfo");
            
            
        	return movieInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return movieInfo;	
    }
    
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
    
}

