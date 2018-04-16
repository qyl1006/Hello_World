$(function () {
    //找到用户输入数据的框 绑定失去焦点事件
    $("input[tag='price'], input[tag='num']").blur(function () {
        //获取当前事件源所在的行
        var tr = $(this).closest("tr");
        var price = tr.find("[tag='price']").val();
        var num = tr.find("[tag='num']").val();

        tr.find("span").html((price * num).toFixed(2));
    });

    //添加更多的商品
    $("#addMore").click(function () {
        var cp = $("#data tr:first").clone(true);

        cp.find("input").val(0);
        cp.find("span").html(0);

        cp.appendTo("#data");
    });
});