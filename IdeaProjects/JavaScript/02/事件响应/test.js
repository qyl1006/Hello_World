//第一种方式
//定义函数 作为事件的响应函数
function type1(src, e) {
    //改动div中的内容 表示有效果
    document.getElementById("dv").innerHTML = "师姐你在哪儿?";
    console.log(src);//事件源
    console.log(e); //事件对象
}

// //第二种 在js代码中 使用 元素.onxx = 函数对象   来进行事件监听
// onload = function () {
//     //获取事件源
//     var dv = document.getElementById("two");
//     //给事件源中的事件绑定响应函数
//     dv.onclick = function (e) {
//         //改动div的内容
//         document.getElementById("dv").innerHTML = "第二张方式改变师姐";
//         //获取事件源的value属性值
//         console.log(this.value);
//         console.log(e);//事件dx
//     };
// };

//第三种方式   在js中给对象注册监听器来绑定事件的响应函数
onload = function () {
    //获取事件源
    var dv = document.getElementById("two");

    //给事件源添加事件的监听器
    dv.addEventListener("click", function (e) {
            //改动div的内容
            document.getElementById("dv").innerHTML = "第二张方式改变师姐";
            //获取事件源的value属性值
            console.log(this.value);
            console.log(e);//事件dx
    });


}
