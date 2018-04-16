$(function () {
    $.get("/jquery/echo.do", function (data) {
        $("#s1 option[value='"+ data.id+"']").prop("selected", true);
    },"JSON");

});