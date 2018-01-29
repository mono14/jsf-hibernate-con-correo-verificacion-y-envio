/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import inplementacion.Cor;
import interfaz.CorreoI;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Login;
//import org.hibernate.validator.constraints.Email;

/**
 *
 * @author dcontreras
 */
@ManagedBean
@RequestScoped
public class correoBean {

    private Login log;
    private String text;
    private String password, usuario;

//    @Email(message = "must be a valid email")
    private String email;

    public correoBean() {
        log = new Login();
    }

    public Login getLog() {
        return log;
    }

    public void setLog(Login log) {
        this.log = log;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String register() {
//        return "thanks?faces-redirect=true";
//    }

    public String llego() {
        
         
        CorreoI co = new Cor();
        if (co.email(email, usuario)) {
            String a = String.valueOf(co.pass(usuario));
            co.decifrar(a);
            co.Send(email);
        } else {
            System.out.println("no existe");
        }
        return "thanks?faces-redirect=true";
    }
}
