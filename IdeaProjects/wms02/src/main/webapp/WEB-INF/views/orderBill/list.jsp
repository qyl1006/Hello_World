<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--日期处理--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-采购订单管理

</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/orderBill/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<input type="text" class="ui_input_txt02" name="beginDate" value="${qo.keyword}" />
							<input type="text" class="ui_input_txt02" name="endDate" value="${qo.keyword}" />
							供应商
							<select class="ui_select01" name="supplierId">
								<option value="-1">--全部供应商--</option>
								<c:forEach items="${suppliers}" var="item">
								<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>

							状态
							<select class="ui_select01" name="status">
								<option value="-1">--全部--</option>
								<option value="1">--未审核--</option>
								<option value="2">--已审核--</option>
							</select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01"/>
							<input type="button" value="新增" class="custom_button" id="increase"
									data-url="/orderBill/input.do"/>

						</div>
					</div>
				</div>
			</div>

					<script type="text/javascript">
						// 页面跳转
						$("#increase").click(function () {
							location.href = $(this).data("url");
						});
					</script>
			<div class="ui_content">

			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox"  id="all"/></th>
							<th>采购单号</th>
							<th>业务时间</th>
							<th>供应商</th>
							<th>总数量</th>
							<th>总金额</th>
							<th>录入人</th>
							<th>审核人</th>
							<th>状态</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="vs">
							<tr>
								<td><input type="checkbox"  class="acb" data-eid="${item.id}"/></td>
								<td>${item.sn}</td>
								<td><fmt:formatDate value="${item.vdate}" pattern="yyyy-MM-dd"/></td>
								<td>${item.supplier.name}</td>
								<td>${item.totalNumber}</td>
								<td>${item.totalAmount}</td>
								<td>${item.inputUser.name}</td>
								<td>${item.auditor.name}</td>
								<td>${item.status}</td>
								<td>
									<a href="/orderBill/input.do?id=${item.id}">编辑</a>
									<a href="javascript:"  data-url="/orderBill/delete.do?id=${item.id}"
										class="btn_delete">删除</a>
								</td>
							</tr>
						</c:forEach	>
					</table>
				</div>

				<%--分页操作--%>
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>

				<%--//高级查询回显数据--%>
				<script type="text/javascript">
				</script>
			</div>
		</div>
	</form>
</body>
</html>
