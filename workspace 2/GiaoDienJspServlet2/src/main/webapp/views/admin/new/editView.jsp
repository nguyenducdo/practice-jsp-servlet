<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:url var="apiUrl" value="/api-admin-new"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Dashboard</a>
                </li>
                <c:if test="${not empty model.id}">
                	<li class="active">Edit news</li>
                </c:if>
                <c:if test="${empty model.id}">
                	<li class="active">Create news</li>
                </c:if>
                
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-${alert}">
                                    ${errorMessage}
                            </div>
                        </c:if>
                        <form id="submitForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Category</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryId" name="categoryId">
                                       <option disabled selected>Select a category</option>
                                       <c:if test="${empty model}">
	                                       <c:forEach items="${categories}" var="category">
	                                       		<option value="${category.id}">${category.name}</option>
	                                       </c:forEach>
                                       </c:if>
                                       <c:if test="${not empty model}">
	                                       <c:forEach items="${categories}" var="category">
	                                       		<option value="${category.id}" <c:if test="${model.category.id == category.id}">selected</c:if> >${category.name}</option>
	                                       </c:forEach>
                                       </c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Title</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title" value="${model.title}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thumbnail</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value=""/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Short description</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Content</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-12" style="text-align: center; margin-top: 15px; margin-bottom: 15px">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Save" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Create" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	$("#btnAddOrUpdateNew").click(function(e){
		e.preventDefault();
		var data = {};
        var formData = $('#submitForm').serializeArray();
        $.each(formData,function(i,v){
            data[v.name+""] = v.value;
        });
        var id = $("#id").val();
        if(id==""){
            createNew(data);
        }else{
            updateNew(data);
        }
	});

    function createNew(data){
        $.ajax({
            url : '${apiUrl}',
            type : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            dataType : 'json',
            success : function(result){
                console.log(result);
            },
            error : function(error){
                console.log(error);
            }
        });
    }

    function updateNew(data){
        $.ajax({
            url : '${apiUrl}',
            type : 'PUT',
            contentType : 'application/json',
            data : JSON.stringify(data),
            dataType : 'json',
            success : function(result){
                console.log(result);
            },
            error : function(error){
                console.log(error);
            }
        });
    }

</script>
</body>
</html>