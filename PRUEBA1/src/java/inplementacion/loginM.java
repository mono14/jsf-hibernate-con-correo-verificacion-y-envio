/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inplementacion;

import hibernateUtil.HibernateUtil;
import interfaz.loginI;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import modelo.Login;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dcontreras
 */
public class loginM implements loginI {

    @Override
    public String convertir(String password) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    @Override
    public boolean insertarRegistro(Login log) {
        Session session = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
            aux = true;
        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aux;
    }

    @Override
    public boolean lista(String password, String Usuario) {

        Session session = null;
        List<Login> lista = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Login where usuario = '" + Usuario + "' and pass = '" + password + "'");
            lista = (List<Login>) query.list();
            if (lista.size() > 0) {
                aux = true;
            } else {
                aux = false;
            }
        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aux;
    }

   

    @Override
    public boolean usuarioRepetido(String Usuario) {
        Session session = null;
        boolean aux = false;
        List<Login> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Login where usuario = '" + Usuario + "'");
            lista = (List<Login>) query.list();
            if(lista.size()>0){
                aux=false;
            }else{
                aux=true;
            }
                
        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aux;
    }

  

}
