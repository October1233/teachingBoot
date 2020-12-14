<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
<title>用户登录</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />

	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			background-color: #EAEAEA;
		}
		div{
			width: 160px;
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
            $("#logbut").click(function(){
                var username = $("#log_name").val();
                var password = $("#log_pwd").val();
                $.ajax({
                    url:"logup",
                    datatype:"json",
                    type:"post",
                    data:{"u_name":username,"u_pwd":password},

                    success:function(data){
                    if(data.return_id==1){
                    alert("登录成功");
                    window.location.href = "index.jsp";
                        }
                    else{
                        $("#error").show();
                        }
                    },
                    error:function(data)
                    {
                    alert("连接错误");
                    }
                });
            });
        });
  </script>
<%--	<script>
		$(function(){
			$("#register").click(function(){
				$.ajax({
					url:"uregister",
					success:function(data){
						alert("成功");
					},
					error:function(data){
						alert("返回值错误");
					}
				});
			});
		});
	</script>--%>
</head>

<body>
<h1 align="center">用户登录</h1>

	<div class="center-in-center">
		<div>
			<input type="text" name="username" id="log_name">
		</div>
		<div>
			<input type="password" name="password" id = "log_pwd">
		</div>
		<div>
        	<div id="error" style = "display:none">用户名或密码错误</div>
        </div>
		<div>
			<a href="register.jsp">立即注册</a>
			<input type="button" id="logbut" value="登录">
		</div>
	</div>
</body>
</html>