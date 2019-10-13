<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">Start Bootstrap</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<c:if test="${not empty user}">
					<li class="nav-item"><a class="nav-link" href="#">Welcome, ${user.fullName} </a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Logout</a></li>
				</c:if>
				<c:if test="${empty user}">
					<li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Login</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>