package cn.edu.pku.wangying.miniweather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.wangying.app.MyApplication;
import cn.edu.pku.wangying.bean.Future;
import cn.edu.pku.wangying.bean.TodayWeather;
import cn.edu.pku.wangying.util.NetUtil;

/**
 * Created by wangying on 2016/9/27.asdasdasdadasdasd
 */
public class MainActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private List<View> views;
    private static final int UPDATE_TODAY_WEATHER=1;
    private ProgressBar btn;
    private ImageView mUpdateBtn;
    private ImageView mCitySelect;
    private ImageView[] dots;
    private TextView cityTv,timeTv,humidityTv,weekTv,pmDataTv,pmQualityTv,temperatureTv,climateTv,windTv,city_name_Tv;
    private TextView day1,day2,day3,day4,day5,day6;
    private TextView type1,type2,type3,type4,type5,type6;
    private TextView lh1,lh2,lh3,lh4,lh5,lh6;
    private TextView fengli1,fengli2,fengli3,fengli4,fengli5,fengli6;

    private ImageView weatherImg,pmImg;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case UPDATE_TODAY_WEATHER:
                    updateTodayWeather((TodayWeather) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };
    private int[] ids ={R.id.iv_1,R.id.iv_3};
    void initDots(){
        dots=new ImageView[views.size()];
        for(int i=0;i<views.size();i++){
            dots[i]=(ImageView)findViewById(ids[i]);
        }
    }
    void initView(){
        LayoutInflater inflater =LayoutInflater.from(this);

        views=new ArrayList<View>();
        views.add(inflater.inflate(R.layout.sixdays1,null));
        views.add(inflater.inflate(R.layout.sixdays2,null));

        vp=(ViewPager)findViewById(R.id.sixday);
        vpAdapter=new ViewPagerAdapter(views,this);
        vp=(ViewPager)findViewById(R.id.sixday);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);

        city_name_Tv = (TextView) findViewById(R.id.title_city_name);
        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week_today);
        pmDataTv = (TextView) findViewById(R.id.pm_data);
        pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality);
        pmImg = (ImageView) findViewById(R.id.pm2_5_img);
        temperatureTv = (TextView) findViewById(R.id.temperature);
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        weatherImg = (ImageView) findViewById(R.id.weather_img);

        day1=(TextView)views.get(0).findViewById(R.id.day1);
        day2=(TextView)views.get(0).findViewById(R.id.day2);
        day3=(TextView)views.get(0).findViewById(R.id.day3);
        day4=(TextView)views.get(1).findViewById(R.id.day4);
        day5=(TextView)views.get(1).findViewById(R.id.day5);
        day6=(TextView)views.get(1).findViewById(R.id.day6);
        type1=(TextView)views.get(0).findViewById(R.id.climate1);
        type2=(TextView)views.get(0).findViewById(R.id.climate2);
        type3=(TextView)views.get(0).findViewById(R.id.climate3);
        type4=(TextView)views.get(1).findViewById(R.id.climate4);
        type5=(TextView)views.get(1).findViewById(R.id.climate5);
        type6=(TextView)views.get(1).findViewById(R.id.climate6);
        lh1=(TextView)views.get(0).findViewById(R.id.temperature1);
        lh2=(TextView)views.get(0).findViewById(R.id.temperature2);
        lh3=(TextView)views.get(0).findViewById(R.id.temperature3);
        lh4=(TextView)views.get(1).findViewById(R.id.temperature4);
        lh5=(TextView)views.get(1).findViewById(R.id.temperature5);
        lh6=(TextView)views.get(1).findViewById(R.id.temperature6);
        fengli1=(TextView)views.get(0).findViewById(R.id.wind1);
        fengli2=(TextView)views.get(0).findViewById(R.id.wind2);
        fengli3=(TextView)views.get(0).findViewById(R.id.wind3);
        fengli4=(TextView)views.get(1).findViewById(R.id.wind4);
        fengli5=(TextView)views.get(1).findViewById(R.id.wind5);
        fengli6=(TextView)views.get(1).findViewById(R.id.wind6);


        day1.setText("N/A");

        city_name_Tv.setText("N/A");
        cityTv.setText("N/A");
        timeTv.setText("N/A");
        humidityTv.setText("N/A");
        pmDataTv.setText("N/A");
        pmQualityTv.setText("N/A");
        weekTv.setText("N/A");
        temperatureTv.setText("N/A");
        climateTv.setText("N/A");
        windTv.setText("N/A");

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {

        for(int a=0;a<ids.length;a++){
            if(a==i){
                dots[a].setImageResource(R.drawable.page_indicator_focused);
            }else{
                dots[a].setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.weather_info);


        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        btn=(ProgressBar)findViewById(R.id.title_update_progress);
        mUpdateBtn.setOnClickListener(this);

        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            Log.d("myWeather", "网络OK");
            Toast.makeText(MainActivity.this, "网络OK!", Toast.LENGTH_LONG).show();
        } else {
            Log.d("myWeather", "网络挂了");
            Toast.makeText(MainActivity.this, "网络挂了！", Toast.LENGTH_LONG).show();
        }

        mCitySelect=(ImageView) findViewById(R.id.title_city_manager);
        mCitySelect.setOnClickListener(this);



        initView();
        initDots();
        Log.d("myWeather","oncreate调用");


        vpAdapter.notifyDataSetChanged();
    }
