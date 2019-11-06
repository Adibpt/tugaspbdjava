/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugaspbd;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author adib
 */
public class Tugaspbd {

    static final String Jdbc_driver = "com.mysql.jdbc.Driver";
    static final String root = "jdbc:mysql://localhost/toko";
    static final String username = "root";
    static final String password = "";

    static Connection con;
    static Statement st;
    static ResultSet rs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        koneksi();
        try {
            if (login()) {
                tampildata();
                   insertdata();
            } else {
                System.out.println("salah");
            }
        } catch (Exception e) {
            e.getMessage();
        }
     
    }

    static void koneksi() {
        try {
            Class.forName(Jdbc_driver);
            con = DriverManager.getConnection(root, username, password);
            st = con.createStatement();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    static boolean login() {
        boolean cek;
        try {
            cek = false;
            String user = JOptionPane.showInputDialog("username");
            String pass = JOptionPane.showInputDialog("password");
            String sql = "select * from login where username='" + user + "'and password = '" + pass + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cek = true;
            }
            return cek;
        } catch (Exception e) {
            cek = false;
            return cek;
        }
    }

    static void tampildata() {
        try {
            String sql = "select * from pembeli";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getString(1) + " | ");
                System.out.print(rs.getString(2) + " | ");
                System.out.print(rs.getString(3) + " | ");
                System.out.println(rs.getString(4));
            }
        } catch (Exception e) {
        }
    }

    static void insertdata() {

        String id = JOptionPane.showInputDialog("id");
        String nama = JOptionPane.showInputDialog("nama");
        String alamat = JOptionPane.showInputDialog("alamat");
        String jk = JOptionPane.showInputDialog("jk");
        String sql = "insert into pembeli (id_pembeli,nama_pembeli,alamat,jk) values ('" + id + "','" + nama + "','" + alamat + "','" + jk + "')";
        try {
            st.executeUpdate(sql);
            tampildata();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
