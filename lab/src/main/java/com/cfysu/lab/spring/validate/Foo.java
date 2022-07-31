package com.cfysu.lab.spring.validate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @Author canglong
 * @Date 2022/7/12
 */
public class Foo {

    @NotEmpty(groups = GroupC.class)
    private String name;

    @NotNull(groups = GroupA.class)
    private String age;

    @Min(groups = GroupB.class, value = 100)
    @NotNull(groups = GroupB.class)
    private Long height;

    @Valid
    private Bar bar;

    interface GroupA{}

    interface GroupB{}

    interface GroupC{}

    static class Bar {
        @NotNull
        private String brand;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
