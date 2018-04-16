package cn.wolfcode.wms.util;

import lombok.Getter;

@Getter
public class JSONResult {
    private boolean success = true;
    private String msg;

    public void mark(String msg) {
        this.msg = msg;
        success = false;
    }
}
