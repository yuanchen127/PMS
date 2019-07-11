package org.ike.pms.mybatisplus.mybaitsplusdemo.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;
import org.springframework.stereotype.Component;

@Data
@Accessors(chain = true)
@Component
public class BaseVo {
    String table;

    TUser param;
}
