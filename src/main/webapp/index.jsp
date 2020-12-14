<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script language="JavaScript" type="text/JavaScript">
    function ajaxinput(){
    var putuname = $("#u_name").val();
    var putusex = $("#u_sex").val();
    var putuaddr = $("#u_addr").val();
    var putupwd = $("#u_pwd").val();

    $.ajax({
        url:"ajax/insert",
        dataType:"json",
        type:"post",

        data:{"putuname":putuname,
              "putusex":putusex,
              "putupwd":putupwd,
              "putuaddr":putuaddr},

        success:function(data){
            if(data.return_pg == 1){
                $("#low_pwd").show();
            }
            else {alert(data.return);}
        },
        error:function(data){
        alert("连接错误");
        }
    });
};

   function finduser(){
   var inputid = $("#findbyid").val();
        $.ajax({
            url:"user/findbyid",
            dataType:"json",
            type:"post",

            data:{'findid':inputid},

            success:function(data)
            {
                if (data.msg == 1)
                {
                    alert("该用户不存在");
                    $("#showuname").hide();
                    $("#showusex").hide();
                    $("#showuday").hide();
                    $("#showuaddr").hide();
                }
                else {
                    $("#showuname").html(data.username).show();
                    $("#showusex").html(data.sex).show();
                    $("#showuday").html(data.birthday).show();
                    $("#showuaddr").html(data.address).show();
                }
            },
            error:function(data)
            {
                alert("连接异常")
             }
        });
    };

   $(document).ready(function(){
       $("#ajaxdelbut").click(function(){
       var deleteid = $("#ajaxdelid").val();
           $.ajax({
                url:"ajax/delete",
                dataType:"json",
                type:"post",

                data:{'deleteid':deleteid},

                success:function(data){
                    alert(data.userstate);
                },
                error:function(data){
                    alert("网络错误");
                }
           });
       });
   });
  $(document).ready(function(){
           $("#test1").click(function(){
           window.location.href="firstpage.jsp"
           });
           });

    $(document).ready(function(){
        $("#test2").click(function(){
            window.location.href="test2";
        });
    });
    $(document).ready(function(){
        $("#eazyui").click(function(){
            window.location.href="eazyui";
        });
    });


</script>
</head>
<body>
<h1>添加用户</h1>
 <form action="user/input" method="post">
 <table border = "1">
<tr>
		<td>用户名 <input type="text" name="username" /></td>

		<td>性别  <input type="text" name="sex" /></td>

		<td>地址  <input type="text" name="address" /></td>

		<td>密码  <input type="password" name="password" /></td>

        <td><input type="submit" value="添加"/></td>
 </tr>

</table>
	</form>
	<h1>ajax添加用户</h1>
	 <table border = "1">
    <tr>
    		<td>用户名 <input type="text" name="username" id="u_name"/></td>
        <div id="user_now" style = "display:none">用户名已存在</div>
    		<td>性别  <input type="text" name="sex" id="u_sex"/></td>

    		<td>地址  <input type="text" name="address" id="u_addr"/></td>

    		<td>密码  <input type="password" name="password" id="u_pwd"/></td>
        <div id="low_pwd" style = "display:none">10>密码需>6</div>
            <td><input type="button" onclick="ajaxinput()" value="提交数据"></td>
    </table>


	<h1>删除用户</h1>

	 <form action="user/delete" method="post">
     <table border = "1">

     <tr>
            <td>删除的id <input type = "text" name="dlid"/></td>

            <td><input type = "submit" value = "删除"/></td>

     </tr>

    </table>
    	</form>
        	<h1>ajax删除用户</h1>
             <table border = "1">
             <tr>
                    <td>删除的id <input type = "text" id="ajaxdelid"/></td>
                    <td><input type = "button" value = "ajax删除" id="ajaxdelbut"/></td>
             </tr>
            </table>
    	    <h1>查询用户</h1>
    <table border = "1">
    <tr>
        <td>请输入要查询的id<input type = "text" name = "id" id="findbyid">
    </td>
        <td><input type = "button" id = "selectone" value = "单一查询" onclick = "finduser()"/>
    </td>
         <td><input type = "button" id = "selectall" value = "查询所有" onclick = "finduser()"/>
    </td>
    </tr>
    </table>
    <table border = "1" cellpadding="0" cellspacing="0" width="400px">
    		<tr>
    			<td>性名</td>
    			<td>性别</td>
    			<td>生日</td>
    			<td>地址</td>
    		</tr>
    		<tr>
    			<td><div id = "showuname"></div></td>
    			<td><div id = "showusex"></div></td>
    			<td><div id = "showuday"></div></td>
    			<td><div id = "showuaddr"></div></td>
    		</tr>
    	</table>
        <input type="button" id="test1" value="测试界面1">
        <input type="button" id="test2" value="eazyui测试界面1">
        <input type="button" id="eazyui" value="新eazyui测试界面">
</body>
</html>
