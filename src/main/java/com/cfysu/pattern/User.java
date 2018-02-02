package com.cfysu.pattern;

//当属性个数大于4个，可考虑使用builder模式
public class User{
    private String userName;
    private String pwd;

    User(UserBuilder userBuilder){
        this.userName = userBuilder.userName;
        this.pwd = userBuilder.pwd;
    }

    public static class UserBuilder implements Builder<User>{

        private String userName;
        private String pwd;

        public UserBuilder userName(String userName){
            if(userName == null){
                throw  new IllegalArgumentException("userName is null");
            }
            this.userName = userName;
            return this;
        }

        public UserBuilder pwd(String pwd){
            this.pwd = pwd;
            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString(){
        return "userName=" + this.userName + "。pwd=" + this.pwd;
    }

    public static void main(String[] args){
        User user = new User.UserBuilder().userName(null).pwd("pwd").build();
        System.out.println(user);
    }

}
