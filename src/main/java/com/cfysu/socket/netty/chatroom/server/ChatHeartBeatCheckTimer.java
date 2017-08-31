package com.cfysu.socket.netty.chatroom.server;

import com.cfysu.socket.netty.chatroom.session.ChatSession;
import com.cfysu.socket.netty.chatroom.session.ChatSessionListener;
import com.cfysu.socket.netty.chatroom.session.ChatSessionsManager;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
*
*
**/
public class ChatHeartBeatCheckTimer implements Runnable, ChatSessionListener {

    private static Logger _logger = Logger.getLogger(ChatHeartBeatCheckTimer.class);

    private ChatSessionsManager chatSessionManager = null;

    private long checkPeriod = 5 * 1000;

    private ReentrantLock _lock = new ReentrantLock();

    private Condition _notEmpty = _lock.newCondition();

    private volatile boolean stop = false;

    public ChatHeartBeatCheckTimer(ChatSessionsManager httpSessionManager) {
        this.chatSessionManager = httpSessionManager;
    }

    /**
    *
    *
    **/
    public void run() {
        while (!this.stop) {
            if (isEmpty()) {
                lockEmptyQueue();
            }

            try {
                Thread.sleep(getCheckPeriod());
            } catch (InterruptedException e) {
            }

            if(this.stop)
                break;

            try {
                long now = System.currentTimeMillis();
                String identity = null;
                ChatSession session = null;
                for(Iterator<String> it = chatSessionManager.getSessionKeys().iterator(); it.hasNext(); ) {
                    try {
                        identity = it.next();
                        session = chatSessionManager.getSession(identity);
                        if(session.expire()) {
                            _logger.info("--befor hear beat --SessionId:" + identity);
                            session.disconnect();
                            chatSessionManager.remove(identity);
                        }
                    } catch (Exception e) {
                        session.disconnect();
                    }
                }
            } catch (Exception e) {
                _logger.error("check destination! ", e);
            }
        }
    }

    private void lockEmptyQueue() {
        boolean flag = _lock.tryLock();
        if (flag) {
            try {
//                _notEmpty.await();
            } /*catch (InterruptedException e) {
            }*/ catch (Exception e) {
                _logger.error("lock Thread Queue error!", e);
            } finally {
                _lock.unlock();
            }
        }
    }

    private void signalNotEmptyQueue() {
        boolean flag = false;
        try {
            flag = _lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (flag)
                _notEmpty.signalAll();
        } catch (InterruptedException e) {
        } catch (Exception e) {
            _logger.error("lock Thread Queue error!", e);
        } finally {
            if (flag)
                _lock.unlock();
        }
    }

    /**
    *
    *
    **/
    public void stop() {
        this.stop = true;
    }

    private boolean isEmpty() {
        return chatSessionManager.getSessionCount() == 0;
    }

    /**
    *
    * getter of checkperiod
    *
    **/
    public long getCheckPeriod() {
        return checkPeriod;
    }

    /**
    *
    * setter of checkperiod
    * @param checkPeriod
    *
    **/
    public void setCheckPeriod(long checkPeriod) {
        this.checkPeriod = checkPeriod;
    }

    /**
    *
    *
    **/
    public void sessionCreated() {
        signalNotEmptyQueue();
    }

    /**
    *
    *
    **/
    public void sessionDestroyed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
