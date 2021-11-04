package cn.jinty.util;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 汉字转拼音 - 工具类
 *
 * @author Jinty
 * @date 2021/5/26
 **/
public final class PinYinUtil {

    /**
     * 汉字转拼音(无声调)
     *
     * @param chinese 汉字
     * @return 拼音
     */
    public static String chineseToPinYin(String chinese) {
        if (StringUtil.isBlank(chinese)) return null;
        try {
            return PinyinHelper.convertToPinyinString(
                    chinese, " ", PinyinFormat.WITHOUT_TONE
            );
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 汉字转拼音首字母缩写
     *
     * @param chinese 汉字
     * @return 拼音首字母缩写
     */
    public static String chineseToShortPinYin(String chinese) {
        if (StringUtil.isBlank(chinese)) return null;
        try {
            return PinyinHelper.getShortPinyin(chinese);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return null;
    }

}
