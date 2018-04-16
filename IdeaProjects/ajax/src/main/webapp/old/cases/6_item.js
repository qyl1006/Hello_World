//计算
$(function () {
    $("input[tag='price'],input[tag='num']").blur(function () {
        //找到当前输入框/事件源的父元素
        var tr = $(this).parents("tr");

        //获取输入输入的数据
        var price = tr.find("[tag='price']").val();
        var num = tr.find("[tag='num']").val();
        tr.find("span").html(price * num);


    });

    //添加更多
    $("#addMore").click(function () {
        var cp = $("#data tr:first").clone(true);
        cp.find("input").val(0);
        cp.find("span").html(0);

        cp.appendTo("#data");
    });
});