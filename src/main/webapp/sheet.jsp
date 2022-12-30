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
//导入文件
    function importExp() {
        var formData = new FormData();
        var name = $("#upfile").val();
        var row = $("#row").val();
        formData.append("file",$("#upfile")[0].files[0]);
        formData.append("name",name);
        formData.append("row",row);
        $.ajax({
            url : '/xslxSheet/xslxSheet',
            type : 'POST',
            async : false,
            data : formData,
            // 告诉jQuery不要去处理发送的数据
            processData : false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType : false,
            beforeSend:function(){
                console.log("正在进行，请稍候");
            },
            success : function(responseStr) {
                if(responseStr=="01"){
                    alert("导入成功");
                }else{
                    alert("导入失败");
                }
            }
        });
    }

</script>
</head>
<body>
<h1>数据交换</h1>
<li>
    <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传：</span>
    <span class="input">
   <input type="file" id="upfile" name="upfile" placeholder="" title="选择文件"/>
        <br/>

</span>
</li>
<li>
    <span>哪&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;列：</span>
    <input type="text" id="row" name="row" />
    <br/>
</li>
<li>
    <span>冲&nbsp;&nbsp;冲&nbsp;冲：</span>
    <button onclick="importExp();">导入</button>
    <span>格式：.xls</span>
</li>

</body>
</html>
