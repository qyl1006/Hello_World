<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>WMS-采购订单管理</title>
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
							<fmt:formatDate var="beginDate" value="${qo.beginDate}" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="endDate" value="${qo.endDate}" pattern="yyyy-MM-dd"/>
							<input type="text" class="ui_input_txt02 Wdate" name="beginDate" value="${beginDate}"> ~
							<input type="text" class="ui_input_txt02 Wdate" name="endDate" value="${endDate}">
							供应商
							<select id="supplierId" class="ui_select01" name="supplierId">
									<option value="-1">全部供应商</option>
								<c:forEach items="suppliers" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>

							</select>
							状态
							<select id="status" class="ui_select01" name="status">
								<option value="-1">全部</option>
								<option value="1">待审核</option>
								<option value="2">已审核</option>
							</select>
							<script>
                                $("#supplierId option[value='-1']").prop("selected",true);
                                $("#status option[value='-1']").prop("selected",true);
							</script>
						</div>

						<script type="text/javascript">
							//回显
						$(".ui_select01 option[value='${qo.deptId}']").prop("selected", true);
						</script>

						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
									data-url="/orderBill/input.do"/>

							<input type="button" value="批量删除" data-url="/orderBill/batchDelete.do"
								   class="ui_input_btn02 btn_batchDelete">
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
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" data-eid="${item.id}"/></td>
							<td>${num.count}</td>
							<td>${item.name}</td>
							<td>${item.email}</td>
							<td>${item.age}</td>
							<td>${item.dept.name}</td>
							<td>
								<a href="/orderBill/input.do?id=${item.id}">编辑</a>
								<a href="javascript:" class="btn_delete"
								   data-url="/orderBill/delete.do?id=${item.id}">删除</a>
							</td>
						</tr>
						</c:forEach>


					</table>

					<%--//分页操作--%>
					<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</form>
</body>
</html>
