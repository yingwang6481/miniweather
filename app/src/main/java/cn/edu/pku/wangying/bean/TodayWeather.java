package cn.edu.pku.wangying.bean;

import android.net.NetworkInfo;
import android.widget.Toast;

import cn.edu.pku.wangying.miniweather.MainActivity;

/**
 * Created by wangying on 2016/10/17.
 */
public class TodayWeather {
    private String city="N/A";
    private String updatetime="N/A";
    private String wendu="N/A";
    private String shidu="N/A";
    private String pm25="N/A";
    private String quality="N/A";
    private String fengxiang="N/A";
    private String fengli="N/A";
    private String date="N/A";
    private String high="N/A";
    private String low="N/A";
    private String type="N/A";
    public Future future1;
    public Future future2;
    public Future future3;
    public Future future4;
    public Future future5;
    public Future future6;
    public String getCity(){
        return city;
    }
    public String getUpdatetime(){
        return updatetime;
    }
    public String getWendu(){
        return wendu;
    }
    public String getShidu(){
        return shidu;
    }
    public String getPm25(){
        return pm25;
    }
    public String getQuality(){return quality;}
    public String getHigh(){return high;}
    public String getLow(){return low;}
    public String getDate(){return date;}
    public String getFengli(){return fengli;}
    public String getType(){return type;}
    public void setCity(String city){this.city=city;}
    public void setUpdatetime(String updatetime){this.updatetime=updatetime;}
    public void setWendu(String wendu){this.wendu=wendu;}
    public void setShidu(String shidu){this.shidu=shidu;}
    public void setPm25(String pm25){this.pm25=pm25;}
    public void setQuality(String quality){this.quality=quality;}
    public void setFengxiang(String fengxiang){this.fengxiang=fengxiang;}
    public void setFengli(String fengli){this.fengli=fengli;}
    public void setDate(String date){
        this.date=date;
    }
    public void setHigh(String high){
        this.high=high;
    }
    public void setLow(String low){
        this.low=low;
    }
    public void setType(String type){
        this.type=type;
    }
    @Override
    public String toString(){
        return "TodayWeather{"+
                "city='"+city+'\''+
                ",updatetime='"+updatetime+'\''+
                ",wendu='"+wendu+'\''+
                ",shidu='"+shidu+'\''+
                ",pm25='"+pm25+'\''+
                ",quality='"+quality+'\''+
                ",fengxiang='"+fengxiang+'\''+
                ",fengli='"+fengli+'\''+
                ",date='"+date+'\''+
                ",high='"+high+'\''+
                ",low='"+low+'\''+
                ",type='"+type+'\''+
                '}';



    }

}
