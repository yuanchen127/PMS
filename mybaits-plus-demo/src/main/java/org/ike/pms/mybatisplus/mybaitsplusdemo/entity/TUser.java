package org.ike.pms.mybatisplus.mybaitsplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.ike.pms.mybatisplus.mybaitsplusdemo.config.MultiDatasource.Ike;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ike
 * @since 2019-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type = IdType.ID_WORKER_STR)
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("age")
    private Integer age;

    @TableField("createtime")
    private String createtime;

    @TableField("date")
    private String date;

    @TableField("time")
    private String time;

}
