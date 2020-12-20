package test.kh.swing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public JSONObject connectionUrlToJson(String turn) { // turn 은 회차
		JSONObject jObj = null;
		try {
			// https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=112
			URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + turn); // URL에
																											// throws가
																											// 걸려있음
			// 위에 url도 https이기 때문에//https는 security라는 뜻, 데이터 수집

			HttpsURLConnection conn = null;
			HostnameVerifier hnv = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					// return false;
					return true; // 너 인증된거 맞니? false를 true로 바꿔줌. like 로봇이 아닙니다.
				}
			};
			HttpsURLConnection.setDefaultHostnameVerifier(hnv);
			conn = (HttpsURLConnection) url.openConnection(); // 여기까지 접속
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 가용 범위만큼 버퍼에서 읽어서
																									// br에 가져옴 --> 가장
																									// 안전한 방법
			//System.out.println(br.readLine());
			String iLine = br.readLine();  //한줄만 읽으면 될 때 // 여러줄일때는 for문 돌려야 함
			JSONParser ps = new JSONParser();
			jObj = (JSONObject) ps.parse(iLine);
			
		} catch (MalformedURLException e) {
			System.out.println("접속 실패");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObj;
	}
}




























