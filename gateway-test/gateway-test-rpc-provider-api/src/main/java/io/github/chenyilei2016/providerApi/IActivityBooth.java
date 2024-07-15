package io.github.chenyilei2016.providerApi;


import io.github.chenyilei2016.providerApi.dto.XReq;

public interface IActivityBooth {

    String sayHi(String str);

    String insert(XReq req);

    String test(String str, XReq req);

}
