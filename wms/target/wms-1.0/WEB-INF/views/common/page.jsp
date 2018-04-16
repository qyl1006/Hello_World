<%@ page contentType="text/html;charset=UTF-8" %>
<%--分页操作--%>
<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04">${result.rows}</span>
        条记录，当前第
        <span class="ui_txt_bold04">${result.currentPage}/${result.endPage}</span>
        页
    </div>
    <div class="ui_frt">
        <input type="button" value="首页"
               class="ui_input_btn01 btn_page" data-page="1"/>
        <input type="button" value="上一页"
               class="ui_input_btn01 btn_page" data-page="${result.prevPage}"/>
        <input type="button" value="下一页"
               class="ui_input_btn01 btn_page" data-page="${result.nextPage}"/>
        <input type="button" value="尾页"
               class="ui_input_btn01 btn_page" data-page="${result.endPage}"/>

        <select id="pageSize" name="pageSize" class="ui_select02">
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
        </select>
        转到第<input type="text" name="currentPage" value="${qo.currentPage}" class="ui_input_txt01" />页
        <input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
    </div>
</div>
<script>
    $(".btn_page").click(function () {
        //拿到当前页的值
        var page = $(this).data("page") ||
            $(":text[name='currentPage']").val();
        //设置当前页的值
        $(":text[name='currentPage']").val(page);
        //提交表单
        $("#searchForm").submit();
    });

    //改变页面容量
    $("#pageSize").change(function () {
        //当前页设置为1,然后提交表单
        $(":text[name='currentPage']").val(1);
        $("#searchForm").submit();
    });

    //回显当前页
    $("#pageSize option[value='${qo.pageSize}']").prop("selected", true);
</script>
