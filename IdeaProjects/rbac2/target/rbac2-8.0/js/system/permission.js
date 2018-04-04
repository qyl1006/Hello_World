/**重新加载权限*/
$(function(){
	$(".btn_load").click(function(){
		var url = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"亲,重新加载权限可能需要耗费很长的时间,你确定加载吗?",
			icon:"face-smile",
			cancel:true,
			ok:function(){
				$.get(url,function(backData){
					if (backData.success) {
						$.dialog({
							title:"温馨提示",
							content:"权限加载成功!",
							icon:"face-smile",
							ok:function() {
								location.reload();
							}
						});
					}
				});
			}
		});
	});
});