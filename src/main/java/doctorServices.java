import java.sql.*;

public class doctorServices extends Services{
    private String[] schemaID= {
            "mon_0900",
            "mon_0930",
            "mon_1000",
            "mon_1030",
            "tue_0900",
            "tue_0930",
            "tue_1000",
            "tue_1030",
            "wed_0900",
            "wed_0930",
            "wed_1000",
            "wed_1030",
            "thu_0900",
            "thu_0930",
            "thu_1000",
            "thu_1030",
            "fri_0900",
            "fri_0930",
            "fri_1000",
            "fri_1030"
    };

    public void getSchemaforDoctor(int employeeNbr) throws SQLException {
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"dr_schedual_available\" WHERE employe_nbr="+employeeNbr;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        rs.next();
        for(String id : schemaID){
            System.out.print(id+": ");
            switch (rs.getInt(id)){
                case 0:
                    System.out.println("Booked");
                    break;
                case 1:
                    System.out.println("Available");
                    break;
                case 2:
                    System.out.println("Unavailable");
                    break;
            }
        }
        stmt.close();
        con.close();
    }

    public void changeAvalbility(String id, int employeeNbr) throws SQLException {
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"dr_schedual_available\" where employe_nbr="+employeeNbr;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
        rs.next();
        int avaliablity = rs.getInt(id);
        stmt.close();
        con.close();
        switch (avaliablity){
            case 0:
                System.out.println("Day is booked");
                return;
            case 1:
                System.out.println("Switching to unavailable");
                avaliablity = 2;
                break;
            case 2:
                System.out.println("Switching to available");
                avaliablity = 1;
                break;
        }
        con = getDatabaseConnection();
        QUERY = "update dr_schedual_available set "+id+" = "+avaliablity+" where employe_nbr = "+employeeNbr;
        stmt = con.createStatement();
        stmt.executeUpdate(QUERY);
        stmt.close();
        con.close();
    }

    public void listAllUpcomingApointments(int employee_nbr) throws SQLException{
        Connection con = getDatabaseConnection();
        String QUERY = "SELECT * FROM \"bookings\" WHERE employe_nbr="+employee_nbr;
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
}
