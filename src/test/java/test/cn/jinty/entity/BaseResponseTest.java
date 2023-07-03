package test.cn.jinty.entity;

import cn.jinty.entity.BaseResponse;
import cn.jinty.entity.page.PageRequest;
import cn.jinty.entity.page.PageResponse;
import org.junit.Test;

import static cn.jinty.enums.ResponseCodeEnum.*;

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
        BaseResponse<Long> baseResponse2 = BaseResponse.fail("不知道为什么反正就是失败了，真实乌鱼子！");
        System.out.println(baseResponse2);
        BaseResponse<Float> baseResponse3 = BaseResponse.fail(BIZ_LOGIN_FAIL.getCode(), BIZ_LOGIN_FAIL.getDesc());
        System.out.println(baseResponse3);
        BaseResponse<Double> baseResponse4 = BaseResponse.fail(BIZ_LOGIN_ATTEMPT_EXCEED);
        System.out.println(baseResponse4);
    }

    @Test
    public void testPage() {
        PageRequest pageRequest = PageRequest.defaults();
        PageResponse<String> pageResponse = PageResponse.empty(pageRequest);
        BaseResponse<PageResponse<String>> baseResponse = BaseResponse.success(pageResponse);
        System.out.println("默认分页：" + baseResponse);
        pageRequest = PageRequest.one();
        pageResponse = PageResponse.empty(pageRequest);
        baseResponse = BaseResponse.success(pageResponse);
        System.out.println("只查一行：" + baseResponse);
        pageRequest = PageRequest.all();
        pageResponse = PageResponse.empty(pageRequest);
        baseResponse = BaseResponse.success(pageResponse);
        System.out.println("查询所有：" + baseResponse);
    }

}
