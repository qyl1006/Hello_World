package cn.wolfcode.javaconfig._07di_ref;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by wolfcode-lanxw
 */
@Setter@Getter@ToString
public class Nanny {
    private String name;

    public Nanny(String name) {
        this.name = name;
    }
}
