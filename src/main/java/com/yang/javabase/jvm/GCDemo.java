package com.yang.javabase.jvm;

public class GCDemo {

    private static final int _1MB = 1024 * 1024;

    // JVM 配置参数  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    public static void main(String[] args) {

        testAllocation();

    }

    static void testAllocation(){

        byte[] a1,a2,a3,a4;

        a1 = new byte[2 * _1MB];
        a2 = new byte[2* _1MB];
        a2 = new byte[2 * _1MB];
        //a4 = new byte[3 * _1MB];

    }

}
