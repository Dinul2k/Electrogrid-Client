<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bills</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
	
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Bill management</h1>

	<form id="formItem" name="formItem">
 		Name:
 		<input id="name" name="name" type="text" class="form-control form-control-sm">
 		<br> 
 		Address:
		<input id="address" name="address" type="text" class="form-control form-control-sm">
 		<br> 
 		Unitsconsumed:
 		<input id="unitsconsumed" name="unitsconsumed" type="text" class="form-control form-control-sm">
 		<br> 
 		Bill amount:
		<input id="billamount" name="billamount" type="text" class="form-control form-control-sm">
 		<br>
 		Date:
		<input id="date" name="date" type="text" class="form-control form-control-sm">
 		<br>
 		
 		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 		<input type="hidden" id="hidBillIDSave" name="hidBillIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>

	<br>
	<div id="divItemsGrid">
 		<%
 			Bill billObj = new Bill();
 			out.print(billObj.readBills());
 		%>
	</div>
</div> </div> </div>
</body>
</html>