package com.chenyu.monster.yueyue.http;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.List;

/**
 * Created by chenyu on 16/4/26.
 */
public class HttpResult<T extends Entity> {
    private int count;
    private boolean error;
    private List<T> results;
}
