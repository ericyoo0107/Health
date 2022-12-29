package com.example.healthcare.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserDTO {
    private String t;

    private float m_h;
    private float m_m;
    private float m_e;

    private float f_h;
    private float f_m;
    private float f_e;


}
