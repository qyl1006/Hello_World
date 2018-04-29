$(function () {
    $("#myDatagrid").datagrid({
        url:"emp_datagrid.json",

        fitColumns:true,
        singleSelect:true,

        //冻结
        frozenColumns:[[
            {field:'name',title:'姓名',width:100},
        ]
        ],
        columns:[[
            {field:'id',checkbox:true},

            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'dept',title:'部门',width:100,align:'center',formatter:function (value, row,index) {
                    return value?value.name:"";
                }},
            {field:'dept1',title:'部门ID',width:80,align:'center', formatter:function (value,row,index) {
                    return row.dept?row.dept.id:'';
                }},
            {field:'age',title:'年龄',width:100,align:'center'},
            {field:'inputTime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'state',title:'状态',width:100,align:'center', formatter:function (value, row, index) {
                    return value?"在职":"<font color='red'>离职</font>";
                }},
        ]],
        //分页
        pagination:true,
        //引入工具按钮
        toolbar:'#toolbar_thns',

        //显示特别的行,,,用于提醒
        rowStyler:function (index, row) {
            if(row.age > 18){
                return 'background-color:#6293BB;color:#fff;';
            }
        }
    });


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

    })
});

//绑定事件
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
        $.messager.alert("友情提示", "请选择一条数据!", "error");
        return;
    }

    //处理部门数据--->就是dept里面没有dept.yy的属性,所以无法通过该属性找到数据{}
    // 这时通过
    row["dept.yy"] = row.dept.name;
    row["dept.yy"] = row.dept.id;
    row["dept.yy"] = row.dept.aa;

    //回显数据
    $("#myForm").form("load", row);

    //打开弹窗
    $("#myDialog").dialog("open");
    //设置标题
    $("#myDialog").dialog("setTitle", "编辑员工");
}

function del() {

}

function save() {

}

function cancel() {
    //关闭
    $("#myDialog").dialog("close");

}