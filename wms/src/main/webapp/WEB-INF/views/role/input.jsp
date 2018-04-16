<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script src="/js/plugins/jqueryForm/jQueryForm.js"></script>
<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script>
	$(function () {
		//把表单提交方式改成ajax提交
		$("#editForm").ajaxForm(function (data) {
			showDialog("操作成功", function () {
				location.href = "/role/list.do";
            });
        });

        $(".btn_submit").click(function () {
            //选中右边所有的option
            $(".selected_permission option").prop("selected", true);
            $(".selected_menu option").prop("selected", true);
            //手动提交表单
            $("#editForm").submit();
        });

		//去除重复的权限选项
		var arr = $.map($(".selected_permission option"), function (ele) {
            return ele.value;
        });
        $.each(arr, function (index, val) {
			$.each($(".all_permission option"), function (i, ele) {
				if (ele.value == val) { //重复
                    $(ele).remove(); //删除
                }
            });
        });
        //========================================
        arr = $.map($(".selected_menu option"), function (ele) {
            return ele.value;
        });
        $.each(arr, function (index, val) {
            $.each($(".all_menu option"), function (i, ele) {
                if (ele.value == val) { //重复
                    $(ele).remove(); //删除
                }
            });
        });

        //列表移动
		$("#selectAll").click(function () {
			$(".all_permission option").appendTo(".selected_permission");
        });
		$("#select").click(function () {
			$(".all_permission option:selected").appendTo(".selected_permission");
        });
		$("#deselectAll").click(function () {
			$(".selected_permission option").appendTo(".all_permission");
        });
		$("#deselect").click(function () {
			$(".selected_permission option:selected").appendTo(".all_permission");
        });
		//====================================
        //列表移动
        $("#selectMenu").click(function () {
            $(".all_menu option:selected").appendTo(".selected_menu");
        });
        $("#selectMenuAll").click(function () {
            $(".all_menu option").appendTo(".selected_menu");
        });
        $("#deselectMenu").click(function () {
            $(".selected_menu option:selected").appendTo(".all_menu");
        });
        $("#deselectMenuAll").click(function () {
            $(".selected_menu option").appendTo(".all_menu");
        });
    });
</script>
</head>
<body>
<form id="editForm" action="/role/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">角色编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">角色名称</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">角色编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">权限</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_permission">
										<c:forEach var="ele" items="${permissions}">
											<option value="${ele.id}">${ele.name}</option>
										</c:forEach>
									</select>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right"/><br/>
									<input type="button" id="selectAll" value="==>" class="left2right"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right"/>
								</td>
								<td>
									<select name="ids" multiple="true" class="ui_multiselect01 selected_permission">
										<c:forEach var="ele" items="${entity.permissions}">
											<option value="${ele.id}">${ele.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">菜单</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_menu">
										<%--所有子菜单--%>
										<c:forEach var="ele" items="${menus}">
											<option value="${ele.id}">${ele.name}</option>
										</c:forEach>
									</select>
								</td>
								<td align="center">
									<input type="button" id="selectMenu" value="-->" class="left2right"/><br/>
									<input type="button" id="selectMenuAll" value="==>" class="left2right"/><br/>
									<input type="button" id="deselectMenu" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectMenuAll" value="<==" class="left2right"/>
								</td>
								<td>
									<select name="menuIds" multiple="true" class="ui_multiselect01 selected_menu">
										<%--当前角色拥有的菜单--%>
										<c:forEach var="ele" items="${entity.menus}">
											<option value="${ele.id}">${ele.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>