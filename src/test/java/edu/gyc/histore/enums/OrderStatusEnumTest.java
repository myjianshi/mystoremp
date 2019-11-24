package edu.gyc.histore.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class OrderStatusEnumTest {

    @Test
    void getCode() {

        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            log.info(""+orderStatusEnum);
            log.info("code {} message {}",orderStatusEnum.getCode(),orderStatusEnum.getMessage());

        }
    }

    @Test
    void getMessage() {
    }
}