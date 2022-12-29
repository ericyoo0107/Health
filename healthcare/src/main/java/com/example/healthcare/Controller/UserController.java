package com.example.healthcare.Controller;

import com.example.healthcare.DTO.User;
import com.example.healthcare.DTO.UserDTO;
import com.example.healthcare.DTO.UserRequestDTO;
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
    @PostMapping("/healthCare")
    public String callAPI(@RequestBody UserRequestDTO userRequestDTO) {
        StringBuffer result = new StringBuffer();
        String fin="";

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


        for (User user: userList){
            if(userRequestDTO.getT().equals(user.getT())){
                if(userRequestDTO.getGs().equals("m_h")){
                    fin = user.getM_h();
                }
                else if(userRequestDTO.getGs().equals("m_m")){
                    fin = user.getM_m();
                }
                else if(userRequestDTO.getGs().equals("m_e")){
                    fin = user.getM_e();
                }
                else if(userRequestDTO.getGs().equals("f_h")){
                    fin = user.getF_h();
                }
                else if(userRequestDTO.getGs().equals("f_m")){
                    fin = user.getF_m();
                }
                else{
                    fin = user.getF_e();
                }
            }
        }

        return fin;

       /*return userList;*/
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
