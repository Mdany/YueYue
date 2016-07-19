package com.chenyu.monster.yueyue.gankio.model;

import com.chenyu.monster.yueyue.framework.Entity;

import java.util.Date;

/**
 * Created by chenyu on 16/7/14.
 */
public class Gank extends Entity {
    public String desc;
    public String ganhuo_id;
    public String readability;//富文本
    public String type;
    public String url;
    public String who;
    public Date createdAt;
    public Date publishedAt;
    public Date updatedAt;
    public int imageWidth;
    public int imageHeight;
}
