package com.cfysu.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.GregorianCalendar;

import com.alibaba.alime.dialog.api.Attributes;
import com.alibaba.alime.dialog.api.output.OutputMessage;
import com.alibaba.fastjson.JSON;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import de.javakaffee.kryoserializers.ArraysAsListSerializer;
import de.javakaffee.kryoserializers.GregorianCalendarSerializer;
import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.objenesis.strategy.StdInstantiatorStrategy;

import static com.cfysu.io.FileUtil.readBytesFromFile;
import static com.cfysu.io.FileUtil.writeBytesToFile;

/**
 * @Author canglong
 * @Date 2020/6/9
 */
@Slf4j
public class SerialUtil {

    public static byte[] serializeByJdk(Object object) {
        try {
            byte[] data = null;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(os);
            output.writeObject(object);
            output.flush();
            output.close();
            data = os.toByteArray();
            return data;
        }catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }

    public static <T> T deserializeByJdk(byte[] data) throws Exception{
        if(data==null){
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        Object object = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        object = objectInputStream.readObject();
        objectInputStream.close();
        return (T)object;
    }

    public static byte[] serializeByHessian(Object object) throws Exception{
        byte[] data = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput output = new HessianOutput(os);
        output.writeObject(object);
        data = os.toByteArray();
        output.close();
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializeByHessian(byte[] data) throws Exception{
        if(data==null){
            return null;
        }
        Object result = null;
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        HessianInput input = new HessianInput(is);
        result = input.readObject();
        input.close();
        return (T)result;
    }

    public static byte[] serializeByHessian2(Object object) throws Exception{
        byte[] data = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(object);
        output.getBytesOutputStream().flush();
        output.completeMessage();
        output.close();
        data = os.toByteArray();
        return data;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializeByHessian2(byte[] data) throws Exception{
        if(data==null){
            return null;
        }
        Object result = null;
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        Hessian2Input input = new Hessian2Input(is);
        result = input.readObject();
        input.close();
        return (T)result;
    }

    public static byte[] serializeByKryo(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        createKryo().writeClassAndObject(output, o);
        output.close();
        baos.close();
        return baos.toByteArray();
    }

    public static <T> T deserializeByKryo(byte[] in, Class c) throws Exception {
        Input input = null;
        ByteArrayInputStream bais = null;
        bais = new ByteArrayInputStream(in);
        input = new Input(bais);
        input.close();
        T t = (T)createKryo().readObject(input, OutputMessage.class);
        return t;
    }

    public static void main(String[] args) throws Exception {

        //OutputMessage outputMessage = new OutputMessage();
        //Attributes attributes = new Attributes();
        //attributes.put("testKey", "testVal");
        //outputMessage.setAttributes(attributes);

        //UserDO user = new UserDO();
        //user.setName("jack");
        //user.setAge(10);
        //user.setCarDO(new CarDO1("ford"));
        Person person = new Person();
        Cat helloKitty = new HelloKitty();
        person.setCat(helloKitty);

        ////1.jdk
        //1.1 write
        byte[] bytes = serializeByJdk(person);
        writeBytesToFile(bytes, new File("./person.jdk"));
        System.out.println("serialize to file success");

        //1.2 read
        byte[] bytesFromFile = readBytesFromFile(new File("./person.jdk"));
        Person personFromDisk = deserializeByJdk(bytesFromFile);
        System.out.println(JSON.toJSONString(personFromDisk));
        System.out.println(((HelloKitty)personFromDisk.getCat()).name);
        System.out.println("serialize from file success");

        //2.kryo
        ////2.1 write
        //byte[] seBytes = serializeByKryo(outputMessage);
        //writeBytesToFile(seBytes, new File("./outputMessage1.kryo"));
        ////
        //////2.2 read
        //byte[] deBytes = readBytesFromFile(new File("./outputMessage1.kryo"));
        //OutputMessage outputMessage1 = deserializeByKryo(deBytes, OutputMessage.class);
        //System.out.println(JSON.toJSONString(outputMessage1));


        //3.hessian
        //3.1 write
        //byte[] bytes = serializeByHessian(outputMessage);
        //writeBytesToFile(bytes, new File("./outputMessage.hessian"));

        //3.2 read
        //byte[] bytesFromFile = readBytesFromFile(new File("./outputMessage.hessian"));
        //OutputMessage outputMessageFrom = deserializeByHessian(bytesFromFile);
        //System.out.println(JSON.toJSONString(outputMessageFrom));


        //4.hessian2
        //4.1 write
        //byte[] bytes = serializeByHessian2(outputMessage);
        //writeBytesToFile(bytes, new File("./outputMessage.hessian2"));
        //
        ////4.2 read
        //byte[] bytesFromFile = readBytesFromFile(new File("./outputMessage.hessian2"));
        //OutputMessage outputMessageFrom = deserializeByHessian(bytesFromFile);
        //System.out.println(JSON.toJSONString(outputMessageFrom));
    }

    @Data
    static class Person implements Serializable {
        private Cat cat;
    }

    interface Cat extends Serializable{}

    static class HelloKitty implements Cat{
        public String name = "helloKitty";
    }

    public static Kryo createKryo(){
        Kryo kryo = new Kryo();
        DefaultInstantiatorStrategy defaultInstantiatorStrategy = new DefaultInstantiatorStrategy();
        defaultInstantiatorStrategy.setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.setInstantiatorStrategy(defaultInstantiatorStrategy);
        kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
        kryo.register(GregorianCalendar.class, new GregorianCalendarSerializer());
        UnmodifiableCollectionsSerializer.registerSerializers(kryo);
        SynchronizedCollectionsSerializer.registerSerializers(kryo);
        return kryo;
    }

}