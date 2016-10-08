package com.chenyu.monster.yueyue.city.model;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyu on 16/9/21.
 */

public class City extends Entity {
    //筛选完置null
    public String id;
    public Location location;
    public int[] cidx;

    //保留
    public String name;
    public String fullname;
    public List<String> pinyin;
    public List<City> cities = new ArrayList<>();

    //替代置null
    public String cityId;
    public double latitude;
    public double longitude;
    public String _type;
}
