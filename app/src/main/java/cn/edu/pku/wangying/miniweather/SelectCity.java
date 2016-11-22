package cn.edu.pku.wangying.miniweather;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import cn.edu.pku.wangying.app.MyApplication;
import cn.edu.pku.wangying.bean.City;

/**
 * Created by wangying on 2016/10/18.
 */

public class SelectCity extends Activity implements View.OnClickListener{


    private ImageView mBackBtn;
    public String citycode;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        MyApplication app= (MyApplication) getApplication();
        List<City> lst=app.getCityList();
        final String[] city=new String[lst.size()];
       final String[] cityId=new String[lst.size()];
        for(int i=0;i<lst.size();i++){
            city[i]= lst.get(i).getCity();
            cityId[i]=lst.get(i).getNumber();
        }
        ListView mlistView = (ListView)findViewById(R.id.list_view);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,city);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ct;
                ct=city[i];
                citycode=cityId[i];
                Toast.makeText(SelectCity.this, "你选择了:"+ct, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_back:
                Intent i=new Intent();
                i.putExtra("cityCode",citycode);
                setResult(RESULT_OK,i);
                finish();
                break;
            default:
                break;
        }
    }
}
