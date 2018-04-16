<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-商品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/product/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							商品名称/编码
							<input name="keyword" value="" class="ui_input_txt02" />
							商品品牌
							<select id="" name="" class="ui_select01">
								<option value="-1">全部</option>

							</select>
						</div>
						<script>

						</script>
						<div id="box_bottom">
							<input type="button" value="查询"
								   data-page="1" class="ui_input_btn01 btn_page"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url="/product/input.do"/>
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
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品编码</th>
							<th>商品品牌</th>
							<th>成本价</th>
							<th>零售价</th>
							<th></th>
						</tr>
						<c:forEach var="entity" items="${result.data}" varStatus="num">
							<tr>
								<td><input type="checkbox" class="acb" data-eid="${entity.id}"/></td>
								<td>${num.count}</td>
								<td>${entity.imagePath}</td>
								<td>${entity.name}</td>
								<td>${entity.sn}</td>
								<td>${entity.brandName}</td>
								<td>${entity.costPrice}</td>
								<td>${entity.salePrice}</td>
								<td>
									<a href="/product/input.do?id=${entity.id}">编辑</a>
									<a href="javascript:" class="btn_delete"
									   data-url="/product/delete.do?id=${entity.id}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<%--引入分页操作--%>
				<%@ include file="/WEB-INF/views/common/page.jsp" %>
			</div>
		</div>
	</form>
</body>
</html>
