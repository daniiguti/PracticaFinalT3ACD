package Controladores;

import Modelos.Alumnos;
import Modelos.Cursos;
import Modelos.Examenes;
import Modelos.ExamenesId;
import Modelos.Matriculas;
import Modelos.MatriculasId;
import Vistas.VentanaPrincipal;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

public class ControladorBDD_HIB {
    
    //Alumnos
    public void eliminarAlumno(String codAluPulsado)throws org.hibernate.exception.ConstraintViolationException{
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Alumnos aux = (Alumnos) ss.get(Alumnos.class, codAluPulsado);
        ss.beginTransaction();
        ss.delete(aux);
        ss.getTransaction().commit();
        ss.close();
    }
    public void anadirAlumno(String codAlu, String nomAlu) throws org.hibernate.exception.ConstraintViolationException{
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        Alumnos aux = new Alumnos(codAlu, nomAlu);
        ss.save(aux); 
        ss.getTransaction().commit();
        ss.close();
    }
    public void modificarAlumno(String codAlu, String nomAlu){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Alumnos aux = (Alumnos) ss.get(Alumnos.class, codAlu);
        aux.setCcodalu(codAlu);
        aux.setCnomalu(nomAlu);
        ss.beginTransaction();
        ss.update(aux);
        ss.getTransaction().commit();
        ss.close();
    }
    public List<Alumnos> consultarTodosAlumnos(){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        List<Alumnos> alumnos = ss.createCriteria(Alumnos.class).list();
        ss.getTransaction().commit();
        ss.close();
        /*
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Alumnos as A";
        Query query = ss.createQuery(hql);
        List<Alumnos> alumnos = query.list();
        ss.close();*/
        return alumnos;
    }
    
    //Cursos
     public void eliminarCurso(String codCursoPulsado) throws org.hibernate.exception.ConstraintViolationException{
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Cursos aux = (Cursos) ss.get(Cursos.class, codCursoPulsado);
        ss.beginTransaction();
        ss.delete(aux);
        ss.getTransaction().commit();
        ss.close();
    }
    public void anadirCurso(String codCur, String nomCur, String numExam) throws org.hibernate.exception.ConstraintViolationException{
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        Cursos aux = new Cursos(codCur, nomCur, Short.parseShort(numExam));
        ss.save(aux); 
        ss.getTransaction().commit();
        ss.close();
    }
    public void modificarCurso(String codCur, String nomCur, String numExam){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        Cursos aux = (Cursos) ss.get(Cursos.class, codCur);
        aux.setCcodcurso(codCur);
        aux.setCnomcurso(nomCur);
        aux.setNnumexa(Short.parseShort(numExam));
        ss.beginTransaction();
        ss.update(aux);
        ss.getTransaction().commit();
        ss.close();
    }
    public List<Cursos> consultarTodosCursos(){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        List<Cursos> cursos = ss.createCriteria(Cursos.class).list();
        ss.getTransaction().commit();
        ss.close();
        /*Session ss = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Cursos as C";
        Query query = ss.createQuery(hql);
        List<Cursos> cursos = query.list();
        ss.close();*/
        return cursos;
    }
    
    //Matriculas
    public List<Matriculas> consultarMatriculas(String ccodAlu){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Matriculas as M where cCodAlu = '" + ccodAlu + "'";
        Query query = ss.createQuery(hql);
        List<Matriculas> matriculas = query.list();
        ss.close();
        return matriculas;
    }
    public List<Matriculas> consultarTodasMatriculas(){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Matriculas as M";
        Query query = ss.createQuery(hql);
        List<Matriculas> matriculas = query.list();
        //ss.close();
        return matriculas;
    }
    //para matricular un alumno en un curso a través de un procedimiento (tema 2)
    public void altaMatricula(String codAlu, String codCurso){
        int codError = -1;        
        String sentenciaSQL = "{call sp_AltaMatricula(?, ?, ?)}";
        try {            
            CallableStatement sentencia = VentanaPrincipal.conexion.prepareCall(sentenciaSQL);
            sentencia.setString(1, codAlu);
            sentencia.setString(2, codCurso);
            sentencia.registerOutParameter(3, Types.INTEGER);
            sentencia.executeUpdate();
            codError = sentencia.getInt(3);  
            if(codError == -1){
                JOptionPane.showMessageDialog(null, "Este alumno ya esta matriculado en este curso");
            }
            else{
                sentencia.close();
            }    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    //para consultar las matriculas de un alumno
    public ArrayList<Object[]> consultarMatriculasAlumno(String codAlumno){
        //Nos creamos el arraylist que vamos a devolver
        ArrayList<Object[]> registros = new ArrayList<>();
        //Nos creamos la sesión
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        //Buscamos el alumno a través del codAlumno que nos han pasado
        Alumnos alumnoAux = (Alumnos) ss.get(Alumnos.class, codAlumno);
        //Obtenemos las matriculas de ese alumno
        ArrayList<Matriculas> matriculas = alumnoAux.getListMatriculas();
        //Recorremos el arraylist devuelto, añadiendo a nuestro arraylist que vamos
        //a devolver la informacion que nos interesa
        for(Matriculas aux: matriculas){
            registros.add(new Object[]{aux.getId().getCcodalu(), 
                                       alumnoAux.getCnomalu(), 
                                       aux.getId().getCcodcurso(),
                                       aux.getCursos().getCnomcurso(),
                                       aux.getNnotamedia()
            });
        }
        //cerramos la sesion
        ss.close();
        //devolvemos el arrayList con la información
        return registros;
    } 
    
    //Examenes
    public List<Examenes> consultarTodosExamanes(String codCurso, String codAlu){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Examenes as E where cCodAlu = '" + codAlu + "' AND cCodCurso = '" + codCurso + "'";
        Query query = ss.createQuery(hql);
        List<Examenes> examenes = query.list();
        ss.close();
        return examenes;
    }
    public void actualizarNota(String codCurso, String codAlu, int numExam, Date dfecexam, double nota){
        Session ss = NewHibernateUtil.getSessionFactory().openSession();
        //Obtengo el examen que se pulso
        ExamenesId eid = new ExamenesId(codAlu, codCurso, (short)numExam);
        Examenes auxExam = (Examenes) ss.get(Examenes.class, eid);
        //Le cambio la fecha y la nota
        auxExam.setDfecexam(dfecexam);
        BigDecimal bd = new BigDecimal(nota);
        auxExam.setNnotaexam(bd);
        //Obtengo la matricula asociada a ese examen
        Matriculas auxMatri = auxExam.getMatriculas();
        //Calculo la nota media (con la nota del examen ya cambiada)
        BigDecimal bd2 = auxMatri.calcularNotaMedia();
        short notaMedia = bd2.shortValue();
        //Cambio la nota media
        auxMatri.setNnotamedia(notaMedia);
        //Le doy al examen la matricula
        auxExam.setMatriculas(auxMatri);
        ss.beginTransaction();
        //Al tener cascade all, tambien se actualizara la tabla de matriculas
        ss.update(auxExam);
        ss.getTransaction().commit();
        ss.close();
    }
}
