//全选 全不选
function checkAll(check) {
    $("input[name='hobby']").prop("checked", check);
};

//反选
function checkUnAll() {
    $("input[name='hobby']").prop("checked", function (i, val) {
        return !val;
    });
};


//全选/全不选
$(function () {
    $("#checkAll").click(function () {
        checkAll(this.checked);
    });
});