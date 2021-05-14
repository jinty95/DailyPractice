package cn.jinty.util;

/**
 * 字符串工具类
 *
 * @author jinty
 * @date 2021/4/9
 **/
public final class StringUtil {

    //十六进制字符集
    public static char[] hexChar =
            {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

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
        for(int i=0;i<bytes.length;i++){
            byte b = bytes[i];
            //高4位
            sb.append(hexChar[(b>>>4)&15]);
            //低4位
            sb.append(hexChar[b&15]);
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
        return s == null || s.length()==0 || s.trim().length()==0;
    }

}
