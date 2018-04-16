//权限操作
$(function() {
	// 全部从左移动到右
	$("#selectAll").click(function() {
		$(".all_permissions option").appendTo($(".selected_permissions"));
	});
	// 把选中的从左移动到右
	$("#select").click(function() {
		$(".all_permissions option:selected").appendTo($(".selected_permissions"));
	});
	// 全部从右移动到左
	$("#deselectAll").click(function() {
		$(".selected_permissions option").appendTo($(".all_permissions"));
	});
	// 把选中的从右移动到左
	$("#deselect").click(function() {
		$(".selected_permissions option:selected").appendTo($(".all_permissions"));
	});
	//把已经分配的权限从所有的权限列表中移除
	//1:获取已经分配权限的的id,存储到ids数组中
	var ids = $.map($(".selected_permissions option"),function(item){
		return $(item).val();
	});
	//2:迭代所有的权限列表的每一个<option>
	$.each($(".all_permissions option"),function(index,item){
		var optionObject = $(item);
		var optionValue = $(item).val();
		//判断每一个值是否在ids数组中
		if($.inArray(optionValue,ids) >=0 ){
			optionObject.remove();//如果在ids数组中,删除自己
		}
	});
});

//菜单操作
$(function() {
	// 全部从左移动到右
	$("#mselectAll").click(function() {
		$(".all_menus option").appendTo($(".selected_menus"));
	});
	// 把选中的从左移动到右
	$("#mselect").click(function() {
		$(".all_menus option:selected").appendTo($(".selected_menus"));
	});
	// 全部从右移动到左
	$("#mdeselectAll").click(function() {
		$(".selected_menus option").appendTo($(".all_menus"));
	});
	// 把选中的从右移动到左
	$("#mdeselect").click(function() {
		$(".selected_menus option:selected").appendTo($(".all_menus"));
	});
	//把已经分配的权限从所有的权限列表中移除
	//1:获取已经分配权限的的id,存储到ids数组中
	var ids = $.map($(".selected_menus option"),function(item){
		return $(item).val();
	});
	//2:迭代所有的权限列表的每一个<option>
	$.each($(".all_menus option"),function(index,item){
		var optionObject = $(item);
		var optionValue = $(item).val();
		//判断每一个值是否在ids数组中
		if($.inArray(optionValue,ids) >=0 ){
			optionObject.remove();//如果在ids数组中,删除自己
		}
	});
});
//在表单提交之前,应该选中所有已经分配的权限选项和菜单选项
$(function(){
	$("#editForm").submit(function(){
		$(".selected_permissions option").prop("selected",true);
	});
});


