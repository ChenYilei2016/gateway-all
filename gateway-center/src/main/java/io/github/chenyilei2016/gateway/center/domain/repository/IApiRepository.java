package io.github.chenyilei2016.gateway.center.domain.repository;


import io.github.chenyilei2016.gateway.center.domain.model.ApiData;

import java.util.List;

/**
 * 
 * @description API 仓储
 * 
 * 
 */
public interface IApiRepository {

    List<ApiData> queryHttpStatementList();

}
