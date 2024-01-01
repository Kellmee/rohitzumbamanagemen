package com.zumba.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.zumba.model.Batch;
import com.zumba.util.DbUtil;

@WebServlet("/batch")
public class BatchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM batches WHERE id = ?")) {

            ps.setInt(1, batchId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Batch batch = new Batch();
                    batch.setBatchId(rs.getInt("id"));
                    batch.setName(rs.getString("name"));

                  
                } else {
                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Batch batch = new Batch();
        batch.setName(request.getParameter("name"));

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO batches (name) VALUES (?)")) {

            ps.setString(1, batch.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));
        Batch updatedBatch = new Batch();
        updatedBatch.setName(request.getParameter("name"));

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE batches SET name=? WHERE id=?")) {

            ps.setString(1, updatedBatch.getName());
            ps.setInt(2, batchId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM batches WHERE id=?")) {

            ps.setInt(1, batchId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
