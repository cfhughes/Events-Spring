<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
			rel="stylesheet" 
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
			crossorigin="anonymous">
		<link rel="stylesheet" href="css/main.css" />
		<meta charset="ISO-8859-1">
	<title>Event Planner</title>
</head>
	<body>
		<div class="container">
			<h2>Welcome, ${ user.email }</h2>
			<hr />
			<div class="new-event float float-right">
				<p>Plan an Event:</p>
				<form:form action="/events" method="post" modelAttribute="event">
					<form:hidden value="${ user.id }" path="host"/>
					<div class="form-group">
						<form:label path="name">Name</form:label>
						<form:errors path="name"></form:errors>
						<form:input class="form-control" path="name"></form:input>
					</div>
					<div class="form-group">
						<form:label path="date">Date</form:label>
						<form:errors path="date"></form:errors>
						<form:input class="form-control" type="date" value="${ now }" path="date"></form:input>
					</div>
					 <div class="form-group">
				        <form:label path="locationCity">City</form:label>
				        <form:errors path="locationCity"/>
				        <form:input class="form-control" path="locationCity" />
				    </div>
				    <div class="form-group">
				        <form:label path="locationState">State</form:label>
				        <form:errors path="locationState"/>
						<form:input class="form-control" path="locationState"/>
				    </div>
				    <button>Create Event</button>
			</form:form>
			</div>
			<div class="float float-left">
				<h3>Here are some events in your state:</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Date</th>
							<th>City</th>
							<th>Host</th>
							<th>Action/Status</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ eventsInState }" var="event">
						<tr>
							<td><a href="/events/${ event.id }">${ event.name }</a></td>
							<td>${ event.date }</td>
							<td>${ event.locationCity }</td>
							<td>${ event.host.email }</td>
							<td>
							<c:choose>
								<c:when test="${ event.host.id == user.id }">
									<a href="/events/${ event.id }/edit">Edit</a> |
									<form class="delete-form" action="/events/${ event.id }" method="post">
										<input type="hidden" name="_method" value="delete" />
										<button>Delete</button>
									</form>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${ event.attendees.contains(user) }">
											<span>Joining <a href="/events/${ event.id }/a/cancel">Cancel</a></span>
										</c:when>
										<c:otherwise>
											<a href="/events/${ event.id }/a/join">Join</a>								
										</c:otherwise>
									
									</c:choose>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<h3>Here are some events in other states:</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Date</th>
							<th>City</th>
							<th>State</th>
							<th>Host</th>
							<th>Action/Status</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ otherEvents }" var="event">
						<tr>
							<td><a href="/events/${ event.id }">${ event.name }</a></td>
							<td>${ event.date }</td>
							<td>${ event.locationCity }</td>
							<td>${ event.locationState }</td>
							<td>${ event.host.email }</td>
							<td>
							<c:choose>
								<c:when test="${ event.host.id == user.id }">
									<a href="/events/${ event.id }/edit">Edit</a> |
									<form class="delete-form" action="/events/${ event.id }" method="post">
										<input type="hidden" name="_method" value="delete" />
										<button>Delete</button>
									</form>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${ event.attendees.contains(user) }">
											<span>Joining <a href="/events/${ event.id }/a/cancel">Cancel</a></span>
										</c:when>
										<c:otherwise>
											<a href="/events/${ event.id }/a/join">Join</a>								
										</c:otherwise>
									
									</c:choose>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>