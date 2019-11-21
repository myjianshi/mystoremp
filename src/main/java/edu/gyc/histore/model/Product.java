package edu.gyc.histore.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.gyc.histore.enums.OrderStatusEnum;
import edu.gyc.histore.enums.ProductStatusEnum;
import edu.gyc.histore.utils.EnumUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
@NotBlank(message = "商品名不为空")
    private String productName;

    /**
     * 单价
     */
    @NotNull(message = "商品价格不为空")
    @DecimalMin(value = "0.01",message = "商品最小值为0.01元")
    @DecimalMax(value = "10000",message = "商品最大值为10000元")
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @NotNull(message = "商品库存不为空")
    @Range(min = 1,max = 10000,message = "商品库存量介于1-10000")
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 商品状态,0正常1下架
     */

    private Integer productStatus;

    @JsonIgnore   //输出json数据时忽略
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

    /**
     * 类目编号
     */
    @NotNull(message = "类型编号不为空")
    private Integer categoryType;

    @JsonIgnore
    private transient String categoryTypeName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryType() {
        return categoryType;
    }


    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }



    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Product{" +
        "id=" + id +
        ", productName=" + productName +
        ", productPrice=" + productPrice +
        ", productStock=" + productStock +
        ", productDescription=" + productDescription +
        ", productIcon=" + productIcon +
        ", productStatus=" + productStatus +
        ", categoryType=" + categoryType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
