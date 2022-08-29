package test.cn.jinty.util;

import cn.jinty.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * XML - 工具类
 *
 * @author Jinty
 * @date 2022/4/1
 **/
public class XmlUtilTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement
    static class XmlObj {
        private Long id;
        private String name;
    }

    @Test
    public void testObjToXml() {
        try {
            boolean[] arr = new boolean[]{true, false};
            for (boolean one : arr) {
                for (boolean two : arr) {
                    System.out.printf("保留头部: %s, 格式化: %s\n", one, two);
                    String xml = XmlUtil.objToXml(new XmlObj(1L, "me"), one, two);
                    System.out.println(xml);
                    System.out.println(xml.replace("id>", "Id>").replace("name>", "Name>"));
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}