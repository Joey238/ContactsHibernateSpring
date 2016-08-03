 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<title>${company.name}</title>
</head>
<body>
		<h1><a href=/>${company.name}</h1>
		<ul>
			<c:forEach var="office" items="${company.offices}">
				<li><a href="${office.url}">${office.name}</a></li>
			</c:forEach>
		</ul>
		<!--TODO: list offices  -->	
		<a href="company?edit=${company.id}">Edit company</a> |
		<a href="contacts">Back to contact list</a>
</body>
</html> 