package cn.edu.pku.wangying.miniweather;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangying on 2016/11/15.
 */
public class MyService extends Service{
    int counter = 0;
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        Toast.makeText(this, "启动服务，测试使用，作用为在控制台每秒输出数字“666”", Toast.LENGTH_LONG).show();
        doSomethingRepeatedly();

        return START_STICKY;
    }
    private void doSomethingRepeatedly() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("myWeather", "666");
            }
        }, 0, UPDATE_INTERVAL);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
        Toast.makeText(this, "服务终止", Toast.LENGTH_LONG).show();
    }
}
