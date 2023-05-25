
package universidad;

import Conexion.Conexion;
import Controlador.AlumnoData;
import Controlador.InscripcionData;
import Controlador.MateriaData;
import Modelo.Alumno;
import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Universidad {


    public static void main(String[] args) throws SQLException {
         
        //Conexion.getConexion();

        /**************************************************************************************************/
        //   Alumnos
        /**************************************************************************************************/

      
      //  Alumno alumno = new Alumno(96332514, "Fort  ", "Ricardo", LocalDate.parse("1999-02-12"), true);
     /*     AlumnoData ad = new AlumnoData();
        // Agregar alumno
       ad.guardarAlumno(alumno);
        // Buscar alumno
        System.out.println(ad.buscarAlumno(4));
        System.out.println(ad.buscarAlumnoDni(12345678));
        // Listar alumnos
        List<Alumno> alumnos = ad.listarAlumnos();
        for (Alumno alum : alumnos) {
            System.out.println(alum);
        }
        
        // Modificar alumnos
       ad.modificarAlumno(new Alumno(4, 66666666, "Maradona", "El Diegote", LocalDate.parse("1888-05-06"), true));
        
        // Eliminar y activar alumno
        ad.eliminarAlumno(5);
        ad.activarAlumno(5);
        */
        
        /**************************************************************************************************/
        //   Materias
        /**************************************************************************************************/

        /**/
        Materia materia = new Materia("Futurologia", 3, true);
        MateriaData md = new MateriaData();
        // Agregar materia
/*        md.guardarMateria(materia);
        // Buscar materia
        System.out.println(md.buscarMateria(9));
        System.out.println(md.buscarMateriaNombre("EDA"));
        // Listar materias
        List<Materia> materias = md.listarMaterias();
        for (Materia mat : materias) {
            System.out.println(mat);
        }
        // Modificar materias
        md.modificarMateria(new Materia(1, "Matematicas 1", 1, true));
        // Eliminar y activar materia
        md.eliminarMateria(7);
*/     //md.activarMateria(7);
        /**/
        
        /**************************************************************************************************/
        //   Inscripciones
        /**************************************************************************************************/
        /*
        Materia materia = new Materia(2,"WEB 1", 1, true);
        Alumno alumno = new Alumno(5, 38497593, "Arjona", "Ricardios", LocalDate.parse("1888-05-06"), true);
        Inscripcion inscripcion = new Inscripcion(alumno, materia, 0);
        InscripcionData idt = new InscripcionData();
        // Agregar inscripcion
        idt.guardarInscripcion(inscripcion);
        // Buscar inscripcion unica
        System.out.println(idt.buscarInscripcionUnica(alumno, materia));
        // Buscar inscripciones de un alumno 
        List<Inscripcion> inscripcionesA = idt.buscarInscripcionAlumno(alumno);
        for (Inscripcion insc : inscripcionesA) {
            System.out.println(insc);
        }
        // Buscar alumnos inscriptos a una materia
        List<Inscripcion> inscripcionesM =idt.buscarInscripcionMateria(materia);
        for (Inscripcion insc : inscripcionesM) {
            System.out.println(insc);
        }
        // Modificar inscripcion
        Inscripcion inscripcionModif = new Inscripcion(7, alumno, materia, 9);
        idt.modificarInscripcion(inscripcionModif);
        // Eliminar inscripcion
        idt.eliminarInscripcion(7);
        */
        
        
        // Buscar materias no inscriptas
       
       // Alumno alumno = new Alumno(5, 38497593, "Arjona", "Ricardios", LocalDate.parse("1888-05-06"), true);
        InscripcionData idt = new InscripcionData();
       // System.out.println(idt.buscarInscripcionNoCursadas(alumno));
       
        
        List<Inscripcion> inscripciones = idt.buscarTodasInscripciones();
        
        for (Inscripcion ins : inscripciones) {
            System.out.println(ins);
        }
    }

}
