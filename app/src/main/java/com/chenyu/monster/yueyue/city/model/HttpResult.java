package com.chenyu.monster.yueyue.city.model;

import java.util.List;

/**
 * Created by chenyu on 16/9/21.
 */

public class HttpResult<T> {
    public int status;
    public String message;
    public String data_version;
    public List<List<T>> result;
}
