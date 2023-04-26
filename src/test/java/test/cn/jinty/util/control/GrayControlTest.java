package test.cn.jinty.util.control;

import cn.jinty.util.control.GrayControl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 灰度控制 - 测试
 *
 * @author Jinty
 * @date 2023/4/26
 **/
public class GrayControlTest {

    public static boolean isControl = true;
    public static List<String> keys = Arrays.asList("1", "2", "3");

    public static class MyGrayControl extends GrayControl {
        @Override
        protected boolean isControl() {
            return isControl;
        }

        @Override
        protected boolean isSelected(String key) {
            return keys != null && keys.contains(key);
        }
    }

    private final static GrayControl grayControl = new MyGrayControl();

    @Test
    public void test() {
        for (boolean isControl : new boolean[]{true, false}) {
            GrayControlTest.isControl = isControl;
            for (String key : new String[]{"1", "2", "3", "4", "5", "6"}) {
                boolean toNewFeature = grayControl.toNewFeature(key);
                boolean toOldFeature = grayControl.toOldFeature(key);
                System.out.printf("灰度控制：isControl=%s, keys=%s, key=%s, => %s\n",
                        isControl, keys, key, toNewFeature ? "toNewFeature" : "toOldFeature");
            }
            System.out.println();
        }
    }

}
