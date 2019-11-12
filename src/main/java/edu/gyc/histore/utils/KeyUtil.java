package edu.gyc.histore.utils;

import java.util.Random;

/**
 * Created by 廖师兄
 * 2017-06-11 19:12
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized Long genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;


        return System.currentTimeMillis()*1000000 + number;
    }
}
