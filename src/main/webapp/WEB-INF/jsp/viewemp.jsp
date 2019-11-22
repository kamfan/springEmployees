<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>View Employees List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Salary</th>
        <th>Designation</th>
        <th>Action</th>
        <th>Email<th>
    </tr>
    <c:forEach var="emp" items="${list}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.salary}</td>
            <td>${emp.designation}</td>
            <td>${emp.email}</td>

            <td>
                <form:form method="post" action="delete">
                    <input type="hidden" id="id" name="id" value="${emp.id}"/>
                    <input type="submit" class="button" name="Delete" value="delete"/>
                </form:form>
                <form:form method="post" action="edit">
                    <input type="hidden" id="id" name="id" value="${emp.id}"/>
                    <input type="submit" class="button" name="Edit" value="edit"/>
                </form:form>
        <%--    <form:form method="post" action="delete">
             <input type="submit" class="button" name="Delete" value="delete"/>
         </form:form>--%>
            </td>
        </tr>
    </c:forEach>

    <td>
        <form:form method="post" action="test">
            <input type="submit" class="button" name="test" value="test"/>
        </form:form>
    </td>

</table>