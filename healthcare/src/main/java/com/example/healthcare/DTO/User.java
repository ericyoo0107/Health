package com.example.healthcare.DTO;



import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class User {

    private String t;

    private String m_h;
    private String m_m;
    private String m_e;

    private String f_h;
    private String f_m;
    private String f_e;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getM_h() {
        return m_h;
    }

    public void setM_h(String m_h) {
        this.m_h = m_h;
    }

    public String getM_m() {
        return m_m;
    }

    public void setM_m(String m_m) {
        this.m_m = m_m;
    }

    public String getM_e() {
        return m_e;
    }

    public void setM_e(String m_e) {
        this.m_e = m_e;
    }

    public String getF_h() {
        return f_h;
    }

    public void setF_h(String f_h) {
        this.f_h = f_h;
    }

    public String getF_m() {
        return f_m;
    }

    public void setF_m(String f_m) {
        this.f_m = f_m;
    }

    public String getF_e() {
        return f_e;
    }

    public void setF_e(String f_e) {
        this.f_e = f_e;
    }

    @Override
    public String toString() {
        return "User{" +
                "t='" + t + '\'' +
                ", m_h='" + m_h + '\'' +
                ", m_m='" + m_m + '\'' +
                ", m_e='" + m_e + '\'' +
                ", f_h='" + f_h + '\'' +
                ", f_m='" + f_m + '\'' +
                ", f_e='" + f_e + '\'' +
                '}';
    }


    @Builder
    public User(String t, String m_h, String m_m, String m_e,
                String f_h, String f_m, String f_e){
        setF_e(f_e); setF_h(f_h); setF_m(f_m);
        setT(t);
        setM_e(m_e); setM_h(m_h); setM_m(m_m);
    }
}
