package com.cfysu.university;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class MyTank extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MyPanel mp = null;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyTank tank = new MyTank();
    }


    public MyTank() {
        mp = new MyPanel();
        this.add(mp);
        //注册监听
        this.addKeyListener(mp);
        //画布开始绘制
        Thread th = new Thread(mp);
        th.start();
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}

//画布
class MyPanel extends JPanel implements KeyListener, Runnable {

    /**
     *画布的成员
     */
    Hero hero = null;
    Vector<Enemy> ets = new Vector<Enemy>();
    int etsCount = 3;
    private static final long serialVersionUID = 1L;

    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //判断己方子弹是否击中敌方坦克
            for (int i = 0; i < hero.bb.size(); i++) {
                Bullet b = hero.bb.get(i);
                if (b.isAlive) {
                    for (int j = 0; j < ets.size(); j++) {
                        Enemy tank = ets.get(j);
                        isHit(b, tank);
                    }
                }
            }
            //判断己方是否中单
            for (int i = 0; i < ets.size(); i++) {
                Enemy eTank = ets.get(i);
                for (int j = 0; j < eTank.bb.size(); j++) {
                    Bullet b = eTank.bb.get(j);
                    if (b.isAlive) {
                        isHit(b, hero);
                    }
                }
            }

            if (!hero.isAlive) {
                System.exit(0);
            }

            //重绘
            this.repaint();

        }
    }

    //判断是否中单
    public void isHit(Bullet b, Tank et) {
        switch (et.direct) {

            case 0:
            case 2:
                if (b.isAlive && et.isAlive && b.getY() < et.getY() + 30 && b.getY() > et.getY() && b.getX() > et.getX() && b.getX() < et.getX() + 20) {
                    //设置线程死亡
                    b.isAlive = false;
                    et.isAlive = false;
                }
                break;
            case 1:
            case 3:
                if (b.isAlive && et.isAlive && b.getY() < et.getY() + 20 && b.getY() > et.getY() && b.getX() > et.getX() && b.getX() < et.getX() + 30) {
                    b.isAlive = false;
                    et.isAlive = false;
                }
                break;
        }

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("in");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.MoveUp();
                hero.setDirect(0);
                break;
            case KeyEvent.VK_DOWN:
                hero.MoveDown();
                hero.setDirect(2);
                break;
            case KeyEvent.VK_LEFT:
                hero.MoveLeft();
                hero.setDirect(3);
                break;
            case KeyEvent.VK_RIGHT:
                hero.MoveRight();
                hero.setDirect(1);
                break;
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            if (hero.bb.size() < 4) {
                hero.fire(hero.getDirect());
            }
        }
        //重绘
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public MyPanel() {
        //初始化数据
        hero = new Hero(200, 200);
        for (int i = 0; i < etsCount; i++) {
            Enemy et = new Enemy((i + 1) * 100, 0);
            Thread th1 = new Thread(et);
            th1.start();
            ets.add(et);
        }

    }

    //重绘
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 400, 300);
        if (hero.isAlive) {
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
        }
        //绘制敌坦
        for (int i = 0; i < ets.size(); i++) {
            Enemy eTank = ets.get(i);
            if (eTank.isAlive) {
                this.drawTank(eTank.getX(), eTank.getY(), g, eTank.getDirect(), 1);
            }
        }
        //绘自己的子弹
        for (int i = 0; i < hero.bb.size(); i++) {
            Bullet b = hero.bb.get(i);

            if (b != null && b.isAlive) {
                g.setColor(Color.GREEN);
                g.fillOval(b.getX(), b.getY(), 3, 3);
            }
            if (!b.isAlive) {
                hero.bb.remove(b);
            }
        }
        //绘制敌人坦克的子弹
        for (int i = 0; i < ets.size(); i++) {
            Enemy eTank = ets.get(i);
            for (int j = 0; j < eTank.bb.size(); j++) {
                Bullet b = eTank.bb.get(j);
                if (b != null && b.isAlive) {
                    g.setColor(Color.RED);
                    g.fillOval(b.getX(), b.getY(), 3, 3);
                }
            }
        }


    }

    //
    public void drawTank(int x, int y, Graphics g, int direc, int type) {

        //̹设置己方和敌方的颜色
        switch (type) {
            case 0:
                g.setColor(Color.GREEN);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
        }

        switch (direc) {
            case 0:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 9, y + 15, x + 9, y);
                break;
            case 1:
                g.fill3DRect(x, y, 30, 5, false);

                g.fill3DRect(x, y + 15, 30, 5, false);

                g.fill3DRect(x + 5, y + 5, 20, 10, false);

                g.fillOval(x + 10, y + 5, 10, 10);

                g.drawLine(x + 15, y + 10, x + 30, y + 10);

                break;
            case 2:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 9, y + 15, x + 9, y + 30);
                break;
            case 3:
                g.fill3DRect(x, y, 30, 5, false);

                g.fill3DRect(x, y + 15, 30, 5, false);

                g.fill3DRect(x + 5, y + 5, 20, 10, false);

                g.fillOval(x + 10, y + 5, 10, 10);

                g.drawLine(x + 15, y + 10, x, y + 10);
                break;

        }
    }
}


