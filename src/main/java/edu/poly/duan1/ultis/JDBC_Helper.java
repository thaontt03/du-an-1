/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.duan1.ultis;

import edu.poly.duan1.hibernateConfig.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thi Thu Thao
 */
public class JDBC_Helper {
    public static ResultSet selectTongQuat(String sql, Object... params) {
        PreparedStatement pstm=null;
        ResultSet rs=null;
        Connection con=null;
        
        try {
            try {
                con= DBContext.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i+1, params[i]);
            }
            rs = pstm.executeQuery();
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            close(con, pstm,rs);
            return null;
            //Logger.getLogger(Khoa_ripository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   
 public static void  close(Connection con, PreparedStatement pstm){
     
     try {
         pstm.close();
         con.close();
     } catch (SQLException ex) {
         ex.printStackTrace();
         //Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
 public static void  close(Connection con, PreparedStatement pstm, ResultSet rs){
     
     try {
         rs.close();
         close(con, pstm);
     } catch (SQLException ex) {
         ex.printStackTrace();
         //Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
 public static int updateTongQuat(String sql, Object...params){  
         PreparedStatement pstm=null;
         Connection con=null ;
     try {
             try {
                 con=DBContext.getConnection();
             } catch (Exception ex) {
                 Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
             }
         pstm=con.prepareStatement(sql);
         for(int i=0;i<params.length; i++){
            pstm.setObject(i+1, params[i]);
         }
         return pstm.executeUpdate();
     } catch (SQLException ex) {
        
         ex.printStackTrace();
         return 0;
         //Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
     }
     finally{
         close(con, pstm);
     }
        
     } 
    
}
