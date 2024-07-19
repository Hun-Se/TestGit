import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
   static {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch(ClassNotFoundException e){
         
      }
   }

   public static Connection getConnection() throws Exception {
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr","root","rootroot");
      
      return con;
   }
   
   

}
