/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.fsi.controller;

import br.com.ifsul.fsi.model.entity.Usuario;
import br.com.ifsul.fsi.model.dao.UsuarioDAO;
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
public class UsuarioBean implements Serializable {

    final Logger logger = Logger.getLogger(UsuarioBean.class);
    
    private String welcome;
    private Usuario usuarioAtual;
    private List<Usuario> usuarioList;
    
    private final String redirect = "/protegiod/usuario.xhtml/faces-redirect=true";
    
    public String menuAction(){
        return redirect;
    }
    
    @PostConstruct
    public void init() {
        this.welcome = "Olá tudo bem?";
        this.usuarioAtual = new Usuario();
        this.usuarioList = UsuarioDAO.getInstance().getAll(Usuario.class);
        
    }

    public void salvar(){
        
        try{
            logger.info("Requisito: " + this.usuarioAtual);
            if(this.usuarioAtual.getNome().equalsIgnoreCase("")){
                message("Salvando requsito", "Falha ao tentar salvar o requisito", FacesMessage.SEVERITY_ERROR);
                return;
            }
            UsuarioDAO.getInstance().save(usuarioAtual);
            
            this.usuarioList = UsuarioDAO.getInstance().getAll(Usuario.class);
            this.usuarioAtual = new Usuario();
            message("Salvando requsito", "Requisito salvo com sucesso", FacesMessage.SEVERITY_INFO);
        } catch(Exception e){
            e.printStackTrace();
            message("Salvando requsito", "Falha ao tentar salvar o requisito", FacesMessage.SEVERITY_FATAL);
        }
        
    }
    
    public void novo(){
        usuarioAtual = new Usuario();
        
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

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

   

    @Override
    public String toString() {
        return "HelloBean{" + "logger=" + logger + ", welcome=" + welcome + ", requisitoAtual=" + usuarioAtual + ", requisitoList=" + usuarioList + '}';
    }
    
    
    
}
