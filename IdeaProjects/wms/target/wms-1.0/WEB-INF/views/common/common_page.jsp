<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04">${result.pageCount}</span>
        条记录，当前第
        <span class="ui_txt_bold04">${result.currentPage}/${result.endPage}</span>
        页
    </div>
    <div class="ui_frt">
        <input type="button" value="首页" class="ui_input_btn01" data-page="1"/>
        <input type="button" value="上一页" class="ui_input_btn01" data-page="${result.prevPage}"/>
        <input type="button" value="下一页" class="ui_input_btn01" data-page="${result.nextPage}"/>
        <input type="button" value="尾页" class="ui_input_btn01" data-page="${result.endPage}"/>

        <select name="pageSize" class="ui_select02">
            <option value="3">3</option>
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
        </select>
        转到第<input type="text" name="currentPage" value="${result.currentPage}" class="ui_input_txt01" />页
        <input type="button" class="ui_input_btn01" value="跳转"/>
    </div>
</div>

<script type="text/javascript">
    $(".ui_input_btn01").click(function () {
        //获取
        var currentPage = $(this).data("page") || $("input[name='currentPage']").val();
        //设置值
        if (currentPage <= ${result.endPage}){
            $("input[name='currentPage']").val(currentPage);

            //提交表单
            $("#searchForm").submit();
        }


    });

    //新增跳转
    $(".btn_input").click(function () {
        location.href = $(this).data("url");
    });

    //页面容量回显
    $(".ui_select02 option[value='"+ ${result.pageSize}+"']").prop("selected", true);
    //页面容量改变自动提交
    $(".ui_select02").change(function () {

        //当前页设置1
        $("input[name='currentPage']").val(1);
        //提交表单
        $("#searchForm").submit();
    });
</script>