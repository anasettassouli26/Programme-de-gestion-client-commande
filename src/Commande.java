import java.sql.*;
import java.util.Scanner;

public class Commande {
    public static void menu() {
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-----Menu Commande-----");
            System.out.println("1. Create table Commande");
            System.out.println("2. Display Table Commande");
            System.out.println("3. Supprimer table Commande");
            System.out.println("4. Display Commande par client");
            System.out.println("5. Passer commande");
            System.out.println("6. Update quantité");
            System.out.println("0. Quitter");
            System.out.print("Enter choix : ");
            try{
                choix = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (choix) {
                case 1:
                    createTableCommande();
                    break;
                case 2:
                    displayTableCommande();
                    break;
                case 3:
                    supprimerTableCommande();
                    break;
                case 4:
                    displayCommandeParClient();
                    break;
                case 5:
                    passerCommande();
                    break;
                case 6:
                    updateQuantitation();
                    break;
                case 0:
                    System.out.println("Bye Bye !");
                    break;
                default:
                    System.out.println("Invalid choix");
            }
        }while (choix != 0);
    }

    public static void createTableCommande(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Commande(" +
                    "idcmd int primary key auto_increment," +
                    "idclt int not null," +
                    "datcmd date," +
                    "qtecmd int," +
                    "foreign key (idclt) references Client(idclt)" +
                    ")");
            System.out.println("Table Commande created");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayTableCommande(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Commande");
            while(rs.next()){
                System.out.println(rs.getInt("idcmd") + " " +  rs.getDate("datcmd")
                        + " " + rs.getInt("qtecmd") + " " + rs.getInt("idclt"));
            }
            System.out.println("Table Commande displayed");
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void supprimerTableCommande(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn =  DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop table if exists Commande");
            System.out.println("Table Commande dropped");
            conn.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayCommandeParClient(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBcafe";
            String user = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("select * from Commande where idclt = ?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrer Id client : ");
            stmt.setInt(1, sc.nextInt());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("idcmd") + " " +  rs.getDate("datcmd")
                + " " + rs.getInt("qtecmd") + " " + rs.getInt("idclt"));
            }
            System.out.println("Table Commande displayed");
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void passerCommande(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBCafe";
            String username = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement("insert into Commande (idclt, datcmd, qtecmd) values(?,?,?)");
            Scanner sc = new Scanner(System.in);
            String choix = "oui";
            while(choix.equals("oui")){
                System.out.print("Entrer ID client : ");
                stmt.setInt(1, sc.nextInt());
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                stmt.setDate(2, date);
                System.out.print("Entrer quantité : ");
                stmt.setInt(3, sc.nextInt());
                stmt.executeUpdate();
                System.out.println("Vous voulez ajouter une autre commande (oui/non) ?");
                choix =  sc.next();
            }
            conn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateQuantitation(){
        try{
            String url = "jdbc:mysql://localhost:3306/DBCafe";
            String username = "root";
            String password = "Anas123";
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement("update Commande set qtecmd = ? where idcmd = ?");
            Scanner sc = new Scanner(System.in);
            System.out.print("Entrer ID commande : ");
            stmt.setInt(2, sc.nextInt());
            System.out.print("Entrer le nouvelle quantitation : ");
            stmt.setInt(1, sc.nextInt());
            stmt.executeUpdate();
            System.out.println("Quantité updated.");
            conn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
