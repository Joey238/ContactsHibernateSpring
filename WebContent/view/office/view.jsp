 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<title>${office.name}</title>
</head>
<body>
		<h1>${office.name}</h1>
		<ul>
			<c:set var="address" value="${office.address }"/>
			<li>${address.street}</li>
			<li>${address.city} ${address.state} ${address.zip}</li>
		</ul>
		<a href="${office.url}&edit">Edit office</a>	
		<a href="${office.company.url}">Back to ${office.company.name}</a>
	</body>
</html> 