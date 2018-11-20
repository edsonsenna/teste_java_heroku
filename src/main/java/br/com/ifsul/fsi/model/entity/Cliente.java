/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.fsi.model.entity;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Edson
 */
public class Cliente implements Serializable{
    
    public static final String STATUS_ATIVO = "a";
    public static final String STATUS_INATIVO = "i";
    
    private Integer id;
    private String fullName;
    private String user;
    private String password;
    private String status;
    private Date creationDate;
    private String telegramChatID;
    
    public Cliente(){
        this.creationDate = new Date();
    }

    public String getStatusImg(){
        if(this.status.equals(STATUS_ATIVO)){
            return "img/status-ativo.png";
        }
        return "img/status-inativo.png";
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", fullName=" + fullName + ", user=" + user + ", password=" + password + ", status=" + status + ", creationDate=" + creationDate + '}';
    }
    
    
    
}
