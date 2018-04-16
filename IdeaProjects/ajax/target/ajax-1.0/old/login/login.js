$(function () {
    $("#bth").bind('click', function () {
        //获取用户输入的数据信息
        var usernameVal = $("input[name='username']").val();
        var passwordVal = $("input[name='password']").val();
        //封装一个JSON对象
        var param = {"username":usernameVal,"password":passwordVal}

       //发送ajax请求
        $.get("/ajax/login.do", param, function (data) {
            if(data.succes){
                //跳转
                console.log(123);
                location.href="https://www.baidu.com/";
            }else{
                $("span").css("color", "red").html(data.msg);
            }
        }, "JSON");


    })
})