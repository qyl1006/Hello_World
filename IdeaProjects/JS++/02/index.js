$(function () {
    //按钮样式
    var cls = "l-btn l-btn-small"
    //按钮文字模板
    var temp =  "<span class=\"l-btn-left\"><span class=\"l-btn-text\">点我</span></span>";


    //找到要初始化为我们定义的组件的元素
    var btns = $(".myeasyui-linkbutton");

    //把模板和样式添加到元素中
    for (var i = 0; i < btns.length; i++){
        var btn = btns[i];

        $(btn).addClass(cls);
        $(btn).html(temp);

    }
});