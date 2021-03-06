<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS- 订货报表

</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/chart/order.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">

							业务时间
							<fmt:formatDate var="beginDate" value="${qo.beginDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
							<fmt:formatDate var="endDate" value="${qo.endDate}" pattern="yyyy-MM-dd"/>
							<input type="text" class="ui_input_txt02" name="beginDate" value="${beginDate}" class="ui_input_btn02 Wdate"/> ~
							<input type="text" class="ui_input_txt02" name="endDate" value="${endDate}" calss="ui_input_btn02 Wdate"/>

							货品名称/编码
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}" />
							供应商
							<select id="supplierId" class="ui_select01" name="supplierId">
								<option value="-1">--全部供应商--</option>
								<c:forEach items="${suppliers}" var="item">
								<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>

							品牌
							<select id="brandId" class="ui_select01" name="brandId">
								<option value="-1">--全部品牌--</option>
								<c:forEach items="${brands}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							类型
							<select id="groupType" class="ui_select01" name="groupType">
								<option value="e.name">订货员</option>
								<option value="p.name">商品名称</option>
								<option value="p.brandName">商品品牌</option>
								<option value="s.name">供应商</option>

								<option value="DATE_FORMAT(bill.vdate,'%Y-%m')">订货月份</option>
								<option value="DATE_FORMAT(bill.vdate,'%Y-%m-%d')">订货日期</option>
							</select>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_submit"/>
						</div>
					</div>
				</div>
			</div>

					<script type="text/javascript">
						$(function () {


						$("#supplierId option[value='${qo.supplierId}']").prop("selected", true);
						$("#brandId option[value='${qo.brandId}']").prop("selected", true);
						$("#groupType option[value=\"${qo.groupType}\"]").prop("selected",true);

                        //处理开始时间
                        $("input[name='beginDate']").click(function () {
                            WdatePicker({
                                    readOnly: true,
                                    lang:'zh-cn',
                                    maxDate: new Date()
                                }
                            )
                        });

                        //处理结束时间
                        $("input[name='endDate']").click(function () {
                            WdatePicker({
                                    readOnly: true,
                                    lang:'zh-cn',
                                    minDate: $("input[name='beginDate']").val(),
                                    maxDate: new Date()
                                }
                            )
                        });



						$(".btn_submit").click(function () {
							$("#searchForm").submit();
                        });

                        })
					</script>
			<div class="ui_content">

			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox"  id="all"/></th>
							<th>分组类型</th>
							<th>订货总数</th>
							<th>订货总额</th>
						</tr>

						<c:forEach items="${list}" var="item" varStatus="vs">
							<tr>
								<td><input type="checkbox"  class="acb" data-eid="${item.id}"/></td>
								<td>${item.groupType}</td>
								<td>${item.totalNumber}</td>
								<td>${item.totalAmount}</td>

							</tr>
						</c:forEach	>
					</table>
				</div>

			</div>
		</div>
	</form>
</body>
</html>
