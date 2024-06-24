/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.exception;

/**
 *
 * @author Marlucio // INDENTAR
 */
public class ReportException extends RuntimeException{

    public ReportException(String string) {
        super(string);
    }

    public ReportException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ReportException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ReportException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
