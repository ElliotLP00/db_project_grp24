import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class mainloop {
    private adminServices adminServices;
    private patientServices patientServices;
    private doctorServices doctorServices;
    public static void main(String[] args) {
        new mainloop();
    }

    public mainloop(){
        adminServices = new adminServices();
        doctorServices = new doctorServices();
        patientServices = new patientServices();
        try {
            //adminServices.addDoctor(23,"Elliot","Porn","087");

            //patientServices.getPatient(44);
            SignIn();
        } catch (SQLException e) {
            //Catching all exeptions in one place
            e.printStackTrace();
        }
    }

    private void SignIn() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Admin");
        System.out.println("2. Doctor");
        System.out.println("3. Patient");
        int choise = scanner.nextInt();
        switch (choise){
            case 1:
                System.out.println("Proccecing...");
                adminLoop();
                break;
            case 2:
                System.out.println("Enter employee number...");
                int e_nbr = scanner.nextInt();
                if(e_nbr == 00){ //TODO Fetch data from database and compare to the input
                    System.out.println("Proccecing...");
                    doctorLoop(e_nbr);
                }else{
                    System.out.println("INVALID EMPLOYEE NUMBER");
                }
                break;
            case 3:
                String m_nbr = scanner.next();
                if(patientServices.getPatient(m_nbr)){ //TODO Fetch data from database and compare to the input
                    System.out.println("Proccecing...");
                    patientLoop(m_nbr);
                }else{
                    System.out.println("INVALID MEDICAL NUMBER");
                    System.out.println("Enter your 9 digit medical number");
                    m_nbr = scanner.next();
                    System.out.println("Enter first name");
                    String f_name = scanner.next();
                    System.out.println("Enter last name");
                    String l_name = scanner.next();
                    System.out.println("Enter gender");
                    String gender = scanner.next();
                    System.out.println("Enter adress");
                    String adress = scanner.next();
                    System.out.println("Enter phone number");
                    String phone = scanner.next();
                    System.out.println("Enter you birth year");
                    int year = scanner.nextInt();
                    System.out.println("Enter you birth month");
                    int month = scanner.nextInt();
                    System.out.println("Enter you birth day");
                    int day = scanner.nextInt();
                    Date bithdate = new Date(year,month,day);
                    patientServices.addPatienties(""+m_nbr, f_name,l_name,gender,adress,phone,bithdate,new Date(System.currentTimeMillis()));
                    patientLoop(m_nbr);
                }
                break;
        }
    }

    private void adminLoop() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME ADMIN");
        int choise = 0;
        while (choise != 12){
            System.out.println("1.Add a doctor");
            System.out.println("2.Delete a doctor");
            System.out.println("3.See list of patients in the health center");
            System.out.println("4.See all upcoming appointments");
            System.out.println("5.See medical record for a patient");
            System.out.println("6.Add specilization");
            System.out.println("12.exit");
            choise = scanner.nextInt();
            switch (choise){
                case 1:
                    System.out.println("Enter your employee number");
                    int employeeNbr = scanner.nextInt();
                    System.out.println("Please enter the type of doctor.");
                    String doctorType = scanner.next();
                    System.out.println("Please enter the name of the doctor.");
                    String doctorName = scanner.next();
                    System.out.println("Please enter the phone number to the doctor");
                    String doctorPhone = scanner.next();
                    adminServices.addDoctor(employeeNbr,doctorName,doctorType,doctorPhone);
                    break;
                case 2:
                    System.out.println("Please enter the employee number of the doctor.");
                    employeeNbr = scanner.nextInt();
                    adminServices.removeDoctor(employeeNbr);
                    break;
                case 3:
                    adminServices.seeAllPatients();
                    break;
                case 4:
                    adminServices.seeAllBookings();
                    break;
                case 5:
                    System.out.println("Please enter medicalnumber.");
                    String medicalNbr = scanner.next();
                    adminServices.seeMedicalRecordForPatient(medicalNbr);
                    break;
                case 6:
                    System.out.println("Enter the name of the specilization");
                    String specilization = scanner.next();
                    System.out.println("Enter the cost of the specilization");
                    int cost = scanner.nextInt();
                    adminServices.addSpecialzation(specilization,cost);
                    break;
            }
        }
    }

    private void doctorLoop(int employeeNbr) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME "+employeeNbr);
        int choise = 0;
        while (choise != 12){
            System.out.println("1.Define availabilities");
            System.out.println("2.List all upcoming appointments");
            System.out.println("3.List all patients ");
            System.out.println("4.Add medical record to patient");
            System.out.println("12.exit");
            choise = scanner.nextInt();
            switch (choise){
                case 1:
                    doctorServices.getSchemaforDoctor(employeeNbr);
                    System.out.println("What day you want to define");
                    String day = scanner.next();
                    System.out.println("What time you want to define");
                    String time = scanner.next();
                    String id = day+"_"+time;
                    doctorServices.changeAvalbility(id,employeeNbr);
                    break;
                case 2:
                    //TODO Hämta och skriv ut alla bokningar
                    doctorServices.listAllUpcomingApointments(employeeNbr);
                    break;
                case 3:
                    //TODO Hämta och skriv ut alla patienter som doktorn har haft
                    break;
                case 4:
                    System.out.println("Enter medical number of patient");
                    //TODO Kolla om patienten finns
                    //TODO
                    break;
            }
        }
    }

    private void patientLoop(String medicalNbr){
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME "+medicalNbr); //TODO Fetch name of patient
        int choise = 0;
        while (choise != 12){
            System.out.println("1.");
            System.out.println("2.patient stuff");
            System.out.println("12.exit");
            choise = scanner.nextInt();
            switch (choise){
                case 1:

                    break;
                case 2:
                    System.out.println("patient MORE SHIT");
                    break;
                case 3:
                    System.out.println("patient STUFF");
                    break;
            }
        }
    }



}

