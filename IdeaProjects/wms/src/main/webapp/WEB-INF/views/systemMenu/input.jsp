<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>

	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
	<script type="text/javascript" src="/plugins/jQuery-form/jQueryForm.js"></script>

	<%--表单提交--jquery-Form--%>
	<script type="text/javascript">
		$(function () {
			$("#editForm").ajaxForm(function (data) {
                console.log(data);
                showDialog("确认保存?", function () {
                    console.log(123456);
					showDialog("保存成功", function () {
						location.href="/systemMenu/list.do";
                    })
                }, true);
            });

			$(".btn_submit").click(function () {
				$("#editForm").submit();
            });
        });
	</script>
</head>
<body>

<%--表单验证--%>
<script type="text/javascript">
    $().ready(function () {
        $("#editForm").validate({
            rules: {
                name: {
                    required: true,
                    rangelength: [2, 16]
                },
                sn: {
                    required: true,
                    rangelength: [2, 16]
				}
        }
    })
 });




</script>

<form id="editForm" action="/systemMenu/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">菜单编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">父菜单</td>
					<td class="ui_text_lt">
						<input  value="${parent.name}" readonly class="ui_input_txt02"/>
						<input type="hidden" name="parent.id" value="${parent.id}" class="ui_input_txt02" readonly/>
					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">菜单名称</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">菜单编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">URL</td>
					<td class="ui_text_lt">
						<input name="url" value="${entity.url}" class="ui_input_txt02"/>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>