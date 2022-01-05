import java.sql.*;

public class adminServices extends Services{


    public void removeDoctor(int employee_nbr) throws SQLException {
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = con.prepareStatement("call deleteDoctor(?)");
        pstmt.setInt(1, employee_nbr);
        pstmt.execute();
        pstmt.close();
        con.close();
    }

    public void addDoctor(int employee_nbr, String full_name, String specialaiztion, String phone_nbr) throws SQLException {
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = con.prepareStatement("call adddoctor(?,?,?,?)");
        pstmt.setInt(1, employee_nbr);
        pstmt.setString(2, full_name);
        pstmt.setString(3, specialaiztion);
        pstmt.setString(4, phone_nbr);
        pstmt.execute();
        pstmt.close();
        con.close();
    }

    public void addSpecialzation(String specilazation, int cost) throws SQLException{
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = con.prepareStatement("call addspecialist(?,?)");
        pstmt.setString(1, specilazation);
        pstmt.setInt(2, cost);
        pstmt.execute();
        pstmt.close();
        con.close();
    }

    public void seeAllPatients() throws SQLException{
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"patients\"";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("Medical number: " + rs.getString("medical_nbr"));
            System.out.print(", first name: " + rs.getString("f_name"));
            System.out.print(", Last name: " + rs.getString("l_name"));
            System.out.println(", Total cost ");//TODO Fetch total visit costs
            System.out.println("----------------------------------------------------------");
        }
        stmt.close();
        con.close();
    }
    //TODO imp and test
    public void seeAllBookings() throws SQLException {
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"bookings\"";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("Booking ID: " + rs.getString("booking_id"));
            System.out.print(", date: " + rs.getDate("date_booked"));
            System.out.print(", time of day: " + rs.getString("time_of_day"));
            System.out.print(", doctor ID: " + rs.getString("dr_id"));
            System.out.print(", patient ID: "+ rs.getString("patient_id"));
            System.out.println("----------------------------------------------------------");
        }
        stmt.close();
        con.close();
    }
    //TODO imp and test
    public void seeMedicalRecordForPatient(String medical_nbr) throws SQLException{
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"medical_records\" where medical_nbr="+medical_nbr;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        while (rs.next()) {
            // Retrieve by column name
            System.out.print("record ID: " + rs.getString("record_id"));
            System.out.print(", diagnosis: " + rs.getDate("diagnosis"));
            System.out.print(", description: " + rs.getString("descrition"));
            System.out.print(", prescription: " + rs.getString("perscreiption"));
            System.out.print(", medical number: "+ rs.getString("medical_nbr"));
            System.out.println("----------------------------------------------------------");
        }
        stmt.close();
        con.close();
    }




}
