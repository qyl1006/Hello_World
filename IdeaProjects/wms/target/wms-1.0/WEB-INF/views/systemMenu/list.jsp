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
<title>WMS-系统菜单管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/systemMenu/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>

						<%--<div id="box_center">--%>
							<%--姓名/邮箱--%>
							<%--<input type="text" class="ui_input_txt02" name="" />--%>
							<%--所属系统菜单--%>
							<%--<select class="ui_select01" name="">--%>
								<%--<option>全部</option>--%>
								<%--<option>总经办</option>--%>
								<%--<option>技术部</option>--%>
							<%--</select>--%>
						<%--</div>--%>

						<div id="box_bottom">

							<input type="button" value="新增" class="ui_input_btn01 btn_input"
									data-url="/systemMenu/input.do?parentId=${qo.parentId}"/>
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
					<div>
						当前菜单:
						<a href="/systemMenu/list.do">根菜单</a>
						<c:forEach items="${menuDirs}" var="item">
							-&gt;<a href="/systemMenu/list.do?parentId=${item.id}">${item.name}</a>
						</c:forEach>
					</div>

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

						<c:forEach items="${menus}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" /></td>
							<td>${num.count}</td>
							<td>${item.name}</td>
							<td>${item.sn}</td>
							<td>${item.parent.name}</td>
							<td>${item.url}</td>
							<td>
								<a href="/systemMenu/input.do?id=${item.id}&parentId=${item.parent.id}">编辑</a>
								<a href="javascript:" class="btn_delete"
								   data-url="/systemMenu/delete.do?id=${item.id}">删除</a>
								<a href="/systemMenu/list.do?parentId=${item.id}">子菜单</a>
							</td>
						</tr>
						</c:forEach>

					</table>
				</div>

				<!-- 分页操作 -->
				<%--<%@ include file="/WEB-INF/views/common/common_page.jsp" %>--%>
			</div>
		</div>
	</form>
</body>
</html>
