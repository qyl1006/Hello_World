function distinct() {
    //获取到右边option的value
    var arr = $.map($("#s2 option"), function (ele) {
        return ele.value;
    });

    //迭代数组
    $.each(arr, function (index, val) {
        $.each($("#s1 option"), function (i, ele) {
            if(ele.value == val){
                $(ele).remove();
            }
        })
    })

}