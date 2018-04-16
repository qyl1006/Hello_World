package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Systemmenu extends BaseDomain{
    private String name;
    private String url;
    private String sn;

    //many-one  子菜单-->父菜单
    private Systemmenu parent;
}
