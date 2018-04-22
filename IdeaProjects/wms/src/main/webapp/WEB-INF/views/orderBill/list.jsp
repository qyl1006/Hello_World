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
	<script type="text/javascript" src="/plugins/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript">
		$(function () {
            //处理开始时间
            $("input[name='beginDate']").click(function () {
                WdatePicker({
                        readOnly: true,
                        lang:'zh-cn',
                        maxDate: new Date()
                    })
            });

            //处理结束时间
            $("input[name='endDate']").click(function () {
                WdatePicker({
                        readOnly: true,
                        lang:'zh-cn',
                        minDate: $("input[name='beginDate']").val(),
                        maxDate: new Date()
                    })
            });
        });
	</script>

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
								<c:forEach items="${suppliers}" var="item">
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
                                $("#supplierId option[value='${qo.supplierId}']").prop("selected",true);
                                $("#status option[value='${qo.status}']").prop("selected",true);
							</script>
						</div>


						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
									data-url="/orderBill/input.do"/>

						</div>


					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
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

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" data-eid="${item.id}"/></td>
							<td>${item.sn}</td>
							<td>
								<fmt:formatDate value="${item.vdate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>${item.supplier.name}</td>
							<td>${item.totalNumber}</td>
							<td>${item.totalAmount}</td>
							<td>${item.inputUser.name}</td>
							<td>${item.auditor.name}</td>
							<td>
								<c:choose>
									<c:when test="${item.status == 1}">
										<span style="color: green">待审核</span>
									</c:when>
									<c:when test="${item.status == 2}">
										<span style="color: red">已审核</span>
									</c:when>
								</c:choose>
							</td>

							<td>
								<c:choose>
										<c:when test="${item.status == 1}">
											<a href="/orderBill/input.do?id=${item.id}">编辑</a>
											<a href="javascript:"  data-url="/orderBill/delete.do?id=${item.id}"
											   class="btn_delete">删除</a>
											<a href="/orderBill/auditor.do?id=${item.id}">审核</a>
										</c:when>
										<c:when test="${item.status == 2}">
											<a href="/orderBill/view.do?id=${item.id}">查看</a>
										</c:when>

								</c:choose>
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
