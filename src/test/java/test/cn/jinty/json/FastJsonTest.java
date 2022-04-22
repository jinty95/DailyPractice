package test.cn.jinty.json;

import cn.jinty.entity.KeyValue;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * FastJson - 测试
 *
 * @author Jinty
 * @date 2021/12/18
 **/
public class FastJsonTest {

    private JSONObject build() {
        KeyValue<String, String> kv = new KeyValue<>("name", "hello");
        JSONObject json = new JSONObject();
        json.put("key1", kv);
        json.put("key2", JSONObject.toJSONString(kv));
        json.put("key3", "hi");
        return json;
    }

    @Test
    public void testToString() {
        JSONObject json = build();
        System.out.println(json);
        System.out.println(json.toJSONString());
        System.out.println(JSONObject.toJSONString(new Object()));
    }

    @Test
    public void testGet() {
        JSONObject json = build();
        System.out.println("key1 is " + json.get("key1"));
        System.out.println("key2 is " + json.get("key2"));
        System.out.println("key3 is " + json.get("key3"));
    }

    @Test
    public void testGetString() {
        JSONObject json = build();
        System.out.println("key1 is " + json.getString("key1"));
        System.out.println("key2 is " + json.getString("key2"));
        System.out.println("key3 is " + json.getString("key3"));
    }

    @Test
    public void testGetJSONObject() {
        JSONObject json = build();
        System.out.println("key1 is " + json.getJSONObject("key1"));
        System.out.println("key2 is " + json.getJSONObject("key2"));
        System.out.println("key3 is " + json.getJSONObject("key3"));
    }

    @Test
    public void testParse() {
        JSONObject json = build();
        System.out.println(json.getJSONObject("key1").toJavaObject(KeyValue.class));
        System.out.println(JSONObject.parseObject(json.getString("key2"), KeyValue.class));
    }

    @Test
    public void testParseNull() {
        String param = "";
        System.out.println(JSONObject.parseObject(param, KeyValue.class));
        param = "{}";
        System.out.println(JSONObject.parseObject(param, KeyValue.class));
        param = "{\"plazaCode\":\"001\"}";
        System.out.println(JSONObject.parseObject(param, KeyValue.class));
    }

}
