package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SystemMenu extends BaseDomain{
    private String name;
    private String url;

    private String sn;

    //父菜单对象--->对应数据库表中列为父菜单的ID   parent_id
    private SystemMenu parent;
}
