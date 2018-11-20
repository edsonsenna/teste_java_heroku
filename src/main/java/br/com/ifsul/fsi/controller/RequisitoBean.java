/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.fsi.controller;

import br.com.ifsul.fsi.model.entity.Requisito;
import br.com.ifsul.fsi.model.dao.RequisitoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author admin
 */
@ManagedBean
@SessionScoped
public class RequisitoBean extends BaseBean  implements Serializable {

    final Logger logger = Logger.getLogger(RequisitoBean.class);
    
    private String welcome;
    private Requisito requisitoAtual;
    private List<Requisito> requisitoList;
    
    private final String redirect = "/protegiod/requisito.xhtml/faces-redirect=true";
    
    public String menuAction(){
        return redirect;
    }
    
    
    
    @PostConstruct
    public void init() {
        this.welcome = "Olá tudo bem?";
        this.requisitoAtual = new Requisito();
        this.requisitoList = RequisitoDAO.getInstance().getAll(Requisito.class);
        
    }

    public void salvar(){
        
        try{
            logger.info("Requisito: " + this.requisitoAtual);
            if(this.requisitoAtual.getDescricao().equalsIgnoreCase("")){
                message("Salvando requsito", "Falha ao tentar salvar o requisito", FacesMessage.SEVERITY_ERROR);
                return;
            }
            RequisitoDAO.getInstance().save(requisitoAtual);
            
            this.requisitoList = RequisitoDAO.getInstance().getAll(Requisito.class);
            this.requisitoAtual = new Requisito();
            message("Salvando requsito", "Requisito salvo com sucesso", FacesMessage.SEVERITY_INFO);
        } catch(Exception e){
            e.printStackTrace();
            message("Salvando requsito", "Falha ao tentar salvar o requisito", FacesMessage.SEVERITY_FATAL);
        }
        
    }
    
    public void deletarRequisito(){
        RequisitoDAO.getInstance().delete(Requisito.class, this.requisitoAtual.getUsuario());
        this.requisitoList = RequisitoDAO.getInstance().getAll(Requisito.class);
    }
    
    public void novo(){
        requisitoAtual = new Requisito();
        requisitoAtual.setUsuario("user2");
        requisitoAtual.setDataRequisito(new Date(System.currentTimeMillis()));
    }
    public String editarCliente(){
        return "cliente.xhtml";
    }
    
    public void message(String title, String detail, FacesMessage.Severity severity){
        FacesMessage message = new FacesMessage(severity, title, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public Requisito getRequisitoAtual() {
        return requisitoAtual;
    }

    public void setRequisitoAtual(Requisito requisitoAtual) {
        this.requisitoAtual = requisitoAtual;
    }

    public List<Requisito> getRequisitoList() {
        return requisitoList;
    }

    public void setRequisitoList(List<Requisito> requisitoList) {
        this.requisitoList = requisitoList;
    }

    @Override
    public String toString() {
        return "HelloBean{" + "logger=" + logger + ", welcome=" + welcome + ", requisitoAtual=" + requisitoAtual + ", requisitoList=" + requisitoList + '}';
    }
    
    
    
}
