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
$(function() {
	// 翻页操作
	$(".btn_page").click(function() {
		// 获取data-page属性的值
		var pageNo = $(this).data("page")
				|| $(":input[name='currentPage']").val();
		$(":input[name='currentPage']").val(pageNo);
		$("#searchForm").submit();
	});
	// 设置每页显示多少条数据:改变ageSize
	$(":input[name='pageSize']").change(function() {
		$(":input[name='currentPage']").val(1);
		$("#searchForm").submit();
	});
});
/** 点击,跳转到指定URL */
$(function() {
	$(".btn_redirect").click(function() {
		location.href = $(this).data("url");
	});
});

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