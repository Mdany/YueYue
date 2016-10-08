package com.chenyu.monster.yueyue.city;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chenyu.monster.yueyue.city.model.City;
import com.chenyu.monster.yueyue.city.model.CityService;
import com.chenyu.monster.yueyue.city.model.HttpResult;
import com.chenyu.monster.yueyue.city.model.HttpResultFunc;
import com.chenyu.monster.yueyue.http.HttpRequest;
import com.chenyu.monster.yueyue.http.ResponseFunc;
import com.chenyu.monster.yueyue.http.ScheduleCompat;
import com.google.gson.GsonBuilder;

import java.util.List;

import rx.Subscriber;

/**
 * Created by chenyu on 16/9/21.
 */

public class CityActivity extends AppCompatActivity {
    private List<City> pro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    private void loadData() {
        CityService cityService = HttpRequest.getInstance().retrofit.create(CityService.class);
        cityService.getCities()
                .flatMap(new ResponseFunc<HttpResult<City>>())
                .flatMap(new HttpResultFunc<City>())
                .doOnNext(this::saveData)
                .compose(ScheduleCompat.<List<List<City>>>applyIoSchedulers())
                .subscribe(new Subscriber<List<List<City>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<List<City>> lists) {

                    }
                });
    }

    private void saveData(List<List<City>> cityLists) {
        Log.i("save", "start");
        List<City> allCity = cityLists.get(1);
        pro = cityLists.get(0);
        for (City proCity : pro) {
            Log.i("save", proCity.name);
            if (proCity.cidx != null && proCity.cidx.length > 0 && !"香港".equals(proCity.name)) {
                if (proCity.cidx.length == 1) {
                    //防止这种只有自己本身还有cidx的问题,比如台湾就只有本身,虽然没有cidx,数据正规就不需要这个判断
                    allCity.get(proCity.cidx[0]).id = proCity.id;//北京市和北京市id不同,这里统一下
                    proCity.cities.add(allCity.get(proCity.cidx[0]));
                }
                if (proCity.name.equals(allCity.get(proCity.cidx[0]).name)) {
                    //证明是像北京市  北京市
                    allCity.get(proCity.cidx[0]).id = proCity.id;
                    proCity.cities.add(allCity.get(proCity.cidx[0]));
                } else {
                    //有下属城市
                    for (int i = proCity.cidx[0]; i < proCity.cidx[1]; i++) {
                        Log.i("cityName", allCity.get(i).name);
                        proCity.cities.add(allCity.get(i));
                    }
                }
            } else {
                City taiwan = new City();
                taiwan.name = proCity.name;
                taiwan.cities = proCity.cities;
                taiwan.cidx = proCity.cidx;
                taiwan.location = proCity.location;
                taiwan.fullname = proCity.fullname;
                taiwan.id = proCity.id;
//                taiwan.id = String.valueOf(Integer.valueOf(proCity.id)-1);
                taiwan.pinyin = proCity.pinyin;

                proCity.cities.add(taiwan);
            }
        }


        for (City province : pro) {
            province.cityId = province.id;
            province.latitude = province.location.lat;
            province.longitude = province.location.lng;
            province._type = "City";
            province.id = null;
            province.location = null;
            province.cidx = null;
            Log.i("pro", province.fullname);
            for (City city : province.cities) {
                city.cityId = city.id;
                city.latitude = city.location.lat;
                city.longitude = city.location.lng;
                city._type = "City";
                city.id = null;
                city.location = null;
                city.cidx = null;
                city.cities = null;
                Log.i("city", city.fullname);
            }
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                String gson = new GsonBuilder().create().toJson(pro);
            }
        }).start();
    }
}
