/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.fsi.controller;


import br.com.ifsul.fsi.model.entity.Cliente;
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
 * @author Edson
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable{
    
    final Logger logger = Logger.getLogger(ClienteBean.class);
    
    private Cliente clienteAtual;
    private List<Cliente> clienteList;
    
    @PostConstruct
    public void init() {
        this.clienteAtual = new Cliente();
        this.clienteList = new ArrayList<Cliente>();
    }
    
    
    public void salvar(){
        
        try{
            logger.info("Cliente: " + this.clienteAtual);
            if(this.clienteAtual.getUser().equalsIgnoreCase("")){
                message("Salvando Cliente", "Falha ao tentar salvar o cliente", FacesMessage.SEVERITY_ERROR);
                return;
            }
            this.clienteAtual.setCreationDate(new Date());
            this.clienteList.add(clienteAtual);
            this.clienteAtual = new Cliente();
            message("Salvando Cliente", "Cliente Salvo com Sucesso", FacesMessage.SEVERITY_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message("Salvando Cliente", "Falha ao tentar salvar o cliente", FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void message(String title, String detail, FacesMessage.Severity severity){
        FacesMessage message = new FacesMessage(severity, title, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public String toString() {
        return "ClienteBean{" + "logger=" + logger + ", clienteAtual=" + clienteAtual + ", clienteList=" + clienteList + '}';
    }
    
    
    
    
    
    
    
}
