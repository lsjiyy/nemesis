package com.lsjyy.nemesis.web.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.kafka.KafkaMsgProducer;
import com.lsjyy.nemesis.web.vo.PlaceOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Authoer LsjYy
 * @DATE 2020-03-04 21:14
 * @Description:
 */
@Service
public class WebServiceImpl implements WebService {
    private static final Logger log = LoggerFactory.getLogger(WebServiceImpl.class);

    @Autowired
    private KafkaMsgProducer msgProducer;


    @Override
    public void rushCargo(PlaceOrderVO vo) {
        log.info("vo ===>{}", vo);
        msgProducer.sendMessage(JSONObject.toJSONString(vo));
    }


}
