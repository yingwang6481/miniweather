package cn.edu.pku.wangying.bean;

import android.util.Log;

/**
 * Created by wangying on 2016/12/16.
 */
public class Future {
    public String date="N/A";
    public String high="N/A";
    public String low="N/A";
    public String type="N/A";
    public String fengli="N/A";
    public String low2high(){
        String[] lows=low.split(" ");
        String[] highs=high.split(" ");
        String answer=lows[lows.length-1]+"~"+highs[highs.length-1];

        return answer;
    }
}
