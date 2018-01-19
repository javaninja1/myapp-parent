<%@ taglib uri="http://myapp/tags" prefix="myapp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body bgcolor='wheat'>
  <div align='center' style="margin: auto; border: 3px solid #73AD21;">
    <hr>
    <b>Param passing and custom annotation:</b> <br> Reached the
    jsp: ${result}

    <hr>
    <b>Custom Taglib: </b> <br>
    <myapp:mytag param1="aaa" />
    <hr>
    <b>File Tag: </b><br>
    <myapp:filetag param1="wwww" />
    <hr>
    <b>JSP Custom Function:</b> <br>
    <c:if test="${myapp:isEven(4)}">
        4 is even <br>
    </c:if>
    <c:if test="${not myapp:isEven(3)}">
        3 is not even
     </c:if>

    <hr>
    <b>queryLookup:</b> <br> ${queryLookup}

    <hr>
    <b>Spring form:</b> <br>
    <form:form name="books" action="books.jsp" method="POST">
      <form:input path="title"/> &nbsp; 
      <input type=submit name="addBook" value="Add Book" />
    </form:form>

    
    <hr>
    <form:form name="books" method="POST" commandName="command">
      <input type=submit name="initData" value="Init Data" />
    </form:form>

    <hr>
    <b>Create Query:</b> <br>
    <c:if test="${not empty bookList}">
      <table border=1>
        <tr>
          <td>BookId</td>
          <td>Title</td>
        </tr>
        <c:forEach items="${bookList}" var="book">
          <tr>
            <td>${book.bookId}</td>
            <td>${book.title}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>

    <hr>
    <b>Named Queries:</b> <br>
    <c:if test="${not empty namedQueryList}">
      <table>
        <tr>
          <td>BookId</td>
          <td>Title</td>
        </tr>
        <c:forEach items="${namedQueryList}" var="book">
          <tr>
            <td>${book.bookId}</td>
            <td>${book.title}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
    <br> Max Book Id: ${maxBookId} <br>

    <hr>
    <b>Named Native Query using sql-mapping:</b> <br>
    <c:if test="${not empty bookView}">
       ${bookView.bookId} - ${bookView.title}
    </c:if>
    
    <hr>
    <b>EH Cache Info:</b> <br>
    <c:forEach items="${cacheInfo}" var="entry">
       ${entry} <br>
    </c:forEach>
    
    <hr>
    <b>Entity Manager Properties:</b> <br>
    <div align="center">
      <table style="border: 1px;">
        <c:forEach items="${emProperties}" var="entry" varStatus="loopStatus">
          <tr bgcolor='${loopStatus.index % 2 == 0 ? "#ccccff" : "#ffcc99"}'>
            <td align="right">${entry.key}</td>
            <td>${entry.value}</td>
          </tr>
        </c:forEach>
      </table>
    </div>

    
    
  </div>
</body>
</html>