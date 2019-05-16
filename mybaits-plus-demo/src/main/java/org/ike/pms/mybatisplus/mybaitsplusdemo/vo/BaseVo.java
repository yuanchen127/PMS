package org.ike.pms.mybatisplus.mybaitsplusdemo.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

@Data
@Accessors(chain = true)
public class BaseVo {
    String table;

    TUser param;
}
