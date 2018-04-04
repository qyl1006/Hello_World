<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>rbac-员工管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<form id="searchForm" action="/employee/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱 
							<input name="keyword" value="${qo.keyword}" class="ui_input_txt02" />
							所属部门 
							<select id="deptId" name="deptId" class="ui_select01">
								<option value="-1">所有部门</option>
								<c:forEach items="${depts}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							<script type="text/javascript">
								$(function() {
									$("#deptId option[value='${qo.deptId}']").prop("selected", true);
								});
							</script>
							<input type="button" value="查询" class="ui_input_btn01 btn_page"
								data-page="1" />
						</div>
						<div id="box_bottom">
							<input type="button" value="新增"
								class="ui_input_btn01 btn_redirect"
								data-url="/employee/input.do" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%"
						align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" class="acb" /></th>
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th></th>
						</tr>
						<tbody>
						<c:forEach items="${result.data}" var="item" varStatus="num">
							<tr>
								<td><input type="checkbox" class="acb"/></td>
								<td>${num.count} </td>
								<td>${item.name}</td>
								<td>${item.email}</td>
								<td>${item.age}</td>
								<td>${item.dept.name}</td>
								<td><a href="/employee/input.do?id=${item.id}">编辑</a> <a
									href="javascript:;" class="btn_delete"
									data-url="/employee/delete.do?id=${item.id}">删除</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 分页操作 -->
				<%@ include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</form>
</body>
</html>

