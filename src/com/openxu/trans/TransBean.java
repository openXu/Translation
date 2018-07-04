package com.openxu.trans;

import java.util.List;

public class TransBean {


    private List<String> translation; //"translation":["帮助"]   有道翻译
    private Basic basic;   // 有道词典-基本词典
    private List<Web> web;    // 有道词典-网络释义

    @Override
    public String toString() {

        String basicExplains = "";
        String webExplains = "";
        try {
            for (String str : basic.getExplains()) {
                basicExplains+=(str+"\n");
            }
        }catch (Exception e){}
        try {
            for (Web w : web) {
                String wv = w.getKey()+"\n";
                for(String v:w.getValue()) {
                    wv += (v+"，");
                }
                webExplains+=(wv+"\n");
            }
        }catch (Exception e){}

        return ":"+translation+"\n"+
        "发音："+basic.getPhonetic()+"\n\n"+
                basicExplains+"\n"+
                "网络释义:"+"\n"+webExplains;

    }


    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public List<Web> getWeb() {
        return web;
    }

    public void setWeb(List<Web> web) {
        this.web = web;
    }
}
