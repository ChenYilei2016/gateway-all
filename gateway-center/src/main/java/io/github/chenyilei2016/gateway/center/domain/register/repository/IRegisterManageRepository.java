package io.github.chenyilei2016.gateway.center.domain.register.repository;

import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 *
 * @description 接口注册仓储服务
 * 
 *
 */
public interface IRegisterManageRepository {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
