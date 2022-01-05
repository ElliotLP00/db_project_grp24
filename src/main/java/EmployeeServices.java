import java.sql.*;

public class EmployeeServices {


    public void printEmployeesByDepartment(int deptId) throws Exception {

        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"Employee\" where department_id=" + deptId;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", first name: " + rs.getString("first_name"));
            System.out.print(", Last name: " + rs.getString("Last_name"));
            System.out.println(", Salary: " + rs.getInt("salary"));

            System.out.println("----------------------------------------------------------");
        }

        stmt.close();
        con.close();


    }

    public Connection getDatabaseConnection() {

        String url = "jdbc:postgresql://localhost:5432/HR";
        String user = "Fahed";
        String password = "Fahed";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            System.out.println("Connection Established");
            return con;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public void printAllEmployees() throws Exception {

        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"Employee\"";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", first name: " + rs.getString("first_name"));
            System.out.print(", Last name: " + rs.getString("Last_name"));
            System.out.println(", Salary: " + rs.getInt("salary"));

            System.out.println("----------------------------------------------------------");
        }

        stmt.close();
        con.close();

    }


    public void printEmployeesWithSalaryGreaterThanLimit(int salary) throws Exception {
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"Employee\" where salary>" + salary;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", first name: " + rs.getString("first_name"));
            System.out.print(", Last name: " + rs.getString("Last_name"));
            System.out.println(", Salary: " + rs.getInt("salary"));

            System.out.println("----------------------------------------------------------");
        }
        stmt.close();
        con.close();

    }

    public void raiseEmployeesSalariesInDept(int deptId, int percentage) throws Exception {
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = con.prepareStatement("call raiseemployees(?,?)");
        pstmt.setInt(1, deptId);
        pstmt.setInt(2, percentage);
        pstmt.execute();
        pstmt.close();
        con.close();
    }

    public void sumEmployeesSalariesByDept(int deptId) throws Exception {

        String query = "{ call sumEmployeesSalaries( ?,? ) }";



        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/HR", "Fahed", "Fahed");
             Statement statement = conn.createStatement();
             CallableStatement callableStatement = conn.prepareCall(query)) {
            callableStatement.registerOutParameter(2, Types.INTEGER);


            // input
            callableStatement.setInt(1, deptId);

            // Run hello() function
            callableStatement.executeUpdate();

            // Get result
            int result1 = callableStatement.getInt(2);


            System.out.println(result1 + " --");

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteEmployee(int empId) throws Exception {

        int affectedrows = 0;

        String query = "DELETE FROM \"Employee\" WHERE id = ?";
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, empId);
        affectedrows = pstmt.executeUpdate();

        if (affectedrows >0){

            System.out.println("employee + " +empId+ " is deleted successfully");

        }

        pstmt.close();
        con.close();


    }
}