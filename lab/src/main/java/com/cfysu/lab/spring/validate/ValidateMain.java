package com.cfysu.lab.spring.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import com.cfysu.lab.spring.validate.Foo.Bar;
import com.cfysu.lab.spring.validate.Foo.GroupA;
import com.cfysu.lab.spring.validate.Foo.GroupB;
import com.cfysu.lab.spring.validate.Foo.GroupC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author canglong
 * @Date 2022/7/12
 */
@SpringBootApplication
public class ValidateMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ValidateMain.class, args);
        Validator validator = applicationContext.getBean(Validator.class);
        Foo foo = new Foo();
        foo.setBar(new Bar());

        Set<ConstraintViolation<Foo>> validate = validator.validate(foo, GroupA.class, Default.class);
        System.out.println();

        Set<ConstraintViolation<Foo>> validate1 = validator.validate(foo);
        System.out.println();

        foo.setHeight(99L);
        Set<ConstraintViolation<Foo>> validate2 = validator.validate(foo,  GroupB.class);
        System.out.println();

        foo.setName("");
        Set<ConstraintViolation<Foo>> validate3 = validator.validate(foo,  GroupC.class);
        System.out.println();
    }
}
