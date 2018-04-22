<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

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
	<script type="text/javascript" src="/plugins/artDialog/iframeTools.js"></script>


	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>



<%--表单提交--jquery-Form--%>
	<script type="text/javascript">
        $(function () {
            //把所有文本框改为只读
            $("input").prop("readonly", true);

            //点击返回  回到上一个界面
            $(".btn_back").click(function () {
                location.href = "/orderBill/list.do"
            });
        })
	</script>

</head>
<body>

<script type="text/javascript">
    $().ready(function() {

        $("#editForm").validate({
			rules: {
                name: {
                    required: true,
					rangelength:[2,16]
                },
                password: {
                    required: true,
                    rangelength:[1,16]
				},
                repassword : {
                    equalTo:"#password"
				},

                email: {
                    required: true,
                    email: true
                },
                age: {
                    required: true,
					digits: true
				}
			}
		})

    })
</script>


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

						<select id="supplierId" name="supplier.id" class="">
							<c:forEach items="${suppliers}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
							<script>
								$("#supplierId option[value='${entity.supplier.id}']").prop("selected", true)
							</script>
						</select>

					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						<c:choose>
							<c:when test="${empty entity.id}">
								<input name="vdate" value="${vdate}" class="ui_input_txt02 Wdate"/>
							</c:when>
							<c:otherwise>
								<fmt:formatDate var="vdate" value="${entity.vdate}" pattern="yyyy-MM-dd"/>
								<input name="vdate" value="${vdate}" class="ui_input_txt02 Wdate"/>
							</c:otherwise>
						</c:choose>

					</td>
				</tr>


				<tr>
					<td></td>
					<td>
						<input type="button" value="添加明细" class="ui_input_btn02 appendRow">
						<table class="edit_table" cellspacing="0" cellpadding="0" border="0">
							<thead>
							<tr>
								<th width="170">货品</th>
								<th width="100">品牌</th>
								<th width="80">价格</th>
								<th width="80">数量</th>
								<th width="100">金额小计</th>
								<th width="180">备注</th>
								<th width="120"></th>
							</tr>
							</thead>
							<tbody id="edit_table_body">
									<c:forEach var="item" items="${entity.items}">

										<tr>
											<td>
												<input readonly class="ui_input_txt01" tag="name" value="${item.product.name}">
												<img src="/images/common/search.png" class="searchproduct">
												<input type="hidden" tag="pid" value="${item.product.id}">
											</td>
											<td><span tag="brand">${item.product.brandName}</span></td>
											<td><input type="number" tag="costPrice" class="ui_input_txt01" value="${item.costPrice}"></td>
											<td><input type="number" tag="number"  class="ui_input_txt01" value="${item.number}"></td>
											<td><span tag="amount">${item.amount}</span></td>
											<td><input tag="remark"  class="ui_input_txt01" value="${item.remark}"></td>
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="返回" class="ui_input_btn01 btn_back"/>
					</td>
				</tr>
			</table>

		</div>
	</div>
</form>
</body>
</html>