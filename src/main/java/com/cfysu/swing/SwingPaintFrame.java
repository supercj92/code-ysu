package com.cfysu.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class SwingPaintFrame extends JFrame {

    //将原本声明的 JPanel 注释掉，改为 MyPanel
    private MyPanel contentPane;
    /*
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingPaintFrame frame = new SwingPaintFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SwingPaintFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        //将原本的实例化方式注释掉，改为 MyPanel()
        //contentPane = new JPanel();
        contentPane=new MyPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
}

class MyPanel extends JPanel {
    //创建一个 BufferedImage
    BufferedImage image = null;

    public MyPanel() {
//        try {
//            //使用 ImageIO.read()读取图像，传入 URL
//            //可以是本地图像，也可以是网络图像
//            //这里读取本地图像，因为使用的是 getClass().getResource()
//            //所以 img.png 必须和调用的类 MyPanel 在同一个包中
//            //有异常抛出，使用 try catch 捕获
//            image = ImageIO.read(getClass().getResource("img.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
        //覆盖 JPanel 的 paintComponent() 方法，
        //对于每一个组件来说，paintComponent() 方法是绘制组件本身
        //传入 Graphics，通过它在界面绘制图像
    @Override
    protected void paintComponent(Graphics g) {
        //注释掉默认的从父类继承的绘图方法
        //最常用的绘图方法，很多是以 draw 和 fill 开头的
        //draw 方法绘制的都是线框、轮廓（空心），而 fill 方法绘制的是填充的图像（实心）
        //对于一般的 draw 方法，都会有对应的 fill 方法（空心对实心）
        //先使用 setColor() 为当前的绘图指定颜色
        //使用匿名对象，创建一个新的Color对象
        //每一个 rgb 的值最大为 255 ，最小为 0
        //可以为不同的形状填充不同的颜色
        g.setColor(new Color(255, 0, 0));//红色
        //绘制方块，需要指定 X Y 坐标，宽度，高度
        // 0 0 即从左上角开始绘制
        g.drawRect(0, 0, 100, 100);
        //也可以使用静态方法通过类调用
        g.setColor(Color.GREEN);
        //绘制圆形、椭圆形，需要指定 X Y 坐标，宽度，高度
        //如果宽高一致，绘制的就是圆形
        g.drawOval(0, 0, 100, 100);//圆形
        g.setColor(Color.BLUE);
        g.drawOval(0, 25, 100, 50);//椭圆形
        g.setColor(Color.ORANGE);
        //填充方块，需要指定 X Y 坐标，宽度，高度
        g.fillRect(100, 100, 100, 100);
        //填充带圆角效果的方块，需要指定 X Y 坐标，宽度，高度，圆角的弧宽，圆角的弧高
        //一般情况下，将圆角的弧宽，圆角的弧高，两个弧度值设置成相等
        //弧宽越大，则 X 方向上圆角越长，弧高越大，则 Y 方向上圆角越长
        //弧宽与弧高指定了 X Y 方向上圆角的大小
        g.fillRoundRect(200, 0, 100, 100, 10, 10);
        g.fillRoundRect(300, 100, 100, 100, 200, 50);
        //绘制 String，需要指定 X Y 坐标
        //绘制 Bytes Chars 同 String
        //设置颜色与字体
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TestAPI", 110, 50);
        g.setColor(Color.CYAN);
        //绘制弧线 需要指定 x y 坐标，宽度，高度，起始角度，弧线延长的角度
        //绘制时按照逆时针绘制弧线
        //先画一个矩形，然后以这个矩形的中心为所要画的弧的中心，
        //以水平向右为 0 度，逆时针为正方向
        g.drawArc(0, 200, 100, 100, 270, 90);
        g.fillArc(0, 200, 100, 100, 0, 270);
        g.fillArc(100, 200, 100, 200, 0, 90);
        g.drawArc(100, 200, 100, 200, 90, 90);
        g.fillArc(200, 200, 200, 200, 30, 120);
        g.setColor(new Color(0, 0, 0));
        //绘制直线 需要指定两组 X Y 坐标
        g.drawLine(200, 100, 300, 200);
        //绘制图像，选择传参最少的方法
        //需要指定：图像对象image，X Y 坐标，observer（可指定为空）
        //对于 image 可以在构造函数 MyPanel() 中加载图像
        if (image != null) {
            //绘制图像时可以指定图像的大小
            g.drawImage(image, 0, 100, null);
        }
    //绘制多边形，传入一组 X 坐标，一组 Y 坐标，和 坐标数目
        g.fillPolygon(new int[]{350, 300, 333, 366, 400}, new int[]{0, 50, 100, 100, 50}, 5);
    }
}
