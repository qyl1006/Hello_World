<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
	<script type="text/javascript" src="/js/plugins/jquery-validation-1.8.1/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery-validation-1.8.1/localization/messages_cn.js"></script>
	<script type="text/javascript" src="/js/system/employee.js"></script>
</head>
<body>
<form name="editForm" action="/employee/saveOrUpdate.do" method="post" id="editForm">
	<input type="hidden" name="id" value="${employee.id}"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">用户编辑</span>
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
						<input name="name" value="${employee.name}" class="ui_input_txt02"/>
					</td>
				</tr>
				<c:if test="${empty employee.id}">
				<tr>
					<td class="ui_text_rt" width="140">密码</td>
					<td class="ui_text_lt">
						<input id="password" name="password" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">验证密码</td>
					<td class="ui_text_lt">
						<input name="repassword" class="ui_input_txt02"/>
					</td>
				</tr>
				</c:if>
				<tr>
					<td class="ui_text_rt" width="140">Email</td>
					<td class="ui_text_lt">
						<input name="email" value="${employee.email}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">年龄</td>
					<td class="ui_text_lt">
						<input name="age" value="${employee.age}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">所属部门</td>
					<td class="ui_text_lt">
						<select name="dept.id" class="ui_select01 deptId">
							<c:forEach items="${depts}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">超级管理员</td>
					<td class="ui_text_lt">
						<input type="checkbox"  name="admin" class="ui_checkbox01" ${employee.admin?"checked":""}/>
					</td>
				</tr>
				<script type="text/javascript">
					$(function(){
						$(".deptId option[value='${employee.dept.id}']").prop("selected", true);
					});
				</script>
				<tr class="role">
					<td class="ui_text_rt" width="140">分配角色</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select class="ui_multiselect01 all_roles" multiple="true">
										<c:forEach items="${roles}" var="item">
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
									<select name="roleIds" class="ui_multiselect01 selected_roles" multiple="true">
										<c:forEach items="${employee.roles}" var="item">
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
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>