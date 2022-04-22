package test.cn.jinty.entity;

import cn.jinty.enums.ErrorCodeEnum;
import cn.jinty.entity.BaseResponse;
import org.junit.Test;

/**
 * 基础响应体 - 测试
 *
 * @author Jinty
 * @date 2022/1/14
 **/
public class BaseResponseTest {

    @Test
    public void testSuccess() {
        BaseResponse<String> baseResponse1 = BaseResponse.success();
        System.out.println(baseResponse1);
        BaseResponse<Long> baseResponse2 = BaseResponse.success(1L);
        System.out.println(baseResponse2);
        BaseResponse<Integer> baseResponse3 = BaseResponse.success("执行成功", 1);
        System.out.println(baseResponse3);
    }

    @Test
    public void testFail() {
        BaseResponse<Integer> baseResponse1 = BaseResponse.fail();
        System.out.println(baseResponse1);
        BaseResponse<Long> baseResponse2 = BaseResponse.fail("执行失败");
        System.out.println(baseResponse2);
        BaseResponse<Float> baseResponse3 = BaseResponse.fail("10001", "超时失败");
        System.out.println(baseResponse3);
        BaseResponse<Double> baseResponse4 = BaseResponse.fail(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        System.out.println(baseResponse4);
    }

}
