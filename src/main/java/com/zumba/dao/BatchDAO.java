package com.zumba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zumba.model.Batch;
import com.zumba.util.DbUtil;

public class BatchDAO {
    public void createBatch(String batchName) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO batches (name) VALUES (?)")) {

            preparedStatement.setString(1, batchName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public Batch getBatchById(int batchId) {
        Batch batch = null;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM batches WHERE id = ?")) {

            preparedStatement.setInt(1, batchId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    batch = extractBatchFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return batch;
    }

    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM batches");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Batch batch = extractBatchFromResultSet(resultSet);
                batches.add(batch);
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return batches;
    }

    public void updateBatch(Batch updatedBatch) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE batches SET name=? WHERE id=?")) {

            preparedStatement.setString(1, updatedBatch.getName());
            preparedStatement.setInt(2, updatedBatch.getBatchId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public void deleteBatch(int batchId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM batches WHERE id=?")) {

            preparedStatement.setInt(1, batchId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    private Batch extractBatchFromResultSet(ResultSet resultSet) throws SQLException {
        Batch batch = new Batch();
        batch.setBatchId(resultSet.getInt("id"));
        batch.setName(resultSet.getString("name"));
        
        return batch;
    }
}

