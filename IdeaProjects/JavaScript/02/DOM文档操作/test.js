//把span加入到div中
function span2div() {
    //步骤
    //1: 先获取到span元素
    var sp = document.getElementById("sp");
    //2获取div元素
    var div = document.getElementById("div");

    //3把span作为div的子元素
    div.appendChild(sp);
}

function newspan2div() {
    //步骤
    //1: 新创建新的span元素
    var sp = document.createElement("span");
    sp.innerHTML = "你好!师姐!好烦躁啊!怎么办?";
    //2获取div元素
    var div = document.getElementById("div");

    //3把span作为div的子元素
    div.appendChild(sp);
}