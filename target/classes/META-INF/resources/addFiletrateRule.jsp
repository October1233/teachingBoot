
<%@ page language='java' import='java.util.*' pageEncoding='utf-8'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="zh_cn">
    <title>添加数据筛选规则</title>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="http://mgmt-order.msyidai.com/static/easyui/1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-order.msyidai.com/static/easyui/1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://mgmt-order.msyidai.com/static/easyui/1.5.2/themes/color.css">
    <script src="http://mgmt-order.msyidai.com/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://mgmt-order.msyidai.com/static/easyui/1.5.2/jquery.easyui.min.js"></script>
    <script src="http://mgmt-order.msyidai.com/static/easyui/1.5.2/easyui-lang-zh_CN.js"></script>
    <script src="http://mgmt-order.msyidai.com/static/easyUIValidate.js?v=1.0"></script>
    <script src="/static/common.js?v=1.1"></script>
    <script src="/static/easyUIValidate.js?v=1.0"></script>
    <script src="/static/layer/layer.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #formFooter{
                bottom:20px;
                left:50%;
                margin-left:100px;
            }
            .loadMask{
                top:0px;
                left:0px;
                position: fixed;
                width:100%;
                height:100%;
                background-color: rgba(0,0,0,0.5);
                display:none;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
                $.post(
                    '${ctx}/orderTm/getLoanInfo',
                    function(result) {
                        if(result.retRes=='success'){
                            $('#fm-tmescDlg').form('load',result.retMsg);
                        }else{
                            $.messager.alert("提示信息", "获取回显信息失败,请重试!", "info");
                        }
                    },
                    'json'
                );

            });




            //证件类型转换
            function formatCertType(value,row,index){
                if(value == 0){
                    return "身份证";
                }
            };



            // $('input[name=loans]').change(function(){
            //     $('#Account').val($('input[name=loans]:checked').
            //     map(function(){return this.value}).get().join(','))
            // });

            /* 信息保存*/
            function saveTmescExt() {
                var index = $("#tmescCarInfo_list").datagrid("getRows").length;
                if(index<=0){
                    $.messager.alert("提示信息","未添加任何规则，无法保存!", "info");
                    return;
                }
                var ruleName = $("#ruleName").textbox('getValue');
                if(ruleName==''){
                    $.messager.alert("提示信息","规则名称不能为空", "info");
                    return;
                };
                var ruleCode = $("#ruleCode").textbox('getValue');
                if(ruleCode==''){
                    $.messager.alert("提示信息","规则码不能为空", "info");
                    return;
                };
                var loanProduct = $("#loanProduct").combobox('getValue');
                if(loanProduct==''){
                    $.messager.alert("提示信息","数据类型不能为空", "info");
                    return;
                }
                var dataType = $("#dataType").combobox('getValue');
                if(dataType==''){
                    $.messager.alert("提示信息","数据类型不能为空", "info");
                    return;
                }
                var strat= $("#startTime").datebox('getValue');
                var end= $("#endTime").datebox('getValue');
                if(strat!='' && end!= ''){
                    if(strat > end) {
                        $.messager.alert("提示信息","开始时间必须小于结束时间", "info");
                        return;
                    }
                }else if ((strat==null||strat=="")&&(end!=""&&end!=null)){
                    $.messager.alert("提示信息","必须输入开始时间", "info");
                    return;
                }else if ((strat!=null&&strat!="")&&(end==""||end==null)){
                    $.messager.alert("提示信息","必须输入结束时间", "info");
                    return;
                }
                var ruleState = $("#ruleState").combobox('getValue');
                if(ruleState==''){
                    $.messager.alert("提示信息", "规则状态不能为空", "info");
                    return;
                }
                var reMark = $("#reMark").textbox('getValue');
                // //提交上方
                // $.ajax({
                //     url : '/filtrateRule/saveRuleList',
                //     type : "get",
                //     data:
                //         // $("#fm-tmescDlg").serialize(),
                //         {"ruleName":ruleName,"ruleCode":ruleCode,
                //           "dataType":dataType,"loanProduct":loanProduct,
                //             "startTime":strat,"endTime":end,
                //             "ruleState":ruleState,"reMark":reMark},
                //     async : false,
                //     success : function(data) {
                //         if(data.ec == "M00001"){
                //             $.messager.alert("提示信息",data.em, "info");
                //             flag = true;
                //         }
                //     }
                // });
                //提交list
                var flag = false;//全局变量，以便下面做判断
                var rowsData = $('#tmescCarInfo_list').datagrid('getRows');
                var tmescCarInfoListJson = encodeURI(JSON.stringify(rowsData));
                $.ajax({
                    url : '/filtrateRule/saveRuleList',
                    type : "get",
                    data:{tmescCarInfoListJson:tmescCarInfoListJson},
                    async : false,
                    success : function(data) {
                        if(data.ec == "M00001"){
                            $.messager.alert("提示信息",data.em, "info");
                            flag = true;
                        }
                    }
                });



                function append(){
                    if (endEditing()){
                        $('#dg').datagrid('appendRow',{status:'P'});
                        editIndex = $('#dg').datagrid('getRows').length-1;
                        $('#dg').datagrid('selectRow', editIndex)
                            .datagrid('beginEdit', editIndex);
                    }
                }

                if(flag){
                    return false;
                }

                //打开遮罩
                $.messager.progress({
                    text: '正在保存，请稍等。。'
                });

                $('#fm-tmescDlg').form('submit', {
                    url: '/filtrateRule/saveRuleInfo',
                    onSubmit: function(param) {
                        if(!$(this).form('validate')){
                            $.messager.progress('close');
                            return false;
                        }
                        var rowsData = $('#tmescCarInfo_list').datagrid('getRows');
                        param.tmescCarInfoListJson = encodeURI(JSON.stringify(rowsData));
                        return true;
                    },
                    success: function(result) {
                        $.messager.progress('close');
                        var res = eval('('+result+')');
                        if(res.retRes == 'success') {
                            $.messager.alert("提示", "保存成功","info");
                        } else {
                            $.messager.alert("提示信息", res.retMsg, "info");
                        }
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        alert( '服务器正忙');
                    }
                });
            }

            var editFlag = false;
            //新增一行
            function doAddTmrscCarInfo(){
                $('#tmescCarInfo_list').datagrid("acceptChanges");
                $("#tmescCarInfo_list").datagrid("appendRow", {
                    ruleDetailName: "",
                    symbol: "",
                    ruleValue: "",
                    explain:""
                });
                var index = $("#tmescCarInfo_list").datagrid("getRows").length-1;
                editFlag = true;
                $('#tmescCarInfo_list').datagrid("refreshRow",index);
                $("#tmescCarInfo_list").datagrid("beginEdit",index);
            }

            // 删除一行
            function doDelTmrscInfo() {
                var row = $('#tmescCarInfo_list').datagrid('getSelected');
                if(!row){
                    $.messager.alert("提示信息", "选择需要删除的数据", "info");
                    return;
                }
                var index = $('#tmescCarInfo_list').datagrid('getRowIndex',row);
                $('#tmescCarInfo_list').datagrid('deleteRow',index);
                caculFee();
            }

            function formatOpt(value,row,index){
                var a = "<a style='text-decoration:none' href='javascript:beginEdit()'>编辑</a>";
                if(editFlag){
                    a ="<a style='text-decoration:none' href='javascript:cancelEdit()'>保存</a>";
                }
                a +="&nbsp;&nbsp;<a style='text-decoration:none' href='javascript:doDelTmrscInfo()'>删除</a>";
                return a;
            }


            function beginEdit(){
                var row = $('#tmescCarInfo_list').datagrid('getSelected');
                var index = $('#tmescCarInfo_list').datagrid('getRowIndex',row);
                editFlag = true;
                $('#tmescCarInfo_list').datagrid("refreshRow",index);
                $("#tmescCarInfo_list").datagrid("beginEdit",index);
            }

            //取消编辑
            function cancelEdit(){
                var row = $('#tmescCarInfo_list').datagrid('getSelected');
                var index = $('#tmescCarInfo_list').datagrid('getRowIndex',row);
                var ok  = $('#tmescCarInfo_list').datagrid('validateRow',index);
                if(!ok){
                    return;
                }
                editFlag = false;
                var isFlag = false;
                $('#tmescCarInfo_list').datagrid('endEdit',index);
                var rowsData = $('#tmescCarInfo_list').datagrid('getRows');

                $('#tmescCarInfo_list').datagrid("refreshRow",index);
                $.each(rowsData, function (i) {
                    if(i!=index){
                        if(rowsData[i].ruleDetailName==rowsData[index].ruleDetailName){
                            //弹框  操作失败：车架号重复
                            $.messager.alert("提示信息", "操作失败：规则重复", "info");
                            isFlag = true;
                        }
                    }
                });
                $('#tmescCarInfo_list').datagrid("refreshRow",index);
                if(isFlag){
                    $('#tmescCarInfo_list').datagrid('rejectChanges');
                    editIndex = undefined;
                    $('#tmescCarInfo_list').datagrid("refreshRow",index);
                    return false;
                }



            }







        </script>
        <style type="text/css">
            .textbox-label {
                display: inline-block;
                width: 100px;
                height: 22px;
                line-height: 22px;
                vertical-align: middle;
                white-space: nowrap;
                padding-right: 5px;
                overflow: hidden;
                margin: 0px;
            }
        </style>
        <title>添加数据筛选规则</title>
    </head>
