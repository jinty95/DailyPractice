package test.cn.jinty.util;

import cn.jinty.util.XmlUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
    @XmlAccessorType(XmlAccessType.FIELD)  // 这个注解用于解决这个报错：2 counts of IllegalAnnotationExceptions 类的两个属性具有相同名称 "id"
    @XmlRootElement(name = "MyField")
    static class XmlObj {
        @XmlElement(name = "ID")
        private Long id;
        @XmlElement(name = "Name")
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
                    System.out.println();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXmlToObj() {
        try {
            String xml = XmlUtil.objToXml(new XmlObj(1L, "me"));
            System.out.println(xml);
            XmlObj obj = XmlUtil.xmlToObj(xml, XmlObj.class);
            System.out.println(obj);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}