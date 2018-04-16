jQuery.ajaxSettings.traditional = true;
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


});
// /** 确定删除的对话框 */
// $(function() {
//     $(".btn_delete").click(function() {
//         var deleteUrl = $(this).data("url");
//         $.dialog({
//             title : "温馨提示",
//             content : "你确定要删除吗?",
//             icon : "face-sad",
//             cancel : true,
//             ok : function() {
//                 location.href = deleteUrl;
//             }
//         });
//     });
// });


$(function () {
    //新增
    $(".btn_input").click(function () {
        location.href = $(this).data("url");
    });

    //删除功能
    $(".btn_delete").click(function () {
        var url = $(this).data("url");

        showDialog("请确认删除?", function () {
            //发送ajax请求
            $.get(url, function (data) {

                //删除成功后再次调用该函数 提示用户同时刷新
                showDialog("删除成功", function () {
                    //刷新
                    location.reload();
                })
            }, "JSON")
        }, true);

    });

})

    function showDialog(content, ok, cancel) {
        $.dialog({
            title: "友情提示",

            //内容
            content: content,

            ok: ok || true,

            //取消
            cancel: cancel,

            //图标
            icon: "face-smile"
        });
}

