package io.github.chenyilei2016.gateway.center.domain.service;

import io.github.chenyilei2016.gateway.center.application.IApiService;
import io.github.chenyilei2016.gateway.center.domain.model.ApiData;
import io.github.chenyilei2016.gateway.center.domain.repository.IApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * @description API 服务
 * 
 * 
 */
@Service
public class ApiServiceImpl implements IApiService {

    @Resource
    private IApiRepository apiRepository;

    @Override
    public List<ApiData> queryHttpStatementList() {
        return apiRepository.queryHttpStatementList();
    }

}
