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
					location.href="/employee/list.do";
                });
            });


			$(".ui_input_btn01").click(function () {

			    $(".selected_role option").prop("selected", true);
			    //手动提交
                $("#editForm").submit();
            });

			//部门名称回显
			$(".ui_select03 option[value='${entity.dept.id}']").prop("selected", true);


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
<form id="editForm" action="/employee/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${entity.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">员工编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">用户名</td>
					<td class="ui_text_lt">
						<input name="name" value="${entity.name}" class="ui_input_txt02"/>
					</td>
				</tr>
			<c:if test="${empty  entity.id}">
				<tr>
					<td class="ui_text_rt" width="140">密码</td>
					<td class="ui_text_lt">
						<input type="password" name="password"  id="password" class="ui_input_txt02"/>
					</td>
				</tr>


				<tr>
					<td class="ui_text_rt" width="140">验证密码</td>
					<td class="ui_text_lt">
						<input name="repassword" type="password" class="ui_input_txt02" />
					</td>
				</tr>

			</c:if>

				<tr>
					<td class="ui_text_rt" width="140">Email</td>
					<td class="ui_text_lt">
						<input name="email" value="${entity.email}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>

					<td class="ui_text_rt" width="140">年龄</td>
					<td class="ui_text_lt">
						<input name="age" value="${entity.age}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">所属部门</td>
					<td class="ui_text_lt">

						<select name="dept.id" class="ui_select03">
							<c:forEach items="${departments}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>

					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">超级管理员</td>
					<td class="ui_text_lt">
						<input type="checkbox" name="admin" class="ui_checkbox01" ${entity.admin?"checked":""}/>
					</td>
				</tr>
				<tr id="distribution_permission">
					<td class="ui_text_rt" width="140">分配权限</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<select multiple="true" class="ui_multiselect01 all_role">
										<c:forEach items="${roles}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right"/><br/>
									<input type="button" id="selectAll" value="==>" class="left2right"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right"/>
								</td>
								<td>
									<select multiple="true" name="ids" class="ui_multiselect01 selected_role">
										<c:forEach items="${entity.roles}" var="item">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>

			<script type="text/javascript">
                $(function () {
                    //去重
                    $(".selected_role option").each(function (i, e) {

                        $(".all_role option").each(function (index, val) {
                            if(e.value == val.value){
                                $(val).remove();
                            }
                        })
                    });

                    //全部右移
                    $("#selectAll").click(function () {
                        $(".all_role option").appendTo(".selected_role")
                    });
                    //全部左移
                    $("#deselectAll").click(function () {
                        $(".selected_role option").appendTo(".all_role")
                    });

                    //单右移
                    $("#select").click(function () {
                        $(".all_role option:selected").appendTo(".selected_role")
                    });
                    //单左移
                    $("#deselect").click(function () {
                        $(".selected_role option:selected").appendTo(".all_role")
                    });

                    //超级管理员
                    // $(".ui_checkbox01").prop("checked");

                    //定义变量 用户根据管理员状态显示
                    var det = null
                    if ($(".ui_checkbox01").prop("checked")){
                        det = $("#distribution_permission").detach();
                    };

                    $(".ui_checkbox01[name='admin']").click(function () {
                        if(this.checked){
                            det = $("#distribution_permission").detach();
                        }else{
                            $(this).parents("tr").after(det);
                        }
                    });



                })
			</script>
		</div>
	</div>
</form>
</body>
</html>