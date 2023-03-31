<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/color.csss">
    <script src="http://mgmt-center.msyidai.com/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://mgmt-center.msyidai.com/static/easyui/1.5.2/jquery.easyui.min.js"></script>
    <script src="http://mgmt-center.msyidai.com/static/easyui/1.5.2/easyui-lang-zh_CN.js"></script>

</head>
<body>
我的信息表
<script type="text/javascript">
    $(function(){
        //创建DataGrid
        $("#dg").datagrid({
            url:'${pageContext.request.contextPath}/datagrid/showAllData',  //数据来源
            //冻结列
            columns:[[
                {field:'checkbox',checkbox:true},
                {field:'id',title:'id',width:100},
                {field:'username',title:'姓名',width:100,align:'center',halign:'center'},
                {field:'sex',title:'性别',width:100,align:'center',align:'center',halign:'center'},
                {field:'formatDate',name:'formatData',title:'出生年月',width:100,align:'center',align:'center',halign:'center'},
                {field:'address',title:'地址',width:100,align:'center',align:'center',halign:'center'},
                {field:'password',title:'密码',width:100,align:'center',align:'center',halign:'center'},
                // {field:'class_',title:'班级',width:100,align:'center'}

            ]],
            fitColumns:false,//自适应宽度，占满,不能和冻结列同时设置成true
            striped:true,   //斑马线效果
            idField:'id',    //主键列
            rownumbers:true,            //显示行号
            singleSelect:true,          //是否单选
            pagination:true,
            pageList:[5,10,15,20],//每页行数选择列表
            pageSize:5, //设置默认初始的每页行数rows
            pageNumber:1,//设置默认初始的页码page
            remoteSort:false,    //是否服务器端排序，设成false才可以在页面进行排序
            sortName:'username', //指定列名可以进行排序
            multiSort:true,
            toolbar:[{iconCls:'icon-add',text:'添加',handler:function(){$("#addDataWin").window('open');}},
                     {iconCls:'icon-edit',text:'修改',handler:function(){onclick=selectUpDataUser()}},
                     {iconCls:'icon-remove',text:'删除',handler:function(){onclick=deleteData()}},
                     {name:"mobile",id:"searchMobile",class:"easyui-textbox"},
                     {iconCls:'icon-help',text:'查找',hhandler:function () {
                }}
            ]
        });
    });

        function deleteData() {
            var deleteId = $("#dg").datagrid("getSelected");
            if (deleteId==null) {
                $.messager.alert("提示", "请勾选要删除的数据", "info");
                return;
            }
            var sid = deleteId.id;
            $.ajax({
                url: "datagrid/eazyUiDelete",
                data: {"id": sid},
                success: function (data) {
                        if (data == 1) {
                            $.messager.alert("提示", "删除成功", "info");
                            $("#dg").datagrid('reload');
                        } else {
                            $.messager.alert("警告", "删除失败", "error");
                        }
                }
            });
        }



</script>



<table cellpadding="0" cellspacing="1" border="0">
    <tr>
        <td>选择查询项目：</td>
        <td><input id="queryItem" name="queryItem" class="easyui-combobox" style="width:330px;" ></td>
        <!-- 用户选择运算条件 -->
        <td><input id="operType" name="operType" class="easyui-combobox" style="width:90px;" data-options="panelHeight:150"/></td>
    </tr>
</table>

