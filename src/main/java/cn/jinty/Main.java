package cn.jinty;

import cn.jinty.enums.common.EnumFactory;

/**
 * 入口函数
 * 执行：java -jar daily-practice-1.0.0-SNAPSHOT-jar-with-dependencies.jar
 *
 * @author Jinty
 * @date 2020/11/24
 **/
public class Main {

    public static void main(String[] args) {
        System.out.println(EnumFactory.getByTypeAndCode("YesNoEnum", (byte) 0));
    }

}