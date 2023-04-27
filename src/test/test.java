/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.OperationCredit;
import entities.credit;
import java.sql.SQLException;
import service.OperationCreditService;

/**
 *
 * @author MSI
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        credit c = new credit(1);
        OperationCredit o = new OperationCredit();
       
        OperationCreditService os = new OperationCreditService();
        System.out.println(os.getAll());
        
    }
    
}
