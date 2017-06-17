package com.cfysu.datastructure.list;

/**
 * Created by cj on 17-6-17.
 */
public class Node {
    public Node(){

    }
    public Node(int iData){
        this.iData = iData;
        this.next = null;
    }

   private int iData;
   private Node next;

   public void dispalyNode(){
       System.out.print(this.iData + ", ");
   }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getiData() {
        return iData;
    }

    public void setiData(int iData) {
        this.iData = iData;
    }
}
