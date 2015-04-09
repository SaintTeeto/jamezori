/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jamezori;

import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import sql.UserDAO;

/**
 *
 * @author 5ia13jathomas
 */

@ManagedBean
@SessionScoped
public class Register {
    
    @ManagedProperty(value="#{userDAO}")
    private UserDAO user;
    
    private String userid;
    private String password;
    private String errorMessage;
    
    
    public String doRegister() throws ClassNotFoundException, SQLException{
        
        if(user.registerUser(userid, password)==false){
            errorMessage = "Der Username ist schon vergeben.";
            userid = "";
            return "register.xthml";
        }
        
        userid = "";
        errorMessage = "";
        
        return "index.html";
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public UserDAO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserDAO user) {
        this.user = user;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
