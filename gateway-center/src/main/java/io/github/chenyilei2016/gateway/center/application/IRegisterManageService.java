package io.github.chenyilei2016.gateway.center.application;


import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationInterfaceMethodVO;
import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationInterfaceVO;
import io.github.chenyilei2016.gateway.center.domain.register.model.vo.ApplicationSystemVO;

/**
 *
 * @description 接口注册服务
 *
 * 
 */
public interface IRegisterManageService {

    void registerApplication(ApplicationSystemVO applicationSystemVO);

    void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO);

    void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO);

}
