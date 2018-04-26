$(function () {
    $("#myDatagrid").datagrid({
        fitColumns:true,
        pageNumber:1,
        pageSize:5,
        url:"emp_datagrid.json",
        pageList:[5,10,15,20],
        singleSelect:true,
        columns:[ [
            {field:'id',checkbox:true},
            {field:'name',title:'姓名',width:80},
            {field:'email',title:'邮箱',width:80},
            {field:'dept',title:'部门名称',width:80, formatter:function (value,row,index) {
                    return value?value.name:"";
                }},
            {field:'dept1',title:'部门ID',width:80, formatter:function (value,row,index) {
                    return row.dept?row.dept.id:'';
                }},

            {field:'age',title:'年龄',width:80,sortable:true,styler: function(value,row,index){
                    if (value < 20){
                        return 'background-color:#ffee00;color:red;';
                    }
                }},
            {field:'inputTime',title:'录入时间',width:80},
            {field:'tel',title:'电话',width:80},
            {field:'state',title:'状态',width:80,formatter:function (value, row,index) {
                    return value?"在职":"<font color='red'>离职</font>";
                }},
        ]],
        toolbar:'#toolbar_thns',
        pagination:true,

        rowStyler: function (index, row) {
            if(row.age>18){
                return 'background-color:#6293BB;color:#fff;';

            }
        },

    })

    // 弹窗
    $("#myDialog").dialog({
        width:"300",
        height:"300",
        closed:true,

        buttons:'#form_thns',
        onClose:function () {
            //清空表单数据
            $("#myForm").form("clear");
        }
    });

    //搜索
    $("#keword").searchbox({

        searcher:function (value, name) {
            var data = $("#d1").datebox('getValue');
            var data2 = $("#d2").datebox('getValue');
            console.log(data);
            console.log(data2);
            console.log(value);
            //让数据表格重新加载数据 从第一页加载  并且带上查询参数
            $("#myDatagrid").datagrid("load",{
                data:data,
                endData:data2,
                keword:value
            });
        }
    })

});

// 绑定事件
function add() {
    //打开弹窗
    $("#myDialog").dialog("open");
    //设置标题
    $("#myDialog").dialog("setTitle", "新增员工");


}

function edit() {
    var row = $("#myDatagrid").datagrid("getSelected");
    if(!row){
        //关闭弹窗
        cancel();
        $.messager.alert('友情提示','请选择一条数据！','error');
        return;
    }

    //处理部门数据
    row["dept.id"] = row.dept.id;
    //回显数据
    $("#myForm").form("load", row);

    //打开弹窗
    $("#myDialog").dialog("open")
    //设置标题
    $("#myDialog").dialog("setTitle", "编辑员工");

}

function del() {
    var row = $("#myDatagrid").datagrid("getSelected");
    if(!row){
        //关闭弹窗
        cancel();
        $.messager.alert('友情提示','请选择一条数据！','error');
        return;
    }
    //弹出确认框
    $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
        if (r){
            //发送请求到后台
            $.get("xxxxx",{id:row.id},function (data) {
                if(data.success()){
                    $.messager.confirm('友情提示','删除成功','info',function () {
                        //当前页面上加载
                        $("#myDatagrid").datagrid('reload');
                    });
                }else{
                    $.messager.confirm('提示','删除失败','error');
                }
            })
        }
    });

}

function save() {
    //提交表单
    $("#myForm").form('submit',{
        url:"employee_saveOrUpdate.json",

        //转换为json对象
        success:function (data) {
            data = $.parseJSON(data);
            if(data.success){
                //提示用户操作结果
                $.messager.alert('友情提示','保存成功！','info',function () {
                    //关闭弹窗
                    cancel();
                    //让datagrid重新查询员工数据
                    $("#myDatagrid").datagrid("reload");
                });
            }else {
                //关闭弹窗
                cancel();
                $.messager.alert('友情提示','保存失败！','error');


            }
        }
    });
}

function cancel() {
    //关闭
    $("#myDialog").dialog("close");

}