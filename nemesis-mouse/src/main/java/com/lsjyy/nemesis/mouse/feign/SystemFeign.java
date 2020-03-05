package com.lsjyy.nemesis.mouse.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 16:20
 * @Description:
 */
@FeignClient(value = "nemesis-system", fallback = SystemFeignImpl.class)
public interface SystemFeign {

    @RequestMapping(value = "/role/usr", method = RequestMethod.GET)
    AjaxResult getUsrRole();
}
