package com.openxu.trans;

import com.google.gson.Gson;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.sun.deploy.net.HttpUtils;
import gherkin.deps.com.google.gson.JsonObject;
import org.apache.http.util.TextUtils;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class TranslateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {

        /**① 获取选中的单词*/
        final Editor mEditor = event.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor)
            return;
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText))
            return;

        /**② 调用API 翻译*/
        String baseUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=Skykai521&key=977124034&type=data&doctype=json&version=1.1&q=";
        HttpUtil.doGetAsyn(baseUrl+selectedText, new HttpUtil.CallBack() {
            @Override
            public void onRequestComplete(boolean ok, String msg, String result) {
                if(ok){
                    Gson gson = new Gson();
                    TransBean transResult = gson.fromJson(result,TransBean.class);
                    System.out.println(transResult);
                    showPopupBalloon(mEditor,selectedText+transResult.toString());
                }else{
                    showPopupBalloon(mEditor,msg);
                }
            }
        });
    }

    private void showPopupBalloon(final Editor editor, final String result) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                        .setFadeoutTime(5000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}
