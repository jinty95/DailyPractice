package cn.jinty.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * XML - 工具类
 *
 * @author Jinty
 * @date 2022/4/1
 **/
public final class XmlUtil {

    private XmlUtil() {
    }

    /**
     * 对象转XML
     *
     * @param obj       对象
     * @param keepHead  是否保留XML头部
     * @param formatted 是否格式化XML
     * @return XML字符串
     * @throws JAXBException 异常
     */
    public static String objToXml(Object obj, boolean keepHead, boolean formatted) throws JAXBException {
        if (obj == null) {
            return null;
        }
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        if (!keepHead) {
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        }
        if (formatted) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        }
        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        return sw.toString();
    }

    /**
     * 对象转XML
     * (不保留XML头部，不格式化XML)
     *
     * @param obj 对象
     * @return XML字符串
     * @throws JAXBException 异常
     */
    public static String objToXml(Object obj) throws JAXBException {
        return objToXml(obj, false, false);
    }

    /**
     * XML转对象
     *
     * @param xml XML字符串
     * @param <T> 对象类型
     * @return 对象
     * @throws JAXBException 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToObj(String xml, Class<T> clazz) throws JAXBException {
        if (xml == null || clazz == null) {
            return null;
        }
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Reader reader = new StringReader(xml);
        return (T) unmarshaller.unmarshal(reader);
    }

}
