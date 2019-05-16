package org.ike.pms.mybatisplus.mybaitsplusdemo.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.ike.pms.mybatisplus.mybaitsplusdemo.entity.TUser;

import java.util.List;

@Data
@Accessors(chain = true)
public class TUserBatchVo {

    private String table;

    private List<TUser> list;
}
