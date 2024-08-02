package io.github.chenyilei2016.gateway.center.infrastructure.dao;

import io.github.chenyilei2016.gateway.center.infrastructure.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @description 应用系统
 * 
 *
 */
@Mapper
public interface IApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

}
