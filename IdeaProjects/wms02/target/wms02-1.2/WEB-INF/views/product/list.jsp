<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link href="/plugins/fancyapps-fancyBox/jquery.fancybox.css?v=2.1.5" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/plugins/fancyapps-fancyBox/jquery.fancybox.pack.js?v=2.1.5"></script>
<script type="text/javascript" src="/plugins/fancyapps-fancyBox/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>WMS-商品管理

</title>
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
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}" />
							商品品牌
							<select class="ui_select01" name="brandId">
								<option value="-1">--全部品牌--</option>
								<c:forEach items="${brands}" var="item">
								<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>

						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01"/>
							<input type="button" value="新增" class="custom_button" id="increase"
									data-url="/product/input.do"/>
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
							<th>编号</th>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品编码</th>
							<th>商品品牌</th>
							<th>成本价</th>
							<th>零售价</th>
							<th></th>
						</tr>

						<c:forEach items="${result.data}" var="item" varStatus="vs">
							<tr>
								<td><input type="checkbox"  class="acb" data-eid="${item.id}"/></td>
								<td>${vs.count}</td>
								<td>
									<a class="fancybox" href="${item.imagePath}" data-fancybox-group="gallery" title="${item.name}">
									<img src="${item.smallImage}" class="list_img_min"></a>
								</td>
								<td>${item.name}</td>
								<td>${item.sn}</td>
								<td>${item.brandName}</td>
								<td>${item.costPrice}</td>
								<td>${item.salePrice}</td>
								<%--<td>${item.dept}</td>--%>
								<td>
									<a href="/product/input.do?id=${item.id}">编辑</a>
									<a href="javascript:"  data-url="/product/delete.do?id=${item.id}&imagePath=${item.imagePath}"
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
					$(function () {
                        $(".ui_select01 option[value='${qo.brandId}']").prop('selected', true);

                        //图片弹出层
                        $(".fancybox").fancybox();
                    })

				</script>
			</div>
		</div>
	</form>
</body>
</html>
