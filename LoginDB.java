

import Business.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/LoginDB"})
public class LoginDB extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String Email,Password;
       Email = request.getParameter("Email");
       Password = request.getParameter("Password");
       
       System.out.println("Email: " + Email);
       System.out.println("Password: " + Password);
       
       response.setContentType("text/html;charset=UTF-8");
try {

Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
Connection conn = DriverManager.getConnection("jdbc:ucanaccess://" + 
           "jdbc:ucanaccess://C:/Users/jairp/OneDrive/Desktop/delanie Java labs/GreetingCards/GreetingCardsDB.mdb");

Statement stmt = conn.createStatement();

String sql ="SELECT Password, Email FROM Customers WHERE Email = '" + Email + "'";

ResultSet rs = stmt.executeQuery(sql);
          
while(rs.next()){
 
          Users users = new Users();
          users.selectDB(Email);
          
String dbpw = rs.getString("Password");
//String dbem = rs.getString("Email");
if( dbpw.equals(Password) ){
    HttpSession session = request.getSession();
    session.setAttribute("users", Email);
System.out.println("CMONNNNNN!!!");

              //forward to accountlookup.jsp
              RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
              rd.forward(request, response);
    /*out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Servlet LoginServletDB</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Login Successful!</h1>");
    out.println("</body>");
    out.println("</html>"); */
}

else {
    HttpSession session = request.getSession();
    session.setAttribute("users", Email);


    /* out.println("<!DOCTYPE html>");
     out.println("<html>");
     out.println("<head>");
     out.println("<title>Servlet LoginServletDB</title>");
     out.println("</head>");
     out.println("<body>");
     out.println("<h1>Invalid login...</h1>");
     out.println("</body>");
     out.println("</html>");*/
    RequestDispatcher rd = request.getRequestDispatcher("LoginError.jsp");
              rd.forward(request, response);
   
                }
conn.close();
}}

 catch(ClassNotFoundException ex){
System.out.println("class not found "+ex.toString());
}
catch(SQLException ex){
System.out.println("SQL Exception "+ex.toString());
}

}


    
}






  

   /*
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     *
/
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
*/


