<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Dashboard - SB Admin</title>

<!-- Bootstrap core CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
rel='stylesheet' type='text/css'>

<!-- Add custom CSS here -->
<link href="<c:url value='/template/admin/css/sb-admin.css'/>" rel="stylesheet">
<!-- <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/css/font-awesome.min.css'/>"> -->


<!-- Page Specific CSS -->
<link rel="stylesheet"
	href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
<title><dec:title default="Admin page" /></title>

<!-- Table-->
<link href="<c:url value='/template/admin/css/news-style.css'/>"
	rel="stylesheet" media="all">
	
<!-- Paging -->
<script src="<c:url value='/template/pagination/jquery.twbsPagination.js'/>" type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/common/admin/header.jsp"%>
		
		
		<dec:body />
		
		
		<%@ include file="/common/admin/footer.jsp"%>
	</div>
	
	
	<!-- JavaScript -->
    <!-- <script src="<c:url value='/template/admin/js/jquery-1.10.2.js'/>"></script> -->
    <!--<script src="<c:url value='/template/admin/js/bootstrap.js'/>"></script>-->

    <!-- Page Specific Plugins -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
    <script src="<c:url value='/template/admin/js/morris/chart-data-morris.js'/>"></script>
    <script src="<c:url value='/template/admin/js/tablesorter/jquery.tablesorter.js'/>"></script>
    <script src="<c:url value='/template/admin/js/tablesorter/tables.js'/>"></script>
</body>
</html>