package com.chenyu.monster.yueyue.http;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.List;

/**
 * Created by chenyu on 16/4/26.
 */
public class HttpResult<T extends Entity> {
    public int count;
    public boolean error;
    public List<T> results;
}
