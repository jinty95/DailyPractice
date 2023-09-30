package test.cn.jinty.util.object;

import cn.jinty.entity.BaseResponse;
import cn.jinty.util.object.IntrospectUtil;
import org.junit.Test;

import java.beans.IntrospectionException;

/**
 * 内省 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/9/30
 */
public class IntrospectUtilTest {

    @Test
    public void testGetSetterAndGetter() {
        String[] props = {"code", "msg", "data"};
        for (String prop : props) {
            try {
                System.out.println(IntrospectUtil.getSetter(BaseResponse.class, prop));
                System.out.println(IntrospectUtil.getGetter(BaseResponse.class, prop));
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        }
    }

}
