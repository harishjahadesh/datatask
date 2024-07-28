package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class data {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/empdb"; 
        String user = "root"; 
        String password = "@1234"; 

        String[] employees = {
            "101, 'Jenny', 25, 10000",
            "102, 'Jacky', 30, 20000",
            "103, 'Joe', 20, 40000",
            "104, 'John', 40, 80000",
            "105, 'Shameer', 25, 90000"
        };

        String insertSQL = "INSERT INTO Employee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            for (String emp : employees) {
                String[] empDetails = emp.split(", ");
                pstmt.setInt(1, Integer.parseInt(empDetails[0]));
                pstmt.setString(2, empDetails[1].replace("'", ""));
                pstmt.setInt(3, Integer.parseInt(empDetails[2]));
                pstmt.setDouble(4, Double.parseDouble(empDetails[3]));
                pstmt.executeUpdate();
            }

            System.out.println("Records inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
