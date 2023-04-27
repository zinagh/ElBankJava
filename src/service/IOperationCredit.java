/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.OperationCredit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface IOperationCredit {
                    public boolean ajoutOperationCredit(OperationCredit O)throws SQLException;
        public boolean updateOperationCredit(OperationCredit O)throws SQLException;
    public boolean suprimeOperationCredit(OperationCredit O)throws SQLException;
    public List<OperationCredit> getAll()throws SQLException;
    
    
}
