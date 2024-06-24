/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped

/**
 *
 * @author MÁRCIA
 */

public class FacesContextService implements FacesService {

    @Override
    public void addMessage(String clientId, FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
    }

    @Override
    public void addInfoMessage(String clientId, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        addMessage(clientId, facesMessage);
    }

    @Override
    public void addErrorMessage(String clientId, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        addMessage(clientId, facesMessage);
    }
}
