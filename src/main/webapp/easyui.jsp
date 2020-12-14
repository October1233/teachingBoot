
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

<body>
<h2>Row Editing in DataGrid</h2>
<p>Click the row to start editing.</p>
<div style="margin:20px 0;"></div>

<table id="dg" class="easyui-datagrid" title="Row Editing in DataGrid" style="width:700px;height:auto"
       data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: 'datagrid_data1.json',
				method: 'get',
				onClickRow: onClickRow
			">
    <thead>
    <tr>
        <th data-options="field:'itemid',width:80">Item ID</th>
        <th data-options="field:'productid',width:100,
						formatter:function(value,row){
							return row.productname;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'car',
								method:'get',
								url:'/ajax/ccccccc',
								required:true
							}
						}">Product</th>
        <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
        <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
        <th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
        <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
    </tr>
    </thead>
</table>

<div id="tb" style="height:auto">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
</div>

<script type="text/javascript">
    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
            var productname = $(ed.target).combobox('getText');
            $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickRow(index){
        if (editIndex != index){
            if (endEditing()){
                $('#dg').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#dg').datagrid('selectRow', editIndex);
            }
        }
    }
    function append(){
        if (endEditing()){
            $('#dg').datagrid('appendRow',{status:'P'});
            editIndex = $('#dg').datagrid('getRows').length-1;
            $('#dg').datagrid('selectRow', editIndex)
                .datagrid('beginEdit', editIndex);
        }
    }
    function removeit(){
        if (editIndex == undefined){return}
        $('#dg').datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
        editIndex = undefined;
    }
    function accept(){
        if (endEditing()){
            $('#dg').datagrid('acceptChanges');
        }
    }
    function reject(){
        $('#dg').datagrid('rejectChanges');
        editIndex = undefined;
    }
    function getChanges(){
        var rows = $('#dg').datagrid('getChanges');
        alert(rows.length+' rows are changed!');
    }
</script>
</body>
</html>