<table id="dg">全部信息</table>
<script>

    $( function(){
        var queryItemData = [{text : "注册天数", value : "registerDayNum"},
            {text : "注册渠道", value : "registerChannel"},
            {text : "平台首次授信", value : "creditFirstPlatform"},
            {text : "授信日期距离注册日期", value : "creditDateThroughRegisterDate"},
            {text : "授信渠道是否属于注册渠道", value : "creditChannelInRegisterChannel"},
            {text : "授信渠道", value : "creditChannel"},
            {text : "借款日期距离注册日期", value : "loanDateThroughRegisterDate"},
            {text : "借款渠道是否属于注册渠道", value : "loanChannelInRegisterChannel"},
            {text : "借款渠道", value : "loanChannel"}];

        var options01 = [{text : "=", value : "eq"}];

        var options02 = [{text : "=", value : "eq"},
                        {text : ">=", value : "ge"},
                        {text : ">", value : "gt"},
                         {text : "<=", value : "le"},
                        {text : "<", value : "lt"},
                        {text : "!=", value : "ne"}];

        var options03 = [{text : "=", value : "eq"},
                           {text : "!=", value : "ne"}];

        //初始化查询项目的下拉列表
        $("#queryItem").combobox({
            valueField: 'value',
            textField: 'text',
            data : queryItemData,
            panelHeight:170,
            //注册Easy-UI, combobox的onSeclete事件
            //目的：实现理财产品中，高级查询的“运算条件”随着“查询项目”的改变而发生联动。
            onChange : function(){
                var myOptValue = $("#queryItem").combobox("getValue") ;

                //1.清空原来的operType这个combobox中的选项
                $("#operType").combobox("clear");

                //2.动态添加"操作类型"的下拉列表框的option
                if( myOptValue != null && (myOptValue == 'creditFirstPlatform' || myOptValue == 'creditChannelInRegisterChannel'
                    || myOptValue == 'loanChannelInRegisterChannel') ){
                    console.info("myOptValue = "+myOptValue);
                    $("#operType").combobox({
                        panelHeight:50,
                        data : options01
                    });
                }else if ( myOptValue != null && (myOptValue == 'registerChannel' || myOptValue == 'creditChannel'
                    || myOptValue == 'loanChannel')) {
                    $("#operType").combobox({
                        panelHeight:140,
                        data : options03
                    });
                }else if (myOptValue != null && (myOptValue == 'registerDayNum' || myOptValue == 'creditDateThroughRegisterDate'
                || myOptValue == 'loanDateThroughRegisterDate')) {
                    $("#operType").combobox({
                        panelHeight:140,
                        data : options02
                    });
                }
            }
        });
        //初始化operType的下拉列表
        $("#operType").combobox({
            valueField: 'value',
            textField: 'text',
            data : options02,
            panelHeight:140,
        });
    });

    


    function selectUpDataUser() {
        var selectUpdataId = $("#dg").datagrid("getSelected");
        if (selectUpdataId==null) {
            $.messager.alert("提示", "请勾选要修改的数据", "info");
        }
        else {
            $("#upDataUser").window('open');
            var retData = $("#dg").datagrid("getSelected");
            var re_name = retData.username;
            var re_sex = retData.sex;
            $(":radio[name='sex'][value='" + re_sex + "']").attr("checked", "checked");
            // var re_day = retData.birthday.getDay();
            var re_address = retData.address;
            $("#updatausername").textbox("setValue",re_name);
            // $("#updatayear").textbox("setValue",re_year);
            // $("#updatamonth").textbox("setValue",re_month);
            // $("#updataday").textbox("setValue",re_day);
            $("#updataaddress").textbox("setValue",re_address);
            $("#updatesex").textbox("setValue",re_sex);
        }
    }
    function submitUpData() {
        var updataId = $("#dg").datagrid("getSelected");
        var uid = updataId.id;
        var username = $('#updatausername').textbox('getValue');
        var sex = $('input[name="sex"]:checked ').val();
        var address=$('#updataaddress').textbox('getValue');
        var year=$('#updatayear').textbox('getValue');
        var month=$('#updatamonth').textbox('getValue');
        var day=$('#updataday').textbox('getValue');
        if(username==null||username==''){
            $.messager.alert("提示", "姓名不能为空","alert");
            return;
        }
        if(sex==null||sex==''){
            $.messager.alert("提示", "性别不能为空","alert");
            return;
        }

        if(address==null||address==''){
            $.messager.alert("提示", "地址不能为空","alert");
            return;
        }
        if(year==null||year==''){
            $.messager.alert("提示", "年份不能为空","alert");
            return;
        }
        if(month==null||month==''){
            $.messager.alert("提示", "月份不能为空","alert");
            return;
        }
        if(day==null||day==''){
            $.messager.alert("提示", "日期不能为空","alert");
            return;
        }
        $.ajax({
            url:"datagrid/upDataUser",
            data:{"updatausername":username,
                  "updatasex":sex,
                  "updataaddress":address,
                  "updatayear":year,
                  "updatamonth":month,
                  "updataday":day,
                  "uid":uid},
            type:"post",
            success:function(data){//ajax返回的数据
                if(data.code=='success'){
                    $.messager.alert("提示", "更改成功","alert");
                    $("#dg").datagrid('reload');
                    $("#upDataUser").window('close');
                }else{
                    $.messager.alert("提示", data.msg,"alert");
                }
            },
            error:function (data) {
                alert("数据错误");
            }
        });
    }


    function submitForm(){
        var username = $('#username').textbox('getValue');
        var sex=$('#sex').textbox('getValue');
        var password=$('#password').textbox('getValue');
        var surepassword=$('#surepassword').textbox('getValue');
        var address=$('#address').textbox('getValue');
        var year=$('#year').textbox('getValue');
        var month=$('#month').textbox('getValue');
        var day=$('#day').textbox('getValue');
        var regex = "^(?![A-Za-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$";
        if(username==null||username==''){
            $.messager.alert("提示", "用户名不能为空","alert");
            return;
        }
        if(sex==null||sex==''){
            $.messager.alert("提示", "必须选择性别","alert");
            return;
        }
        if(password==null||password==''){
            $.messager.alert("提示", "密码不能为空","alert");
            return;
        }

        if(surepassword==null||surepassword==''){
            $.messager.alert("提示", "确认密码不能为空","alert");
            return;
        }
        if(surepassword!=password){
            $.messager.alert("提示", "确认密码和密码不一致","alert");
            return;
        }
        if(address==null||address==''){
            $.messager.alert("提示", "地址不能为空","alert");
            return;
        }
        if(year==null||year==''){
            $.messager.alert("提示", "年份不能为空","alert");
            return;
        }
        if(month==null||month==''){
            $.messager.alert("提示", "月份不能为空","alert");
            return;
        }
        if(day==null||day==''){
            $.messager.alert("提示", "日期不能为空","alert");
            return;
        }
        $.ajax({
            url:"datagrid/insertUser",
            data:$("#addDataForm").serialize(),
            type:"post",
            success:function(data){//ajax返回的数据
                if(data.code=='success'){
                    $.messager.alert("提示", "添加成功","alert");
                    $("#dg").datagrid('reload');
                    $("#addDataWin").window('close');
                }else{
                    $.messager.alert("提示", data.msg,"alert");
                }
            }
        });
    }
    function selectY() {
        var addr = $("#addressTest").combobox("getValue");
        alert(addr);
        $.ajax({
            url:"datagrid/cctest",
            data:{"addr":addr},
            type:"post",
            success:function(data){//ajax返回的数据
                $("#passwordTest").textbox("setValue",data.rtaddr);
            }
        });
    }
    $(document).ready(function(){
        $("#addressTest").select(function(){
            var addr = $("#addressTest").combobox("getValue");
            alert(addr);
            $.ajax({
                url:"datagrid/cctest",
                data:{"addr":addr},
                type:"post",
                success:function(data){//ajax返回的数据
                    $("#passwordTest").textbox("setValue",data.rtaddr);
                }
            });

        });
    });

    $('#addressTest').combobox({
        onChange : function(n,o)
        {alert(n);}
    });

    $("#addressTest").combobox({
        valueField: 'value',
        textField: 'text',
        data : queryItemData,
        panelHeight:170,
        //注册Easy-UI, combobox的onSeclete事件
        //目的：实现理财产品中，高级查询的“运算条件”随着“查询项目”的改变而发生联动。
        onSelect : function(){
            var myOptValue = $("#addressTest").combobox("getValue") ;

            //1.清空原来的operType这个combobox中的选项
            $("#passwordTest").combobox("clear");

            //2.动态添加"操作类型"的下拉列表框的option
            if( myOptValue != null && (myOptValue == '东北' || myOptValue == '呼兰') ){
                console.info("myOptValue = "+myOptValue);
                $("#passwordTest").textbox({
                    panelHeight:50,
                    data : 11111
                });
            }else{
                $("#passwordTest").combobox({
                    panelHeight:140,
                    data : 22222
                });
            }

            //3.清空文本输入框——用户输入的条件
            $("#userInputCondition").val("");
        }
    });
