<%@ page import="java.util.List" %>
<%@ page import="com.zumba.model.Participant" %>
<%@ page import="com.zumba.dao.ParticipantDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Participants</title>
</head>
<body>
    <h1>View Participants</h1>

<ul>
    <% 
        try {
            
            ParticipantDAO participantDAO = new ParticipantDAO();
            List<Participant> participants = participantDAO.getAllParticipants();
            
        
            for (Participant participant : participants) {
    %>
                <li>
                    <strong>ID:</strong> <%= participant.getParticipantId() %><br>
                    <strong>Name:</strong> <%= participant.getName() %><br>
                    <strong>Phone Number:</strong> <%= participant.getPhoneNumber() %><br>
                    <strong>Email Address:</strong> <%= participant.getEmailAddress() %><br>
                </li>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
    %>
            <li>An error occurred while retrieving participant details.</li>
    <%
        }
    %>
</ul>


</body>
</html>
