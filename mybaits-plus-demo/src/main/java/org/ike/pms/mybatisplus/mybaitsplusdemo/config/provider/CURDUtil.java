package org.ike.pms.mybatisplus.mybaitsplusdemo.config.provider;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class CURDUtil {
    public static String getSelectFields(Wrapper wrapper) {
        try {
            if (wrapper != null) {
                String var1 = wrapper.getSqlSelect();
                if (StringUtils.isNotEmpty(var1)) {
                    return var1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "*";
    }

    public static String getCondition(Wrapper wrapper) {
        try {
            if (wrapper != null) {
                String condition = wrapper.getSqlSegment();
                if (StringUtils.isNotEmpty(condition)) {
                    return condition;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1=1";
    }

}
