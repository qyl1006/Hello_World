<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/plugins/jQuery-form/jQueryForm.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>

	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript">
		$(function () {
            $("#editForm").ajaxForm(function (data) {
                console.log("Jquery-form 提交执行");
                showDialog("保存成功!",function () {
					location.href="/role/list.do";
                });
            });


			$(".ui_input_btn01").click(function () {
			    $(".select_permission option").prop("selected", true);
			    $(".select_menu option").prop("selected", true);
			    //手动提交
                $("#editForm").submit();
            });


			//表单验证
            $().ready(function() {

                $("#editForm").validate({
                    rules: {
                        name: {
                            required: true,
                            rangelength:[2,16]
                        },
                        sn: {
                            required: true,
                            rangelength:[2,16]
                        },
                    }
                })

            })
        })
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
					<td class="ui_text_rt" width="140">分配权限</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_permission">
									<c:forEach items="${permissions}" var="item">
										<option value="${item.id}">${item.name}</option>
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
									<select multiple="true" name="ids" class="ui_multiselect01 select_permission">
										<c:forEach items="${entity.permissions}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>


				<tr>
					<td class="ui_text_rt" width="140">分配菜单</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_menu">
										<c:forEach items="${menus}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
								<td align="center">
									<input type="button" id="selectMenu" value="-->" class="left2right"/><br/>
									<input type="button" id="selectAllMenu" value="==>" class="left2right"/><br/>
									<input type="button" id="deselectMenu" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectAllMenu" value="<==" class="left2right"/>
								</td>
								<td>
									<select multiple="true" name="menuIds" class="ui_multiselect01 select_menu">
										<c:forEach items="${entity.systemmenus}" var="item">
											<option value="${item.id}">${item.name}</option>
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
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>

			<script type="text/javascript">
				$(function () {
					//去重
					$(".select_permission option").each(function (i, e) {

                        $(".all_permission option").each(function (index, val) {
							if(e.value == val.value){
							    $(val).remove();
							}
                        })
                    });

					//全部右移
					$("#selectAll").click(function () {
                        $(".all_permission option").appendTo(".select_permission")
                    });
                    //全部左移
                    $("#deselectAll").click(function () {
                        $(".select_permission option").appendTo(".all_permission")
                    });

                    //单右移
                    $("#select").click(function () {
                        $(".all_permission option:selected").appendTo(".select_permission")
                    });
                    //单左移
                    $("#deselect").click(function () {
                        $(".select_permission option:selected").appendTo(".all_permission")
                    });

                    // -----------------------

                    //去重
                    $(".select_menu option").each(function (i, e) {

                        $(".all_menu option").each(function (index, val) {
                            if(e.value == val.value){
                                $(val).remove();
                            }
                        })
                    });

                    //全部右移
                    $("#selectAllMenu").click(function () {
                        $(".all_menu option").appendTo(".select_menu")
                    });
                    //全部左移
                    $("#deselectAllMenu").click(function () {
                        $(".select_menu option").appendTo(".all_menu")
                    });

                    //单右移
                    $("#selectMenu").click(function () {
                        $(".all_menu option:selected").appendTo(".select_menu")
                    });
                    //单左移
                    $("#deselectMenu").click(function () {
                        $(".select_menu option:selected").appendTo(".all_menu")
                    });




                })
			</script>
		</div>
	</div>
</form>
</body>
</html>