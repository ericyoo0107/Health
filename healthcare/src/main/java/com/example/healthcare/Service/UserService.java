package com.example.healthcare.Service;

import java.util.List;

import com.example.healthcare.DTO.User;
import com.example.healthcare.DTO.UserDTO;
import com.example.healthcare.DTO.UserRequestDTO;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private UserRequestDTO userRequestDTO;
	private String fin = "";

	public String returnValue(List<User> userList) {
		for (User user : userList) {
			//userList의 user를 뽑아서 사용자의 성별/초중고에 따라 string에 넣고 바디에 넣어 return하는 함수
			if (userRequestDTO.getT().equals(user.getT())) {
				if (userRequestDTO.getGs().equals("m_h")) {
					fin = user.getM_h();
				} else if (userRequestDTO.getGs().equals("m_m")) {
					fin = user.getM_m();
				} else if (userRequestDTO.getGs().equals("m_e")) {
					fin = user.getM_e();
				} else if (userRequestDTO.getGs().equals("f_h")) {
					fin = user.getF_h();
				} else if (userRequestDTO.getGs().equals("f_m")) {
					fin = user.getF_m();
				} else {
					fin = user.getF_e();
				}
			}
		}
		return fin;
	}

	public User makeLocationDTO(JSONObject data) { //json 형식에서 값을 뽑아서 user 객체를 만들어주는 함수
		User user=User.builder().
			t((String) data.get("지표")).
			m_h((String) data.get("남자_고")).
			m_m((String) data.get("남자_중")).
			m_e((String) data.get("남자_초")).
			f_h((String) data.get("여자_고")).
			f_m((String) data.get("여자_중")).
			f_e((String) data.get("여자_초")).
			build();
		return user;
	}
}
