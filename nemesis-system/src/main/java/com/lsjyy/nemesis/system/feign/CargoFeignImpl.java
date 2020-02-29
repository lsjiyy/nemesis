package com.lsjyy.nemesis.system.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 18:08
 * @Description:
 */
@Component
public class CargoFeignImpl implements CargoFeign {

    @Override
    public AjaxResult callServer() {
        return AjaxResult.error("服务故障");
    }
}
