// //文档加载完毕后执行函数
// onload = function () {
//     //根据ID
//     console.log(document.getElementById("d1"));
//     //根据name名字
//     console.log(document.getElementsByName("hobby"));
//     //根据标签名
//     console.log(document.getElementsByTagName("div"));
//
//     //获取元素的内容
//     console.log(document.getElementById("d1").innerHTML);
//     console.log(document.getElementById("d1").innerText);
// }

//
// onload = function () {
//     var div = document.getElementById("d2");
//     var div2 = document.getElementById("xx");
//     var div3 = document.getElementById("d3");
//     //常用属性操作
//     //1 是否有字节点
//     console.log(div3.hasChildNodes());
//
//     //获取所有子节点
//     console.log(div.childNodes);
//     //获取第一个子节点
//     console.log(div.firstChild);
//
//     //获取最后一个节点
//     console.log(div.lastChild);
//
//     //获取元素上一个哥哥
//     console.log(div.previousSibling);
//     console.log(div2.previousSibling);
//     //获取元素下一个兄弟
//     console.log(div2.nextSibling);

onload = function () {
    var div = document.getElementsByName("qyl")[0];
    //获取标准属性 对象.属性名
    console.log(div.id);
    //对象.属性名 = 值
    div.id = "divv";

    //操作自定义属性  获取  getAttribute()
    console.log(div.getAttribute("name"));

    //设置值    setAttribute()
    div.setAttribute("name", "bunny")

    //属性名和属性值相同的情况---使用boolean类型来表示
    var box = document.getElementById("box");
    console.log(box.checked);
    box.checked = false;

    //操作class属性
    console.log(box.className);
    box.className = "";

}


