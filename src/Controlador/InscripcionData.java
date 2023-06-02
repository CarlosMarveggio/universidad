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
    private AlumnoData alumnoData = new AlumnoData();

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

    public List<Materia> buscarCuarsadasAlumno(int idAlumno) {

        List<Materia> inscripcionesAlumno = new ArrayList<>();

        //String sql = "SELECT idInscripcion, nota FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND M.idMateria=? AND A.estado=1 AND M.estado=1";
        try {
            String sql = "SELECT * FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND A.estado=1 AND M.estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("M.idMateria"));
                materia.setNombre(rs.getString("M.nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("M.estado"));
                inscripcionesAlumno.add(materia);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }
        return inscripcionesAlumno;
    }

    public List<Inscripcion> buscarInscripcionesAlumno(int idAlumno) {

        List<Inscripcion> inscripcionesAlumno = new ArrayList<>();

        //String sql = "SELECT idInscripcion, nota FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND M.idMateria=? AND A.estado=1 AND M.estado=1";
        try {
            String sql = "SELECT * FROM alumno AS A, materia AS M, inscripcion AS I WHERE A.idAlumno=I.idAlumno AND M.idMateria=I.idMateria AND A.idAlumno=? AND A.estado=1 AND M.estado=1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia(rs.getInt("M.idMateria"),rs.getString("M.nombre"), rs.getInt("año"), rs.getBoolean("M.estado"));
                Alumno alumno = alumnoData.buscarAlumno(idAlumno);
                Inscripcion insc = new Inscripcion(rs.getInt("idInscripcion"), alumno, materia, rs.getFloat("nota"));
                inscripcionesAlumno.add(insc);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }
        return inscripcionesAlumno;
    }    
    
    public List<Materia> buscarInscripcionNoCursadas(int idAlumno) {

        List<Materia> noInscriptas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM materia WHERE idMateria NOT IN\n"
                    + "(SELECT idMateria FROM inscripcion WHERE idAlumno = ?) AND estado = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();

                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                noInscriptas.add(materia);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }

        return noInscriptas;
    }

    public List<Inscripcion> buscarTodasInscripciones() {

        List<Inscripcion> inscripciones = new ArrayList<>();

        try {
            String sql = "SELECT * \n"
                    + "FROM alumno AS A, materia AS M, inscripcion AS I \n"
                    + "WHERE A.idAlumno=I.idAlumno \n"
                    + "AND M.idMateria=I.idMateria";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Materia materia = new Materia(rs.getInt("M.idMateria"), rs.getString("M.nombre"), rs.getInt("año"), rs.getBoolean("M.estado"));
                Alumno alumno = new Alumno(rs.getInt("A.idAlumno"), rs.getInt("dni"), rs.getString("apellido"), rs.getString("A.nombre"), rs.getDate("fechaNacimiento").toLocalDate(), true);
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripcion.setNota(rs.getFloat("nota"));
                inscripciones.add(inscripcion);
            }
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

            while (rs.next()) {
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

    public void modificarInscripcion(int idAlumno, int idMateria, float nota) {

        String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=? ";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setFloat(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificada Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La inscripcion no existe.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion" + ex.getMessage());
        }

    }

    public void eliminarInscripcion(int idAlumno, int idMateria) {

        try {
            String sql = "DELETE FROM inscripcion WHERE idAlumno = ? and idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int fila = ps.executeUpdate();
            ps.close();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino la inscripcion.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion." + ex.getMessage());
        }
    }
}
