/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import java.util.List;
import modelo.Persona;

/**
 *
 * @author dcontreras
 */
public interface pruebaI {
    public List<Persona> lista();
    public boolean insertar(Persona per);
    public int personaEliminada(Persona per);
    public boolean actualizacion(Persona per);
    
}
