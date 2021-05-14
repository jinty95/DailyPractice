package cn.jinty.util;

/**
 * @Description 命名工具类
 * @Author jinty
 * @Date 2019/9/9.
 */
public final class NameUtil {

    //下划线命名转驼峰命名
    public static String snakeToCamel(String snake){
        if(snake.charAt(0)=='_'||snake.charAt(snake.length()-1)=='_'){
            throw new IllegalArgumentException("输入不符合下划线命名法");
        }
        StringBuilder camel = new StringBuilder();
        for(int i=0;i<snake.length();i++){
            if(snake.charAt(i)!='_'){
                camel.append(snake.charAt(i));
            }else{
                camel.append((char)(snake.charAt(i+1)-32));
                i++;
            }
        }
        return camel.toString();
    }

    //驼峰命名转下划线命名
    public static String camelToSnake(String camel){
        if(camel.charAt(0)>='A'&&camel.charAt(0)<='Z'){
            throw new IllegalArgumentException("首字母大写违背驼峰命名法");
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<camel.length();i++){
            if(camel.charAt(i)>='A'&&camel.charAt(i)<='Z'){
                sb.append('_');
                sb.append((char)(camel.charAt(i)+32));
            }else{
                sb.append(camel.charAt(i));
            }
        }
        return sb.toString();
    }

}
