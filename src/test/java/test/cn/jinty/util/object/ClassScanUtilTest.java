package test.cn.jinty.util.object;

import cn.jinty.util.object.ClassScanUtil;
import org.junit.Test;

import java.util.Map;

/**
 * 类扫描 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2024/4/20
 */
public class ClassScanUtilTest {

    @Test
    public void testScan() {
        try {
            Map<String, Class> result = ClassScanUtil.scan("cn.jinty.enums");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