</script>

<div>
    <select id="ccess">
        <option value ="volvo">Volvo</option>
        <option value ="saab">Saab</option>
        <option value="opel">Opel</option>
        <option value="audi">Audi</option>
    </select>

</div>




<div class="field">
    <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
    <select name="address" id="addressTest" class="easyui-combobox"
            data-options="panelHeight:'auto',valueField: 'address',panelMaxHeight:200, textField: 'address',url:'newDatagrid/newCombobox'"
            style="width:60%"  required="true" onselect="selectY()">
    </select>
</div>
<div class="field">
    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
    <input name="password" id="passwordTest" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
</div>
<div>
    <select name="productType" id="en" class="form-control" style="width:180px;">
        <option value="">请选择</option>
        <c:forEach items="${intProTyp}" var="feeRulePage"></c:forEach>
    </select>
</div>








<div id="addDataWin" class="easyui-window"
     data-options="footer:'addDataWin_footer',modal:true,closed:true,maximizable:false,
		height:'300px',width:'500px',resizable:false,minimizable:false,collapsible:false,
		title:'添加信息'">
    <form  style="margin-top:20px;margin-left:20px;text-align: center;" enctype="multipart/form-data" id="addDataForm" method="post">
        <div class="field">
            <label for="username">用&nbsp;户&nbsp;名&nbsp;</label>
            <input name="username" id="username" class="easyui-textbox" data-options="prompt:'咖啡选择xls文件...',panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div class="field">
            <label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
            <input name="sex" id="sex" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div class="field">
            <label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</label>
            <input name="year" id="year" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:20%"  required="true">-----
            <input name="month" id="month" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:15%"  required="true">-----
            <input name="day" id="day" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:15%"  required="true">
        </div>

        <div class="field">
            <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
            <input name="address" id="address" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div class="field">
            <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            <input name="password" id="password" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div class="field">
            <label for="surepassword">确认密码</label>
            <input name="surepassword" id="surepassword" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div id="addDataWin_footer" style="height:50px;text-align: center;margin-top:6px">
            <a href="javascript:void(0);" class="easyui-linkbutton c1" style="width:40%;margin-top:10px;" onclick="submitForm();">保存</a>
        </div>
    </form>
</div>

<div id="upDataUser" class="easyui-window"
     data-options="footer:'upData_footer',modal:true,closed:true,maximizable:false,
		height:'300px',width:'500px',resizable:false,minimizable:false,collapsible:false,
		title:'更改信息'">
    <form  style="margin-top:20px;margin-left:20px;text-align: center;" enctype="multipart/form-data" id="upDataForm" method="post">
        <div class="field">
            <label for="username">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
            <input name="username" id="updatausername" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true" value="">
        </div>
        </br>
        <div class="field" >
            <label for="sex" id="updatesex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男" >男
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女" >女
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="保密">保密
        </div>
        </br>
        <div class="field">
            <label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</label>
            <input name="year" id="updatayear" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:20%"  required="true">-----
            <input name="month" id="updatamonth" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:15%"  required="true">-----
            <input name="day" id="updataday" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:15%"  required="true">
        </div>
        </br>
        <div class="field">
            <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
            <input name="address" id="updataaddress" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        </br>
        <div id="upData_footer" style="height:50px;text-align: center;margin-top:6px">
            <a href="javascript:void(0);" class="easyui-linkbutton c1" style="width:40%;margin-top:10px;" onclick="submitUpData();">提交更改</a>
        </div>
    </form>
</div>
</body>
</html>