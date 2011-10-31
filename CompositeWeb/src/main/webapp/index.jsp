<%@ page import="java.util.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.ehoffman.example.ExampleService" %>
<%@ page contentType="text/html; charset=ISO-8859-5" %>
<html>
<body>
  <%
    ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    ExampleService service = (ExampleService) appContext.getBean("exampleService");
  %>
 <%=service.getById(1).toString()%>
</body>
</html>

