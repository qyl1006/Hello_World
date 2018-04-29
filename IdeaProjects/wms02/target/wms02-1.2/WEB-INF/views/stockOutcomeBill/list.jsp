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
	<script type="text/javascript" src="/plugins/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-销售出库管理



</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/stockOutComeBill/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<fmt:formatDate var="beginDate" value="${qo.beginDate}" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="endDate" value="${qo.endDate}" pattern="yyyy-MM-dd"/>
							<input type="text" class="ui_input_txt02" name="beginDate" value="${beginDate}" class="ui_input_btn02 Wdate"/> ~
							<input type="text" class="ui_input_txt02" name="endDate" value="${endDate}" calss="ui_input_btn02 Wdate"/>
							客户
							<select class="ui_select01 clientId" name="clientId">
								<option value="-1">--全部仓库--</option>
								<c:forEach items="${clients}" var="item">
								<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>

							状态
							<select class="ui_select01 status" name="status">
								<option value="-1">--全部--</option>
								<option value="1">--未审核--</option>
								<option value="2">--已审核--</option>
							</select>

							<%--//高级查询回显数据--%>
							<script type="text/javascript">
								$(function () {
                                    $(".clientId option[value='${qo.clientId}']").prop("selected", true);
                                    $(".status option[value='${qo.status}']").prop("selected", true);

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

                                    //查询
									$(".query").click(function () {
                                        $("#searchForm").submit();
                                    });

                                    //审核 --确认弹窗
                                    $(".btn_audit").click(function () {
                                        var url = $(this).data("url");
                                        showDialog("确定要审核吗?", function () {
                                            $.get(url, function (data) {
												if (data.success) {
												    showDialog("操作成功", function () {
                                                        location.reload();
                                                    })

                                                }else{
                                                    console.log(123);
                                                    showDialog(data.msg)
												}
                                            },"JSON");
                                        },true);

                                    });



                                })

							</script>

						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn02 query"/>
							<input type="button" value="新增" class="ui_input_btn02 custom_button" id="increase"
									data-url="/stockOutComeBill/input.do"/>

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
							<th>入库单号</th>
							<th>业务时间</th>
							<th>仓库</th>
							<th>客户</th>
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
								<td>${item.depot.name}</td>
								<td>${item.client.name}</td>
								<td>${item.totalNumber}</td>
								<td>${item.totalAmount}</td>
								<td>${item.inputUser.name}</td>
								<td>${item.auditor.name}</td>
								<td>
									<c:choose>
										<c:when test="${item.status == 1}">
											<span style="color:green">待审核</span>
										</c:when>
										<c:when test="${item.status == 2}">
											<span style="color:red">已审核</span>
										</c:when>
									</c:choose>
									
								</td>
								<td>
									<c:choose>
										<c:when test="${item.status == 1}">
											<a href="/stockOutComeBill/input.do?id=${item.id}">编辑</a>
											<a href="javascript:"  data-url="/stockOutComeBill/delete.do?id=${item.id}"
											   class="btn_delete">删除</a>
											<a href="javascript:" data-url="/stockOutComeBill/auditor.do?id=${item.id}"
												class="btn_audit">审核</a>
										</c:when>
										<c:when test="${item.status == 2}">
											<a href="/stockOutComeBill/view.do?id=${item.id}" >查看</a>
										</c:when>
									</c:choose>

								</td>
							</tr>
						</c:forEach	>
					</table>
				</div>

				<%--分页操作--%>
				<%@include file="/WEB-INF/views/common/common_page.jsp"%>


			</div>
		</div>
	</form>
</body>
</html>
