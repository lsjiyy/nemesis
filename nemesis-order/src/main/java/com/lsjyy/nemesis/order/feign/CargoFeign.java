package com.lsjyy.nemesis.order.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-07 11:31
 * @Description:
 */
@FeignClient(value = "nemesis-cargo",fallback = CargoFeignImpl.class)
public interface CargoFeign {
    @RequestMapping(value = "/reduce",method = RequestMethod.POST)
    AjaxResult reduceCargo(@RequestParam(value = "cargoId")String cargoId);
}
