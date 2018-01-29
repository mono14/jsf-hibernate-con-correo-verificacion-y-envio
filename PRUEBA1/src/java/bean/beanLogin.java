/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import inplementacion.loginM;
import interfaz.loginI;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Login;
import org.primefaces.context.RequestContext;

/**
 *
 * @author dcontreras
 */
@ManagedBean
@ViewScoped
public class beanLogin {

    private Login log;
    public List<String> listaEdad;
    public String Usuario, pass, Nom, Tel, E,correo;

    public List<String> getListaLogin() {
        return listaEdad;
    }

    public void setListaLogin(List<String> listaLogin) {
        this.listaEdad = listaLogin;
    }

    public beanLogin() {
        log = new Login();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Login getLog() {
        return log;
    }

    public void setLog(Login log) {
        this.log = log;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getE() {
        return E;
    }

    public void setE(String E) {
        this.E = E;
    }

    public void convertir() {
        String passIngreso;
        loginI l = new loginM();
        passIngreso = l.convertir(pass);

        if (l.lista(passIngreso, Usuario)) {
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "Usuario y contraseña erroneas"));
            RequestContext.getCurrentInstance().update("tablaLogin:growl");
        }

    }

    public String insertarRegistro() {
        loginI Reg = new loginM();
        String passNew = Reg.convertir(pass);
        log.setUsuario(Usuario);
        log.setPass(passNew);
        log.setNombre(Nom);
        log.setTelefono(Tel);
        log.setEdad(E);
        log.setCorreo(correo);
        if (Reg.usuarioRepetido(Usuario)) {
            if (Reg.insertarRegistro(log)) {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "no se pudo insertar el registro"));
                RequestContext.getCurrentInstance().update("Registro:msgs");
            }
        } else {
            System.out.println("no sale el mensaje");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "usuario no disponible"));
            RequestContext.getCurrentInstance().update("Registro:msgs");
        }
         return "thanks?faces-redirect=true";
    }

    public void buttonAction(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "Registro.xhtml");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void Correo(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "correo.xhtml");
    }

    public void init() {
        listaEdad.add("1");
        listaEdad.add("2");
        System.out.println("--->" + listaEdad.size());
    }
}
