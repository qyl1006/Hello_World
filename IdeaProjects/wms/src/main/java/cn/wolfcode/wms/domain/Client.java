package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client extends BaseDomain{
    //客户名字
    private String name;
    //编码
    private String sn;
    //电话
    private String phone;

}