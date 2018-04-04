//删除item3
function deleteItem3() {
    //找到item3
    var item3 = document.getElementById("item3");
    if(item3){

        //2 获取当前元素的父元素
        var parent = item3.parentNode;
        //3 父元素删除子元素
        parent.removeChild(item3);
    }
}

//替代item3
function replaceItem3() {
    //1 先找到tiem3元素
    var op = document.getElementById("item3");
    if(op){
        //2准备一个替代item3的元素 新创建一个
        var newOp = document.createElement("option");
        newOp.id = "item33333";
        newOp.innerHTML = "item333333";
        //3 替代子节点
        op.parentNode.replaceChild(newOp, op);

    }
}

//在item3前插入itme2
function insertItem2() {
    //找到item3
    var op1 = document.getElementById("item3");
    var op2 = document.getElementById("item22");
    if(op1 && !op2){
        //准备一个item2的元素
        var newOp = document.createElement("option");
        newOp.id = "item22";
        newOp.innerHTML = "item222";
        //把item2插入到item3前
        op1.parentNode.insertBefore(newOp,op1);
    }

}