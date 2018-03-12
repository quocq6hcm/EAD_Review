/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import models.Users;
import sb.UsersFacadeLocal;

/**
 *
 * @author quocq
 */
@Named(value = "userMB")
@SessionScoped
public class UserMB implements Serializable{

    @EJB
    private UsersFacadeLocal usersFacade;

    /**
     * Creates a new instance of UserMB
     */
    private String msg="";
    
    private String loginUsername ="";
    
    private String loginPassword ="";

    private List<models.Users> list = new ArrayList<models.Users>();

    private Users u = new Users();
 
    private String fullname = "";
   
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

   
    
    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    
   
    public Users getU() {
        return u;
    }

    public void setU(Users u) {
        this.u = u;
    }
    
    public List<Users> getUsers() {
        return list;
    }

    public void setUsers(List<Users> users) {
        
        this.list = users;
    }
    
    public List<Users> getList()
    {
        search(fullname);
        return list;
    }
    
    public UserMB() {
        
    }
    
    boolean checkLogin(String username, String password)
    {
        System.out.println("username: " + username + " password: "  + password);
        for(Users u : usersFacade.findAll())
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }
    
    public String check(String username, String password)
    {
        System.out.println("rs: " + checkLogin(username, password));
        if(checkLogin(username, password))
        {
            setMsg("");
            return "/index.xhtml?faces-redirect=true";
        }
        else
        {
          
            setMsg("invalid Login");
            System.out.println("Msg: " + msg);
            return "/login.xhtml?faces-redirect=true";
        }
    }
    
   
    
    public String save(Users u)
    {
        u.setPassword("123");
        u.setLevel((short) 3);
        u.setPhoto("sad");
       
        usersFacade.create(u);
        
        setU(new Users());
        return "/index.xhtml?faces-redirect=true";
        
    }

    
    public String edit(Users u)
    {
        usersFacade.edit(u);
        setU(new Users());
        return "/index.xhtml?faces-redirect=true";

    }
    
    
    public String findById(String username)
    {
        setU(usersFacade.find(username));
        return "edit.xhtml?faces-redirect=true";
    }
    
    public String delete(String username)
    {
        usersFacade.remove(usersFacade.find(username));
        return "/index.xhtml?faces-redirect=true";

    }
    

        public List<Users> searchByName(String fullname)
        {
            List<Users> temp = new ArrayList<>();
            for (Users e : usersFacade.findAll())
            {
                if (e.getFullname().contains(fullname))
                {
                    temp.add(e);
                }
            }

            return temp;
        }

        public void search(String name)
        {
            if (name.isEmpty())
            {
                setUsers(usersFacade.findAll());
            }
            else
            {
                setUsers(searchByName(name));
            }

        }
}
