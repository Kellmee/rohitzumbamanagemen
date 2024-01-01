package com.zumba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zumba.model.Participant;
import com.zumba.util.DbUtil;

public class ParticipantDAO {
    public void createParticipant(String name, String phone, String email) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO participants (name, phone, email) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public Participant getParticipantById(int participantId) {
        Participant participant = null;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM participants WHERE id = ?")) {

            preparedStatement.setInt(1, participantId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    participant = extractParticipantFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return participant;
    }

    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM participants");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Participant participant = extractParticipantFromResultSet(resultSet);
                participants.add(participant);
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return participants;
    }

    public void updateParticipant(Participant updatedParticipant) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE participants SET name=?, phone=?, email=? WHERE id=?")) {

            preparedStatement.setString(1, updatedParticipant.getName());
            preparedStatement.setString(2, updatedParticipant.getPhoneNumber());
            preparedStatement.setString(3, updatedParticipant.getEmailAddress());
            preparedStatement.setInt(4, updatedParticipant.getParticipantId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void deleteParticipant(int participantId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM participants WHERE id=?")) {

            preparedStatement.setInt(1, participantId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    private Participant extractParticipantFromResultSet(ResultSet resultSet) throws SQLException {
        Participant participant = new Participant(0, null, null);
        participant.setParticipantId(resultSet.getInt("id"));
        participant.setName(resultSet.getString("name"));
        participant.setPhoneNumber(resultSet.getString("phone"));
        participant.setEmailAddress(resultSet.getString("email"));
        
        return participant;
    }
}

