package Vista;

import Controlador.AlumnoData;
import Controlador.InscripcionData;
import Modelo.Alumno;
import Modelo.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SE31452
 */
public class FormInscripciones extends javax.swing.JInternalFrame {

    private InscripcionData inscData = new InscripcionData();
    private AlumnoData alumData = new AlumnoData();
    private DefaultTableModel modelo = new DefaultTableModel();
    
    public FormInscripciones() {
        initComponents();
        cargarAlumnos();
        armarCabecera();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInscTitulo = new javax.swing.JLabel();
        lblInscSeleccionarAlumno = new javax.swing.JLabel();
        cmbInscAlumnos = new javax.swing.JComboBox<>();
        lblInscListaMaterias = new javax.swing.JLabel();
        rdbtnMateriasInsc = new javax.swing.JRadioButton();
        rdbtnMateriasNoInsc = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbInscripciones = new javax.swing.JTable();
        btnInscribir = new javax.swing.JButton();
        btnAnularInsc = new javax.swing.JButton();
        btnInscSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        lblInscTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblInscTitulo.setText("Formulario de Inscripción");

        lblInscSeleccionarAlumno.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblInscSeleccionarAlumno.setText("Seleccione un Alumno");

        cmbInscAlumnos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblInscListaMaterias.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblInscListaMaterias.setText("Listado de Materias");

        rdbtnMateriasInsc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnMateriasInsc.setText("Materias Inscriptas");
        rdbtnMateriasInsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnMateriasInscActionPerformed(evt);
            }
        });

        rdbtnMateriasNoInsc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnMateriasNoInsc.setText("Materias no Inscriptas");
        rdbtnMateriasNoInsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnMateriasNoInscActionPerformed(evt);
            }
        });

        tbInscripciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbInscripciones);

        btnInscribir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnInscribir.setText("Inscribir");
        btnInscribir.setEnabled(false);

        btnAnularInsc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAnularInsc.setText("Anular Inscripción");
        btnAnularInsc.setEnabled(false);

        btnInscSalir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnInscSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(lblInscTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(lblInscListaMaterias)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInscSeleccionarAlumno)
                            .addComponent(rdbtnMateriasInsc))
                        .addGap(147, 147, 147)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbtnMateriasNoInsc)
                            .addComponent(cmbInscAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInscribir)
                                .addGap(115, 115, 115)
                                .addComponent(btnAnularInsc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInscSalir)))))
                .addGap(0, 51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInscTitulo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInscSeleccionarAlumno)
                    .addComponent(cmbInscAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblInscListaMaterias)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnMateriasInsc)
                    .addComponent(rdbtnMateriasNoInsc))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInscribir)
                    .addComponent(btnAnularInsc)
                    .addComponent(btnInscSalir))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbtnMateriasNoInscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnMateriasNoInscActionPerformed
        rdbtnMateriasInsc.setSelected(false);
        btnAnularInsc.setEnabled(false);
        btnInscribir.setEnabled(true);
        llenarTabla();
    }//GEN-LAST:event_rdbtnMateriasNoInscActionPerformed

    private void rdbtnMateriasInscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnMateriasInscActionPerformed
        rdbtnMateriasNoInsc.setSelected(false);
        btnAnularInsc.setEnabled(true);
        btnInscribir.setEnabled(false);
        llenarTabla();
    }//GEN-LAST:event_rdbtnMateriasInscActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularInsc;
    private javax.swing.JButton btnInscSalir;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JComboBox<Alumno> cmbInscAlumnos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInscListaMaterias;
    private javax.swing.JLabel lblInscSeleccionarAlumno;
    private javax.swing.JLabel lblInscTitulo;
    private javax.swing.JRadioButton rdbtnMateriasInsc;
    private javax.swing.JRadioButton rdbtnMateriasNoInsc;
    private javax.swing.JTable tbInscripciones;
    // End of variables declaration//GEN-END:variables

    private void cargarAlumnos() {
        List<Alumno> alumnos = alumData.listarAlumnos();
        for (Alumno alumno : alumnos) {
            cmbInscAlumnos.addItem(alumno);
        }
    }

    private void armarCabecera() {
        ArrayList<Object> titulos = new ArrayList<>();
        titulos.add("ID");
        titulos.add("Nombre");
        titulos.add("Año");
        for (Object titulo : titulos) {
            modelo.addColumn(titulo);
        }
        tbInscripciones.setModel(modelo);
    }
    
    private void llenarTabla(){
        Alumno almSelecionado = (Alumno) cmbInscAlumnos.getSelectedItem();
        if(rdbtnMateriasNoInsc.isSelected()){
            List<Materia> materias = inscData.buscarInscripcionNoCursadas(almSelecionado.getIdAlumno());
            for (Materia mat : materias) {
                modelo.addRow(new Object[]{mat.getIdMateria(), mat.getNombre(), mat.getAnio()});
            }
        }else{
            List<Materia> materias = inscData.buscarInscripcionesAlumno(almSelecionado.getIdAlumno());
            for (Materia mat : materias) {
                modelo.addRow(new Object[]{mat.getIdMateria(), mat.getNombre(), mat.getAnio()});
            }
        }
        
    }
    
}
