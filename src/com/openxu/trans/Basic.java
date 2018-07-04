package com.openxu.trans;

import java.util.List;

public class Basic {

//    private String us-phonetic; //英式发音
    private String phonetic;  //发音
//    private String uk-phonetic//美式发音
    private List<String> explains;

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }

    @Override
    public String toString() {
        return "Basic{" +
                "phonetic='" + phonetic + '\'' +
                ", explains=" + explains +
                '}';
    }
}
