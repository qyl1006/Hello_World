package cn.wolfcode.wms.util;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class JSONResult {
    private Boolean success = true;
    private String msg;

    public void mark(String msg){
        this.msg = msg;
        success = false;
    }

}
