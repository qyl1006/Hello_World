$(function () {
    //发送ajax请求
    $.get("/linkage2/getProvinces.do", function (data) {
        $.each(data, function (index, domEle) {
            //<option value="-1">---请选择---</option>
            $("<option ></option>").val(domEle.id).html(domEle.name).appendTo("#province");
        })
    }, "JSON");

    $("#province").bind("change", function () {
        if(this.value){
            //每次请求重置原始状态
            $("#city").html("<option value=\"-1\">--请选择--</option>");

            //发送ajax请求,
            $.get("/linkage2/getCitys.do",{"id":this.value}, function (data) {
                //<option value="-1">--请选择--</option>
                $.each(data, function (index, domEle) {
                    $("<option></option>").val(domEle.id).html(domEle.name).appendTo("#city");
                })



            }, "JSON")
        }
    })

})