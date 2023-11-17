package Business; 
import java.sql.*;
import static net.ucanaccess.util.Logger.log;


public class Users {
    private int UserID;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Email;
    private String Password;
    private String ClientType;
    
    public Users(){
        this.UserID = 0;
        this.FirstName = "";
        this.LastName = "";
        this.Address = "";
        this.Email = "";
        this.Password = "";
        this.ClientType = "";
    }
        
        public Users(int UserID, String FirstName, String LastName, String Address, String Email ,String Password, String ClientType ){
            this.UserID = UserID;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.Address = Address;
            this.Email = Email;
            this.Password = Password;
            this.ClientType = ClientType;
        }
        public void selectDB(String Email) throws ClassNotFoundException{
            
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/jairp/OneDrive/Desktop/delanie Java labs/GreetingCards/GreetingCardsDB (2).accdb")) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Users WHERE Email = ?");
               pstmt.setString(1, Email);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()){
                   this.UserID = rs.getInt("UserID");
                   this.FirstName = rs.getString("FirstName");
                   this.LastName = rs.getString("LastName");
                   this.Address = rs.getString("Address");
                   this.Email = rs.getString("Email");
                   this.Password = rs.getString("Password");
                   this.ClientType = rs.getString("ClientType");
                   
                }

            }
            catch (SQLException e) {
            System.out.println(e);
        }
            
        }
         public void insertDB(int UserID, String FirstName, String LastName, String Address, String Email ,String Password, String ClientType ) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/jairp/OneDrive/Desktop/delanie Java labs/GreetingCards/GreetingCardsDB (2).accdb")) {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users (UserID,FirstName,LastName,Address,Email,Password,ClientType) VALUES (?, ?, ?, ?,?,?,?)");

                pstmt.setInt(1, UserID);
                pstmt.setString(2, FirstName);
                pstmt.setString(3, LastName);
                pstmt.setString(4,Address);
                pstmt.setString(5, Email);
                pstmt.setString(6, ClientType);

                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteDB() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/jairp/OneDrive/Desktop/delanie Java labs/GreetingCards/GreetingCardsDB (2).accdb")) {
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Users WHERE UserID = ?");
                pstmt.setInt(1, this.UserID);
                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void display() {
        System.out.println("UserID = " + UserID);
        System.out.println("First Name: " + FirstName);
        System.out.println("Last Name: " + LastName);
        System.out.println("Address: " + Address);
         System.out.println("Email: " + Email);
          System.out.println("Password: " + Password);
          System.out.println("Client Type: " + ClientType);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String Email = "lsmith@gmail.com";
        Users u1 = new Users();
        u1.selectDB(Email);
        u1.display();
    }

    public int getUserID() {
        log("UserID: " + UserID);
        return UserID;
        
    }
    
    public String getFirstName(){
        log("First Name: " + FirstName);
        return FirstName;
    }

    public String getLastName(){
        log("Last Name: " + LastName);
        return LastName;
    }
    
    public String getAddress() {
                log("Address: " + Address);
                return Address;
    }
                
     public String getEmail() {
                log("Email: " + Email);
                return Email;
     }
                
     public String getPassword() {
                log("Password: " + Password);
                return Password;
     }
      public String getClientType() {
                log("Client Type: " + ClientType);
                return ClientType;

        
    }


}
    

