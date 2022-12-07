<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>数据新增</title>
    <meta charset="UTF-8">
    <link rel="stylesheet"type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet"type="text/css" href="easyui/themes/icon.css">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="/static/common.js"></script>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script language="JavaScript" type="text/JavaScript">
    /**
     * 导入
     */
     function pushExcel(){
        var file = document.getElementById("person_list").files[0];
        var r = new FileReader();
        r.readAsDataURL(file);
        if(null != file){
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (e) {
                vm.importInfo.personExcel = e.target.result;
            }
        }
    }

    function uploadExcel(){
        //.......
        //这里主要做一些数据的判断，或者其他参数的一个绑定。
        if( "" === vm.importInfo.personExcel || null == vm.importInfo.personExcel){
            layer.msg("请上传表格！")
            return;
        }
        $.ajax({
            type: "POST",
            url: baseURL + "basic/person/bulkimport",
            contentType: "application/json",
            data: JSON.stringify(vm.importInfo),
            success: function (r) {
                if (r.code === 0) {
                    if(null != r.data && 0 !== r.data.length){
                        //下载未通过的人员Excel
                        vm.downloadExcel(r.data);
                    }else{
                        layer.msg("所有人员已全部导入成功！")
                    }
                    vm.showList = true;
                    vm.showImport = false;
                    location.reload();
                } else {
                    alert(r.message);
                }
            }
        })
    }


</script>
</head>
<body>
<h1>数据交换</h1>
<div class="form-group" id="excel-file" style="margin-top: 10px">
    <div class="col-sm-2 control-label" >上传表格(仅支持excel)</div>
    <div class="col-sm-10">
        <input id="person_list" type="file" class="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" onclick="pushExcel()"/>
    </div>
    <span style="color:red">*</span>
</div>

</body>
</html>
