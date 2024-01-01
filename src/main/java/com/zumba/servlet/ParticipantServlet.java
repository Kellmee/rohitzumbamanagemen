package com.zumba.servlet;

import com.zumba.dao.ParticipantDAO;
import com.zumba.model.Participant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/participant")
public class ParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParticipantDAO participantDAO = new ParticipantDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Participant> participants = participantDAO.getAllParticipants();
        request.setAttribute("participants", participants);
        request.getRequestDispatcher("participant.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Participant participant = new Participant(0, name, email);
        participantDAO.updateParticipant(participant);

        response.sendRedirect(request.getContextPath() + "/participant");
    }
}
