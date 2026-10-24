import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Easy {
    private static final String URL ="jdbc:postgresql://localhost:5432/company_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int empId = resultSet.getInt("EmpID");
                String name = resultSet.getString("Name");
                double salary = resultSet.getDouble("Salary");

                System.out.printf(
                        "EmpID: %d, Name: %s, Salary: %.2f%n",
                        empId, name, salary
                );
            }
        } catch (SQLException e) {
            System.err.println(
                    "Could not retrieve Employee records: "
                            + e.getMessage()
            );
        }
    }
}