//文档加载完执行
window.onload = function () {
    //获取省份/城市的下拉对象
    var p = document.getElementById("province");
    var c = document.getElementById("city");

    //发送ajax请求, 获取省份数据
    var ajax = new XMLHttpRequest();
    //新建请求
    ajax.open("get", "/linkage2/getProvinces.do");
    //绑定一个事件
    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200){
            //把服务器返回的数据填入HTML
            // p.innerHTML += ajax.responseText;
            var arr = eval("(" + ajax.responseText + ")");
            console.log("括号" + arr);
            var arr2 = eval("ajax.responseText");
            console.log("没有加" + arr2);


            for (var i = 0; i < arr.length; i ++){
                p.innerHTML +=  "<option value=" + arr[i].id + ">" + arr[i].name + "</option>";
            }
        }
    }
    ajax.send();


    //当省份数据发生变化时,触发绑定的函数,带着id发生ajax获取城市数据
    p.onchange = function () {
        //每次触发,,城市下拉列表  都回到最初的状态
        c.innerHTML = " <option value=\"-1\">--请选择--</option>";

        //判断下
        if(this.value){
            //发生ajax请求
            var ajax = new XMLHttpRequest();
            ajax.open("get", "/linkage2/getCitys.do?id=" + this.value);
            ajax.onreadystatechange = function () {
                if(ajax.readyState == 4 && ajax.status == 200){
                    var arr = eval("(" + ajax.responseText + ")");
                    for (var i = 0; i < arr.length; i ++){
                        c.innerHTML +=  "<option value=" + arr[i].id + ">" + arr[i].name + "</option>";
                    }
                }
            }
            ajax.send();
        }
    }
}