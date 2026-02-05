import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

public class Client {
    public static void menu(){
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("-------Menu Client-------");
            System.out.println("1. Create Table Client");
            System.out.println("2. Afficher Table Client");
            System.out.println("3. Supprimer Table Client");
            System.out.println("4. Add Client");
            System.out.println("5. Delete Client");
            System.out.println("6. Load Client from fichier");
            System.out.println("0. Quitter");
            System.out.print("Enter choix: ");
            try{
                choix = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch(choix){
                case 1:
                    createTableClient();
                    break;
                case 2:
                    afficherTableClient();
                    break;
                case 3:
                    supprimerTableClient();
                    break;
                case 4:
                    addClient();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    loadclient();
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }while (choix != 0);
    }

    public static void createTableClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("create table if not exists client (" +
                    "idclt int primary key auto_increment," +
                    "nomclt varchar(30)," +
                    "adrclt varchar(100)," +
                    "telclt long" +
                    ");");
            System.out.println("Table created");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void afficherTableClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from client");
            while(rs.next()){
                System.out.println(rs.getInt("idclt") + " " +  rs.getString("nomclt")
                + " " +  rs.getString("adrclt") +  " " +  rs.getLong("telclt"));
            }
            conn.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void supprimerTableClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop table if exists client");
            System.out.println("Table dropped");
            conn.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("insert into client (nomclt, adrclt, telclt) values(?,?,?)");
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrer le nom :");
            stmt.setString(1, sc.nextLine());
            System.out.print("Entrer l'adress : ");
            stmt.setString(2, sc.nextLine());
            System.out.print("Entrer le tel :");
            stmt.setLong(3, sc.nextLong());
            stmt.executeUpdate();
            System.out.println("Client added successfully");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("delete from client where idclt = ?");
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrer l'Id de client :");
            stmt.setInt(1, sc.nextInt());
            stmt.executeUpdate();
            System.out.println("Client deleted successfully");
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void loadclient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            try{
                PreparedStatement stmt = conn.prepareStatement("insert into client (nomclt, adrclt, telclt) values(?,?,?)");
                FileInputStream file = new FileInputStream("C:\\Users\\hm\\3D Objects\\Desktop\\Review\\src\\List.txt");
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    stmt.setString(1, sc.next());
                    stmt.setString(2, sc.next());
                    stmt.setLong(3, sc.nextLong());
                    stmt.executeUpdate();
                }
                System.out.println("Client loaded successfully");
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}