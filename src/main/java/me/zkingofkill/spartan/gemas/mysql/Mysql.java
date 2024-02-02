package me.zkingofkill.spartan.gemas.mysql;

import me.zkingofkill.spartan.gemas.SpartanGemas;
import me.zkingofkill.spartan.gemas.objects.Gemas;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mysql {
    private String tabela;
    private Connection con;
    public Mysql(){
        this.tabela = SpartanGemas.getInstance().getConfig().getString("mysql.table");
    }
    private Connection openCon() {
        try {
            String password = SpartanGemas.getInstance().getConfig().getString("mysql.password");
            String user = SpartanGemas.getInstance().getConfig().getString("mysql.user");
            String host = SpartanGemas.getInstance().getConfig().getString("mysql.host");
            int port = SpartanGemas.getInstance().getConfig().getInt("mysql.port");
            String database = SpartanGemas.getInstance().getConfig().getString("mysql.database");
            String type = "jdbc:mysql://";
            String url = type + host + ":" + port + "/" + database;
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("[SpartanGemas] Ocorreu um erro ao tentar abrir conexão com o banco de dados: ");
            e.printStackTrace();
        }
        return null;
    }

    public void setup() {
        try {
            con = openCon();
            PreparedStatement st = con.prepareStatement("CREATE TABLE IF NOT EXISTS " + tabela+"(`Player` VARCHAR(25) NOT NULL,`Gemas` DOUBLE NOT NULL);");
            st.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("[SpartanGemas] Ocorreu um erro ao tentar criar a tabela: ");
            e.printStackTrace();
        }
    }
    public Double getGemas(OfflinePlayer player){
        try {
            con = openCon();
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabela + " WHERE Player = ?;");
            st.setString(1, player.getName());
            ResultSet rs = st.executeQuery();
            double gemas = 0.0;
            while(rs.next()){
                gemas = rs.getDouble("Gemas");
            }
            con.close();
            return gemas;

        } catch (Exception e) {
            System.out.println("[SpartanGemas] Ocorreu um erro ao tentar verificar o cargo do jogador: ");
            e.printStackTrace();
        }

        return null;
    }
    public void loadGemas(){
        try {
            con = openCon();
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabela);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                OfflinePlayer player = Bukkit.getOfflinePlayer(rs.getString("Player"));
                double gemas = rs.getDouble("Gemas");
                Gemas gemaobj = new Gemas(gemas, player);
                SpartanGemas.getInstance().getCache().getGemas().add(gemaobj);
            }
            con.close();

        } catch (Exception e) {
            System.out.println("[SpartanGemas] Ocorreu um erro ao tentar verificar o cargo do jogador: ");
            e.printStackTrace();
        }
    }
    public void insertGema(Gemas gemas) {
        try {
            con = openCon();
            PreparedStatement insert = con.prepareStatement("INSERT INTO " + tabela + "(Player,Gemas) VALUES (?,?);");
            insert.setString(1, gemas.getPlayer().getName());
            insert.setDouble(2, gemas.getGemas());
            insert.execute();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateGema(Gemas gemas) {
        try {
            con = openCon();
            PreparedStatement insert = con.prepareStatement("UPDATE " + tabela + " SET Gemas = ? WHERE Player = ?;");
            insert.setString(2, gemas.getPlayer().getName());
            insert.setDouble(1, gemas.getGemas());
            insert.execute();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean hasGemas(OfflinePlayer player){
        try {
            con = openCon();
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabela+ " WHERE Player = ?;");
            st.setString(1, player.getName());
            ResultSet rs = st.executeQuery();
           if(rs.next()){
               return true;
            }
            con.close();

        } catch (Exception e) {
            System.out.println("[SpartanGemas] Ocorreu um erro ao tentar verificar as gemas do jogador: ");
            e.printStackTrace();
        }
        return false;
    }
}