<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/9/29
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新页</title>
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-center.msyidai.com/static/easyui/1.5.2/themes/color.csss">
    <script src="http://mgmt-center.msyidai.com/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://mgmt-center.msyidai.com/static/easyui/1.5.2/jquery.easyui.min.js"></script>
    <script src="http://mgmt-center.msyidai.com/static/easyui/1.5.2/easyui-lang-zh_CN.js"></script>
    <script>
        <%-------------------------------------------------修改数据回显------------------------------------------------------------%>
        function selectUpDataUser() {
            var selectUpdataId = $("#user_list").datagrid("getSelected");
            if (selectUpdataId==null) {
                $.messager.alert("提示", "请勾选要修改的数据", "info");
            }
            else {
                $("#upDataUser").window('open');
                var retData = $("#user_list").datagrid("getSelected");
                var re_name = retData.username;
                var re_sex = retData.sex;
                $(":radio[name='sex'][value='" + re_sex + "']").attr("checked", "checked");
                var re_birthday = retData.formatDate;
                var re_address = retData.address;
                $("#updatausername").textbox("setValue",re_name);
                $("#updatebirthday").textbox("setValue",re_birthday);
                $("#updataaddress").textbox("setValue",re_address);
                $("#updatesex").textbox("setValue",re_sex);
            }
        }

        <%-------------------------------------------------等待------------------------------------------------------------%>
        function ajaxLoading(){
            $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
            $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
        }
        function ajaxLoadEnd(){
            $(".datagrid-mask").remove();
            $(".datagrid-mask-msg").remove();
        }

        function timer() {
            $.ajax({
                type: 'POST',
                dataType : 'json',
                url: "newDatagrid/timer",
                beforeSend: ajaxLoading,
                success: function(json){
                    ajaxLoadEnd();
                    alert("完成")
                    // createAll(json.Data);
                }
            });
        }
        <%-------------------------------------------------修改数据函数------------------------------------------------------------%>
        function submitUpData() {
            var username = $('#updatausername').textbox('getValue');
            var sex = $('input[name="sex"]:checked ').val();
            var address=$('#updataaddress').textbox('getValue');
            var birthday=$('#updatebirthday').datebox('getValue')
            var updateId = $('#user_list').datagrid("getSelected");
            var uid = updateId.id;
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
            if(birthday==null||birthday==''){
                $.messager.alert("提示", "必须选择年份","alert");
                return;
            }

            $.ajax({
                url:"newDatagrid/newUpdateUser",
                data:{"updatausername":username,
                    "updatasex":sex,
                    "updataaddress":address,
                    "updatabirthday":birthday,
                    "uid":uid},
                type:"post",
                success:function(data){//ajax返回的数据
                    if(data.code=='success'){
                        $.messager.alert("提示", "更改成功","alert");
                        $("#user_list").datagrid('reload');
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
        <%-------------------------------------------------查询详情回显函数------------------------------------------------------------%>
        function findUser() {
            var id = $("#finduser").val();
            $.ajax({
                url:"newDatagrid/finduser",
                data:{'allDataId':id},
                success:function(data){
                    if (data.msg == 1){
                        $.messager.alert("提示","没有对应该ID的用户","info");
                    }else {
                        $("#return_id").html(data.id);
                        $("#return_username").html(data.username);
                        $("#return_sex").html(data.sex);
                        $("#return_date").html(data.formatDate);
                        $("#return_address").html(data.address);
                        $("#return_password").html(data.password);
                        $("#returnUserData").window("open");
                    }
                    },
                error:function(data){
                    $.messager.alert("提示","链接错误","error");
                }
            });
         }
        <%-------------------------------------------------添加函数------------------------------------------------------------%>
        function submitForm(){
            var username = $('#username').textbox('getValue');
            var gerder = $('input[name="gender"]:checked ').val();
            var password=$('#password').textbox('getValue');
            var surepassword=$('#surepassword').textbox('getValue');
            var address=$('#address').textbox('getValue');
            var birthday = $('#birthday').datebox('getValue');
            var regex = "^(?![A-Za-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,20}$";
            if(username==null||username==''){
                $.messager.alert("提示", "用户名不能为空","alert");
                return;
            }
            if(gerder==null||gerder==''){
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
            if(birthday==null||birthday==''){
                $.messager.alert("提示", "生日不能为空","alert");
                return;
            }
            $.ajax({
                url:"newDatagrid/newInsertUserInfo",
                data:{"newUsername":username,
                    "newGender":gerder,
                    "newAddress":address,
                    "newBirthday":birthday,
                    "newPassword":password,
                    "newSurePassword":surepassword},
                type:"post",
                success:function(data){//ajax返回的数据
                    if(data.code=='success'){
                        $.messager.alert("提示", "添加成功","alert");
                        $("#user_list").datagrid('reload');
                        $("#addDataWin").window('close');
                    }else{
                        $.messager.alert("提示", data.msg,"alert");
                    }
                },
                error:function (data) {
                    $.messager.alert("警告", "数据错误"+data.msg,"error");
                }
            });
        }
        
        function toAdd() {
            $("#addDataWin").window('open');
        }


        function newfindUser() {
            $('#user_list').datagrid('load',{
                        id:$("#newfinduser").combobox('getValue')});
        }



        <%-------------------------------------------------删除函数------------------------------------------------------------%>
        function newDeleteData() {
            var deleteId = $("#user_list").datagrid("getSelected");
            if (deleteId==null) {
                $.messager.alert("提示", "请勾选要删除的数据", "info");
                return;
            }
            var sid = deleteId.id;
            $.ajax({
                url: "newDatagrid/eazyUiDelete",
                data: {"id": sid},
                success: function (data) {
                    if (data == 1) {
                        $.messager.alert("提示", "删除成功", "info");
                        $("#user_list").datagrid('reload');
                    } else {
                        $.messager.alert("警告", "删除失败", "error");
                    }
                }
            });
        }
    </script>
</head>
<body>
<%-------------------------------------------------表上按键堆------------------------------------------------------------%>
<h2 style="color: #0c7cd5">所有数据</h2>
<a href="javascript:void(0)" class="easyui-linkbutton"
   data-options="iconCls:'icon-add',iconAlign:'right',plain:false" onclick="toAdd();">添加</a>
<a href="javascript:void(0)" class="easyui-linkbutton"
   data-options="iconCls:'icon-cancel',iconAlign:'right',plain:false" onclick="newDeleteData();">删除</a>
<a href="javascript:void(0)" class="easyui-linkbutton"
   data-options="iconCls:'icon-edit',iconAlign:'right',plain:false" onclick="selectUpDataUser();">修改</a>
<a href="javascript:void(0)" class="easyui-linkbutton"
   data-options="iconCls:'icon-edit',iconAlign:'right',plain:false" onclick="timer();">延时</a>
<div id="finduser" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:4%"></div>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-help',iconAlign:'right',plain:false" onclick="findUser();">查询</a>
<div id="newfinduser" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:4%"></div>
<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',iconAlign:'right',plain:false" onclick="newfindUser();">新查询</a>
<div id="finduser" class="easyui-filebox" data-options="panelHeight:'auto',readonly:'false',prompt:'咖啡选择xls文件...',buttonText:'选择文件'"></div>

<%-------------------------------------------------数据表------------------------------------------------------------%>
<table id="user_list" class="easyui-datagrid" style="width:100%;height: 200px;"
       data-options="
			url: 'newDatagrid/showAllDat',
			rownumbers:true,
			toolbar:'#toolbar',
			singleSelect:true,
			fitColumns:true,
            striped:true,
			method:'get',
			pagination:true,
			selectOnCheck:true,
			striped:true,
			fit:true,
			pageList:[5,10,15,20],
			pageNumber:1,
			pageSize: 20,
            multiSort:true">
    <thead>
    <tr>
        <th data-options="field:'checkbox',checkbox:true"></th>
        <th data-options="field:'id',width:40,align:'center',halign:'center'">id</th>
        <th data-options="field:'username',width:40,align:'center',halign:'center'">用户姓名</th>
        <th data-options="field:'sex',width:40,align:'center',halign:'center'">性别</th>
        <th data-options="field:'formatDate',width:40,align:'center',halign:'center'">出生年月日</th>  <!-- formatter:fmts.formatYYYYMMDD -->
        <th data-options="field:'address',width:40,align:'center',halign:'center'">地址</th>  <!-- formatter:fmts.formatYYYYMMDD -->
        <th data-options="field:'password',width:40,align:'center',halign:'center'">密码</th>
    </tr>
    </thead>
</table>



<%-------------------------------------------------添加弹窗------------------------------------------------------------%>
<div id="addDataWin" class="easyui-window"
     data-options="footer:'addDataWin_footer',modal:true,closed:true,maximizable:false,
		height:'300px',width:'500px',resizable:false,minimizable:false,collapsible:false,
		title:'添加信息'">
    <form  style="margin-top:20px;margin-left:20px;text-align: center;" enctype="multipart/form-data" id="addDataForm" method="post">
        <div class="field">
            <label for="username">用&nbsp;户&nbsp;名&nbsp;</label>
            <input name="username" id="username" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div class="field" >
            <label for="gender" id="gender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="男" >男
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="女" >女
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" value="保密">保密
        </div>
        <div class="field">
            <label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</label>
            <input name="birthday" id="birthday" class="easyui-datebox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>

        <div class="field">
            <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
            <select name="address" id="address" class="easyui-combobox"
                    data-options="panelHeight:'auto',valueField: 'address',panelMaxHeight:200, textField: 'address',url:'newDatagrid/newCombobox'"
                    style="width:60%"  required="true">
                    <option value =""> 请选择 </option >
            </select>
        </div>
        <div class="field">
            <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            <input name="password" id="password" class="easyui-textbox"
                   data-options="url:'newDatagrid/newCombobox',valueField:'id',textField:'text'" style="width:60%"  required="true">
        </div>
        <div class="field">
            <label for="surepassword">确认密码</label>
            <input name="surepassword" id="surepassword" class="easyui-textbox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        <div id="addDataWin_footer" style="height:50px;text-align: center;margin-top:6px">
            <a href="javascript:void(0);" class="easyui-linkbutton c1" style="width:40%;margin-top:10px;" onclick="submitForm();">保存</a>
        </div>
        <label for="productId">贷款产品名称：</label><select name="productId" id="productId" class="easyui-combobox" data-options="panelHeight:'auto'" style="width:10%">
        <option value="">全部</option>
        <option value="1">咖啡</option>
        <option value="2">趣店</option>
    </select>
    </form>
</div>



<%-------------------------------------------------更改弹窗------------------------------------------------------------%>
<div id="upDataUser" class="easyui-window"
     data-options="footer:'upData_footer',modal:true,closed:true,maximizable:false,
		height:'300px',width:'500px',resizable:false,minimizable:false,collapsible:false,
		title:'更改信息'">
    <form  style="margin-top:20px;margin-left:20px;text-align: center;" enctype="multipart/form-data" id="upDataForm" method="post">
        <div class="field">
            <label for="username">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
            <input name="username" id="updatausername" class="easyui-textbox"
                   data-options="panelHeight:'auto'" style="width:60%"  required="true" value="">
        </div>
        </br>
        <div class="field" >
            <label for="gender" id="updatesex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="男" >男
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女" >女
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="保密">保密
        </div>
        </br>
        <div class="field">
            <label>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</label>
            <input name="formatDate" id="updatebirthday" class="easyui-datebox" data-options="panelHeight:'auto'" style="width:60%"  required="true">
        </div>
        </br>
        <div class="field">
            <label for="address">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
            <input name="address" id="updataaddress" class="easyui-combobox"
                   data-options="panelHeight:'auto',valueField: 'address',panelMaxHeight:200, textField: 'address',url:'newDatagrid/newCombobox'"
                   style="width:60%"  required="true">
        </div>
        </br>
        <div id="upData_footer" style="height:50px;text-align: center;margin-top:6px">
            <a href="javascript:void(0);" class="easyui-linkbutton c1" style="width:40%;margin-top:10px;" onclick="submitUpData();">提交更改</a>
        </div>
    </form>
</div>
<%-------------------------------------------------回显弹窗------------------------------------------------------------%>
<div id="returnUserData" class="easyui-window"
     data-options="footer:'addDataWin_footer',modal:true,closed:true,maximizable:false,
		height:'400px',width:'450px',resizable:false,minimizable:false,collapsible:false,
		title:'详细信息'">
    <table id="addData" class="easyui-datagrid">
        <thead>
        <tr>
            <th data-options="field:'find_id',width:150,align:'center',halign:'center'">用户id</th>
            <th data-options="field:'find_username',width:150,align:'center',halign:'center'">用户名</th>
            <th data-options="field:'find_sex',width:150,align:'center',halign:'center'">性别</th>
        </tr>
        </thead>
        <tbody>
        <tr id="findData1" datagrid-row-index="3" class="datagrid-row">
            <td field="find_id"><div style="text-align: center; height: auto;" ><div id="return_id"></div></div></td>
            <td field="find_username"><div style="text-align: center; height: auto;" ><div id="return_username"></div></div></td>
            <td field="find_sex"><div style="text-align: center; height: auto;" ><div id="return_sex"></div></div></td>
        </tr>
        </tbody>
    </table>
    <table id="addData1" class="easyui-datagrid">
        <thead>
        <tr>
            <th data-options="field:'find_date',width:150,align:'center',halign:'center'">日期</th>
            <th data-options="field:'find_address',width:150,align:'center',halign:'center'">地址</th>
            <th data-options="field:'find_password',width:150,align:'center',halign:'center'">密码</th>
        </tr>
        </thead>
        <tbody>
        <tr id="findData2" datagrid-row-index="3" class="datagrid-row">
            <td field="find_date"><div style="text-align: center; height: auto;" ><div id="return_date"></div></div></td>
            <td field="find_address"><div style="text-align: center; height: auto;" ><div id="return_address"></div></div></td>
            <td field="find_password"><div style="text-align: center; height: auto;" ><div id="return_password"></div></div></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>