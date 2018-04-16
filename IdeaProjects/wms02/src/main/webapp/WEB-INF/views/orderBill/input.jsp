<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--日期处理--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<script type="text/javascript" src="/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>

	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript">
		$(function () {
		    //日期插件
			$(".Wdate").click(function () {
                WdatePicker({isShowClear:true,readOnly:true,lang:'zh-cn'})
            });


            $("#editForm").ajaxForm(function (data) {
                console.log("Jquery-form 提交执行");
                showDialog("保存成功!",function () {
					location.href="/orderBill/list.do";
                });
            });


			$(".ui_input_btn01").click(function () {

			    $(".selected_role option").prop("selected", true);
			    //手动提交
                $("#editForm").submit();
            });

			//供应商信息回显
			$(".ui_select03 option[value='${entity.supplier.id}']").prop("selected", true);


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
<form id="editForm" action="/orderBill/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">采购订单编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">采购单编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">供应商</td>
					<td class="ui_text_lt">

						<select name="supplier.id" class="ui_select03">
							<c:forEach items="${suppliers}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>

					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						<input name="vdate" value="${entity.vdate}" class="ui_input_txt02 Wdate"/>
					</td>
				</tr>


				<tr>
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