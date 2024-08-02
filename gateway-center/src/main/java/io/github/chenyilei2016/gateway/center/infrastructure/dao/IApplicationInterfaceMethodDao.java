package io.github.chenyilei2016.gateway.center.infrastructure.dao;

import io.github.chenyilei2016.gateway.center.infrastructure.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description 应用接口方法
 *
 *
 */
@Mapper
public interface IApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

}
