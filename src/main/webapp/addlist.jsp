
<%@ page language='java' import='java.util.*' pageEncoding='utf-8'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="zh_cn">
    <title>腾铭惠商订单新增</title>
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

            //城市信息格式化
            function formatAreaName(value,row,index){
                if(value == null || value == ""){
                    return "";
                }

                var htmlObj = $.ajax({
                    url : "${ctx}/orderInfoArea/getAreaName",
                    type : "post",
                    async : false,
                    data : {code:value}
                });
                var text = htmlObj.responseText;
                return text;
            };


            /* 腾铭二手车订单信息保存*/
            function saveTmescExt() {
                var index = $("#tmescCarInfo_list").datagrid("getRows").length;
                if(index<=0){
                    $.messager.alert("提示信息","未添加任何车辆信息，无法保存!", "info");
                    return;
                }

                var cooprMerchan = $("#cooprMerchant_1").textbox('getValue');
                if(cooprMerchan==''){
                    $.messager.alert("提示信息","商户名称不能为空", "info");
                    return;
                };
                var cooprStore = $("#cooprStore_1").textbox('getValue');
                if(!cooprStore){
                    $.messager.alert("提示信息","门店名称不能为空", "info");
                    return;
                };

                var custName = $("#custName1").textbox('getValue');
                if(!custName){
                    $.messager.alert("提示信息","联系人不能为空", "info");
                    return;
                }

                var custTel= $("#custTel1").textbox('getValue');
                if(!custTel){
                    $.messager.alert("提示信息", "手机号码不能为空", "info");
                    return;
                }

                var custId = $("#custId1").textbox('getValue');
                if(!custId){
                    $.messager.alert("提示信息", "身份证号不能为空", "info");
                    return;
                }

                /* 	var orderAmt = $("#orderAmt1").numberbox('getValue');
                    if(!orderAmt){
                        $.messager.alert("提示信息", "申请总金额不能为空", "info");
                        return;
                    } */

                var loanTerm=$("#loanTerm1").textbox('getValue');
                if(!loanTerm){
                    $.messager.alert("提示信息", "借款期限不能为空", "info");
                    return;
                }

                var flag = false;//全局变量，以便下面做判断
                var rowsData = $('#tmescCarInfo_list').datagrid('getRows');

                var tmescCarInfoListJson = encodeURI(JSON.stringify(rowsData));
                $.ajax({
                    url : '${ctx}/orderTm/checkChassisNumIsExists',
                    type : "post",
                    data:{tmescCarInfoListJson:tmescCarInfoListJson},
                    async : false,
                    success : function(data) {
                        if(data.ec == "M00001"){
                            $.messager.alert("提示信息",data.em, "info");
                            flag = true;
                        }
                    }
                });

                if(flag){
                    return false;
                }

                //打开遮罩
                $.messager.progress({
                    text: '正在保存，请稍等。。'
                });

                $('#fm-tmescDlg').form('submit', {
                    url: '${ctx}/orderTm/addTmescExtInfo',
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
                            $.messager.alert("提示", "保存成功","info",function(){
                                window.location.href="${ctx}/orderTm/getList";
                            });
                        } else {
                            $.messager.alert("提示信息", res.retMsg, "info");
                        }
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        alert( '服务器正忙');
                    }
                });
            }
            function cccc() {
                alert(sssss)
            }
            $('#mySelect').change(function(){
                alert($(this).children('option:selected').val());

            })

            $(function () {
                $('#dataType').combobox({
                    onChange: function (newValue, oldValue) {
                        sssss=newValue;
                    }
                });
            });



            var editFlag = false;
            //新增一行
            function doAddTmrscCarInfo(){
                $("#tmescCarInfo_list").datagrid("appendRow", {
                    certificateType: "",
                    certificateNo: "",
                    applyUserName: "",
                    province: "",
                    city: "",
                    address: "",
                    entityName: "",
                    applyUserMobile: "",
                    applyAmt:"",
                    bankApprovalNo:"",
                    emergencyContact:"",
                    contactMobile:"",
                    chassisNum:"",
                    models:"",
                    assessedValue:"",
                    mileage:"",
                    carAddress:"",
                    deliveryTime:"",
                    policyNo:"",
                    carLicensePlate:"",
                    insurantName:"",
                    insurantIdNo:""
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
                        if(rowsData[i].chassisNum==rowsData[index].chassisNum){
                            //弹框  操作失败：车架号重复
                            $.messager.alert("提示信息", "操作失败：车架号重复", "info");
                            caculFee();
                            isFlag = true;
                        }
                    }
                });
                if(isFlag){
                    return false;
                }
                $('#tmescCarInfo_list').datagrid("refreshRow",index);
                caculFee();
            }

            var sssss;

            /** 计算申请总金额  */
            function caculFee(){
                var rowsData = $('#tmescCarInfo_list').datagrid('getRows');
                var totalApplyAmt = 0;
                $.each(rowsData, function (i) {
                    if(!rowsData[i].applyAmt){
                        totalApplyAmt = parseFloat(applyAmt) + 0;
                    }else{
                        totalApplyAmt = parseFloat(totalApplyAmt) + parseFloat(rowsData[i].applyAmt);
                    }
                });
                if(!totalApplyAmt){
                    totalApplyAmt = 0;
                }
                $('#orderAmt1').textbox('setValue',totalApplyAmt.toFixed(2));
            }
            var queryItemData = [{text : "注册天数", value : "registerDayNum"},
                {text : "注册渠道", value : "registerChannel"},
                {text : "平台首次授信", value : "creditFirstPlatform"},
                {text : "授信日期距离注册日期", value : "creditDateThroughRegisterDate"},
                {text : "授信渠道是否属于注册渠道", value : "creditChannelInRegisterChannel"},
                {text : "授信渠道", value : "creditChannel"},
                {text : "借款日期距离注册日期", value : "loanDateThroughRegisterDate"},
                {text : "借款渠道是否属于注册渠道", value : "loanChannelInRegisterChannel"},
                {text : "借款渠道", value : "loanChannel"}];

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
                        alert(myOptValue);
                        sssss="2222";

                        //1.清空原来的operType这个combobox中的选项
                        $("#operType").combobox("clear");

                        //2.动态添加"操作类型"的下拉列表框的option
                        if( myOptValue != null && (myOptValue == 'creditFirstPlatform' || myOptValue == 'creditChannelInRegisterChannel'
                            || myOptValue == 'loanChannelInRegisterChannel') ){
                            console.info("myOptValue = "+myOptValue);
                            $("#operType").combobox({
                                panelHeight:50,
                                data : [{text : "=", value : "eq"}]
                            });
                        }else if ( myOptValue != null && (myOptValue == 'registerChannel' || myOptValue == 'creditChannel'
                            || myOptValue == 'loanChannel')) {
                            $("#operType").combobox({
                                panelHeight:140,
                                data : [{text : "=", value : "eq"}]
                            });
                        }else if (myOptValue != null && (myOptValue == 'registerDayNum' || myOptValue == 'creditDateThroughRegisterDate'
                            || myOptValue == 'loanDateThroughRegisterDate')) {
                            $("#operType").combobox({
                                panelHeight:140,
                                data : [{text : "=", value : "eq"}]
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
            $('#tests').combobox({
                onChange : function(n,o) {
                    alert(n);
                    alert(o);
                    var myOptValue = $("#certificateType").combobox("getValue") ;
                    alert(myOptValue);
                    //1.清空原来的operType这个combobox中的选项
                    $("#certificateType").combobox("clear");
                    if( myOptValue != null && (myOptValue == 'creditFirstPlatform' || myOptValue == 'creditChannelInRegisterChannel'
                        || myOptValue == 'loanChannelInRegisterChannel') ){
                        console.info("myOptValue = "+myOptValue);
                        $("#certificateNum").combobox({
                            panelHeight:50,
                            data : [{text : "=1", value : "eq"}]
                        });
                    }else if ( myOptValue != null && (myOptValue == 'registerChannel' || myOptValue == 'creditChannel'
                        || myOptValue == 'loanChannel')) {
                        $("#certificateNum").combobox({
                            panelHeight:140,
                            data : [{text : "=1", value : "eq"}]
                        });
                    }else if (myOptValue != null && (myOptValue == 'registerDayNum' || myOptValue == 'creditDateThroughRegisterDate'
                        || myOptValue == 'loanDateThroughRegisterDate')) {
                        $("#certificateNum").combobox({
                            panelHeight:140,
                            data : [{text : "=", value : "eq"}]
                        });
                }}
    });




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
        <title>腾铭订单信息</title>
    </head>
<body>
<div id="fm-tmescDlg_div" style="margin-bottom: 15px;margin-top: 35px">
    <form id="fm-tmescDlg" method="post" style="font-size: 12px; margin-left: 15%;width: 900px">
        <input name="storeCode" type="hidden" id="storeCode_hidden" >
        <h2>贷款信息</h2>
        <br/>

        <input name="cooprMerchantName" class="easyui-textbox" style="height: 33px" readonly="readonly"
               data-options="label:'商户名称:',required:true,width:350" id="cooprMerchant_1">
        <div style="display: inline-block;width: 100px"></div>
        <select name="cooprStoreName" required="true"  id="cooprStore_1" class="easyui-textbox" readonly="readonly"
                data-options="label:'门店名称:',width:350" >
        </select>
        <br/><br/>
        <select name="dataType" id="dataType" style="height: 33px" class="easyui-combobox"
                data-options="required:true,width:130" onchange="change();" >
            <option value="">请选择</option>
            <option value="1">注册数据</option>
            <option value="2">授信成功</option>
            <option value="3">授信申请</option>
            <option value="4">放款成功</option>
            <option value="5">还款成功</option>
        </select>
        <input name="custName" class="easyui-textbox" style="height: 33px" id="custName1" readonly="readonly"
               data-options="label:'联系人:',required:true,width:350">
        <div style="display: inline-block;width: 100px"></div>
        <input name="custTel" class="easyui-textbox" style="height: 33px" id="custTel1" readonly="readonly"
               data-options="label:'手机号码:',required:true,width:350,validType:'mobile'">
        <br/><br/>

        <input name="custId" class="easyui-textbox" style="height: 33px" id="custId1" readonly="readonly"
               data-options="label:'身份证号:',required:true,width:350,validType:'idcard'">
        <div style="display: inline-block;width: 100px"></div>
        <br/><br/>

        <input name="orderAmt" class="easyui-numberbox" style="height: 33px" id="orderAmt1" readonly="readonly"
               data-options="label:'申请总金额:',width:350,required:true,precision:2,validType:'intOrFloat'">
        <div style="display: inline-block;width: 100px"></div>
        <input name="loanTerm" class="easyui-combobox" readonly="readonly" style="height: 33px" id="loanTerm1" data-options="label:'借款期限:',required:true,width:350">
        <br/><br/>
        <div style="height: 400px;width: 950px">
            <div id="toolbar" style="height:auto;padding-top: 5px;padding-bottom: 5px;display: none;">
                <a href="javascript:void(0)" class="easyui-linkbutton" style="float: right;margin-right: 10px;" data-options="iconCls:'icon-add',plain:false" onclick="doAddTmrscCarInfo();">新增</a>
                <div style="clear: both;"></div>
            </div>
            <table id="tmescCarInfo_list" class="easyui-datagrid" title="车辆信息" style="height: auto"
                   data-options="
					url: '',
					rownumbers:true,singleSelect:true,method:'get',toolbar:'#toolbar',
					pagination:true,checkOnSelect:true,selectOnCheck:true,striped:true,fit:true,showFooter: true">
                <thead>
                <tr>
                    <th data-options="field:'rule',width:108,align:'center',halign:'center',
							   editor:{type:'combobox',options:{required: true,panelHeight:'auto',panelMaxHeight:200,multiple:false,
							   editable:false,width:170,valueField:'code',textField:'name',
							    url: '/ajax/ccccccc?type=province',
							    onChange:function(n,o){
										if(n){
										    var rowIndex = $(this).parents('.datagrid-row').attr('datagrid-row-index');
											var name = $('#tmescCarInfo_list').datagrid('getEditor',{
												index:rowIndex,
												field:'city',
											});
											if(name){
												var url = '/ajax/bbbb?type=city&parentCode='+n;
												$(name.target).combobox('clear');
												$(name.target).combobox('reload',url);
											}
										}
									}
							    }},formatter:formatAreaName">户籍省份</th>
                    <th data-options="field:'city',width:108,align:'center',halign:'center',
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
												var url = '/ajax/ddddd=type=area&parentCode='+n;
												$(name.target).combobox('clear');
												$(name.target).combobox('reload',url);
											}
										}
									}}},formatter:formatAreaName">户籍城市</th>
                </thead>
            </table>
        </div>

        <br/>
        <div id="formFooter" style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" style="width:80px" class="easyui-linkbutton" onclick="javascript:history.back(-1);">返回</a>
            <a href="javascript:void(0)" style="width:80px" class="easyui-linkbutton" onclick="saveTmescExt();">保存</a>
            <a href="javascript:void(0)" style="width:80px" class="easyui-linkbutton" onclick="cccc();">sssss</a>
        </div>
    </form>
</div>

</body>
</html>