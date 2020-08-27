package com.yang.javabase.news;


import com.yang.javabase.news.interfaces.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * lambda 行为参数化
 *
 *  自定义供测试lambda接口，以及实现
 *  对比使用lambda编码与传统编码对文件取数据的例子
 *
 *  使用lambda需要自定义的接口
 *  1.BufferedReaderProcessor
 *
 *  抽取的方法
 *  1.processFile
 *
 */

public class LambdaAction {


    public static void main(String[] args) throws IOException {

        String oneLine = processFile((BufferedReader br) -> br.readLine());

        String oneTwo = processFile((BufferedReader br)->br.readLine() + br.readLine());

        System.out.println(oneLine);
        System.out.println(oneTwo);

    }




    /**
     * 从一个文件中读取数据
     * @return
     * @throws IOException
     */
    static String oldFun() throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(LambdaAction.class.getClassLoader().getResource("data.txt").getPath()))){

            return br.readLine();
        }
    }

    static String processFile(BufferedReaderProcessor processor) throws IOException{

        try (BufferedReader br = new BufferedReader(new FileReader(LambdaAction.class.getClassLoader().getResource("data.txt").getPath()))){

            return processor.process(br);
        }
    }

}
