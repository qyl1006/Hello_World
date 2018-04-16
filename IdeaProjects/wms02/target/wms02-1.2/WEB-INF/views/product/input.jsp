<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/plugins/jQuery-form/jQueryForm.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>

	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript">
		$(function () {
            $("#editForm").ajaxForm(function (data) {
                console.log("Jquery-form 提交执行");
                showDialog("保存成功!",function () {
					location.href="/product/list.do";
                });
            });


            $(".ui_input_btn01").click(function () {

			    $(".selected_role option").prop("selected", true);
			    //手动提交
                $("#editForm").submit();
            });

            //商品品牌回显
            $(".ui_select03 option[value='${entity.brandId}']").prop("selected", true);



			//表单验证
            $().ready(function() {

                $("#editForm").validate({
                    rules: {
                        name: {
                            required: true,
                            rangelength:[2,16]
                        },
                        sn: {
                            required: true,
                            rangelength:[2,16]
                        },
                    }
                })

            })
        })
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
					<td class="ui_text_rt" width="140">品牌名称</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>


				<tr>
					<td class="ui_text_rt" width="140">品牌编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>

					<td class="ui_text_rt" width="140">成本价</td>
					<td class="ui_text_lt">
						<input name="costPrice" value="${entity.costPrice}" class="ui_input_txt02"/>
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

						<select name="brandId" class="ui_select03">
							<c:forEach items="${brands}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>

					</td>
				</tr>


				<tr>

					<td class="ui_text_rt" width="140">商品图片</td>
					<td class="ui_text_lt">
						<%--<input name="imagePath" value="${entity.imagePath}" class="ui_input_txt02"/>--%>
						<input type="file" name="pic" class="ui_file">
						<c:if test="${not empty entity.imagePath}">
							<img src="${entity.imagePath}" class="list_img_min">
							<input type="hidden" name="imagePath" value="${entity.imagePath}">
						</c:if>
					</td>
				</tr>

				<tr>

					<td class="ui_text_rt" width="140">商品简介</td>
					<td class="ui_text_lt">
						<textarea  name="intro"  class="ui_input_txtarea">${entity.intro}</textarea>
					</td>
				</tr>




				<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>


		</div>
	</div>
</form>
</body>
</html>