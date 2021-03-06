<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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
	<script type="text/javascript" src="/plugins/artDialog/iframeTools.js"></script>


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
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}" />
							品牌
							<select class="ui_select01" name="brandId">
								<option value="-1">----全部品牌----</option>
								<c:forEach items="${brands}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>

						<script type="text/javascript">
							//回显
						$(".ui_select01 option[value='${qo.brandId}']").prop("selected", true);
						</script>

						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01" data-page="1"/>

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

						<c:forEach items="${result.data}" var="item" varStatus="num">
						<tr>
							<td><input type="checkbox" class="acb" data-eid="${item.id}"/></td>
							<td>${num.count}</td>
							<td>
								<a class="fancybox" data-fancybox-group="img" href="${item.imagePath}" title="${item.name}">
									<img class="list_img" src="${item.smallImage}">
								</a>

							</td>
							<td>${item.name}</td>
							<td>${item.sn}</td>
							<td>${item.brandName}</td>
							<td>${item.costPrice}</td>
							<td>${item.salePrice}</td>
							<td>
								<input class="left2right" type="button" value="选中" data-json='${item.jsonString}'>

							</td>
						</tr>
						</c:forEach>


					</table>

					<script type="text/javascript">
						$(function () {
                            //图片弹出层
                            $(".fancybox").fancybox();




                            });


                        //绑定选中按钮点击事件--
                        $(".left2right").click(function () {
                            var json = $(this).data("json");
                            //共享数据给父窗口 , 关闭自己
                            $.dialog.data("data", json);
                            $.dialog.close();

                        });

					</script>

					<%--//分页操作--%>
					<%@include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</form>
</body>
</html>
