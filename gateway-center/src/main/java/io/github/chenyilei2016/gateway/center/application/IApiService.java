package io.github.chenyilei2016.gateway.center.application;


import io.github.chenyilei2016.gateway.center.domain.model.ApiData;

import java.util.List;

/**
 * @description API 服务
 */
public interface IApiService {

    List<ApiData> queryHttpStatementList();

}
