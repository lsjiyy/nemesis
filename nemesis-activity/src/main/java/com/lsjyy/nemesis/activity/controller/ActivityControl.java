package com.lsjyy.nemesis.activity.controller;

import com.lsjyy.nemesis.common.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-12 17:56
 * @Description:
 */
@RestController
public class ActivityControl {
    private static final Logger log = LoggerFactory.getLogger(ActivityControl.class);

    @GetMapping("/call")
    public AjaxResult activityAnswer() {
        return AjaxResult.success();
    }
}
