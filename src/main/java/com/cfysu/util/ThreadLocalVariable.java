package com.cfysu.util;

public class ThreadLocalVariable {

    /**
     * 品牌id
     */
    private final static InheritableThreadLocal<Long> brandIdThreadLocal = new InheritableThreadLocal<Long>();

    public static Long getBrandId(){
        return brandIdThreadLocal.get();
    }
    public static void setBrandId(Long brandId){
        brandIdThreadLocal.set(brandId);
    }
}
