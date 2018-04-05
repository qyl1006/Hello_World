//文档加载完毕, 执行函数
window.onload = function () {
    //获取到省份的下拉列表
    var pv = document.getElementById("province");
    var city = document.getElementById("city");

    //发送ajax请求, 获取省份数据
    var ajax = new XMLHttpRequest();
    ajax.open("get", "/linkage/getProvinces.do");
    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4 && ajax.status == 200) {
            // //把返回的HTML数据填入...
            // pv.innerHTML += ajax.responseText;

            var arr = eval("(" + ajax.responseText + ")");
            for (var i = 0; i < arr.length; i++) {
                var p = "<option value=" + arr[i].id + ">" +
                    arr[i].name + "</option>";
                pv.innerHTML += p;
            }

        }
    };
    ajax.send();


    //当用户改变域的内容-->省份数据发生变化, 触发事件,带着id发送ajax获取城市数据
    pv.onchange = function () {
        //回到最初的状态
        city.innerHTML = "<option value=\"-1\">--请选择--</option>";

        //如果有选择省份才发送请求.获取城市数据
        if (this.value) {
            //发送ajax请求,
            var ajax = new XMLHttpRequest();
            ajax.open("GET", "/linkage/getCity.do?id=" + this.value);
            ajax.onreadystatechange = function () {
                if (ajax.readyState == 4 && ajax.status == 200) {
                    //     //把返回的HTML数据填入..
                    //     city.innerHTML += ajax.responseText;
                    var arr = eval("(" + ajax.responseText + ")");
                    console.log(arr);
                    for (var i = 0; i < arr.length; i ++){
                        var p = "<option value=" + arr[i].id + ">" +
                            arr[i].name + "</option>";
                        city.innerHTML += p;
                    }
                }
            };
            ajax.send();
        }

    }
}