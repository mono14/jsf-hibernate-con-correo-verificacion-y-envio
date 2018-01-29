/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.List;
import modelo.Login;

/**
 *
 * @author dcontreras
 */
public interface CorreoI {

    public boolean email(String usuario,String correo);

    public void Send(String correo);

    public String decifrar(String pass);

    public List<Login> pass(String usuario);
}
