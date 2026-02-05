import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authentification {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void authentification(){
        try {
            String url = "jdbc:mysql://localhost:3306/dbcafe";
            String username = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, username, password);
            int i = 1;
            while (i <= 3) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE login = ? and  password = ?");
                System.out.print("Login : ");
                stmt.setString(1, br.readLine());
                System.out.print("Password : ");
                stmt.setString(2, br.readLine());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    PreparedStatement stmt1 = conn.prepareStatement("update users set nbrConnection = nbrConnection + 1 where login = ? and password = ?");
                    stmt1.setString(1, rs.getString("login"));
                    stmt1.setString(2, rs.getString("password"));
                    stmt1.executeUpdate();
                    break;
                }
                i++;
            }
            if (i == 4){
                System.out.println("ERREUR D'AUTHENTIFICATION après 3 essais échoués!!!");
            }else {
                System.out.println("Login succée.");
                Main.menu();
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
