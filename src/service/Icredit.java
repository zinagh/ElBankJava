/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.credit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface Icredit {
                public boolean ajoutcredit(credit c)throws SQLException;
        public boolean updatecredit(credit c, int id) throws SQLException;
    public boolean suprimecredit(credit c)throws SQLException;
    public List<credit> getAll()throws SQLException;
    
}
