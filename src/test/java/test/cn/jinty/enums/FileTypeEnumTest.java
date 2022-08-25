package test.cn.jinty.enums;

import cn.jinty.enums.FileTypeEnum;
import org.junit.Test;

/**
 * 枚举 - 文件类型 - 测试
 *
 * @author Jinty
 * @date 2022/8/25
 **/
public class FileTypeEnumTest {

    @Test
    public void convertToJsObject() {
        StringBuilder sb = new StringBuilder("{");
        for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
            sb.append(fileTypeEnum.getCode()).append(":\"").append(fileTypeEnum.getMimeType()).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        System.out.println(sb);
    }

}
