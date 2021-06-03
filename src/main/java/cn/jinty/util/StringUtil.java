package cn.jinty.util;

/**
 * 字符串 - 工具类
 *
 * @author jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    //十六进制字符集
    public static char[] hexChar = {
            '0','1','2','3','4','5','6','7',
            '8','9','A','B','C','D','E','F'
    };

    /**
     * 字节数组转为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String byteToHexString(byte[] bytes){
        //空数组
        if(bytes==null || bytes.length==0) return null;
        //字符串拼接
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            //高4位
            sb.append(hexChar[(b >>> 4) & 15]);
            //低4位
            sb.append(hexChar[b & 15]);
        }
        return sb.toString();
    }

    /**
     * 字符串空判断
     *
     * @param s 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String s){
        return s == null || s.length()==0;
    }

    /**
     * 字符串空白判断
     *
     * @param s 字符串
     * @return 是否空白
     */
    public static boolean isBlank(String s){
        return isEmpty(s) || s.trim().length()==0;
    }

    /**
     * 下划线命名转驼峰命名
     *
     * @param snake 下划线命名
     * @return 驼峰命名
     */
    public static String snakeToCamel(String snake){
        if(StringUtil.isBlank(snake)
                || snake.charAt(0)=='_'
                || snake.charAt(snake.length()-1)=='_'){
            throw new IllegalArgumentException(String.format("illegal snake name [%s]",snake));
        }
        StringBuilder camel = new StringBuilder();
        for(int i=0;i<snake.length();i++){
            if(snake.charAt(i)!='_'){
                camel.append(snake.charAt(i));
            }else{
                i++;
                camel.append((char)(snake.charAt(i)-32));
            }
        }
        return camel.toString();
    }

    /**
     * 驼峰命名转下划线命名
     *
     * @param camel 驼峰命名
     * @return 下划线命名
     */
    public static String camelToSnake(String camel){
        if(StringUtil.isBlank(camel)
                || (camel.charAt(0)>='A' && camel.charAt(0)<='Z')){
            throw new IllegalArgumentException(String.format("illegal camel name [%s]",camel));
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<camel.length();i++){
            if(camel.charAt(i)>='A' && camel.charAt(i)<='Z'){
                sb.append('_');
                sb.append((char)(camel.charAt(i)+32));
            }else{
                sb.append(camel.charAt(i));
            }
        }
        return sb.toString();
    }

}
