
package Controlador;

import Conexion.Conexion;
import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {

    private Connection con = null;

    public InscripcionData() {

        con = Conexion.getConexion();

    }

    public void guardarInscripcion(Inscripcion inscripcion) {

        String sql = "INSERT INTO inscripcion (idAlumno, idMateria, nota) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setFloat(3, inscripcion.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción añadida con exito");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
    }

    public Inscripcion buscarInscripcionUnica(Alumno alumno, Materia materia) {
        Inscripcion inscripcion = null;
        String sql = "SELECT idInscripcion, nota FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND M.idMateria=? AND A.estado=1 AND M.estado=1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getIdAlumno());
            ps.setInt(2, materia.getIdMateria());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion")); //tambien se puede poner getInt(num de columna)
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripcion.setNota(rs.getInt("nota"));
            } else {
                JOptionPane.showMessageDialog(null, "La inscripcion no existe");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }
        return inscripcion;
    }
        
    public List<Inscripcion> buscarInscripcionAlumno(Alumno alumno) {
        
        List<Inscripcion> inscripciones = new ArrayList<>();
        
        //String sql = "SELECT idInscripcion, nota FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND M.idMateria=? AND A.estado=1 AND M.estado=1";
        try {
            String sql = "SELECT * FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND A.estado=1 AND M.estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getIdAlumno());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                Materia materia = new Materia(rs.getInt("M.idMateria"), rs.getString("M.nombre"), rs.getInt("año"), rs.getBoolean("M.estado"));
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripcion.setNota(rs.getFloat("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }
        return inscripciones;
    }
    
    public List<Inscripcion> buscarInscripcionMateria(Materia materia) {
        
        List<Inscripcion> inscripciones = new ArrayList<>();
        
        //String sql = "SELECT idInscripcion, nota FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND M.idMateria=? AND A.estado=1 AND M.estado=1";
        try {
            String sql = "SELECT * FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND M.idMateria=? AND A.estado=1 AND M.estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, materia.getIdMateria());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                Alumno alumno = new Alumno(rs.getInt("A.idAlumno"), rs.getInt("dni"), rs.getString("apellido"), rs.getString("A.nombre"), rs.getDate("fechaNacimiento").toLocalDate(), true);
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripcion.setNota(rs.getFloat("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }
        return inscripciones;
    }
    
    public void modificarInscripcion(Inscripcion inscripcion){
        
        String sql = "UPDATE inscripcion SET nota=? WHERE idInscripcion=? ";
        PreparedStatement ps = null;
        
        try{
            ps = con.prepareStatement(sql);
            ps.setFloat(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getIdInscripcion());
            int exito = ps.executeUpdate();
            
            if (exito == 1){
                JOptionPane.showMessageDialog(null, "Modificada Exitosamente.");               
            }else{
                JOptionPane.showMessageDialog(null, "La inscripcion no existe.");
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion"+ex.getMessage());
        }
        
    }
    
    public void eliminarInscripcion(int id){
        
        try{
            String sql = "DELETE FROM inscripcion WHERE idInscripcion = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila==1){
                JOptionPane.showMessageDialog(null,"Se elimino la inscripcion.");
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion."+ex.getMessage());
        }
    }
}
