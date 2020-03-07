package com.lsjyy.nemesis.order.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.stereotype.Component;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-07 11:33
 * @Description:
 */
@Component
public class CargoFeignImpl implements CargoFeign {
    @Override
    public AjaxResult reduceCargo(String cargoId) {
        return AjaxResult.error();
    }
}
