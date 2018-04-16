package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Role extends BaseDomain{
    private Long id;
    private String name;

    private String sn;

    //many-many
    private List<Permission> permissions = new ArrayList<>();


}