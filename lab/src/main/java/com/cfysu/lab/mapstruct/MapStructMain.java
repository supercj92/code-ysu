package com.cfysu.lab.mapstruct;

import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author canglong
 * @Date 2023/9/20
 */
public class MapStructMain {

    @Test
    public void testMap() {
        DemoMapper mapper = Mappers.getMapper(DemoMapper.class);
        DemoDto chris = mapper.toDto(new Demo("chris"));
        Assert.assertEquals("chris", chris.name);
    }

    public static class Demo {
        String name;

        //public Demo(){}
        public Demo(String name) {
            this.name = name;
        }
    }

    public static class DemoDto {
        String name;
    }

    @Mapper
    public interface DemoMapper {
        DemoDto toDto(Demo demo);
    }
}
