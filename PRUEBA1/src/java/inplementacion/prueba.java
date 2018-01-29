/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inplementacion;

import hibernateUtil.HibernateUtil;

import interfaz.pruebaI;
import java.util.List;
import modelo.Persona;
import org.hibernate.Query;
import org.hibernate.Session;

public class prueba implements pruebaI {

    @Override
    public List<Persona> lista() {
        Session session = null;
        List<Persona> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Persona");
            lista = (List<Persona>) query.list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lista;
    }

    @Override
    public boolean insertar(Persona per) {
        Session session = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(per);
            session.getTransaction().commit();
            aux = true;

        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }

        return aux;
    }

    @Override
    public int personaEliminada(Persona per) {
        Session session = null;
        int aux = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(per);
            session.getTransaction().commit();
            aux = 1;
        } catch (Exception e) {
            aux = 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aux;
    }

    @Override
    public boolean actualizacion(Persona per) {
        Session session = null;
        boolean aux = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(per);
            System.out.println("---->"+per.getNombre());
            aux = true;
        } catch (Exception e) {
            aux = false;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return aux;
    }

}
