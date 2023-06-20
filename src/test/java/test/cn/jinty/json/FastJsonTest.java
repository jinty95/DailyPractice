package test.cn.jinty.json;

import cn.jinty.entity.BaseResponse;
import cn.jinty.entity.KeyValue;
import cn.jinty.entity.page.PageResponse;
import cn.jinty.util.string.EscapeStringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * FastJson - 测试
 *
 * @author Jinty
 * @date 2021/12/18
 **/
public class FastJsonTest {

    private JSONObject build() {
        JSONObject json = new JSONObject();
        KeyValue<String, String> kv = new KeyValue<>("name", "hello");
        // 值为对象
        json.put("key1", kv);
        // 值为对象JSON字符串
        json.put("key2", JSON.toJSONString(kv));
        // 值为数组
        json.put("key3", new int[]{1, 2, 3});
        // 值为数组JSON字符串
        json.put("key4", JSON.toJSONString(new int[]{1, 2, 3}));
        json.put("key5", "hi");
        json.put("key6", null);
        return json;
    }

    @Test
    public void testToString() {
        JSONObject json = build();
        // 默认情况下，值为null的键值对会被忽略
        System.out.println(json);
        System.out.println(JSON.toJSONString(json));
        // 通过配置WriteMapNullValue，显示值为null的键值对
        System.out.println(json.toString(SerializerFeature.WriteMapNullValue));
        System.out.println(JSON.toJSONString(json, SerializerFeature.WriteMapNullValue));
    }

    @Test
    public void testGet() {
        JSONObject json = build();
        System.out.println("key1 is " + json.get("key1"));
        System.out.println("key2 is " + json.get("key2"));
        System.out.println("key3 is " + json.get("key3"));
        System.out.println("key4 is " + json.get("key4"));
        System.out.println("key5 is " + json.get("key5"));
        System.out.println("key6 is " + json.get("key6"));
    }

    @Test
    public void testGetJSON() {
        JSONObject json = build();
        // 值为对象、对象JSON字符串，可转为JSONObject
        System.out.println("key1 is " + json.getJSONObject("key1"));
        System.out.println("key2 is " + json.getJSONObject("key2"));
        // 值为数组、数组JSON字符串，可转为JSONArray
        System.out.println("key3 is " + json.getJSONArray("key3"));
        System.out.println("key4 is " + json.getJSONArray("key4"));
        // 值为普通值，抛异常
        System.out.println("key5 is " + json.getJSONObject("key5"));
    }

    @Test
    public void testParse() {
        String jsonStr = "{\"key1\":{\"key\":\"name\",\"value\":\"hello\"},\"key2\":\"{\\\"key\\\":\\\"name\\\",\\\"value\\\":\\\"hello\\\"}\",\"key5\":\"hi\",\"key6\":null,\"key3\":[1,2,3],\"key4\":\"[1,2,3]\"}";
        JSONObject json = JSON.parseObject(jsonStr);
        // 值为对象，解析为对象
        System.out.println(json.get("key1"));
        System.out.println(json.getJSONObject("key1").toJavaObject(KeyValue.class));
        System.out.println(JSON.parseObject(json.getString("key1"), KeyValue.class));
        System.out.println();
        // 值为对象JSON字符串，解析为对象
        System.out.println(json.get("key2"));
        System.out.println(json.getJSONObject("key2").toJavaObject(KeyValue.class));
        System.out.println(JSON.parseObject(json.getString("key2"), KeyValue.class));
        System.out.println();
        // 值为数组，解析为数组
        System.out.println(json.get("key3"));
        System.out.println(Arrays.toString(json.getJSONArray("key3").toArray()));
        System.out.println(Arrays.toString(JSON.parseArray(json.getString("key3")).toArray()));
        System.out.println();
        // 值为数组JSON字符串，解析为数组
        System.out.println(json.get("key4"));
        System.out.println(Arrays.toString(json.getJSONArray("key4").toArray()));
        System.out.println(Arrays.toString(JSON.parseArray(json.getString("key4")).toArray()));
        System.out.println();
    }

    @Test
    public void testParseNull() {
        String jsonStr = "";
        System.out.println(JSONObject.parseObject(jsonStr, KeyValue.class));
        jsonStr = "{}";
        System.out.println(JSONObject.parseObject(jsonStr, KeyValue.class));
        jsonStr = "{\"name\":\"001\"}";
        System.out.println(JSONObject.parseObject(jsonStr, KeyValue.class));
        jsonStr = "{\"key\":1,\"value\":1}";
        System.out.println(JSONObject.parseObject(jsonStr, KeyValue.class));
    }

    @Test
    public void testGenerics() {
        BaseResponse<PageResponse<String>> response = BaseResponse.success(PageResponse.empty());
        System.out.println("原对象：" + response);
        String json = JSON.toJSONString(response);
        System.out.println("原对象转Json：" + json);
        response = JSON.parseObject(json, new TypeReference<BaseResponse<PageResponse<String>>>() {
        });
        System.out.println("Json恢复原对象：" + response);
    }

    @Test
    public void testParseByte() {
        JSONObject json = new JSONObject();
        json.put("key1", true);
        json.put("key2", 1);
        json.put("key3", "1");
        json.put("key4", null);
        System.out.println(json.getByte("key1"));
        System.out.println(json.getByte("key2"));
        System.out.println(json.getByte("key3"));
        System.out.println(json.getByte("key4"));
    }

    @Test
    public void testParseMap() {
        String[] jsonList = {
                "",
                "{}",
                "{\"id\":1, \"name\":\"2\", \"age\":\"23\"}",
                "{\\\"id\\\":1, \\\"name\\\":\\\"2\\\", \\\"age\\\":\\\"23\\\"}",
                "{\\\\\\\"id\\\\\\\":1, \\\\\\\"name\\\\\\\":\\\\\\\"2\\\\\\\", \\\\\\\"age\\\\\\\":\\\\\\\"23\\\\\\\"}"
        };
        for (String json : jsonList) {
            Map map = JSON.parseObject(EscapeStringUtil.unescapeAll(json), Map.class);
            System.out.println("json字符串: " + json);
            System.out.println("解析成Map: " + map);
        }
    }

}
