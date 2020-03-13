package com.lsjyy.nemesis.gateway;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lsjyy.nemesis.common.domain.AjaxResult;
import com.lsjyy.nemesis.common.domain.InterfaceType;
import com.lsjyy.nemesis.common.jwt.JwtUtil;
import com.lsjyy.nemesis.common.redis.RedisKey;
import com.lsjyy.nemesis.common.redis.RedisUtil;
import com.lsjyy.nemesis.common.role.InterfacePath;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-08 20:45
 * @Description:
 */
@Component
public class NemesisGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(NemesisGlobalFilter.class);

    @Autowired
    private InterfaceUtil interfaceUtil;

    /**
     * 过滤器 在这里进行token认证
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        //获取请求方法
        String method = exchange.getRequest().getMethodValue();
        log.info("path===>{},method ===>{}",path,method);
        //缓存中是否有接口
        String interfaceId = interfaceUtil.checkInterface(path, method);
        String auth = exchange.getRequest().getHeaders().getFirst("Authorization");
        log.info("auth ===>{}",auth);
        //缓存中没有接口
        if (StringUtils.isEmpty(interfaceId)) {
            return chain.filter(exchange);
        } else {
            //请求tou
            //String auth = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (!StringUtils.isEmpty(auth) && auth.length() > 7) {
                //截取token
                String HeadStr = auth.substring(0, 6).toLowerCase();
                if (HeadStr.compareTo("bearer") == 0) {
                    auth = auth.substring(7, auth.length());
                }
                try {
                    Map<String, Object> map = JwtUtil.parseJWT(auth);
                    //解析失败,token过期
                    if (map == null) {
                        return makeResponse(exchange.getResponse(), HttpStatus.UNAUTHORIZED, "token过期,请重新登录");
                    }
                    //判断当前token是否有接口权限
                    List<String> idList = (List<String>) map.get("ROLES");
                    for (String id : idList) {
                        if (interfaceId.equals(id)) {
                            return chain.filter(exchange);
                        }
                    }
                    return makeResponse(exchange.getResponse(), HttpStatus.UNAUTHORIZED, "权限不足");
                } catch (Exception e) {
                    e.printStackTrace();
                    return makeResponse(exchange.getResponse(), HttpStatus.UNAUTHORIZED, "请去登录");
                }
            } else {
                return makeResponse(exchange.getResponse(), HttpStatus.UNAUTHORIZED, "请去登录");
            }
        }

    }

    /**
     * 返回信息
     *
     * @param response
     * @param status
     * @param msg
     * @return
     */
    public Mono<Void> makeResponse(ServerHttpResponse response, HttpStatus status, String msg) {
        response.setStatusCode(status); //响应码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8"); //响应头
        AjaxResult result = AjaxResult.error(msg);
        DataBuffer buffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
