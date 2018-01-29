/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import inplementacion.prueba;
import interfaz.pruebaI;
import static java.lang.String.valueOf;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Persona;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
/**
 *
 * @author dcontreras el bean es el controlador para que encuentre los metodos y
 * los implemente y se los pase despues a la interfaz
 *
 */
@ManagedBean
@ViewScoped
public class NewJSFManagedBean {
    private Persona per;
    public List<Persona> persona;
    public int idUsuario;
    public String NUsuario;
    public int Edad;

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
    public String getNUsuario() {
        return NUsuario;
    }

    public void setNUsuario(String NUsuario) {
        this.NUsuario = NUsuario;
    }

  
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
       
    public NewJSFManagedBean() {
        per = new Persona();
    }
    public Persona getPer() {
        return per;
    }
    public void setPer(Persona per) {
        this.per = per;
    }
    public List<Persona> getPersona() {
        pruebaI generar=new prueba();
        persona=generar.lista();
        return persona;
    }
    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }
    public void boton() {
        pruebaI datoinsertado = new prueba();
        if (datoinsertado.insertar(per)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Perrito Insertado Exitosamente"));
            RequestContext.getCurrentInstance().update("panel2:msgs5");
        }
    }

    public void botonEliminar(String ide) {
        per.setId(Integer.parseInt(ide));
        pruebaI datoeliminado = new prueba();
        if (datoeliminado.personaEliminada(per) != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Persona eliminada Exitosamente"));
            RequestContext.getCurrentInstance().update("myform:msgs");
        }
    }
    
      public void onRowEdit(RowEditEvent event) {
           Persona c=new Persona();
//        FacesMessage msg = new FacesMessage("Car Edited", valueOf(((Persona) event.getObject()).getId()));
//          System.out.println("1236654--->"+((Persona) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion!", "Datos Actualizados"));
        RequestContext.getCurrentInstance().update("myform:msgs");
        pruebaI actualizar=new prueba();
        idUsuario=((Persona) event.getObject()).getId();
        c.setId(idUsuario);
          if (NUsuario!=null) {
              c.setNombre(NUsuario);
          }
          if(Edad!=0){
              c.setEdad(Edad);
          }
          System.out.println("--->"+c.getNombre()+"--->"+c.getEdad()+" ID-->"+c.getId());
          actualizar.actualizacion(c);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", valueOf(((Persona) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         System.out.println("--->"+newValue);
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
//
//    public void generarinformacion() {
//
//        pruebaI seleccionar = new prueba();
//        seleccionar.lista();
//        persona = seleccionar.lista();
//
//    }
//
//    public void onRowSelect(SelectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Selected", String.valueOf(((Persona) event.getObject()).getId()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        String a = String.valueOf(((Persona) event.getObject()).getId());
//        System.out.println("" + a);
//    }
//
//    public void onRowUnselect(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Unselected", String.valueOf(((Persona) event.getObject()).getId()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//}
