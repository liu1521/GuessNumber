package indi.liu.player;

import java.util.Scanner;

/**
 * Create by : liu
 * Create on : 2018/6/12
 * Create for : 保存用户游戏时的状态,提供猜数字方法
 */

public class Player extends Joiner {

    public Player(String name) {
        super(name);
    }

    /**
     * 加入游戏,初始化计数器
     */
    public void joinGame() {
        count = 0;
    }

    /**
     * 猜数字
     * @return 返回用户猜的数字,这个数字只能是1-1000
     */
    public int guess() {
        count++;
        String guessNumber;
        Scanner sc = new Scanner(System.in);
        while (!(guessNumber = sc.nextLine()).matches("[1-9]\\d{0,2}[0]?")) {
            System.out.println("输入有误,请重输");
        }
        return Integer.valueOf(guessNumber);
    }

}
