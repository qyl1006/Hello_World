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
<script type="text/javascript" src="/plugins/artDialog/iframeTools.js"></script>


	<script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

	<script type="text/javascript">

		$(function () {
            //添加明细功能
            //克隆第一行
            $(".appendRow").click(function () {
                var tr = $("#edit_table_body tr:first").clone();


                //情况克隆体的数据
                tr.find("input").val("");
                tr.find("span").html("");

                //加入到
                tr.appendTo("#edit_table_body");
            });



            //统一事件绑定
			$("#edit_table_body").on("click", ".searchproduct", function () {
                //获取当前行
                var tr = $(this).closest("tr");


                $.dialog.open("/product/productListViews.do", {
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
                            tr.find("[tag='salePrice']").val(data.salePrice);
                            tr.find("[tag='number']").val(1);
                            tr.find("[tag='brand']").html(data.brandName);
                            tr.find("[tag='amount']").html(data.salePrice.toFixed(2));
                        }
                    }
                });


            } ).on("blur", "input[tag='salePrice", function () {
                //得到当前行
                var tr =$(this).closest("tr");

                //当前行在找成本价 和 数量
                var salePrice = tr.find("[tag='salePrice']").val() || 0;
                var number = tr.find("[tag='number']").val() || 0;
                var amount = salePrice * number;
                tr.find("[tag='amount']").html(amount.toFixed(2));
            }).on("click", ".removeItem", function () {
                var tr = $(this).closest("tr");

                //剩下最后一行, 情况数据即可
                if($("#edit_table_body tr").size() == 1){
                    tr.find("input").val("");
                    tr.find("span").html("");
                    return;
                }
                tr.remove();
            })




            // //对于单价和数量文本框 绑定失去焦点事件 重新计算小计
            // $("input[tag='salePrice'],input[tag='number']").blur(function () {
				//
            // });

            //
            // //删除功能, 删除当前所在的行
            // $(".removeItem").click(function () {
            //
            // });



            //
            // //放大镜按钮
            // $(".searchproduct").click(function () {
            //
            // });





            //日期插件
			$(".Wdate").click(function () {
                WdatePicker({isShowClear:true,readOnly:true,lang:'zh-cn'})
            });


            $("#editForm").ajaxForm(function (data) {
                showDialog("保存成功!",function () {
					location.href="/stockOutComeBill/list.do";
                });
            });


			$(".btn_submit").click(function () {
                //修改明细的参数名称的索引
			    $.each($("#edit_table_body tr"), function (index, tr) {
			        //找到4个提交到后台的参数
					$(tr).find("[tag='pid']").prop("name", "items["+ index +"].product.id");
					$(tr).find("[tag='salePrice']").prop("name", "items["+ index +"].salePrice");
					$(tr).find("[tag='number']").prop("name", "items["+ index +"].number");
					$(tr).find("[tag='remark']").prop("name", "items["+ index +"].remark");
                });

			    //手动提交
                $("#editForm").submit();
            });

			//供应商信息回显
			$(".select_depot option[value='${entity.depot.id}']").prop("selected", true);
			$(".select_client option[value='${entity.depot.id}']").prop("selected", true);


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
<form id="editForm" action="/stockOutComeBill/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">出库单编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">出库单编码</td>
					<td class="ui_text_lt">
						<input name="sn" value="${entity.sn}" class="ui_input_txt02"/>
					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">仓库</td>
					<td class="ui_text_lt">

						<select name="depot.id" class="ui_select03 select_depot">
							<c:forEach items="${depots}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>

					</td>
				</tr>

				<tr>
					<td class="ui_text_rt" width="140">客户</td>
					<td class="ui_text_lt">

						<select name="client.id" class="ui_select03 select_client">
							<c:forEach items="${clients}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
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
											<td><input type="number" tag="salePrice" class="ui_input_txt01" value="${item.salePrice}"></td>
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
										<td><input type="number" tag="salePrice" class="ui_input_txt01"></td>
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