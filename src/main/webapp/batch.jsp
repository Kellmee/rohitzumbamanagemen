<%@ page import="com.zumba.model.Batch" %>
<%@ page import="com.zumba.dao.BatchDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Batch Details</title>
</head>
<body>
    <h1>Batch Details</h1>

    <% 
        String batchIdParam = request.getParameter("id");
        if (batchIdParam != null) {
            int batchId = Integer.parseInt(batchIdParam);
            
            try {
                BatchDAO batchDAO = new BatchDAO();
                Batch batch = batchDAO.getBatchById(batchId);
                
                if (batch != null) {
    %>
                    <p><strong>ID:</strong> <%= batch.getBatchId() %></p>
                    <p><strong>Name:</strong> <%= batch.getName() %></p>
    <%
                } else {
    %>
                    <p>No batch found with ID <%= batchId %></p>
    <%
                }
            } catch (Exception e) {
                e.printStackTrace();
    %>
                <p>An error occurred while retrieving batch details.</p>
    <%
            }
        } else {
    %>
            <p>Batch ID not provided.</p>
    <%
        }
    %>

</body>
</html>
