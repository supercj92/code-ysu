package com.cfysu.thread.threadlocal;

public class User {
   private ThreadLocal<String> name = new ThreadLocal();

   User(String name){
       this.name.set(name);
   }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
