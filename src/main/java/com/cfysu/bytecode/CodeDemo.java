package com.cfysu.bytecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_SUPER;
import static jdk.internal.org.objectweb.asm.Opcodes.ALOAD;
import static jdk.internal.org.objectweb.asm.Opcodes.ARETURN;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKESTATIC;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode.RETURN;

/**
 * @Author canglong
 * @Date 2021/4/7
 * 1、生成class字节码
 * 2、classloader加载字节码，生成class
 * 3、根据class，得到实例
 */
public class CodeDemo {
    public static void main(String[] args) throws Exception {

        CodeDemo demo = new CodeDemo();
        demo.test();

        //byte[] classBytes = dumpImpl(className, intfName);
        //byte[] intfBytes = dumpIntf(intfName);
        //
        //ClassLoader parentClassloader = CodeDemo.class.getClassLoader();
        //ClassLoader classLoader = new ClassLoader(parentClassloader) {
        //    @Override
        //    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //        byte[] bytes = name.contains("Impl") ? classBytes : intfBytes;
        //        return defineClass(name, bytes, 0, bytes.length);
        //    }
        //};
        //
        //classLoader.loadClass(intfName);
        //Class<?> aClass = classLoader.loadClass(className);
        //Object obj = aClass.newInstance();
        //Method method = aClass.getMethod("invoke");
        //Object res = method.invoke(obj, new JSONObject());
        //System.out.println(res);
    }

    private void test() throws Exception{
        String implName = "com.cfysu.bytecode.TestServiceImpl";
        String intfName = "com.cfysu.bytecode.TestService";

        byte[] intfCode = dumpIntf(intfName);
        byte[] implCode = dumpImpl(implName, intfName);
        ClassLoader classLoader = this.getClass().getClassLoader();
        ClassLoader cl = new ClassLoader(classLoader) {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                byte[] code = name.contains("Impl") ? implCode : intfCode;
                return defineClass(name, code, 0, code.length);
            }
        };
        cl.loadClass(intfName);
        Class<?> implClass = cl.loadClass(implName);
        Object o = implClass.newInstance();
        System.out.println(o);
    }

    private static byte[] generateClazzBytes(String className, String intfName){
        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER,
            className.replace('.', '/'), null,
            "java/lang/Object", null);

        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
            false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        mv = cw.visitMethod(ACC_PUBLIC, "invoke",
            "(Lcom/alibaba/fastjson/JSONObject;)Lcom/alibaba/fastjson/JSONObject;",
            null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "com/cfysu/bytecode/InvokeUtil",
            "doInvoke",
            "(Lcom/alibaba/fastjson/JSONObject;)Lcom/alibaba/fastjson/JSONObject;",
            false);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 2);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }

    public byte[] dumpImpl(String className, String intfName) throws Exception {

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER,
            className.replace('.', '/'), null,
            "java/lang/Object",
            new String[]{intfName.replace('.', '/')});

        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
            false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        mv = cw.visitMethod(ACC_PUBLIC, "invoke",
            "(Lcom/alibaba/fastjson/JSONObject;)Lcom/alibaba/fastjson/JSONObject;",
            null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "com/alibaba/xlab/util/InvokeUtil",
            "doInvoke",
            "(Lcom/alibaba/fastjson/JSONObject;)Lcom/alibaba/fastjson/JSONObject;",
            false);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 2);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }


    public byte[] dumpIntf(String className) throws Exception {

        ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;
        cw.visit(52, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
            className.replace('.', '/'), null,
            "java/lang/Object", null);
        mv = cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "invoke",
            "(Lcom/alibaba/fastjson/JSONObject;)Lcom/alibaba/fastjson/JSONObject;",
            null, null);
        mv.visitEnd();
        cw.visitEnd();
        return cw.toByteArray();
    }
}
