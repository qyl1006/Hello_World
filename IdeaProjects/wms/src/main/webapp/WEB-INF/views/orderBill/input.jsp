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
            //日期插件
            $(".Wdate").click(function () {
                WdatePicker({
                    readOnly: true,
                    lang:'zh-cn',
                    maxDate: new Date()
                })
            });

            $("#editForm").ajaxForm(function (data) {
                showDialog("保存成功", function () {
					location.href="/orderBill/list.do";
                    });
            });

            $(".btn_submit").click(function () {
                //修改明细中的参数名称name
				$.each($("#edit_table_body tr"), function (index, tr) {
					//找到4个提交的后台的参数
                    $(tr).find("[tag='pid']").prop("name", "items["+ index +"].product.id");
                    $(tr).find("[tag='costPrice']").prop("name", "items["+ index +"].costPrice");
                    $(tr).find("[tag='number']").prop("name", "items["+ index +"].number");
                    $(tr).find("[tag='remark']").prop("name", "items["+ index +"].remark");
                });
                $("#editForm").submit();
            });

         //添加明细功能
			//克隆第一行
			$(".appendRow").click(function () {
				var tr = $("#edit_table_body tr:first").clone();

				//清空克隆体的数据
				tr.find("input").val("");
				tr.find("span").html("");

				//加入
				tr.appendTo("#edit_table_body");
            });

			//统一事件绑定
			$("#edit_table_body").on("click", ".searchproduct", function () {
				//获取当前行
				var tr = $(this).closest("tr");

				$.dialog.open("/product/productListViews.do",{
                    title: "商品选择",
                    width: "90%",
                    height: "90%",
                    left: '50%',
                    top: '50%',
                    lock: true,
                    resize: false,
                    close: function () {
                        //获取子窗口传过来的数据
                        var data = $.dialog.data("data");
                        //判断 有数据就执行
                        if(data){
                            //清空
                            $.dialog.removeData("data");

                            //回填数据
                            tr.find("[tag='name']").val(data.name);
                            tr.find("[tag='pid']").val(data.id);
                            tr.find("[tag='costPrice']").val(data.costPrice);
                            tr.find("[tag='number']").val(1);
                            tr.find("[tag='brand']").html(data.brandName);
                            tr.find("[tag='amount']").html(data.costPrice.toFixed(2));
                        }
                    }

				});
            }).on("blur", "input[tag='costPrice'],input[tag='number']", function () {
				//得到当前行
				var tr = $(this).closest("tr");

                //在当前行找成本价 和数量
				var costPrice = tr.find("[tag='costPrice']").val() || 0;
				var number = tr.find("[tag='number']").val() || 0;
				var amount = costPrice * number;
				tr.find("[tag='amount']").html(amount.toFixed(2));
            }).on("click", ".removeItem", function () {
				var tr = $(this).closest("tr");
				//剩下最后一行 清空数据即可
				if($("#edit_table_body tr").size() == 1){
				    tr.find("input").val("");
				    tr.find("span").html("");
				    return;
				}
				tr.remove();
            })

        });
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
							<c:choose>
								<%--更新操作时 动态显示当前订单的明细--%>
								<c:when test="${not empty entity.id}">
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
											<td>
												<a href="javascript:;" class="removeItem">删除明细</a>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<%--新增时 显示静态一行明细--%>
								<c:otherwise>
									<tr>
										<td>
											<input readonly class="ui_input_txt01" tag="name">
											<img src="/images/common/search.png" class="searchproduct">
											<input type="hidden" tag="pid">
										</td>
										<td><span tag="brand"></span></td>
										<td><input type="number" tag="costPrice" class="ui_input_txt01"></td>
										<td><input type="number" tag="number"  class="ui_input_txt01"></td>
										<td><span tag="amount"></span></td>
										<td><input tag="remark"  class="ui_input_txt01"></td>
										<td>
											<a href="javascript:;" class="removeItem">删除明细</a>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
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