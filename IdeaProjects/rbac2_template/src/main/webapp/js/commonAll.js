//禁用jQuery对数组参数做的序列化操作,对数组参数名带上[]
jQuery.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({
			background : "#CDDAEB"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#1D1E21"
			});
		});
	}).mouseout(function() {
		$(this).css({
			background : "#FFF"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#909090"
			});
		});
	});
});

/** 翻页操作 */
$(function () {
	$(".btn_page").click(function () {
		//获取每次按钮操作的当前页的值
		var currentPage = $(this).data("page") || $("input[name='currentPage']").val();
		//设置值
		$("input[name='currentPage']").val(currentPage);
		//提交表单
		$("#searchForm").submit();

    });

    //页面容量改变时,自动提交表单
	$(".pageSize[name='pageSize']").change(function () {
        //提交表单
        $("#searchForm").submit();
    });



    /** 点击,跳转到指定URL */
    $("#box_bottom input").click(function () {
    	var url = $(this).data("url");
    	location.href = url;
     });

})











/** 确定删除的对话框 */
$(function() {
	$(".btn_delete").click(function() {
		var deleteUrl = $(this).data("url");
		$.dialog({
			title : "温馨提示",
			content : "你确定要删除吗?",
			icon : "face-smile",
			cancel : true,
			ok : function() {
				location.href = deleteUrl;
			}
		});
	});
});


// 对话框
function showDialog(content, ok, cancel) {
	$.dialog({
		title : "温馨提示",
		icon : "face-smile",
		content : content || "",
		ok : ok || true,
		cancel : cancel
	});
}