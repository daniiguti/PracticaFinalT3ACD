package Vistas;

import Controladores.ConectarBDD;
import Controladores.ControladorBDD_HIB;
import Controladores.ControladorFicheros;
import Modelos.Alumnos;
import Modelos.Cursos;
import Modelos.Examenes;
import Modelos.Matriculas;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends javax.swing.JFrame {
    //Conexion
    public static Connection conexion;
    
    //Controladores
    ControladorBDD_HIB cbdd;
    ConectarBDD conector;
    ControladorFicheros cf;
    
    //Modelos
    DefaultTableModel modeloAlumnos;
    DefaultTableModel modeloCursos;
    DefaultTableModel modeloMatriculas;
    DefaultTableModel modeloExamenes;
    
    //Arrays
    List<Alumnos> alumnos;
    List<Cursos> cursos;
    List<Matriculas> matriculas;
    List<Examenes> examenes;
    
    //Codigos necesarios
    String codAlumnoPulsado;
    String codCursoPulsado;
    short numExamenPulsado;
    int rowExamenes;
    
    public VentanaPrincipal() {
        //Nos conectamos a la base de datos
        conector = new ConectarBDD();
        try{
            conexion = conector.conectarBDD();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"No se ha podido conectar a la base de datos");
            System.exit(0);
        }
        
        //Configuracion de la ventana
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("ACADEMIA");
        
        //Inicializamos todo
        modeloAlumnos = (DefaultTableModel) JTableAlumnos.getModel();
        modeloCursos = (DefaultTableModel) JTableCursos.getModel();
        modeloMatriculas = (DefaultTableModel) JTableMatriculas.getModel();
        modeloExamenes = (DefaultTableModel) JTableExamenes.getModel();
        cbdd = new ControladorBDD_HIB();
        cf = new ControladorFicheros();
        
        //Mostramos los datos de las tablas
        actualizarTablaAlumnos();
        actualizarTablaCursos();
        
        //Listener de las tablas
        JTableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowAlumnos = JTableAlumnos.rowAtPoint(evt.getPoint()); 
                codAlumnoPulsado = (String) JTableAlumnos.getValueAt(rowAlumnos, 0);
                actualizarTablaMatriculas();
            }            
        });
        JTableCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowCursos = JTableCursos.rowAtPoint(evt.getPoint());    
                codCursoPulsado = (String) JTableCursos.getValueAt(rowCursos, 0);
            }
        });
        JTableMatriculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableMatriculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowMatriculas = JTableMatriculas.rowAtPoint(evt.getPoint());
                codAlumnoPulsado = (String) JTableMatriculas.getValueAt(rowMatriculas, 0);
                codCursoPulsado = (String) JTableMatriculas.getValueAt(rowMatriculas, 2);     
                actualizarTablaExamenes();
            }            
        });
        JTableExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rowExamenes = JTableExamenes.rowAtPoint(evt.getPoint()); 
                numExamenPulsado = (short) JTableExamenes.getValueAt(rowExamenes, 0);
                actualizarCeldasExamen();
            }            
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btActualizar = new javax.swing.JButton();
        tfFechaExamen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableAlumnos = new javax.swing.JTable();
        tfNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableCursos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btJSON = new javax.swing.JButton();
        btMatricular = new javax.swing.JButton();
        btXML = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTableMatriculas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTableExamenes = new javax.swing.JTable();
        btCrudAlumnos = new javax.swing.JButton();
        btCrudCursos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btActualizar.setText("Actualizar");
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        JTableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTableAlumnos);

        jLabel1.setText("Fecha Examen");

        JTableCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Curso", "Nombre Curso", "Nº Examenes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(JTableCursos);

        jLabel2.setText("Nota");

        btJSON.setText("Boletin JSON");
        btJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btJSONActionPerformed(evt);
            }
        });

        btMatricular.setText("Matricular Alumno en Curso");
        btMatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMatricularActionPerformed(evt);
            }
        });

        btXML.setText("Listado Matricula XML");
        btXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXMLActionPerformed(evt);
            }
        });

        JTableMatriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre Alumno", "Codigo Curso", "Nombre Curso", "Nota Media"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(JTableMatriculas);

        JTableExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Examen", "Fecha Examen", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(JTableExamenes);

        btCrudAlumnos.setText("CRUD");
        btCrudAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrudAlumnosActionPerformed(evt);
            }
        });

        btCrudCursos.setText("CRUD");
        btCrudCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCrudCursosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btXML, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btJSON, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(122, 122, 122))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfFechaExamen, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(tfNota)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btActualizar)
                                        .addGap(12, 12, 12)))
                                .addGap(64, 64, 64))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(btMatricular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(btCrudAlumnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCrudCursos)
                .addGap(243, 243, 243))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCrudAlumnos)
                    .addComponent(btCrudCursos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btMatricular)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfFechaExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(btActualizar)
                        .addGap(35, 35, 35)
                        .addComponent(btJSON)
                        .addGap(18, 18, 18)
                        .addComponent(btXML)
                        .addGap(35, 35, 35))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCrudAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrudAlumnosActionPerformed
        DialogAlumnos da = new DialogAlumnos(this, true);
        da.setVisible(true);
        actualizarTablaAlumnos();
    }//GEN-LAST:event_btCrudAlumnosActionPerformed

    private void btCrudCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCrudCursosActionPerformed
        DialogCursos dc = new DialogCursos(this, true);
        dc.setVisible(true);
        actualizarTablaCursos();
    }//GEN-LAST:event_btCrudCursosActionPerformed

    private void btMatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMatricularActionPerformed
        if(JTableAlumnos.getSelectedRowCount() > 0 && JTableCursos.getSelectedRowCount() > 0){         
            cbdd.altaMatricula(codAlumnoPulsado, codCursoPulsado);
            actualizarTablaMatriculas();
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un registro en la tabla alumnos y cursos");
        }
    }//GEN-LAST:event_btMatricularActionPerformed

    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        if(JTableExamenes.getSelectedRowCount() > 0){
            String fecha = tfFechaExamen.getText();
            String nota = tfNota.getText();
            if(fecha.equals("") == false && nota.equals("") == false){
                double notaNum = Double.parseDouble(nota);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date parsed = null;
                try {
                    parsed = dateFormat.parse(fecha);
                    cbdd.actualizarNota(codCursoPulsado, codAlumnoPulsado, numExamenPulsado, parsed, notaNum);
                    actualizarTablaExamenes();
                    actualizarTablaMatriculas();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto (dd/MM/yyyy)");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "No defe campos vacíos");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione un registro en la tabla examenes");
        }
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJSONActionPerformed
        String rutaArchivo = abrirFileChooser();
        cf.guardarArchivoJSON(rutaArchivo, codAlumnoPulsado, codCursoPulsado);
        JOptionPane.showMessageDialog(this, "Archivo JSON guardado exitosamente");
    }//GEN-LAST:event_btJSONActionPerformed

    private void btXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXMLActionPerformed
        String rutaArchivo = abrirFileChooser();
        cf.guardarArchivoXML(rutaArchivo);
        JOptionPane.showMessageDialog(this, "Archivo XML guardado exitosamente");
    }//GEN-LAST:event_btXMLActionPerformed

    //Metodos auxiliares
    private void actualizarTablaAlumnos(){
        //Limpiamos la tabla
        modeloAlumnos.setRowCount(0);
        //Obtenemos todos los Alumnos
        alumnos = cbdd.consultarTodosAlumnos();
        //Los añadimos a la tabla
        for (Alumnos aux: alumnos){
            modeloAlumnos.addRow(new Object[]{aux.getCcodalu(), aux.getCnomalu()});            
        }
    }
    private void actualizarTablaCursos(){
        //Limpiamos la tabla
        modeloCursos.setRowCount(0);
        //Obtenemos todos los Cursos
        cursos = cbdd.consultarTodosCursos();
        //Los añadimos a la tabla
        for (Cursos aux: cursos){
            modeloCursos.addRow(new Object[]{aux.getCcodcurso(), aux.getCnomcurso(), aux.getNnumexa()});            
        }
    }
    private void actualizarTablaMatriculas(){
        modeloMatriculas.setRowCount(0);        
        ArrayList<Object[]> registros = new ArrayList<>();
        registros = cbdd.consultarMatriculasAlumno(codAlumnoPulsado);
        for(int i = 0; i < registros.size(); i++){
            modeloMatriculas.addRow(registros.get(i));
        }
    }
    private void actualizarTablaExamenes(){
        modeloExamenes.setRowCount(0);
        examenes = cbdd.consultarTodosExamanes(codCursoPulsado, codAlumnoPulsado);
        for(Examenes aux: examenes){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaComoCadena = "";
            if(aux.getDfecexam() != null){
                fechaComoCadena = sdf.format(aux.getDfecexam());
            }            
            modeloExamenes.addRow(new Object[]{aux.getId().getNnumexam(), fechaComoCadena, aux.getNnotaexam()});
        }
    }
    private void actualizarCeldasExamen(){
        Examenes aux = examenes.get(rowExamenes);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = "";
        if(aux.getDfecexam() != null){
            fecha = sdf.format(aux.getDfecexam());
        }   
        tfFechaExamen.setText(fecha);
        tfNota.setText(aux.getNnotaexam() + "");
    }
    private String abrirFileChooser(){
        String rutaArchivo = "";
        int opcion = 0;
        JFileChooser jfc = null;
        //Bucle para que hasta que no seleccione un fichero no se salga del jfc
        do{
            jfc = new JFileChooser();
            opcion = jfc.showOpenDialog(this);
        }while(opcion == 1);
        //obtenemos la ruta para devolverla
        rutaArchivo = jfc.getSelectedFile().getAbsolutePath();
        return rutaArchivo;
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableAlumnos;
    private javax.swing.JTable JTableCursos;
    private javax.swing.JTable JTableExamenes;
    private javax.swing.JTable JTableMatriculas;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btCrudAlumnos;
    private javax.swing.JButton btCrudCursos;
    private javax.swing.JButton btJSON;
    private javax.swing.JButton btMatricular;
    private javax.swing.JButton btXML;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField tfFechaExamen;
    private javax.swing.JTextField tfNota;
    // End of variables declaration//GEN-END:variables
}
