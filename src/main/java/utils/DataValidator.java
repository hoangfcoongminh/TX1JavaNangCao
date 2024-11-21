/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils;

import java.awt.Color;
import javax.swing.*;


/**
 *
 * @author hoang
 */
public class DataValidator {
    public static void ValidateTextBox(JTextField txtF, StringBuilder sb, String errorMess){
        if(txtF.getText().equals("")){
            sb.append(errorMess);
            txtF.setBackground(Color.red);
        } else {
            txtF.setBackground(Color.white);
        }
    }
    public static void ValidateButtonGroup(ButtonGroup btnGr, StringBuilder sb, String errorMess){
        ButtonModel tmp = btnGr.getSelection();
        if(tmp == null) {
            sb.append(errorMess);
        }
    }
}
