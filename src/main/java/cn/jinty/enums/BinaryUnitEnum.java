package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 枚举 - 二进制单位
 *
 * @author Jinty
 * @date 2022/4/22
 **/
@ToString
@Getter
@AllArgsConstructor
public enum BinaryUnitEnum implements EnumInterface<String> {

    B("B", "Byte", BigInteger.valueOf(1L), null),
    KB("KB", "KiloByte", BigInteger.valueOf(1024L).multiply(B.bytes), B),
    MB("MB", "MegaByte", BigInteger.valueOf(1024L).multiply(KB.bytes), KB),
    GB("GB", "GigaByte", BigInteger.valueOf(1024L).multiply(MB.bytes), MB),
    TB("TB", "TrillionByte", BigInteger.valueOf(1024L).multiply(GB.bytes), GB),
    PB("PB", "PetaByte", BigInteger.valueOf(1024L).multiply(TB.bytes), TB),
    EB("EB", "ExaByte", BigInteger.valueOf(1024L).multiply(PB.bytes), PB),
    ZB("ZB", "ZettaByte", BigInteger.valueOf(1024L).multiply(EB.bytes), EB),
    YB("YB", "YottaByte", BigInteger.valueOf(1024L).multiply(ZB.bytes), ZB),
    BB("BB", "BrontoByte", BigInteger.valueOf(1024L).multiply(YB.bytes), YB),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;
    // 字节数
    private final BigInteger bytes;
    // 上一级单位
    private final BinaryUnitEnum last;

    /**
     * 单位换算
     *
     * @param sourceNum  源数值
     * @param sourceUnit 源单位
     * @param targetUnit 目标单位
     * @return 目标数值
     */
    public static BigDecimal transferUnit(BigDecimal sourceNum, BinaryUnitEnum sourceUnit, BinaryUnitEnum targetUnit) {
        BigDecimal sourceUnitBytes = new BigDecimal(sourceUnit.getBytes().toString());
        BigDecimal targetUnitBytes = new BigDecimal(targetUnit.getBytes().toString());
        BigDecimal bytes = sourceNum.multiply(sourceUnitBytes);
        if (targetUnit == B) {
            return bytes.setScale(0, RoundingMode.HALF_UP);
        }
        return bytes.divide(targetUnitBytes, 2, RoundingMode.HALF_UP);
    }

}