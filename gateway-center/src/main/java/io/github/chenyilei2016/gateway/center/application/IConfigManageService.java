package io.github.chenyilei2016.gateway.center.application;

import io.github.chenyilei2016.gateway.center.domain.manage.model.vo.GatewayServerVO;

import java.util.List;

/**
 *
 * @description 网关配置服务
 *
 * 
 */
public interface IConfigManageService {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

}
