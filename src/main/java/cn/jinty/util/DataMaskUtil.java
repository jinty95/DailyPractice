package cn.jinty.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据脱敏 - 工具类
 *
 * @author Jinty
 * @date 2022/5/6
 **/
public final class DataMaskUtil {

    private static final String MASK = "*";
    private static final Map<Integer, String> MASKS = new HashMap<>();

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            sb.append(MASK);
            MASKS.put(i, sb.toString());
        }
    }

    /**
     * 手机号码脱敏
     *
     * @param phone 手机号码
     * @return 脱敏手机号码
     */
    public static String maskPhone(String phone) {
        if (phone == null) {
            return "";
        }
        if (phone.length() <= 7) {
            return phone;
        }
        return phone.substring(0, 3) + MASKS.get(4) + phone.substring(phone.length() - 4);
    }

    /**
     * 姓名脱敏
     *
     * @param name 姓名
     * @return 脱敏姓名
     */
    public static String maskName(String name) {
        if (name == null) {
            return "";
        }
        if (name.length() <= 1) {
            return name;
        }
        if (name.length() == 2) {
            return name.charAt(0) + MASKS.get(1);
        }
        return name.charAt(0) + MASKS.get(1) + name.charAt(name.length() - 1);
    }

    /**
     * 身份证脱敏
     *
     * @param idCard 身份证
     * @return 脱敏身份证
     */
    public static String maskIdCard(String idCard) {
        if (idCard == null) {
            return "";
        }
        if (idCard.length() <= 10) {
            return idCard;
        }
        return idCard.substring(0, 6) + MASKS.get(8) + idCard.substring(idCard.length() - 4);
    }

    /**
     * 邮箱脱敏
     *
     * @param mail 邮箱
     * @return 脱敏邮箱
     */
    public static String maskMail(String mail) {
        if (mail == null) {
            return "";
        }
        int index = mail.indexOf("@");
        if (index == -1) {
            return mail;
        }
        return MASKS.get(4) + mail.substring(index);
    }

}
