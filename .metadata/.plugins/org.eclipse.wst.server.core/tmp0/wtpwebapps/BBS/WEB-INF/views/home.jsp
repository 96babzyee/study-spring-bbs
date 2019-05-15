<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@include file="./includes/header.jsp"%>
<div class="container-fluid">
<div class="page-header">

<h1>Hello world!</h1>
<P class="lead">  The time on the server is ${serverTime}. </P>
<!-- <P class="lead"> <a href="/board/list">Board List</a> </P> -->

</div><!-- /.page-header -->
</div><!-- /.container -->

<script type="text/javascript">
  $(function () {
    console.log("jQuery ready");
  });
</script>

<%@include file="./includes/footer.jsp"%>
