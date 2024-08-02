package io.github.chenyilei2016.gateway.center.infrastructure.dao;

import io.github.chenyilei2016.gateway.center.infrastructure.po.ApplicationInterface;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description 应用接口
 *
 *
 */
@Mapper
public interface IApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

}
