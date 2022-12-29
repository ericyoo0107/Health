package com.example.healthcare.Controller;

import com.example.healthcare.DTO.User;
import com.example.healthcare.DTO.UserDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @PostMapping("/healthCare")
    public List<User> callAPI(@RequestBody String user) {
        StringBuffer result = new StringBuffer();

        try {
            String urlstr = "https://api.odcloud.kr/api/15051012/v1/uddi:4ee0c7ac-82f5-4119-9c8c-b542165acc67?page=1&perPage=26&serviceKey=mwSpkJOt%2BVCEr%2BXvSVXIRw%2Bu1CqsVaONuyFXZPWiItdZAVYsB9Y02dky%2B%2FYJvw4vbQYBJgRFF9JkJY2mzF1ROQ%3D%3D";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String jsonData = result.toString();

        List<User> userList = new ArrayList<>();
        try {
            JSONObject jObj;

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);

            /*JSONObject parseResponse = (JSONObject) jsonObject.get("Download");*/

            JSONArray jsonArray = (JSONArray) jsonObject.get("data");

            for (Object o : jsonArray) {
                JSONObject data = (JSONObject) o;
                userList.add(makeLocationDTO(data));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




        return userList;
    }

    private User makeLocationDTO(JSONObject data) {
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
