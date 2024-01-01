<%@ page import="com.zumba.dao.ParticipantDAO" %>
<%@ page import="com.zumba.model.Participant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Participant Details</title>
</head>
<body>
    <h1>Participant Details</h1>

    <% 
        String participantIdParam = request.getParameter("id");
        if (participantIdParam != null) {
            int participantId = Integer.parseInt(participantIdParam);
            
            try {
                ParticipantDAO participantDAO = new ParticipantDAO();
                Participant participant = participantDAO.getParticipantById(participantId);
                
                if (participant != null) {
    %>
                    <p><strong>ID:</strong> <%= participant.getParticipantId() %></p>
                    <p><strong>Name:</strong> <%= participant.getName() %></p>
                    <p><strong>Phone Number:</strong> <%= participant.getPhoneNumber() %></p>
                    <p><strong>Email Address:</strong> <%= participant.getEmailAddress() %></p>
    <%
                } else {
    %>
                    <p>No participant found with ID <%= participantId %></p>
    <%
                }
            } catch (Exception e) {
                e.printStackTrace();
    %>
                <p>An error occurred while retrieving participant details.</p>
    <%
            }
        } else {
    %>
            <p>Participant ID not provided.</p>
    <%
        }
    %>

</body>
</html>
