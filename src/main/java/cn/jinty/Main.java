package cn.jinty;

/**
 * 入口函数
 * 执行：java -jar daily-practice-1.0.0-SNAPSHOT-jar-with-dependencies.jar
 *
 * @author Jinty
 * @date 2020/11/24
 **/
public class Main {

    public static void main(String[] args) {
        String[] arr = {"abc"};
        for (String a : arr) {
            System.out.printf("字符串=%s, 大写=%s, 小写=%s, 长度=%s%n", a, a.toUpperCase(), a.toLowerCase(), a.length());
        }
    }

}