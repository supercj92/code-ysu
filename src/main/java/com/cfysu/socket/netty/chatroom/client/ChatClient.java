package com.cfysu.socket.netty.chatroom.client;

import com.cfysu.socket.netty.chatroom.message.ChatMessageUtils;
import com.cfysu.socket.netty.chatroom.session.ChatSession;
import io.netty.channel.ChannelHandlerContext;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 聊天室界面
 */
public class ChatClient extends Frame implements Runnable {
    private static Logger _logger = Logger.getLogger(ChatClient.class);
    private ChatSession session;

    private String userName;

    private volatile boolean stop = false;

    TextField tf = new TextField(); //消息区

    TextArea ta = new TextArea(); //输入框

    public ChatClient(String clientId, ChannelHandlerContext ctx) {
        this.userName = clientId;
        this.session = new ChatSession();
        this.session.setId(clientId);
        this.session.setClient(ctx);
    }

    /**
    *
    *
    **/
    public void run() {
        createFrame();

        while (!stop) {
            try {
                Thread.sleep(1000);
                session.send(ChatMessageUtils.generateHeartbeatMessage(userName));
            } catch (Exception e) {
                _logger.error("send is err.",e);
            }
        }
    }

    /**
    *
    *
    **/
    public void createFrame() { //创建窗口
        this.setTitle(userName);
        this.setBounds(300, 300, 300, 300);
        ta.setEditable(false);
        this.add(tf, BorderLayout.SOUTH);
        this.add(ta, BorderLayout.NORTH);
        this.addWindowListener(new WindowAdapter() { //退出聊天室
            public void windowClosing(WindowEvent e) {
                send(ChatMessageUtils.generateUnRegisterMessage(userName));
                session.disconnect();
                stop = true;
                //关闭窗口
                System.exit(0);
            }
        });
        tf.addActionListener(new TfListener()); //绑定输入框事件
        this.pack();
        this.setVisible(true);
    }

    class TfListener implements ActionListener { //发送消息

        /**
        *
        * @param e
        *
        **/
        public void actionPerformed(ActionEvent e) {
            String newData = tf.getText();
            if (newData.startsWith("@")) {
                newData = newData.substring(1);
                String[] temp = newData.split(" ");
                newData = ChatMessageUtils.generateToMessage(session.getId(), temp[0], temp[1]);
            }
            else if(newData.startsWith("#")) {
                newData = ChatMessageUtils.generateGetAllMessage(session.getId());
            }
            else {
                newData = ChatMessageUtils.generateBroadcastMessage(session.getId(), newData);
            }
            //ta.setText(str);
            tf.setText("");

            send(newData);
        }
    }

    /**
    *
    * setter of text
    * @param text
    *
    **/
    public void setText(String text) {
        ta.setText(ta.getText() + text.toString() + "\n");//追加消息
    }

    /**
    *
    * @param msg
    *
    **/
    public void send(String msg) {
        session.send(msg);
    }

    /**
    *
    * getter of username
    *
    **/
    public String getUserName() {
        return userName;
    }
}
