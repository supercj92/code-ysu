package com.cfysu.midware.cache;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by cj on 2017/6/27.
 */
public class MongoDBTest {

    @Test
    public void testMongoDB(){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("cj");
        MongoCollection<Document> collection = mongoDatabase.getCollection("table_name");
        System.out.print(collection.find().into(new ArrayList<Document>()));
    }
}
