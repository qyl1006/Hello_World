package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

//系统菜单
@Setter@Getter
public class SystemMenu extends BaseDomain {
    private String name; //菜单名称
    private String url;  //URL
    private String sn;  //菜单编码

    //many2one
    private SystemMenu parent; //父级菜单
}
