
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Contacts</title>
</head>
<body>
<h1>Contacts</h1>
	<ul>
	<c:forEach var="contact" items="${contacts}">
			<!-- what 's kind of contact it is ? person? or company?-->
		<li><a href="${contact.url}">${contact.id}. ${contact.name}</a></li><!-- Fixme: url -->
	</c:forEach>
	</ul>
	<a href="person?add">add a new contact</a> | 
	<a href="company?add">add a new company</a> 
</body>
</html> 