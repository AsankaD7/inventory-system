/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import controller.IdController;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 * copyright to IJSE
 * @author niroth
 */
public class IdGenerator {
    
     public static String getNewId(String tableName, String columnName, String prefix) throws ClassNotFoundException, SQLException {
         String lastId = IdController.getLastId(tableName, columnName);
         if (lastId != null) {
             lastId = lastId.split(prefix)[1];
             int id = Integer.parseInt(lastId);
             id++;
             NumberFormat numberFormat = NumberFormat.getIntegerInstance();
             numberFormat.setMinimumIntegerDigits(3);
             numberFormat.setGroupingUsed(false);
             String formatedId = numberFormat.format(id);
             return prefix + formatedId;
         } else {
             return prefix + "001";
         }
     }
}
