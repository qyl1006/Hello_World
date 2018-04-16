//禁用数组参数加[]的功能
$.ajaxSettings.traditional = true;

/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({
            background: "#CDDAEB"
        });
        $(this).children('td').each(function (index, ele) {
            $(ele).css({
                color: "#1D1E21"
            });
        });
    }).mouseout(function () {
        $(this).css({
            background: "#FFF"
        });
        $(this).children('td').each(function (index, ele) {
            $(ele).css({
                color: "#909090"
            });
        });
    });
    //===========================================
    //跳转到编辑界面
    $(".btn_input").click(function () {
        //data方法就是获取自定义属性  data-xxx
        location.href = $(this).data("url");
    });

    //删除功能
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        showDialog("你确定要删除吗?", function () {
            //发送ajax请求
            $.get(url, function (data) {
                showDialog("删除成功", function () {
                    location.reload();
                });
            }, "json");
        }, true);
    });

    //全选/全不选
    $("#all").click(function () {
        $(".acb").prop("checked", this.checked);
    });

    //批量删除
    $(".btn_batchDelete").click(function () {
        var url = $(this).data("url");
        showDialog("确定要批量删除吗?", function () {
            //获取到选中的复选框
            if (!$(".acb:checked").size()) {//提示至少选中一个
                showDialog("至少选中1个");
                return;
            }
            //获取到选中的复选框对应数据的id
            var ids = $.map($(".acb:checked"), function (ele) {
                return $(ele).data("eid");
            });

            //发送ajax请求,执行后台的批量操作
            $.post(url, {ids: ids}, function (data) {
                showDialog("批量删除成功!", function () {
                    location.reload();
                });
            });
        }, true);
    });
});

//显示对话框
function showDialog(content, ok, cancel) {
    $.dialog({
        title: "温馨提示",
        content: content,
        ok: ok || true,
        cancel: cancel,
        icon: "face-smile"
    });
}
