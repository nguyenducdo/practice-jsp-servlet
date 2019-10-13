<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<div class="container" style="width: 100%; padding-left: 0px; padding-right: 0px">
		<div class="row" style="margin-left: 0px; margin-right: 0px">
			<div class="breadcrumbs" id="breadcrumbs">
	            <script type="text/javascript">
	                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	            </script>
	            <ul class="breadcrumb">
	                <li>
	                    <i class="ace-icon fa fa-home home-icon"></i>
	                    <a href="#">Dashboard</a>
	                </li>
	                <li class="active">News List</li>
	            </ul><!-- /.breadcrumb -->
	        </div>
			<p></p>
			<h1>Bootstrap Table Panel with Pagination</h1>
			<p>A simple example of how-to put a bordered table within a
				panel. Responsive, place holders in header/footer for buttons or
				pagination.</p>
			<p>
				Follow me <a href="https://twitter.com/asked_io" target="_new">@asked_io</a>
				&amp; <a href="https://asked.io/" target="_new">asked.io</a>.
			</p>

			<div class="col-md-10 col-md-offset-1" style="width: 98%; margin-left: 1%">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">Panel Heading</h3>
							</div>
							<div class="col col-xs-6 text-right">
								<button type="button" class="btn btn-sm btn-primary btn-create"
								onclick="window.location.href='<c:url value="/admin-news?action=create"/>'">Create New</button>
							</div>
						</div>
					</div>
					<div class="panel-body">
					<form action="<c:url value='/admin-news'/>" id="submitForm" method="get">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>
									<th style="text-align: left; width: 50px">ID</th>
									<th>Title</th>
									<th>Thumbnail</th>
									<th>Short Description</th>
									<th>Content</th>
									<th>Category</th>
									<th style="text-align: center; width: 100px"><em
										class="fa fa-cog"></em></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${model.list}" var="element">
									<tr>
										<td>${element.id}</td>
										<td>${element.title}</td>
										<td>${element.thumbnail}</td>
										<td>${element.shortDescription}</td>
										<td>${element.content}</td>
										<td>${element.category.name}</td>
										<td><a class="btn btn-default" href="<c:url value='/admin-news?action=edit&id=${element.id}'/>"><em class="fa fa-pencil"></em></a>
											<a class="btn btn-danger" href="<c:url value='/admin-news?action=delete&id=${element.id}'/>"><em class="fa fa-trash"></em></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="" id="page" name="page"/>
						<input type="hidden" value="" id="limit" name="limit"/>
						<!-- <input type="hidden" value="" id="sortName" name="sortName"/>
						<input type="hidden" value="" id="sortBy" name="sortBy"/> -->
					</form>	
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col col-xs-4">Page ${startPage} of ${totalPages}</div>

							<!-- paging -->
							<div style="position: absolute; right: 15px; padding-right: 15px">
							    <nav aria-label="Page navigation">
							        <ul class="pagination" id="pagination"></ul>
							    </nav>
							</div>
							<script type="text/javascript">
								var totalPages = ${totalPages};
								var startPage = ${startPage};
								var limit = 5;
								$(function() {
									window.pagObj = $('#pagination').twbsPagination({
										totalPages : totalPages,
										visiblePages : 3,
										startPage: startPage,
										onPageClick : function(event, page) {
											//console.info(page + ' (from options)');
										}
									}).on('page', function (event, page) {
										console.info(page + ' (from options)');
										$('#page').val(page);
										$('#limit').val(limit);
										$('#sortName').val('title');
										$('#sortBy').val('desc');
										$('#submitForm').submit();
							        });
								});
							</script>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>