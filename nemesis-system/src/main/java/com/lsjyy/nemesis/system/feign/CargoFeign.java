package com.lsjyy.nemesis.system.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 18:08
 * @Description:
 */
@FeignClient(value = "nemesis-cargo", fallback = CargoFeignImpl.class)
public interface CargoFeign {

    @RequestMapping(value = "/call", method = RequestMethod.GET)
    AjaxResult callServer();
}
