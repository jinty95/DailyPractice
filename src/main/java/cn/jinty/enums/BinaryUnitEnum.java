package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

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

    B("B", "Byte", BigDecimal.valueOf(1L), null),
    KB("KB", "KiloByte", BigDecimal.valueOf(1024L).multiply(B.bytes), B),
    MB("MB", "MegaByte", BigDecimal.valueOf(1024L).multiply(KB.bytes), KB),
    GB("GB", "GigaByte", BigDecimal.valueOf(1024L).multiply(MB.bytes), MB),
    TB("TB", "TrillionByte", BigDecimal.valueOf(1024L).multiply(GB.bytes), GB),
    PB("PB", "PetaByte", BigDecimal.valueOf(1024L).multiply(TB.bytes), TB),
    EB("EB", "ExaByte", BigDecimal.valueOf(1024L).multiply(PB.bytes), PB),
    ZB("ZB", "ZettaByte", BigDecimal.valueOf(1024L).multiply(EB.bytes), EB),
    YB("YB", "YottaByte", BigDecimal.valueOf(1024L).multiply(ZB.bytes), ZB),
    BB("BB", "BrontoByte", BigDecimal.valueOf(1024L).multiply(YB.bytes), YB),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;
    // 字节数
    private final BigDecimal bytes;
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
        BigDecimal sourceUnitBytes = sourceUnit.getBytes();
        BigDecimal targetUnitBytes = targetUnit.getBytes();
        BigDecimal bytes = sourceNum.multiply(sourceUnitBytes);
        if (targetUnit == B) {
            return bytes.setScale(0, RoundingMode.HALF_UP);
        }
        return bytes.divide(targetUnitBytes, 2, RoundingMode.HALF_UP);
    }

    /**
     * 单位转成字节
     *
     * @param sourceNum  源数值
     * @param sourceUnit 源单位
     * @return 字节数
     */
    public static BigDecimal transferUnitToByte(BigDecimal sourceNum, BinaryUnitEnum sourceUnit) {
        return transferUnit(sourceNum, sourceUnit, B);
    }

    /**
     * 单位从字节转成指定的单位
     *
     * @param sourceNum  源数值
     * @param targetUnit 目标单位
     * @return 目标数值
     */
    public static BigDecimal transferUnitFromByte(BigDecimal sourceNum, BinaryUnitEnum targetUnit) {
        return transferUnit(sourceNum, B, targetUnit);
    }

    /**
     * 单位从字节转成合适的单位
     *
     * @param bytes 字节数
     * @return 数值(保留2位小数) + 单位
     */
    public static String transferUnitFromByte(long bytes) {
        for (BinaryUnitEnum unit : Arrays.asList(B, KB, MB, GB, TB)) {
            if (bytes < unit.getBytes().longValue()) {
                BinaryUnitEnum targetUnit = unit.getLast() != null ? unit.getLast() : unit;
                BigDecimal targetNum = BinaryUnitEnum.transferUnit(BigDecimal.valueOf(bytes), BinaryUnitEnum.B, targetUnit);
                return targetNum + targetUnit.getCode();
            }
        }
        return bytes + B.getCode();
    }

}