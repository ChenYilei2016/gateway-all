package io.github.chenyilei2016.gateway.center.test;

import com.alibaba.fastjson.JSON;
import io.github.chenyilei2016.gateway.center.ApiGatewayApplication;
import io.github.chenyilei2016.gateway.center.application.IConfigManageService;
import io.github.chenyilei2016.gateway.center.application.IRegisterManageService;
import io.github.chenyilei2016.gateway.center.domain.manage.model.vo.GatewayServerVO;
import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
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
    @Resource
    private IRegisterManageService registerManageService;

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

    @Test
    public void test_registerApplicationInterfaceMethod() {
        ApplicationInterfaceMethodVO applicationInterfaceVO01 = new ApplicationInterfaceMethodVO();
        applicationInterfaceVO01.setSystemId("api-gateway-test");
        applicationInterfaceVO01.setInterfaceId("io.github.chenyilei2016.providerApi.IActivityBooth");
        applicationInterfaceVO01.setMethodId("sayHi");
        applicationInterfaceVO01.setMethodName("测试方法");
        applicationInterfaceVO01.setParameterType("java.lang.String");
        applicationInterfaceVO01.setUri("/wg/activity/sayHi");
        applicationInterfaceVO01.setHttpCommandType("GET");
        applicationInterfaceVO01.setAuth(0);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO01);

        ApplicationInterfaceMethodVO applicationInterfaceVO02 = new ApplicationInterfaceMethodVO();
        applicationInterfaceVO02.setSystemId("api-gateway-test");
        applicationInterfaceVO02.setInterfaceId("io.github.chenyilei2016.providerApi.IActivityBooth");
        applicationInterfaceVO02.setMethodId("insert");
        applicationInterfaceVO02.setMethodName("插入方法");
        applicationInterfaceVO02.setParameterType("io.github.chenyilei2016.providerApi.dto.XReq");
        applicationInterfaceVO02.setUri("/wg/activity/insert");
        applicationInterfaceVO02.setHttpCommandType("POST");
        applicationInterfaceVO02.setAuth(1);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceVO02);
    }
}