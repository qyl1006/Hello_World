<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/plugins/jqueryForm/jQueryForm.js"></script>
<script type="text/javascript" src="/js/plugins/validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/plugins/validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
	<script>
		$(function () {
            //把表单提交方式改成ajax提交
            $("#editForm").ajaxForm(function (data) {
                showDialog("操作成功", function () {
                    location.href = "/product/list.do";
                });
            });

            
        });
	</script>
</head>
<body>
<form id="editForm" action="/product/saveOrUpdate.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">商品编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">商品名称</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">成本价</td>
					<td class="ui_text_lt">
						<input name="costPrice" value="${entity.costPrice}" class="ui_input_txt02" />
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">零售价</td>
					<td class="ui_text_lt">
						<input name="salePrice" value="${entity.salePrice}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品品牌</td>
					<td class="ui_text_lt">
						<select id="" name="brandId" class="ui_select03">
							<c:forEach var="ele" items="${brands}">
								<option value="${ele.id}">${ele.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品图片</td>
					<td class="ui_text_lt">
						<input type="file" name="pic" class="ui_file"/>
						<c:if test="${not empty entity.imagePath}">
							<img src="${entity.imagePath}" class="list_img"/>
							<input type="hidden" name="imagePath" value="${entity.imagePath}"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品简介</td>
					<td class="ui_text_lt">
						<textarea name="intro" class="ui_input_txtarea">${entity.intro}</textarea>
					</td>
				</tr>
				<script>

				</script>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>