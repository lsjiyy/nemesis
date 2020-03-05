package com.lsjyy.nemesis.mouse.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 16:23
 * @Description:
 */
@Component
public class SystemFeignImpl implements SystemFeign {


    @Override
    public AjaxResult getUsrRole() {
        return AjaxResult.warn("服务中断");
    }
}
