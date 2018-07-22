package indi.liu.game;

import indi.liu.Rank;
import indi.liu.player.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Create by : liu
 * Create on : 2018/6/12
 * Create for : 猜数字游戏
 */

public class Manager {

    //猜数字的用户
    private Player player;
    //生成的随机数
    private int randomNumber;
    //排行榜
    private Rank rank;

    public Manager() {
        rank = new Rank();
    }

    //判断猜的数字大还是小,大则返回1,小则返回-1
    private boolean bigger(int randomNumber, int guessNumber) {
        if (guessNumber > randomNumber) return true;
        else return false;
    }

    //开始游戏,生成一个1到1000的随机数,将用户的状态修改为游戏中
    private void start() {
        randomNumber = new Random(System.currentTimeMillis()).nextInt(1000) + 1;
        player.joinGame();
        gaming();
    }

    //游戏中
    private void gaming() {
        int guessNumber;
        int low = 1;
        int high = 1000;
        System.out.println("游戏开始,请输入一个1-1000的数字");
        //判断结果
        while (randomNumber != (guessNumber = player.guess())) {
            if (bigger(randomNumber, guessNumber)) {
                high = guessNumber <= high ? guessNumber - 1 : high;
                System.out.println("猜大了,请重猜一个" + low + "-" + high + "的数字");
            } else {
                low = guessNumber >= low ? guessNumber + 1 : low;
                System.out.println("猜小了,请重猜一个" + low + "-" + high + "的数字");
            }
        }
        System.out.println("恭喜你第" + player.getCount() + "次猜中了");
        //将用户信息写入排行榜
        rank.add(player);
        System.out.println("目前第一名猜中只用了" + rank.getFirst() + "次哦");
        System.out.println("按回车返回菜单");
        new Scanner(System.in).nextLine();
    }

    //显示排行榜
    private void showRank() {
        rank.show();
        System.out.println("---------------");
        System.out.println("按回车键返回菜单");
        new Scanner(System.in).nextLine();
    }

    //菜单栏
    private void menu() {
        Scanner sc = new Scanner(System.in);
        String choose;
        while (true) {
            System.out.println("--------------菜单-------------");
            System.out.println("-------输入\"begin\"开始游戏------");
            System.out.println("-----输入\"shutdown\"退出游戏-----");
            System.out.println("-----输入\"rinkList\"查看排行-----");
            choose = sc.nextLine();
            switch (choose) {
                case "begin":
                    start();
                    break;
                case "shutdown":
                    return;
                case "rinkList":
                    showRank();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.println("请输入你的昵称");
        String name = null;
        Scanner sc = new Scanner(System.in);
        //避免出现空白昵称
        while ((name = sc.nextLine()).equals("")) ;
        manager.player = new Player(name);
        manager.menu();
    }
}
