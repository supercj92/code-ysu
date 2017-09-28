package com.cfysu.enums;

public enum MsgTypeEnum {
    MSG_TYPE_TO_SEND("应发送", "0"),MSG_TYPE_SUCCEED("发送成功", "1"),MSG_TYPE_FAIL("发送失败", "2");
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    MsgTypeEnum(String name, String value){
        this.name = name;
        this.value = value;
    }
}
