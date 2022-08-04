package com.spring.boardweb.controller.api;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
 
public class KobisSearchMovieListApi {
	
    private final String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
    private final String KEY = "1ed4c7c650525909f4840fee0d47b75b";
       
    //영화이름으로 요청해서 영화코드를 받아오는 메서드
    public String searchMovieCode(String movieTitle) {
    	System.out.println(movieTitle);
    	String encodedMovieTitle = null;
    	String movieCode = null;
    	
    	// 변수 설정 - movieTitle을 UTF-8 인코딩
    	try {
    		encodedMovieTitle = URLEncoder.encode(movieTitle, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
    	
        // 변수 설정 - 요청(Request) 인터페이스 Map
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key", KEY);                        		
        paramMap.put("movieNm", encodedMovieTitle);  
                                
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
	        JSONObject movieListResult = responseBody.getJSONObject("movieListResult");
            JSONArray movieList = movieListResult.getJSONArray("movieList");
            
            Iterator<Object> iter = movieList.iterator();
            while(iter.hasNext()) {
                JSONObject movie = (JSONObject) iter.next();
                
                if(movie.get("movieNm").equals(movieTitle) ) {
                	movieCode = (String)movie.get("movieCd");
                	return movieCode;
                }
            }  
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return movieCode;
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
    
  public static void main(String[] args) {
  // API 객체 생성
	  KobisSearchMovieListApi api = new KobisSearchMovieListApi();
  // API 요청 테스트
	  System.out.println(api.searchMovieCode("사랑할 땐 누구나 최악이 된다"));
	  System.out.println(api.searchMovieCode("내가 누워있을 때"));	  
  }
}

