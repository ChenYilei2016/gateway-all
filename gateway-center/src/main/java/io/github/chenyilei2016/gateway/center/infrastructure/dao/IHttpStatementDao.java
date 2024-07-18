package io.github.chenyilei2016.gateway.center.infrastructure.dao;

import io.github.chenyilei2016.gateway.center.infrastructure.po.HttpStatement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @description 网关接口映射信息表DAO
 * 
 * 
 */
@Mapper
public interface IHttpStatementDao {

    List<HttpStatement> queryHttpStatementList();

}