package com.example.healthcare.Controller;

import com.example.healthcare.DTO.User;
import com.example.healthcare.DTO.UserDTO;
import com.example.healthcare.DTO.UserRequestDTO;
import com.example.healthcare.Service.UserService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	private UserService userService;

	@PostMapping("/healthCare")
	public String callAPI(@RequestBody UserRequestDTO userRequestDTO) {
		StringBuffer result = new StringBuffer();
		String fin = "";

		try {
			String urlstr = "https://api.odcloud.kr/api/15051012/v1/uddi:4ee0c7ac-82f5-4119-9c8c-b542165acc67?page=1&perPage=26&serviceKey=mwSpkJOt%2BVCEr%2BXvSVXIRw%2Bu1CqsVaONuyFXZPWiItdZAVYsB9Y02dky%2B%2FYJvw4vbQYBJgRFF9JkJY2mzF1ROQ%3D%3D";
			URL url = new URL(urlstr);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

			String returnLine;
			while ((returnLine = br.readLine()) != null) {
				result.append(returnLine + "\n");
			} //openApi에 있는 json 값들을 result에 받아옴
			urlConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String jsonData = result.toString(); //StringBuffer에 있던 json 데이터를 사용하기 편하게 string으로 만듬

		List<User> userList = new ArrayList<>();
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonData);
			//string 형식으로 저장된 jsonData를 객체로 생성
			/*JSONObject parseResponse = (JSONObject) jsonObject.get("Download");*/

			JSONArray jsonArray = (JSONArray)jsonObject.get("data");
			//jsonObject 객체를 객체 array에 넣음

			for (Object o : jsonArray) {
				JSONObject data = (JSONObject)o;
				userList.add(userService.makeLocationDTO(data));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		fin = userService.returnValue(userList);
		if(fin==null){
			fin="0";
		}
		return fin;
		/*return userList;*/
	}
}
