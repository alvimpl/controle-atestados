/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.alvaro.atestados.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author MÁRCIA
 */
 
public interface FacesService {

    default void addMessage(String clientId, FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
    }

    default void addInfoMessage(String clientId, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        addMessage(clientId, facesMessage);
    }

    default void addErrorMessage(String clientId, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        addMessage(clientId, facesMessage);
    }
}
    

