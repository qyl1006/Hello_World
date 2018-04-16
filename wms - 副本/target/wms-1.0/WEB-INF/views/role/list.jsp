<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>




<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-角色管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/role/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>

						<%--<div id="box_center">--%>
							<%--姓名/邮箱--%>
							<%--<input type="text" class="ui_input_txt02" name="" />--%>
							<%--所属角色--%>
							<%--<select class="ui_select01" name="">--%>
								<%--<option>全部</option>--%>
								<%--<option>总经办</option>--%>
								<%--<option>技术部</option>--%>
							<%--</select>--%>
						<%--</div>--%>

						<div id="box_bottom">

							<input type="button" value="新增" class="ui_input_btn01 btn_input"
									data-url="/role/input.do"/>
						</div>

						<script type="text/javascript">
							$(".btn_input").click(function () {
							    location.href = $(this).data("url");
                            });
						</script>

					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>角色名称</th>
							<th>角色编码</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" /></td>
							<td>${num.count}</td>
							<td>${item.name}</td>
							<td>${item.sn}</td>
							<td>
								<a href="/role/input.do?id=${item.id}">编辑</a>
								<a href="javascript:" class="btn_delete"
								   data-url="/role/delete.do?id=${item.id}">删除</a>
							</td>
						</tr>
						</c:forEach>

					</table>
				</div>

				<!-- 分页操作 -->
				<%@ include file="/WEB-INF/views/common/common_page.jsp" %>
			</div>
		</div>
	</form>
</body>
</html>
