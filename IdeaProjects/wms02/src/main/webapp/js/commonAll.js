//禁用数据参数加[]
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

    //批量删除
    //全选/反选
    $("#all").click(function () {
        $(".acb").prop("checked", this.checked)
    });
    
    $("#batch_delete").click(function () {
        var url = $(this).data("url");
        var temp = $(".acb:checked");

        showDialog("确定要批量删除吗?", function () {
            if(!temp.length){
                showDialog("至少选中1条数据");
                return;
            }

            //通用map方法转数据
           var ids =  $.map(temp, function (ele) {
                return $(ele).data("eid");
            });

            //发送Ajax请求
            $.post(url, {"ids":ids}, function (data) {
                showDialog("批量删除成功", function () {
                    //刷新
                    location.reload();
                })
            }, "JSON");

        }, true);
        if(temp.length){

        }

    });

});


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









