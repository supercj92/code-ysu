package com.cfysu.reserve.zookeeper;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * @Author canglong
 * @Date 2021/8/9
 */
public class ZookeeperMain {
    public static void main(String[] args) throws Exception{

        ZookeeperMain zookeeperMain = new ZookeeperMain();

        String authString = "admin:pwd";
        List<ACL> aclList = new ArrayList<>();
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",60 * 1000, new WatchImpl());
        zooKeeper.addAuthInfo("digest", authString.getBytes());

        aclList.add(new ACL(ZooDefs.Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest(authString))));
        aclList.add(new ACL(ZooDefs.Perms.READ, Ids.ANYONE_ID_UNSAFE));

        zookeeperMain.printNodes(zooKeeper.getChildren("/", true));

        zooKeeper.delete("/cfysu", -1);
        zooKeeper.create("/cfysu", "msg".getBytes(), aclList, CreateMode.PERSISTENT);
        zookeeperMain.printNodes(zooKeeper.getChildren("/", true));

        System.out.println(zooKeeper.exists("/cfysu", true));
        zookeeperMain.printNodes(zooKeeper.getChildren("/cfysu", true));
        String finalPathTwo = zooKeeper.create("/cfysu/one", "msg".getBytes(), aclList, CreateMode.EPHEMERAL);
        System.out.println(finalPathTwo);
        zookeeperMain.printNodes(zooKeeper.getChildren("/", true));
    }

    private void printNodes(List<String> list){
        System.out.println(JSONObject.toJSONString(list));
    }

    static class WatchImpl implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.toString());
        }
    }
}
