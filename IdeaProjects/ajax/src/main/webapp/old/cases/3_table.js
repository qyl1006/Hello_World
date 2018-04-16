//删除当前行
function delRow(src) {
    $(src).parents("tr").remove();
};

$(function () {
    //删除所有
    $("#btn_removeAll").click(function () {
        $("#data").remove();
    });

    //添加
    $("#btn_submit").click(function () {
        //获取用户输入的值
        var usernameVal = $(".username").val();
        var emailVal = $(".email").val();
        var telVal = $(".tel").val();

        //新建4个td
        var usernameTd = $("<td></td>").html(usernameVal);
        var emailTd = $("<td></td>").html(emailVal);
        var telTd = $("<td></td>").html(telVal);
        var otherTd = $("<td><a href=\"javascript:\" onclick=\"delRow(this);\">删除</a></td>");

        //新建一个tr 并添加到 变革表格中
        $("<tr></tr>").append(usernameTd).append(emailTd).append(telTd).append(otherTd)
            .appendTo("#data");

        //情况用户输入的添加数据
        $(".username").val("");
        $(".email").val("");
        $(".tel").val("");

    });

});