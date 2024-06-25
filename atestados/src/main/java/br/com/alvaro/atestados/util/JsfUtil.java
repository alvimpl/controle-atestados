/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.util;

import java.io.File;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marlucio  // INDENTAR
 */
public class JsfUtil {

    protected static StringBuilder pathWeb() {
        FacesContext context = FacesContext.getCurrentInstance();
        return new StringBuilder(context.getExternalContext().getRealPath(""));
    }

    public static File carregaJasper(String nomeJasper) {
        StringBuilder path = pathWeb();
        path.append(File.separator);
        path.append("resources");
        path.append(File.separator);
        path.append("relatorios");
        path.append(File.separator);
        path.append(nomeJasper);
        path.append(".jasper");
        return new File(path.toString());
    }
}
