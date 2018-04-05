package cn.wolfcode.ajax.linkage;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
//省份数据
@Getter
public class Province {
    private Long id;
    private String name;

    private static List<Province> provinces;
    static {
        provinces = Arrays.asList(
                new Province(1L, "广东省"),
                new Province(2L, "四川省"),
                new Province(3L, "浙江省")
        );
    }

    public Province(long id, String province) {
        this.id = id;
        this.name = province;
    }

    public static List<Province> getProvinces() {
        return provinces;
    }
}