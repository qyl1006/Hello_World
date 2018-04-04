//文档加载完毕执行函数
onload = function () {
    //获取到时间源,绑定失去焦点事件---失去光标触发
    document.getElementById("tx").onblur = function () {
        //获取文本框的值,作为参数---用户输入的
        var param = "?username=" + this.value;
        //1创建ajax对象
        var ajax = new XMLHttpRequest();
        //2开启一个请求
        ajax.open("GET", "/ajax/checkUser.do" + param);
        //3监听ajax状态的改变
        ajax.onreadystatechange = function () {
            //5处理响应
            if(ajax.readyState == 4 && ajax.status == 200){
                //把响应的数据回显到网页#msg中
                document.getElementById("msg").innerHTML = ajax.responseText;
            }

        };
        //4发送请求
        ajax.send();

    }
}