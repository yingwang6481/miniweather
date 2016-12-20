package cn.edu.pku.wangying.miniweather;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;
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
    private EditText editText;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        mBackBtn=(ImageView)findViewById(R.id.title_back);
        mBackBtn.setOnClickListener(this);
        MyApplication app= (MyApplication) getApplication();
       final List<City> lst=app.getCityList();
      final  ArrayList<String> city=new ArrayList<String>();
      final  ArrayList<String> cityId=new ArrayList<String>();
        for(int i=0;i<lst.size();i++){
            city.add(lst.get(i).getCity());
            cityId.add(lst.get(i).getNumber());
        }
        editText=(EditText)findViewById(R.id.search_edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String city0=editText.getText().toString();
                String citynumber=search(city0,lst);
                Log.d("myweather",citynumber);
                city.clear();
                city.add(city0);
                cityId.clear();
                cityId.add(citynumber);
                adapter.notifyDataSetChanged();
            }
        });



        ListView mlistView = (ListView)findViewById(R.id.list_view);
        adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,city);
        mlistView.setAdapter(adapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ct;
                ct=city.get(i);
                citycode=cityId.get(i);
                Toast.makeText(SelectCity.this, "你选择了:"+ct, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String search(String name,List<City> lst){
        for(City c : lst){
            if(c.getCity().equals(name))
                return c.getNumber();
        }
        return "N/A";
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
