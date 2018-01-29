/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;


import modelo.Login;

/**
 *
 * @author dcontreras
 */
public interface loginI {

    public String convertir(String password);

//    public boolean insertar(Login log);

    public boolean insertarRegistro(Login log);

    public boolean lista(String password,String Usuario);
       
    public boolean usuarioRepetido(String Usuario);
   
}
