package edu.gyc.histore.utils;

import edu.gyc.histore.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                log.info("EnumClass {},code:{}",enumClass,code);
                return each;
            }
        }
        return null;
    }
}
