<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>全部数据</title>
      <meta charset="UTF-8">
      <link rel="stylesheet"type="text/css" href="easyui/themes/default/easyui.css">
      <link rel="stylesheet"type="text/css" href="easyui/themes/icon.css">
      <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
      <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
      <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
      <script src="/static/common.js"></script>
<script language="JavaScript" type="text/JavaScript">
           $(document).ready(function(){
                $("#testbut").click(function(){

                        $.ajax({

                            url: "test/ajaxmap",

                            dataType: "json",

                            type: "post",

                            success: function (data) {
                                alert(data.msg);
                            },
                            error: function (data) {
                                alert("失败");
                            }
                        });
           });
      });


</script>
    <script>

      $(document).ready(function () {
          $("#ccccc").click(function () {
            alert("成功");

          });

      });

      </script>
    <script>
      function c1() {
        window.location.href="test/c1"
      }
    </script>
      <script>
          function repaymentProject() {
              $("#msgtable").window('open');
          }
      </script>
      <script>
          $(document).ready(function () {
              $("#render").click(function () {
                  var renderac =$("#heider").val();
                  $.ajax({
                      url:"test/render",
                      data:{"rendercc":renderac},

                      success:function (data) {
                          alert(data.rendermark);
                      },
                      error:function () {
                          alert("JSON实验错误")

                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#testarray").click(function () {
                  $.ajax({
                      url:"test//testArray",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#testMapToMothed").click(function () {
                  $.ajax({
                      url:"test//testMapToMothed",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#testMapToListToMothed").click(function () {
                  $.ajax({
                      url:"test//testMapToListToMothed",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#arrayListAndJsonArray").click(function () {
                  $.ajax({
                      url:"test//arrayListAndJsonArray",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#showAllData").click(function () {
                  $.ajax({
                      url:"datagrid/showAllData",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#testdata").click(function () {
                  $.ajax({
                      url:"datagrid/testdata",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });
      </script>
      <script>
          $(document).ready(function () {
              $("#allJson").click(function () {
                  $.ajax({
                      url:"test/allJson",
                      success:function (data) {
                          alert("Array成功");
                      },
                      error:function () {
                          alert("Array错误")
                      }
                  })

              });

          });


        function check() {
            var IDcard = $("#IDnum") .val();
            $.ajax({
                url: "test/zhengze",
                data: {"IDCard": IDcard},
                success: function (data) {
                    alert(data.cdkay);
                },
                error: function () {
                    alert("Array错误")
                }
            })
        }
          function GaiBian(osel){
              if(osel.options[osel.selectedIndex].text==1)
              {alert(11);}
          }
          function check1() {
              var IDcard = $("#IDnum") .val();
              $.ajax({
                  url: "test/zhengze",
                  success: function (data) {
                      alert(data.cdkay);
                  },
                  error: function () {
                      alert("Array错误")
                  }
              })
          }

          function duoxiancheng() {
              $.ajax({
                  url: "test/optional",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("多线程错误")
                  }
              })
          }

          function common() {
              $.ajax({
                  url: "transactional/transactionalA",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

          function transactionalA(){
              $.ajax({
                  url: "transactional/transactionalA",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function transactionalB(){
              $.ajax({
                  url: "transactional/transactionalB",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

          function transactionalC(){
              $.ajax({
                  url: "transactional/transactionalC",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function transactionalD(){
              $.ajax({
                  url: "transactional/transactionalD",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

          function required(){
              $.ajax({
                  url: "transactional/required",
                  success: function (data) {
                      alert("好的required");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function supports(){
              $.ajax({
                  url: "transactional/supports",
                  success: function (data) {
                      alert("好的supports");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function mandatory(){
              $.ajax({
                  url: "transactional/mandatory",
                  success: function (data) {
                      alert("好的mandatory");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function requiresnew(){
              $.ajax({
                  url: "transactional/requiresnew",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function supported(){
              $.ajax({
                  url: "transactional/supported",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function never(){
              $.ajax({
                  url: "transactional/never",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function nested(){
              $.ajax({
                  url: "transactional/nested",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function henhao(){
              $.ajax({
                  url: "test/symbleOptional",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function buhao(){
              $.ajax({
                  url: "test/symbleOptional2",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function READUNCOMMITTED(){
              $.ajax({
                  url: "transactional/UNCOMMITTED",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function READCOMMITED(){
              $.ajax({
                  url: "transactional/READ_COMMITTED",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function REPEATABLEREAD(){
              $.ajax({
                  url: "transactional/REPEATABLE_READ",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }
          function SERIALIZABLE(){
              $.ajax({
                  url: "transactional/symbleOptional2",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

          function thread() {
// 提交表单数据到后台处理
              $.ajax({
                  type: "post",
                  url: "test/thread",
                  beforeSend: function () {
                      // 禁用按钮防止重复提交
                      $("#submit").attr({ disabled: "disabled" });
                  },
                  success: function (data) {
                      if (data.sta == "Success") {
                          //清空输入框
                          clearBox();
                      }
                  },
                  complete: function () {
                      $("#submit").removeAttr("disabled");
                  },
                  error: function (data) {
                      console.info("error: " + data.responseText);
                  }
              });
          }

          function jedis(){
              $.ajax({
                  url: "transactional/jedis",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

          function jedisOut(){
              $.ajax({
                  url: "transactional/jedisOut",
                  success: function (data) {
                      alert("好的");
                  },
                  error: function () {
                      alert("普通的错误")
                  }
              })
          }

      </script>
  </head>
  <br>
<h1>测试专用界面</h1>
<input type="button" id="testbut" value="前端直接显示后端ajax信息按键">
    <table align='center' border='1' cellspacing='0'>
      <tr>
        <td>id</td>
        <td>name</td>
      </tr>
      <c:forEach items="${students}" var="s" varStatus="st">
        <tr>
          <td>${s.id}</td>
          <td>${s.name}</td>
        </tr>
      </c:forEach>
    </table>

<form action="test/c1" method="post">
  <table border = "1">
    <tr>
      <td>勿点<input type = "submit" value = "危险的数据按键"/></td>
    </tr>
      <tr>
          <td><input type = "button" value = "eazyUI实验按钮" onclick="repaymentProject()"/></td>
      </tr>
  </table>
</form>
<form action="test/str" method="post">
  <table border = "1">
    <tr>
      <td>根据密码搜索用户名修改性别<input type = "submit" value = "提交按键"/></td>
    </tr>
  </table>
</form>
<input id="heider" type="text"/>
<button id="symble" onclick="common()">普通</button>
<button id="render">JSONObject转换按钮</button>
<button id="testarray">List实验按钮</button>
<button id="testMapToMothed">Map中取对象属性实验按钮</button>
<button id="testMapToListToMothed">Map中取List中的对象的值的实验按钮</button>
<button id="arrayListAndJsonArray">JSONArray与List对比按钮</button>
<button id="showAllData">DataGrid按钮</button>
<button id="testdata">测试时间合成按钮</button>
<button id="allJson">提取List对象生日按钮</button>
<input id="IDnum" type="text">
<button id="zhengze" onclick="check()">身份证正则按键</button>
<button id="pankong" onclick="check1()">判空</button>
<button id="duoxiancheng" onclick="duoxiancheng()">多线程</button>
  <button id="ssdasd" onclick="henhao()">很好</button>
  <button id="duoxiacasdasdncheng" onclick="buhao()">不好</button>
  <button id="easyThread" onclick="thread()">延时</button>




  <select name='selectSS' onChange="GaiBian(this)">
    <option value='1'>1 </option>
    <option value='2'>2 </option>
    <option value='3'>3 </option>
</select>
<a href="">添加列新页</a>




<div id="msgtable" class="easyui-window"
     data-options="footer:'addDataWin_footer',modal:true,closed:true,maximizable:false,
		height:'400px',width:'600px',resizable:false,minimizable:false,collapsible:false,
		title:'还款计划'">
    <table id="addData" class="easyui-datagrid">
        <thead>
        <tr>
            <th data-options="field:'date',width:150,align:'center',halign:'center'">还款日期</th>
            <th data-options="field:'amt',width:150,align:'center',halign:'center'">期次</th>
            <th data-options="field:'lx',width:150,align:'center',halign:'center'">还款金额</th>
            <th data-options="field:'sxf',width:150,align:'center',halign:'center'">状态</th>
        </tr>
        </thead>
        <tbody>
        <tr id="datagrid-row-r1-2-0" datagrid-row-index="0" class="datagrid-row">
            <td field="date">
                <div style="text-align: center; height: auto;" ><div id="repaymentdate">2019-01-01</div></div>
            </td>
            <td field="amt">
                <div style="text-align: center; height: auto;" ><div id="repaymentnum">1</div></div>
            </td>
            <td field="lx">
                <div style="text-align: center; height: auto;" ><div id="repaymentsum">1222202.00</div></div>
            </td>
            <td field="sxf">
                <div style="text-align: center; height: auto;" ><div id="repaymentstatus">未还款</div></div>
            </td>
        </tr>
        </tbody>
        <br/>
    </table>
    <div>收款账户：武汉民商惠小额贷款有限责任公司<br/></div>
    <div>收款银行：民生银行<br/></div>
    <div>开户行：光谷xxx支行<br/></div>
    <div>收款账号：1234567890<br/></div>
    <div>打款备注：BFDZ0088823<br/></div>
</div>
<button id="transactionalA" onclick="transactionalA()">普通回滚</button>

<button id="transactionalB" onclick="transactionalB()">方法嵌套回滚</button>

<button id="duoxianchengC" onclick="transactionalC()">嵌套不同方法回滚</button>

<button id="transactionalD" onclick="transactionalD()">手动回滚</button>
  </br>
  <button id="required" onclick="required()">propagation-required</button>

  <button id="supports" onclick="supports()">.propagation-supports</button>

  <button id="mandatory" onclick="mandatory()">propagation-mandatory</button>

  <button id="requires_new" onclick="requiresnew()">propagation-requires_new</button>

  <button id="supported" onclick="supported()">propagation-not-supported</button>

  <button id="never" onclick="never()">propagation-never</button>
  </br>
  <button id="READUNCOMMITTED" onclick="READUNCOMMITTED()">READ UNCOMMITTED</button>

  <button id="READCOMMITED" onclick="READCOMMITED()">READ COMMITED</button>

  <button id="REPEATABLEREAD" onclick="REPEATABLEREAD()">REPEATABLEREAD</button>

  <button id="SERIALIZABLE" onclick="SERIALIZABLE()">SERIALIZABLE</button>

  <button id="jedis" onclick="jedis()">jedis</button>

  <button id="jedisOut" onclick="jedisOut()">jedisOut</button>



  </body>
</html>
