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
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/plugins/jqueryForm/jQueryForm.js"></script>
<script type="text/javascript" src="/js/plugins/validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/plugins/validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
	<script>
		$(function () {
            //把表单提交方式改成ajax提交
            $("#editForm").ajaxForm(function (data) {
                showDialog("操作成功", function () {
                    location.href = "/employee/list.do";
                });
            });

            $(".btn_submit").click(function () {
                //选中右边所有的option
                $(".selected_role option").prop("selected", true);
                //手动提交表单
                $("#editForm").submit();
            });

            //去除重复的权限选项
            var arr = $.map($(".selected_role option"), function (ele) {
                return ele.value;
            });
            $.each(arr, function (index, val) {
                $.each($(".all_role option"), function (i, ele) {
                    if (ele.value == val) { //重复
                        $(ele).remove(); //删除
                    }
                });
            });

            //列表移动
            $("#selectAll").click(function () {
                $(".all_role option").appendTo(".selected_role");
            });
            $("#select").click(function () {
                $(".all_role option:selected").appendTo(".selected_role");
            });
            $("#deselectAll").click(function () {
                $(".selected_role option").appendTo(".all_role");
            });
            $("#deselect").click(function () {
                $(".selected_role option:selected").appendTo(".all_role");
            });

            //先克隆一份角色选择
            var role = $(".role").clone(true);
            //选择超级管理员,直接删除角色选择
            if ($("#admin").prop("checked")) {
                $(".role").remove();
            }
			//超级管理员按钮绑定点击事件
            $("#admin").click(function () {
                if (this.checked) {
                    $(".role").remove();
                } else {
                    $(this).closest("tr").after(role);
                }
            });

			//获取表单对象,调用验证方法
			$("#editForm").validate({
                rules: {
                    name: {
                        required: true,
                        rangelength: [2,6]
					},
					password: {
                        required: true
					},
					repassword: {
                        equalTo:"#pwd"
					},
					email: {
                        required: true,
                        email:true
					},
					age: {
                        required: true,
                        digits:true,
                        range:[16,65]
					}
				}
			});
        });
	</script>
</head>
<body>
<form id="editForm" action="/employee/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">部门编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">用户名</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>
				<c:if test="${empty entity.id}">
					<tr>
						<td class="ui_text_rt" width="140">密码</td>
						<td class="ui_text_lt">
							<input type="password" name="password" id="pwd" class="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">验证密码</td>
						<td class="ui_text_lt">
							<input name="repassword" type="password" class="ui_input_txt02" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="ui_text_rt" width="140">Email</td>
					<td class="ui_text_lt">
						<input name="email" value="${entity.email}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">年龄</td>
					<td class="ui_text_lt">
						<input name="age" value="${entity.age}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">所属部门</td>
					<td class="ui_text_lt">
						<select id="dept" name="dept.id" class="ui_select03">
							<c:forEach var="ele" items="${depts}">
								<option value="${ele.id}">${ele.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">超级管理员</td>
					<td class="ui_text_lt">
						<input id="admin" type="checkbox" name="admin" class="ui_checkbox01"/>
					</td>
				</tr>
				<script>
					//回显下拉列表
					$("#dept option[value='${entity.dept.id}']").prop("selected", true);
					<c:if test="${not empty entity.id}">
                    	//只有更新的情况,才需要回显是否超级管理员
                    	$("#admin").prop("checked",${entity.admin});
					</c:if>
				</script>
				<tr class="role">
					<td class="ui_text_rt" width="140">角色</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_role">
										<c:forEach var="ele" items="${roles}">
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
									<select name="ids" multiple="true" class="ui_multiselect01 selected_role">
										<c:forEach var="ele" items="${entity.roles}">
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