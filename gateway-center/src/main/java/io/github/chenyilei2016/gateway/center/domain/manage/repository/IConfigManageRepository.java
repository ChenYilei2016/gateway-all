package io.github.chenyilei2016.gateway.center.domain.manage.repository;

import io.github.chenyilei2016.gateway.center.domain.manage.model.vo.GatewayServerDetailVO;
import io.github.chenyilei2016.gateway.center.domain.manage.model.vo.GatewayServerVO;
import io.github.chenyilei2016.gateway.center.infrastructure.po.GatewayServerDetail;

import java.util.List;

/**
 *
 * @description 网关配置仓储服务
 *
 * 
 */
public interface IConfigManageRepository {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

}
