//全选/全不选
function checkAll(check) {
    //获取所有选择的  节点
    var arr = document.getElementsByName("hobby");
    //循环每个出来设置值
    for(var i = 0; i <arr.length; i ++){
        arr[i].checked = check;
    }
};

//反选
function checkUnAll() {
    //获取所有事件源
    var arr = document.getElementsByName("hobby");
    //循环每一个事件源,取反在赋值回去
    for (var i = 0; i < arr.length; i ++){
        arr[i].checked = !arr[i].checked;
    }
};

//全选/全不选
//在文档加载完成后执行
window.onload = function () {
    //获取事件源
    var c = document.getElementById("checkAll");

    //绑定响应函数
    c.onclick = function(){
        checkAll(this.checked);

    }

};