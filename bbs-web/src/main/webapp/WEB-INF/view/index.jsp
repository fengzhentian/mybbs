<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ include file="include.inc.jsp"%>
<!DOCTYPE html>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<c:import url="meta.jsp"></c:import>
<title>首页</title>
<%@ include file="common.jsp"%>
</head>

<body class="easyui-layout">
	<!-- 北 -->
	<div data-options="region:'north',border:false" style="height: 84px;">
		<c:import url="top.jsp"></c:import>
	</div>
	<!-- 西 -->
	<div data-options="region:'west',split:true"
		style="width: 165px; height: 100%; overflow-y: hidden;">
	</div>
	<!-- 中 -->
	<div data-options="region:'center'" style="overflow: hidden">
	</div>
</body>
</html>