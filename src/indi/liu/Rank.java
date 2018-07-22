package indi.liu;


import indi.liu.player.Joiner;

import java.io.*;
import java.util.*;

/**
 * Create by : liu
 * Create on : 2018/6/12
 * Create for : 提供排行榜,以猜的次数排序
 */

public class Rank {

    //储存的文件
    private File file;
    //保存用户信息的集合
    private List<Joiner> players;

    public Rank() {
        file = new File("src/indi/liu/rank.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        players = new ArrayList<>();
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            while (fis.available() > 0) {
                ois = new ObjectInputStream(fis);
                //将文件中所有信息写入集合
                players.add((Joiner) ois.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将用户信息写入集合
     *
     * @param player 每当用户猜完一次,调用这个方法将信息写入排行榜
     */
    public void add(Joiner player) {
        Joiner joiner = new Joiner(player.getName(), player.getCount());
        flushRank(joiner);
        players.add(joiner);
    }

    /**
     * 显示排行榜,以用户猜对次数排序
     * 只显示排名前十
     */
    public void show() {
        Collections.sort(players, new Comparator<Joiner>() {
            @Override
            public int compare(Joiner o1, Joiner o2) {
                return o1.getCount() - o2.getCount();
            }
        });
        Iterator<Joiner> it = players.iterator();
        int count = 0;
        while (it.hasNext() && count++ < 10) {
            Joiner joiner = it.next();
            System.out.println(joiner.getName() + "-----第" + joiner.getCount() + "次猜对了");
        }
    }

    /**
     * 调用这个方法将信息写入文件中
     *
     * @param joiner 需要写入的对象
     */
    private void flushRank(Joiner joiner) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file, true));
            oos.writeObject(joiner);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到第一名猜的次数
     * @return 第一名猜的次数
     */
    public int getFirst() {
        Collections.sort(players, new Comparator<Joiner>() {
            @Override
            public int compare(Joiner o1, Joiner o2) {
                return o1.getCount() - o2.getCount();
            }
        });
        return players.get(0).getCount();
    }
}
