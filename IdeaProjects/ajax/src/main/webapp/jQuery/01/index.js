//jQuery中简单简写方式
$(function () {
    //绑定事件  jQuery对象.bind(事件名称, fu)
    $("#div").click(function () {
        this.innerHTML = "点击事件";
    });

    $("img").bind({
        click:function () {
            console.log("图像被点击");
        },
        mouseover:function () {
            console.log("鼠标移到图片上");
            this.src = "1.png";
        },
        mouseout:function () {
            console.log("鼠标从图片移开");
            this.src = "0.png";
        }
    })
})