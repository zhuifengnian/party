<%--
  Created by IntelliJ IDEA.
  User: liyue
  Date: 2017/7/5
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试 | 聚会助手</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta content='text/html;charset=utf-8' http-equiv='content-type'>
</head>
<body class='contrast-red login contrast-background'>
<div class='middle-container'>
    <div class='middle-row'>
        <div class='middle-wrapper'>
            <div class='login-container-header'>
                <div class='container'>
                    <div class='row'>
                        <div class='col-sm-12'>
                            <div class='text-center'>
                                <img width="121" height="31" src="style/images/logo_new.svg"/>
                                <span class="text">聚会助手测试页面</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class='login-container'>
                <div class='container'>
                    <div class='row'>
                        <div class='col-sm-4 col-sm-offset-4'>
                            <h1 class='text-center title'>登录</h1>
                            <h3 style="color: red">${msg}</h3>
                            <form class='validate-form' method='post' id="myform" action="${pageContext.servletContext.contextPath}/login/test">
                                <div class='form-group'>
                                    <div class='controls with-icon-over-input'>
                                        <input placeholder="用户名" data-rule-required="true" class="form-control"
                                               name="name" id="name" type="text" autocomplete="off"/>
                                        <span id="accountError" class="help-block has-error"
                                              style="display: none;color: #B94A48">账号不存在</span>
                                        <i class='icon-user text-muted'></i>
                                    </div>
                                </div>
                                <div class='form-group'>
                                    <div class='controls with-icon-over-input'>
                                        <input placeholder="******" data-rule-required="true" class="form-control"
                                               name="password" id="password" type="password"/>
                                        <span id="passwordError" class="help-block has-error"
                                              style="display: none;color: #B94A48">密码不正确</span>
                                        <i class='icon-lock text-muted'></i>
                                    </div>
                                </div>
                                <input type="submit" value="&nbsp;登&nbsp;录&nbsp;" id="login" class="btn btn-block"/>
                                <input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-block"
                                       onclick="remove()">
                            </form>
                            <div class='text-center'>
                                <hr class='hr-normal'>
                                <a href='#'>忘记密码?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class='login-container-footer'>
                <div class='container'>
                    <div class='row'>
                        <div class='col-sm-12'>
                            <div class='text-center'>
                                <a href='sign_up.html'>
                                    <i class='icon-user'></i>
                                    Copyright © 2017 聚会助手
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


