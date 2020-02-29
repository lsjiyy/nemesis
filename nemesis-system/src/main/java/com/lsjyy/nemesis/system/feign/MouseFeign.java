package com.lsjyy.nemesis.system.feign;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-15 17:28
 * @Description: 定义一个feign接口，通过@ FeignClient（“服务名”），
 * 来指定调用哪个服务。比如在代码中调用了service-hi服务的“/hi”接口，代码如下：
 * 服务消费者这边feign调用时，在所有参数前加上@RequestParam注解。
 * 服务消费者这边feign调用时，指明为GET方式（注:如果不指明method,那么在条件1满足的情况下，采用的是默认的GET方式）。
 * 注：这里条件1和条件2，是“且”的关系(都满足时，才为GET)。
 * <p>
 * 开启断路器需要在配置文件开启  feign.hystrix.enabled: true
 * 并且在FeignClient添加fallback 实现接口
 */
@FeignClient(value = "nemesis-mouse", fallback = MouseFeignImpl.class)
public interface MouseFeign {

    @RequestMapping(value = "/call", method = RequestMethod.GET)
    AjaxResult callServer();
}
