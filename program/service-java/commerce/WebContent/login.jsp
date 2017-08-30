<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%
request.setAttribute("globalUrl","http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>医家护</title>
<link rel="stylesheet" type="text/css" href="${ht_globalUrl}/css/main_front.css"/>
<!-- 下面的日期选择控件不能跨域 -->
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script language="javascript" type="text/javascript" src="js/ht_frame.js"></script>
<script type="text/javascript">
var globalUrl = '${ht_globalUrl}/';
</script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
<%
   String autoLogin  = request.getParameter("autoLogin");
   if ("1".equals(autoLogin)) {
	   request.setAttribute("webUrl",
			    "http://"+request.getServerName()+":"
	    		+ request.getServerPort()
	    		+ "/bpsfront/frontLogin.do?actionMethod=doLogin&from=timeoutLogin");
	   System.out.println(request.getAttribute("webUrl"));
	   Cookie[] allcookies = request.getCookies();
	   if (allcookies != null) {
		String acctname=null;
		String acctpswd=null;
	       for(int i=0;i<allcookies.length;i++){
			//依次遍历
	        Cookie tempCookie = allcookies[i];
			System.out.println(tempCookie.getName() + "=" + tempCookie.getValue());
	       	if (tempCookie.getName().equals("acctname")) {
	       		acctname=tempCookie.getValue();
	           } else if(tempCookie.getName().equals("acctpswd")){
	           	acctpswd=tempCookie.getValue();
	           }
	       }
	       //out.print(request.getSession().getAttribute("LoginAccount"));
	       if (acctname !=null && acctpswd != null) {
   %>
	   		$("#userName").val('<%=new String(com.sun.org.apache.xml.internal.security.utils.Base64.decode(acctname),"UTF-8")%>');
	   		$("#password").val('<%=new String(com.sun.org.apache.xml.internal.security.utils.Base64.decode(acctpswd),"UTF-8")%>');
	   		$("#login").submit();
   <%
       		}
   		}
   }
%>
</script>


<div class="head" id="top">
	<div class="container clearfix">
    <!--顶部-->
    <div class="left">
		<a href="#" class="logo"></a>
	</div>
    <div class="right">
        	<ul>
              <c:if test="${LoginAccount == null}">
              		<li><a href="#" onclick="window.location.href='login.do?actionMethod=initLogin'"><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>登录</a></li>
              		<li><a href="${ht_globalUrl}/register.show?actionMethod=register" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>注册</a></li>
              		<li><a href="${ht_globalUrl}/register.show?actionMethod=register" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>联系我们</a></li>
              		<li><a href="${ht_globalUrl}/register.show?actionMethod=register" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>帮助说明</a></li>
              </c:if>
              <c:if test="${LoginAccount != null }">
				    <li>欢迎<c:out value="${LoginAccount.acctName}"/>,</li>
              		<li><a href="${ht_globalUrl}/register.show?actionMethod=register" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>个人中心</a></li>
	                <li><a href="${ht_globalUrl}/sso.show?actionMethod=logout" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>注销</a></li>
              		<li><a href="${ht_globalUrl}/register.show?actionMethod=register" title=""><span><img src="${ht_globalUrl}/admin/images/logo03.png" /></span>帮助说明</a></li>
			  </c:if>
          </ul>
        </div>
    </div>
</div>
<div class="login">
<form name="login" id="login" method="post" action="${globalUrl}login.do?actionMethod=doLogin">
<input type="hidden" name="loginForm" value="loginForm"/>
<input type="hidden" name="webUrl" value="${webUrl }"/>
	<div class="wrap-content login-bd">
		<div class="main-wrap login-box">
			<h3><span class="welcome-login"></span></h3>
			<div class="form-control login-form">
				<div class="form-group">
					<label>帐&nbsp;&nbsp;&nbsp;号</label><input type="text"  id="userName" name="userName" value="${userName}" placeholder="手机/用户名/邮箱" class="user-name">
				</div>
				<div class="form-group">
					<label>密&nbsp;&nbsp;&nbsp;码</label><input type="password" id="password" name="password" value=""  placeholder="请输入您的密码"  class="user-psw">
				</div>
				<div>
					<c:if test="${errorLable ne null}">
						<font color=red size=2 style=" line-hright:24px;" ><spring:message code="${errorLable}"/></font>
					</c:if>
					<c:if test="${errorMessage ne null}">
						<font color=red size=2  style=" line-hright:12px;">${errorMessage}</font>
					</c:if>
				</div>
				<div class="form-group">
					<input type="submit" value="登&nbsp;&nbsp;录" class="btn login-btn">
				</div>
				<div class="login-helper">
					<p class="beauty-line"></p>
					<p class="user-helper">
						<a href="#" onclick="window.location.href='register.show?actionMethod=register'">注册新帐号</a>
						<a href="#" >忘记密码</a></p>
				</div>	
			</div>
		</div>
	</div>
</form>
</div>
<div class="footer" id="footer">
		<div class="container clearfix">
				<div class="about-us">
					<h4>关于我们</h4>
					<ul>
						<li><a href="#" target="_blank">运营公司</a></li>
						<li><a href="#" target="_blank">合作机构</a></li>
						<li><a href="#" target="_blank">职位空缺</a></li>
					</ul>
				</div>
				<div class="about-us">
					<h4>联系我们</h4>
					<ul>
						<li>联系电话：010-65901075</li>
						<li>传真：010-65901075</li>
					</ul>
				</div>
				<div class="about-us">
					<h4>友情链接</h4>
					<ul>
						<li><a href="#" target="_blank">南格科技</a></li>
					</ul>
				</div>
			</div>
</div>
</body>
</html>