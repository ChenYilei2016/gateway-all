package io.github.chenyilei2016.gateway.center.test;

import com.alibaba.fastjson.JSON;
import io.github.chenyilei2016.gateway.center.ApiGatewayApplication;
import io.github.chenyilei2016.gateway.center.application.IConfigManageService;
import io.github.chenyilei2016.gateway.center.domain.manage.model.vo.GatewayServerVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chenyilei
 * @since 2024/08/02 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiGatewayApplication.class)
class ApiGatewayApplicationTest {

    private Logger logger = LoggerFactory.getLogger(ApiGatewayApplicationTest.class);

    @Resource
    private IConfigManageService configManageService;

    @Test
    public void test_queryGatewayServerList() {
        List<GatewayServerVO> gatewayServerVOS = configManageService.queryGatewayServerList();
        logger.info("测试结果：{}", JSON.toJSONString(gatewayServerVOS));
    }

    @Test
    public void test_registerGatewayServerNode() {
        configManageService.registerGatewayServerNode("10001", "api-gateway-g1", "电商支付网关", "127.0.0.196");
        configManageService.registerGatewayServerNode("10001", "api-gateway-g2", "电商支付网关", "127.0.0.197");
        configManageService.registerGatewayServerNode("10001", "api-gateway-g3", "电商配送网关", "127.0.0.198");
    }
}