<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ include file="taglib.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value='/support/north.css'/>">
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="overflow:hidden;">
    <tbody>
        <tr>
            <td width="93" height="82" align="left" valign="top" rowspan="2">
                <img width="94" height="82" src="<c:url value='/images/header/logo.jpg'/>" id="logoImg"></td>
            <td height="45" align="left" valign="top">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="505" align="left" valign="top">
                            <img src="<c:url value='/images/header/title.jpg'/>" width="505" height="45" /></td>
                        <td align="right" valign="middle" bgcolor="#2487B7">
                            <table border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="30" align="right" valign="middle" style="white-space: nowrap;">
                                        <img src="<c:url value='/images/header/top-icon01.gif'/>" width="20" height="20" />
                                    </td>
                                    <td style="font-size: 14px; color: #FFF; vertical-align: middle; text-align: left; white-space: nowrap;" valign="middle">
                                    <div id="currentUserDiv">&nbsp;<strong>·</strong>${contextUser.userName}&nbsp;&nbsp;&nbsp;&nbsp;</div>      
                                    </td>
                                    <td width="50" align="left" valign="middle" style="white-space: nowrap; padding-right: 10px;">
                                        <a href="passport/logout">
                                            <img src="<c:url value='/images/header/top-icon02.gif'/>" border="0" width="41" height="18" /></a>
                                    </td>
			                        <td width="229" align="left" valign="top">
			                            <img src="<c:url value='/images/header/right.png'/>" width="229" height="45" /></td>
                                    <td width="20"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td align="left" background="<c:url value='/images/header/nav-bg.jpg'/>" valign="top">
                <table id="menuContainer" style="width: 100%;table-layout: fixed; " cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" valign="middle" background="<c:url value='/images/header/nav-bg.jpg'/>" style="background-repeat: repeat-x;">
                            <table id="menuTable" width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed;">
                                <tr>
                                    <td style='width:120px;' height='37' title='待办(首页)' align='center' valign='middle'>
                                    	<a href='${ctx}/index' class='nav'>待办（首页）</a>
                                    </td>
									<!-- 动态生成菜单 -->
									<!-- for循环生成头部菜单 -->
									<c:forEach items="${topmenulist}" var="topmenu">
										<td width='2' background='${ctx}/images/header/top-icon03.gif'></td>
										<td style='width: 80px;' height='37' title='${topmenu.name}'
											align='center' valign='middle'><a href='javascript:void(0);'
											class='nav'
											onclick="moduleMenuClick('${topmenu.menuguid}', '${ctx}');return false;">${topmenu.name}</a>
										</td>
									</c:forEach>
									<!--end -->
                                    <td height="30"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </tbody>
</table>