package cn.jinty.util.security;

import cn.jinty.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据脱敏 - 工具类
 *
 * @author Jinty
 * @date 2022/5/6
 **/
public final class DataMaskUtil {

    private DataMaskUtil() {
    }

    public static final String MASK = "*";

    /**
     * 手机号码脱敏 (保留前3后4)
     *
     * @param phone 手机号码
     * @return 脱敏手机号码
     */
    public static String maskPhone(String phone) {
        if (StringUtil.isBlank(phone)) {
            return StringUtil.EMPTY;
        }
        if (phone.length() <= 7) {
            return phone;
        }
        return phone.substring(0, 3) + StringUtil.repeat(MASK, 4) + phone.substring(phone.length() - 4);
    }

    /**
     * 姓名脱敏 (保留前1后1)
     *
     * @param name 姓名
     * @return 脱敏姓名
     */
    public static String maskName(String name) {
        if (StringUtil.isBlank(name)) {
            return StringUtil.EMPTY;
        }
        if (name.length() <= 1) {
            return name;
        }
        if (name.length() == 2) {
            return name.charAt(0) + MASK;
        }
        return name.charAt(0) + MASK + name.charAt(name.length() - 1);
    }

    /**
     * 身份证脱敏 (保留前3后4)
     *
     * @param idCard 身份证
     * @return 脱敏身份证
     */
    public static String maskIdCard(String idCard) {
        if (StringUtil.isBlank(idCard)) {
            return StringUtil.EMPTY;
        }
        if (idCard.length() <= 7) {
            return idCard;
        }
        return idCard.substring(0, 3) + StringUtil.repeat(MASK, 8) + idCard.substring(idCard.length() - 4);
    }

    /**
     * 邮箱脱敏 (保留@之后)
     *
     * @param mail 邮箱
     * @return 脱敏邮箱
     */
    public static String maskMail(String mail) {
        if (StringUtil.isBlank(mail)) {
            return StringUtil.EMPTY;
        }
        int index = mail.indexOf("@");
        if (index == -1) {
            return mail;
        }
        return StringUtil.repeat(MASK, 4) + mail.substring(index);
    }

    public static final Pattern ADDRESS_PATTERN = Pattern.compile("([区县镇])(.*)");

    /**
     * 地址脱敏 (保留"区/县/镇"之前，或者保留前6)
     *
     * @param address 地址
     * @return 脱敏地址
     */
    public static String maskAddress(String address) {
        if (StringUtil.isBlank(address)) {
            return StringUtil.EMPTY;
        }
        Matcher matcher = ADDRESS_PATTERN.matcher(address);
        if (matcher.find()) {
            return matcher.replaceAll("$1" + StringUtil.repeat(MASK, 6));
        } else {
            return StringUtil.substring(address, 0, 6) + StringUtil.repeat(MASK, 6);
        }
    }

}
