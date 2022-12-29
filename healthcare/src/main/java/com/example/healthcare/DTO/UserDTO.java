package com.example.healthcare.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserDTO {
    private String t;

    private String m_h;
    private String m_m;
    private String m_e;

    private String f_h;
    private String f_m;
    private String f_e;


}
