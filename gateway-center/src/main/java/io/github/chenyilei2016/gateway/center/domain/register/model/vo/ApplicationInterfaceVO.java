package io.github.chenyilei2016.gateway.center.domain.register.model.vo;

/**
 *
 * @description 应用接口 VO
 * 
 * 
 */
public class ApplicationInterfaceVO {

    /** 系统标识 */
    private String systemId;
    /** 接口标识 */
    private String interfaceId;
    /** 接口名称 */
    private String interfaceName;
    /** 接口版本 */
    private String interfaceVersion;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

}
