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
<title>WMS-仓库管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/depot/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>


						<div id="box_bottom">

							<input type="button" value="新增" class="ui_input_btn01 btn_input"
									data-url="/depot/input.do"/>
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
							<th>仓库名称</th>
							<th>仓库地址</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" /></td>
							<td>${num.count}</td>
							<td>${item.name}</td>
							<td>${item.location}</td>
							<td>
								<a href="/depot/input.do?id=${item.id}">编辑</a>
								<a href="javascript:" class="btn_delete"
								   data-url="/depot/delete.do?id=${item.id}">删除</a>
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
