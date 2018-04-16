package cn.wolfcode.ajax.util;

import lombok.Getter;

//封装返回JSON
@Getter
public class JSONResult {
    //默认操作完成 true
    private boolean succes = true;
    //消息--主要用于操作失败后的错误提示
    private String msg;

    //操作失败调用的方法
    public void mark(String msg){
        this.msg = msg;
        this.succes = false;
    }
}
