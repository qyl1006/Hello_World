$(function () {
    $("#myTree").tree({
        url:"tree.json",
        //点击事件
        onClick:function (node) {
            //判断节点是否有url
            if(node.url){
                var exists = $("#myTabs").tabs("exists", node.text);
                if(exists){
                    //选中选项卡
                    $("#myTabs").tabs("select", node.text);
                }else{
                    //新增选项卡
                    $("#myTabs").tabs("add", {
                        title:node.text,
                        closable:true,
                        content:'<iframe src='+node.url +' width="100%" height="100%" frameborder="0"></frameborder>'
                    });
                }
            }else{
                $("#myTree").tree('toggle',node.target);
            }
        }
    })
});