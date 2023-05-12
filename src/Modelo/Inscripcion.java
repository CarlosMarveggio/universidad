

package Modelo;

/**
 *
 * @author SE31452
 */
public class Inscripcion {
    private int id_inscripcion;
    private Alumno alumno;
    private Materia materia;
    private double nota;

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(int id_inscripcion, Alumno alumno, Materia materia, double nota) {
        this.id_inscripcion = id_inscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }
    
    
    
}