<body>
<div id="fm-tmescDlg_div" style="margin-bottom: 15px;margin-top: 35px">
    <form id="fm-tmescDlg" method="get" style="font-size: 12px; margin-left: 15%;width: 900px">
        <input name="storeCode" type="hidden" id="storeCode_hidden" >
        <h2>添加数据筛选规则</h2>
        <br/>
        <input name="ruleName" class="easyui-textbox" style="height: 33px"
               data-options="label:'规则名称:',required:true,width:250" id="ruleName">

        <input name="ruleCode" id="ruleCode" style="height: 33px" class="easyui-textbox"
                data-options="label:'规则编码:',required:true,width:300" >
        <select name="dataType" id="dataType" style="height: 33px" class="easyui-combobox"
                data-options="label:'数据类型:',required:true,width:300" >
            <option value="1">注册数据</option>
            <option value="2">授信数据</option>
            <option value="3">放款数据</option>
            <option value="4">还款数据</option>
        </select>
        <br/><br/>
        <select id="loanProduct" name="loanProduct" class="easyui-combobox"
                style="width:230px;height:33px"
                data-options="multiple:true">
            <option value="MSZL">助粒贷</option>
            <option value="YGD">员工贷</option>
        </select>
<%--        <div style="display: inline-block;width: 250px">业务类型：--%>
<%--        <input value="MSZL" name="loans" type="checkbox"/><span>助粒贷</span>--%>
<%--        <input value="YZD" name="loans" type="checkbox"/><span>业主贷</span></div>--%>

        <label for="startTime">放款日期：</label>
        <input name="startTime" id="startTime" class="easyui-datebox" data-options="required:true,width:100" style="height: 33px">
        <label for="endTime">至：</label>
        <input name="endTime" id="endTime" class="easyui-datebox" data-options="required:true,width:100" style="height: 33px">

        <select name="ruleState" id="ruleState" style="height: 33px" class="easyui-combobox"
                data-options="label:'规则状态:',required:true,width:300" >
            <option value="0">待生效</option>
            <option value="1">生效</option>
            <option value="2">失效</option>
        </select>
        <br/><br/>
        <div style="display: inline-block;width: 100px"></div>
        <input name="raMark" class="easyui-textbox" style="height: 33px" id="reMark"
               data-options="label:'备注:',width:750">
        <br/><br/>
        <div style="height: 400px;width: 950px">
            <div id="toolbar" style="height:auto;padding-top: 5px;padding-bottom: 5px;display: none;">
                <a href="javascript:void(0)" class="easyui-linkbutton" style="float: right;margin-right: 10px;" data-options="iconCls:'icon-add',plain:false" onclick="doAddTmrscCarInfo();">新增</a>
                <div style="clear: both;"></div>
            </div>
            <table id="tmescCarInfo_list" class="easyui-datagrid" title="规则信息" style="height: auto"
                   data-options="
					url: '',
					rownumbers:true,singleSelect:true,method:'get',toolbar:'#toolbar',
					pagination:true,checkOnSelect:true,selectOnCheck:true,striped:true,fit:true,showFooter: true">
                <thead>
                <tr>
                    <th data-options="field:'ruleDetailName',width:308,align:'center',halign:'center',
							   editor:{type:'combobox',options:{required: true,panelHeight:'auto',panelMaxHeight:200,multiple:false,
							   editable:false,width:170,valueField:'code',textField:'name',
							    url: '/filtrateRule/rule?type=ruleDetailName',
							    onChange:function(n,o){
										if(n){
										    var rowIndex = $(this).parents('.datagrid-row').attr('datagrid-row-index');
											var name = $('#tmescCarInfo_list').datagrid('getEditor',{
												index:rowIndex,
												field:'symbol',
											});
											if(name){
												var url = '/filtrateRule/ruleDetail?type=symbol&parentCode='+n;
												$(name.target).combobox('clear');
												$(name.target).combobox('reload',url);
											}
										}
									}
							    }}">条件名称</th>
                    <th data-options="field:'symbol',width:108,align:'center',halign:'center',
							   editor:{type:'combobox',options:{required: true,panelHeight:'auto',panelMaxHeight:200,multiple:false,
							   editable:false,width:170,valueField:'code',textField:'name',url:'',
							    onChange:function(n,o){
							           if(n){
										    var rowIndex = $(this).parents('.datagrid-row').attr('datagrid-row-index');
											var name = $('#tmescCarInfo_list').datagrid('getEditor',{
												index:rowIndex,
												field:'address',
											});
											if(name){
												var url = '/filtrateRule/ruleDetail?type=symbol&parentCode='+n;
												$(name.target).combobox('clear');
												$(name.target).combobox('reload',url);
											}
										}
									}}}">操作符</th>
                    <th data-options="field:'ruleValue',width:135,align:'center',halign:'center',editor:{type:'textbox',options:{required: true}}">值</th>
                    <th data-options="field:'explain',width:235,align:'center',halign:'center',editor:{type:'textbox',options:{required: true}}">说明</th>
                    <th data-options="field:'operation',width:135,align:'center',halign:'center',formatter:formatOpt">操作</th>
                </thead>
            </table>
        </div>

        <br/>
        <div id="formFooter" style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" style="width:80px" class="easyui-linkbutton" onclick="javascript:history.back(-1);">返回</a>
            <a href="javascript:void(0)" style="width:80px" class="easyui-linkbutton" onclick="saveTmescExt();">保存</a>
        </div>
    </form>
</div>

</body>
</html>