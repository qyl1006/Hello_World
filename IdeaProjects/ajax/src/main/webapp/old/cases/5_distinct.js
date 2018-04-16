function distinct() {
    //去除重复 方式一
    // var arr = $("#s2 option").map(function () {
    //     return $(this).val();
    // })
    //
    // $.each(arr, function (index, val) {
    //     $.each($("#s1 option"),function (i, e) {
    //         if(val == e.value){
    //             $(e).remove();
    //         }
    //     })
    // })

    //去除重复 方式二
    $("#s2 option").each(function (i, b) {
        $("#s1 option").each(function (index, e) {
            if(b.value == e.value){
                $(e).remove()
            }
        })
    });

};