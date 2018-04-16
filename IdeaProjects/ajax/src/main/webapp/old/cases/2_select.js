//全部左  或 右移
function moveAll(src, tgr) {
    $("#"+ src+" option").appendTo("#"+ tgr+"");
}

//单个左 右移
function moveSelected(src, tgr) {
    $("#"+ src +" option:selected").appendTo("#"+ tgr+"");
}
