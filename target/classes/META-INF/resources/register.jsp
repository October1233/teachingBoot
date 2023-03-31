<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <title>立即注册</title>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
            background-color: #EAEAEA;
        }
        div{
            width: 200px;
            height: 25px;
            background-color: #EAEAEA;
        }
        .center-in-center{
            position: absolute;
            top: 40%;
            left: 45%;
        }
    </style>
    <script language="JavaScript" type="text/JavaScript">
        $(document).ready(function(){
            $("#registbut").click(function(){
                var uname = $("#regist_name").val();
                var upwd = $("#regist_pwd").val();
                $.ajax({
                    url:"registerverify",
                    dateType:"json",
                    data:{"fountname":uname,
                          "fountpwd":upwd},

                    success:function(data){
                        if (data.unameStatus==1){
                           $("#unameMark").html("该用户名已存在");
                        }else if (data.unameStatus==2){
                            $("#unameMark").html("用户名不能为空");
                        }else if (data.unameStatus==0){
                            $("#unameMark").html("用户名可用");
                        }else if (data.unameStatus==3){
                            $("#unameMark").html("用户名不能含有非法字符");
                        }
                        if (data.passwdStatus==2){
                            $("#upwdMark").html("密码不能为空");
                        }else if (data.passwdStatus==3){
                            $("#upwdMark").html("密码含有非法字符");
                        }
                        if (data.registerStatus==0){
                            alert("注册成功");
                            window.location.href="welcome";
                        }else if (data.registerStatus==1){
                            alert("注册失败");
                        }
                    },
                    error:function(data){
                        alert("返回值错误");
                    }
                });
            });
        });
  </script>
</head>
<body>
<table align="center" class="center-in-center">
<tr>
    <td>
        <input type="text" id="regist_name">
    </td>
    <td>
        <div id="unameMark"></div>
    </td>
</tr>
<tr>
    <td>
        <input type="password" id = "regist_pwd">
    </td>
    <td>
        <div id="upwdMark"></div>
    </td>
</tr>
<tr>
    <td>
        <input type="button" id="registbut" value="注册">
    </td>
</tr>
</table>
</body>
</html>
