/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package other;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 * copyright to IJSE
 * @author niroth
 */
public class MyOptionPane {
    
    private static Object message = "Do you need to change or replace existing qty?";
    private static String title = "Item is already exist";
    private static Object[] options = {"Cancel", "Replace", "Change"};;
    private static Icon icon;
    private static Object initialValue = "Cancel";
    public static final int CANCEL_OPTION = 0;
    public static final int REPLACE_OPTION = 1;
    public static final int CHANGE_OPTION = 2;
    
    private MyOptionPane() {
    }
    
    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
        MyOptionPane.message = message;
        MyOptionPane.title = title;
        MyOptionPane.options = options;
        MyOptionPane.initialValue = initialValue;
        MyOptionPane.icon = icon;
        return JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
    }
    
    public static int showOptionDialog(Component parentComponent) {
        return JOptionPane.showOptionDialog(parentComponent, message, title, JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, options, initialValue);
    }
    
}
