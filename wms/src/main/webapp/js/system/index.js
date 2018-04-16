$(function() {
    // 加载日期
    loadDate();
    // ======================================
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function() {
        switchSysBar();
    });
    // ======================================
	//面板切换 -> 改变图片和class属性
    $("#TabPage2 li").click(function () {
    	//先复位操作
		$.each($("#TabPage2 li"), function (index, ele) {
			//重置样式和图片
			$(ele).removeClass("selected");
            $(ele).children("img").prop("src", "/images/common/"+(index+1)+".jpg");
        });

		//把当前点击的面板图片变成_hover
        var i = $(this).index() + 1;
        $(this).children("img").prop("src", "/images/common/"+i+"_hover.jpg");
    	$(this).addClass("selected");
    	$("#nav_module img").prop("src","/images/common/module_"+i+".png");
    	//改变菜单
		var sn = $(this).data("rootmenu");
		loadMenu(sn);
    });

	//初始化菜单树
    loadMenu("business");
});

//zTree的设置
var setting = {
    data: {
        //启用简单JSON格式
        simpleData: {
            enable: true
        }
    },
	//设置节点的点击事件
	callback: {
    	onClick: function (e, treeId, node) {
            //获取当前节点的父节点
            var parentNode = node.getParentNode();
            if (parentNode) { //有父节点,执行操作
                var msg = "当前位置："+parentNode.name+"&nbsp;>&nbsp;"+node.name
                $("#here_area").html(msg);
                //跳转界面
				$("#rightMain").prop("src", "/"+node.action+".do");
            }
    	}
	},
    //发送异步请求获取数据
    async: {
        enable: true,
        url: "/systemMenu/getMenusBySn.do",
        autoParam: ["sn=menuSn"]
    }
};

//加载菜单
function loadMenu(sn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNode[sn]);
}

//菜单树
//业务模块菜单
var zNode = {
	//业务菜单
    business: [
        {id:2, pId:0, name: "业务模块", sn: "business", isParent: true}
    ],
    //系统模块菜单
    systemManage: [
        {id:1, pId:0, name: "系统模块", sn: "system", isParent: true}
    ],
    //报表菜单
    charts: [
        {id:3, pId:0, name: "报表模块", sn: "chart", isParent: true}
    ]
};




//------------------------------------------------------------------





//加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 */
function switchSysBar(flag) {
	var side = $('#side');
	var left_menu_cnt = $('#left_menu_cnt');
	if (flag == true) { // flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({
			width : '280px'
		});
		$('#top_nav').css({
			width : '77%',
			left : '304px'
		});
		$('#main').css({
			left : '280px'
		});
	} else {
		if (left_menu_cnt.is(":visible")) {
			left_menu_cnt.hide(10, 'linear');
			side.css({
				width : '60px'
			});
			$('#top_nav').css({
				width : '100%',
				left : '60px',
				'padding-left' : '28px'
			});
			$('#main').css({
				left : '60px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_show.png');
		} else {
			left_menu_cnt.show(500, 'linear');
			side.css({
				width : '280px'
			});
			$('#top_nav').css({
				width : '77%',
				left : '304px',
				'padding-left' : '0px'
			});
			$('#main').css({
				left : '280px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_hide.png');
		}
	}
}
