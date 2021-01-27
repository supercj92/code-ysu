package com.cfysu.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.GregorianCalendar;

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
import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * @Author canglong
 * @Date 2020/6/9
 */
public class SerialUtil {

    public static byte[] serializeByJdk(Object object) throws Exception{
        byte[] data = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(os);
        output.writeObject(object);
        output.flush();
        output.close();
        data = os.toByteArray();
        return data;
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

    public static <T> T deserializeByKryo(byte[] in) throws Exception {
        Input input = null;
        ByteArrayInputStream bais = null;
        bais = new ByteArrayInputStream(in);
        input = new Input(bais);
        input.close();
        return (T)createKryo().readObject(input, UserDO.class);
    }

    public static void writeBytesToFile(byte[] bytes, File file) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];

        while (byteArrayInputStream.read(buffer) != -1){
            fileOutputStream.write(buffer);
        }
        byteArrayInputStream.close();
        fileOutputStream.close();
    }

    public static byte[] readBytesFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        while (fileInputStream.read(buffer) != -1){
            byteArrayOutputStream.write(buffer);
        }
        fileInputStream.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static void main(String[] args) throws Exception {

        UserDO user = new UserDO();
        user.setName("jack");
        user.setAge(10);
        //user.setCarDO(new CarDO1("ford"));

        ////1.jdk
        //1.1 write
        //byte[] bytes = serializeByJdk(user);
        //writeBytesToFile(bytes, new File("./user.jdk"));

        //1.2 read
        //byte[] bytesFromFile = readBytesFromFile(new File("./user.jdk"));
        //UserDO userDO = deserializeByJdk(bytesFromFile);
        //System.out.println(userDO);

        //2.kryo
        ////2.1 write
        //byte[] seBytes = serializeByKryo(user);
        //writeBytesToFile(seBytes, new File("./user.kryo"));
        //
        ////2.2 read
        //byte[] deBytes = readBytesFromFile(new File("./user.kryo"));
        //UserDO userDO = deserializeByKryo(deBytes);
        //System.out.println(userDO);

        //3.hessian
        //3.1 write
        //byte[] bytes = serializeByHessian(user);
        //writeBytesToFile(bytes, new File("./user.hessian"));

        //3.2 read
        //byte[] bytesFromFile = readBytesFromFile(new File("./user.hessian"));
        //UserDO userDO = deserializeByHessian(bytesFromFile);
        //System.out.println(userDO);

        //4.hessian2
        //4.1 write
        //byte[] bytes = serializeByHessian2(user);
        //writeBytesToFile(bytes, new File("./user.hessian2"));

        //4.2 read
        //byte[] bytesFromFile = readBytesFromFile(new File("./user.hessian2"));
        //UserDO userDO = deserializeByHessian(bytesFromFile);
        //System.out.println(userDO);
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