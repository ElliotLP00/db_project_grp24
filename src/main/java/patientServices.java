import java.sql.*;

public class patientServices extends Services{
    public void addPatienties(String medicalnbr, String f_name, String l_name, String gender, String adress, String phone_nbr, Date birthdate, Date re_date) throws SQLException {
        Connection con = getDatabaseConnection();
        PreparedStatement pstmt = null;
        pstmt = con.prepareStatement("call addpatient(?,?,?,?,?,?,?,?)");
        pstmt.setString(1, medicalnbr);
        pstmt.setString(2, f_name);
        pstmt.setString(3, l_name);
        pstmt.setString(4, gender);
        pstmt.setString(5, adress);
        pstmt.setString(6, phone_nbr);
        pstmt.setDate(7, birthdate);
        pstmt.setDate(8, re_date);
        pstmt.execute();
        pstmt.close();
        con.close();
    }

    public boolean getPatient(String m_nbr) throws SQLException{
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"patients\" WHERE medical_nbr='"+m_nbr+"'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        boolean returnValue = true;
        if(rs.next() == false){
            returnValue = false;
        }
        stmt.close();
        con.close();
        return returnValue;
    }
}
