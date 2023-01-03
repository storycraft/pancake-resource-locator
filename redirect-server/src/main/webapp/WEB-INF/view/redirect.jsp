<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url var="url" value="${page.url}" htmlEscape="true" />
<!DOCTYPE html>
<html>
  <head>
    <title>Redirecting...</title>
    <script>
      window.addEventListener('DOMContentLoaded', () => {
        setTimeout(() => {
          window.location.href = '${url}';
        }, 1000);
      }, { once: true });
    </script>
  </head>
  <body>
    Redirecting to ${url}
  </body>
</html>
