package edu.gyc.histore.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Lang.Chen
 * @date 2018/6/20 下午4:39
 */
@Data
public class Whore {

    //账号
    @NotEmpty(message = "芳名不能为空")
    private String name;
    //密码
    @NotNull(message = "价格不能为空")
    @Range(min = 100,max=1000,message = "价格要合适100-1000")
    private Integer price;


}




