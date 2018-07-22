package indi.liu.player;

import java.io.Serializable;

/**
 * Create by : liu
 * Create on : 2018/6/12
 * Create for : 保存用户的昵称和得分信息
 */

public class Joiner implements Serializable {

    //用户昵称
    private String name;
    //用户猜对所用次数
    protected int count;

    public Joiner(String name) {
        this.name = name;
    }

    //写入排行榜时新建对象用
    public Joiner(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return count;
    }
}
