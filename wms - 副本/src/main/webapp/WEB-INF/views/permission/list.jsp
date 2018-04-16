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

	<script type="text/javascript">
		$(function () {
            $("#box_bottom input").bind("click", function () {
                showDialog("加载权限需要耗费较长时间,确实要重新加载吗?", function () {
                    $.get("/permission/reload.do",function (data) {
                        //TODO
                        showDialog("加载成功!", function () {
                            //刷新
                            location.reload();
                        })
                    }, "JSON");
                }, true);
            });
        })
	</script>
<title>WMS-权限管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/permission/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>

						<%--<div id="box_center">--%>
							<%--姓名/邮箱--%>
							<%--<input type="text" class="ui_input_txt02" name="" />--%>
							<%--所属权限--%>
							<%--<select class="ui_select01" name="">--%>
								<%--<option>全部</option>--%>
								<%--<option>总经办</option>--%>
								<%--<option>技术部</option>--%>
							<%--</select>--%>
						<%--</div>--%>

						<div id="box_bottom">

							<input type="button" value="加载权限" class="ui_input_btn_reload"
									data-url="/permission/reload.do"/>
						</div>



					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>权限名称</th>
							<th>权限表达式</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" /></td>
							<td>${num.count}</td>
							<td>${item.name}</td>
							<td>${item.expression}</td>
							<td>
								<a href="javascript:" class="btn_delete"
								   data-url="/permission/delete.do?id=${item.id}">删除</a>
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
