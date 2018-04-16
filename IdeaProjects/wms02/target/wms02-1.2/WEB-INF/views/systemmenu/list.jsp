<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-菜单管理

</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/systemmenu/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="新增" class="custom_button"
									data-url="/systemmenu/input.do?parentId=${qo.parentId}"/>
						</div>

						<script type="text/javascript">
							// 页面跳转
							$(".custom_button").click(function () {
                                location.href = $(this).data("url");
                            });
						</script>

					</div>
				</div>
			</div>

			<div>
				当前菜单:
				<a href="/systemmenu/list.do">根菜单</a>
				<c:forEach items="${menus}" var="item">
					-&gt;<a href="/systemmenu/list.do?parentId=${qo.parentId}">${item.name}</a>
				</c:forEach>

			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>菜单名称</th>
							<th>菜单编码</th>
							<th>父菜单</th>
							<th>URL</th>
							<th></th>
						</tr>

						<c:forEach items="${systemmenus}" var="item" varStatus="vs">
							<tr>
								<td><input type="checkbox" class="acb" /></td>
								<td>${vs.count}</td>
								<td>${item.name}</td>
								<td>${item.sn}</td>
								<td>${item.parent.name}</td>
								<td>${item.url}</td>
								<td>
									<a href="/systemmenu/input.do?id=${item.id}&parentId=${qo.parentId}">编辑</a>
									<a href="javascript:"  data-url="/systemmenu/delete.do?id=${item.id}"
									class="btn_delete">删除</a>
									<a href="/systemmenu/list.do?parentId=${item.id}">子菜单</a>
								</td>
							</tr>
						</c:forEach	>
					</table>
				</div>

				<%--分页操作--%>
				<%--<%@include file="/WEB-INF/views/common/common_page.jsp"%>--%>

			</div>
		</div>
	</form>
</body>
</html>
