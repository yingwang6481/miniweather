package cn.edu.pku.wangying.bean;

/**
 * Created by wangying on 2016/10/18.
 */
public class City {
    private String province;
    private String city;
    private String number;
    private String firstPY;
    private String allPY;
    private String allFristPY;
    public String getProvince(){
        return province;
    }
    public String getCity(){
        return city;
    }
    public String getNumber(){
        return number;
    }
    public String getFirstPY(){
        return firstPY;
    }
    public String getAllPY(){
        return allPY;
    }
    public String getAllFristPY(){
        return  allFristPY;
    }
    public City(String province, String city, String number, String
            firstPY, String allPY, String allFristPY) {
        this.province = province;
        this.city = city;
        this.number = number;
        this.firstPY = firstPY;
        this.allPY = allPY;
        this.allFristPY = allFristPY;
    }
}
