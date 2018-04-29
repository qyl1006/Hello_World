﻿<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>叩丁狼wms(演示版)</title>
	<link href="/style/main_css.css" rel="stylesheet" type="text/css" />
	<link href="/style/zTreeStyle.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/plugins/jqueryZtree/jquery.ztree.core-3.4.min.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript" src="/js/system/index.js"></script>


	<%--<script type="text/javascript">--%>
		<%--$(function () {--%>
		    <%--var setting = {--%>
		        <%--//启用简单JSON格式--%>
				<%--simpleData: {--%>
                    <%--enable : true--%>
				<%--}--%>

			<%--};--%>

		    <%--//菜单树--%>
            <%--var nodes ={--%>
                <%--//业务菜单--%>
				<%--business: [--%>
                    <%--{id:1, pId:0, name: "业务模块"},--%>
                    <%--{id:11, pId:1, name: "品牌管理"},--%>
                    <%--{id:12, pId:1, name: "商品管理"}--%>
				<%--],--%>

				<%--//系统模块菜单--%>
				<%--systemManage: [--%>
                    <%--{id:1, pId:0, name: "系统模块"},--%>
                    <%--{id:11, pId:1, name: "部门管理"},--%>
                    <%--{id:12, pId:1, name: "员工管理"}--%>
				<%--],--%>

                <%--//报表菜单--%>
                <%--chart: [--%>
                    <%--{id:1, pId:0, name: "报表模块"},--%>
                    <%--{id:11, pId:1, name: "即时库存报表"},--%>
                    <%--{id:12, pId:1, name: "订货报表"}--%>
                <%--]--%>
			<%--}--%>


		    <%--//初始化菜单树--%>
			<%--$.fn.zTree.init($("#dleft_tab1"), setting, nodes["systemManage"]);--%>
        <%--})--%>
	<%--</script>--%>
</head>


<body>
    <div id="top">
		<div id="top_logo">
			<img alt="logo" src="/images/common/logo.jpg" width="274" height="49" style="vertical-align:middle;">
		</div>
		<div id="top_links">
			<div id="top_op">
				<ul>
					<li>
						<img alt="当前用户" src="/images/common/user.jpg">：
						<%--<span>${sessionScope.EMP_IN_SESSION.name}</span>--%>
						<span><shiro:principal property="name"/></span>
					</li>
					<li>
						<img alt="今天是" src="/images/common/date.jpg">：
						<span id="day_day"></span>
					</li>
				</ul> 
			</div>
			<div id="top_close">
				<a href="/logout.do" target="_parent">
					<img alt="退出系统" title="退出系统" src="/images/common/close.jpg" style="position: relative; top: 10px; left: 25px;">
				</a>
			</div>
		</div>
	</div>
    <!-- side menu start -->
	<div id="side">
		<div id="left_menu">
		 	<ul id="TabPage2" style="height:200px; margin-top:50px;">
				<li id="left_tab1" class="selected" title="业务模块" data-rootmenu="business">
					<img alt="业务模块" title="业务模块" src="/images/common/1_hover.jpg" width="33" height="31">
				</li>
				<li id="left_tab2" title="系统管理" data-rootmenu="systemManage">
					<img alt="系统管理" title="系统管理" src="/images/common/2.jpg" width="33" height="31">
				</li>
				<li id="left_tab3" title="报表" data-rootmenu="charts">
					<img alt="报表" title="报表" src="/images/common/3.jpg" width="33" height="31">
				</li>
			</ul>
			
			<div id="nav_show" style="position:absolute; bottom:0px; padding:10px;">
				<a href="javascript:;" id="show_hide_btn">
					<img alt="显示/隐藏" title="显示/隐藏" src="/images/common/nav_hide.png" width="35" height="35">
				</a>
			</div>
		 </div>
		 <div id="left_menu_cnt">
		 	<div id="nav_module">
		 		<img src="/images/common/module_1.png" width="210" height="58"/>
		 	</div>
		 	<div id="nav_resource">
		 		<ul id="dleft_tab1" class="ztree"></ul>
					<%--<a href="/department/list.do" target="right">部门管理</a><br>--%>
					<%--<a href="/employee/list.do" target="right">员工管理</a><br>--%>
					<%--<a href="/permission/list.do" target="right">权限管理</a><br>--%>
					<%--<a href="/role/list.do" target="right">角色管理</a><br>--%>
		 	</div>
		 </div>
	</div>
    <!-- side menu start -->
    <div id="top_nav">
		<span id="here_area">当前位置：系统模块&nbsp;>&nbsp;员工管理</span>
	</div>
    <div id="main">
      	<iframe name="right" id="rightMain" src="" frameborder="no" scrolling="auto" 
			width="100%" height="100%" allowtransparency="true"/>
    </div>
</body>
</html>
   
 