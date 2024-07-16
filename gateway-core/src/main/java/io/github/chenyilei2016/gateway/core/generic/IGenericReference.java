package io.github.chenyilei2016.gateway.core.generic;

import java.util.Map;

/**
 * @description 统一泛化调用接口
 */
public interface IGenericReference {

    Object $invoke(Map<String, Object> params);

}