/*    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
        Log.d("myWeather", "启动服务");
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
        Log.d("myWeather", "终止服务");
    }*/
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.title_city_manager){
            Intent i=new Intent(this,SelectCity.class);
            //  startActivity(i);
            startActivityForResult(i,1);
        }
        if (view.getId() == R.id.title_update_btn) {





            mUpdateBtn.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            String cityCode = sharedPreferences.getString("main_city_code", "101010100");
            Log.d("myWeather", cityCode);
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
                Log.d("myWeather", "网络OK");
                queryWeatherCode(cityCode);
            } else {
                Log.d("myWeather", "网络挂了");
                Toast.makeText(MainActivity.this, "网络挂了！", Toast.LENGTH_LONG).show();
            }






        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {


            String newCityCode= data.getStringExtra("cityCode");
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

            editor.putString("main_city_code",newCityCode);
            editor.commit();
            Log.d("myWeather", "选择的城市代码为"+newCityCode);
            if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
                Log.d("myWeather", "网络OK");
                queryWeatherCode(newCityCode);
            } else {
                Log.d("myWeather", "网络挂了");
                Toast.makeText(MainActivity.this, "网络挂了！", Toast.LENGTH_LONG).show();
            }
        }
    }

    /*
            **
            * *
    @param cityCode
    */
    private void queryWeatherCode(String cityCode) {
        final String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        Log.d("myWeather", address);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                TodayWeather todayWeather=null;
                try {

                    URL url = new URL(address);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    InputStream in = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        response.append(str);
                        Log.d("myWeather", str);
                    }
                    String responseStr = response.toString();
                    Log.d("myWeather", responseStr);
                    todayWeather = parseXML(responseStr);
                    if (todayWeather != null) {
                        Log.d("myWeather", todayWeather.toString()+"..");
                        Message msg =new Message();
                        msg.what = UPDATE_TODAY_WEATHER;
                        msg.obj=todayWeather;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }

            }
        }).start();


    }
    private TodayWeather parseXML(String xmldata){
        TodayWeather todayWeather=null;
        int fengxiangCount=0;
        int fengliCount=0;
        int dateCount=0;
        int highCount=0;
        int lowCount=0;
        int typeCount=0;
        int tianqiCount=0;
        int type1count=0;
        int fengli1count=0;
        List<Future> flist=null;
        try{
            XmlPullParserFactory fac=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xmldata));
            int eventType=xmlPullParser.getEventType();
            Log.d("myWeather","parseXml");

            Future yesterday=null;
            Future future=null;
            while(eventType!=XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if(xmlPullParser.getName().equals("resp")){
                            todayWeather= new TodayWeather();
                            flist= new ArrayList<Future>();
                        }
                        if (todayWeather != null) {



                            if (xmlPullParser.getName().equals("city")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setCity(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("updatetime")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setUpdatetime(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("shidu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setShidu(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("wendu")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setWendu(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("pm25")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setPm25(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("quality")) {
                                eventType = xmlPullParser.next();
                                todayWeather.setQuality(xmlPullParser.getText());
                            } else if (xmlPullParser.getName().equals("fengxiang") && fengxiangCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengxiang(xmlPullParser.getText());
                                fengxiangCount++;
                            } else if (xmlPullParser.getName().equals("fengli") && fengliCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setFengli(xmlPullParser.getText());
                                fengliCount++;
                            } else if (xmlPullParser.getName().equals("date") && dateCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setDate(xmlPullParser.getText());
                                dateCount++;
                            } else if (xmlPullParser.getName().equals("high") && highCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setHigh(xmlPullParser.getText().substring(2).trim());
                                highCount++;
                            } else if (xmlPullParser.getName().equals("low") && lowCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setLow(xmlPullParser.getText().substring(2).trim());
                                lowCount++;
                            } else if (xmlPullParser.getName().equals("type") && typeCount == 0) {
                                eventType = xmlPullParser.next();
                                todayWeather.setType(xmlPullParser.getText());

                                typeCount++;
                            }
                            if("yesterday".equals(xmlPullParser.getName())){
                                yesterday=new Future();

                            }
                            if(yesterday!=null){
                                if("date_1".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                    yesterday.date=xmlPullParser.getText();
                                }else if("high_1".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                   yesterday.high=xmlPullParser.getText();
                                }else if("low_1".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                   yesterday.low=xmlPullParser.getText();
                                }else if("type_1".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                  yesterday.type=xmlPullParser.getText();
                                    type1count++;
                                }else if("fl_1".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                  yesterday.fengli=xmlPullParser.getText();
                                    fengli1count++;
                                }
                            }
                            if("weather".equals(xmlPullParser.getName())){
                                future=new Future();
                                Log.d("myweather","111");
                            }
                            if(future!=null){
                                if("date".equals(xmlPullParser.getName())){

                                    eventType=xmlPullParser.next();
                                    future.date=xmlPullParser.getText();
                                }else if("high".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                    future.high=xmlPullParser.getText();
                                }else if("low".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                    future.low=xmlPullParser.getText();
                                }else if("type".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                    future.type=xmlPullParser.getText();
                                    type1count++;
                                }else if("fengli".equals(xmlPullParser.getName())){
                                    eventType=xmlPullParser.next();
                                    future.fengli=xmlPullParser.getText();
                                    fengli1count++;
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("yesterday".equals(xmlPullParser.getName())){
                            todayWeather.future1=yesterday;
                            future=null;
                            fengli1count=0;
                            type1count=0;
                        }else if("weather".equals(xmlPullParser.getName())){

                            flist.add(future);
                            future=null;
                            fengli1count=0;
                            type1count=0;
                        }
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (XmlPullParserException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        todayWeather.future2=flist.get(0);
        todayWeather.future3=flist.get(1);
        todayWeather.future4=flist.get(2);
        todayWeather.future5=flist.get(3);
        todayWeather.future6=flist.get(4);

        todayWeather.future2.date=todayWeather.getDate();
        todayWeather.future2.low=todayWeather.getLow();
        todayWeather.future2.high=todayWeather.getHigh();
        return todayWeather;
    }
    void updateTodayWeather(TodayWeather todayWeather){

        city_name_Tv.setText(todayWeather.getCity()+"天气");
        cityTv.setText(todayWeather.getCity());
        timeTv.setText(todayWeather.getUpdatetime()+ "发布");
        humidityTv.setText("湿度："+todayWeather.getShidu());
        pmDataTv.setText(todayWeather.getPm25());
        pmQualityTv.setText(todayWeather.getQuality());
        weekTv.setText(todayWeather.getDate());
        temperatureTv.setText(todayWeather.getLow()+"~"+todayWeather.getHigh());
        climateTv.setText(todayWeather.getType());
        windTv.setText("风力:"+todayWeather.getFengli());
        day1.setText(todayWeather.future1.date);
        day2.setText(todayWeather.future2.date);
        day3.setText(todayWeather.future3.date);
        day4.setText(todayWeather.future4.date);
        day5.setText(todayWeather.future5.date);
        day6.setText(todayWeather.future6.date);
        type1.setText(todayWeather.future1.type);
        type2.setText(todayWeather.future2.type);
        type3.setText(todayWeather.future3.type);
        type4.setText(todayWeather.future4.type);
        type5.setText(todayWeather.future5.type);
        type6.setText(todayWeather.future6.type);
        lh1.setText(todayWeather.future1.low2high());
        lh2.setText(todayWeather.future2.low2high());
        lh3.setText(todayWeather.future3.low2high());
        lh4.setText(todayWeather.future4.low2high());
        lh5.setText(todayWeather.future5.low2high());
        lh6.setText(todayWeather.future6.low2high());
        fengli1.setText(todayWeather.future1.fengli);
        fengli2.setText(todayWeather.future2.fengli);
        fengli3.setText(todayWeather.future3.fengli);
        fengli4.setText(todayWeather.future4.fengli);
        fengli5.setText(todayWeather.future5.fengli);
        fengli6.setText(todayWeather.future6.fengli);

        int i;
        i=Integer.parseInt(todayWeather.getPm25());
        if(i<=50&&i>=0)
            pmImg.setImageResource(R.drawable.biz_plugin_weather_0_50);
        else if(i>=51&&i<=100)
            pmImg.setImageResource(R.drawable.biz_plugin_weather_51_100);
        else if(i>=101&&i<=150)
            pmImg.setImageResource(R.drawable.biz_plugin_weather_101_150);
        else if(i>=151&&i<=200)
            pmImg.setImageResource(R.drawable.biz_plugin_weather_151_200);
        else if(i>=201&&i<=300)
            pmImg.setImageResource(R.drawable.biz_plugin_weather_201_300);
        else
            pmImg.setImageResource(R.drawable.biz_plugin_weather_greater_300);
        String a=todayWeather.getType();
        if(a.equals("暴雪"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoxue);
        else if(a.equals("暴雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_baoyu);
        else if(a.equals("大暴雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
        else if(a.equals("特大暴雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
        else if(a.equals("大雪"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_daxue);
        else if(a.equals("大雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_dayu);
        else if(a.equals("多云"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_duoyun);
        else if(a.equals("雷阵雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
        else if(a.equals("雷阵雨冰雹"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
        else if(a.equals("晴"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_qing);
        else if(a.equals("沙尘暴"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
        else if(a.equals("雾"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_wu);
        else if(a.equals("小雪"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
        else if(a.equals("小雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
        else if(a.equals("阴"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_yin);
        else if(a.equals("雨夹雪"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
        else if(a.equals("阵雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
        else if(a.equals("中雪"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
        else if(a.equals("中雨"))
            weatherImg.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
        Toast.makeText(MainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
        btn.setVisibility(View.GONE);
        mUpdateBtn.setVisibility(View.VISIBLE);
    }

}