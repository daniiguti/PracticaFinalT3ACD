package Vistas;

import Controladores.ControladorBDD_HIB;
import Controladores.NewHibernateUtil;
import Modelos.Cursos;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.hibernate.Query;
import org.hibernate.Session;

public class DialogCursos extends javax.swing.JDialog {
    
    DefaultTableModel modelo;
    String codCursoPulsado = "";
    List<Cursos> cursos;    
    ControladorBDD_HIB cbdd;
    
    public DialogCursos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("CURSOS");
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) JTableCursos.getModel();
        cbdd = new ControladorBDD_HIB();
        
        //Listener JTable, nos devuelve el pulsado real, aunque filtremos
        JTableCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowAlumnos = JTableCursos.rowAtPoint(evt.getPoint()); 
                int modelRow = JTableCursos.convertRowIndexToModel(rowAlumnos);
                codCursoPulsado = cursos.get(modelRow).getCcodcurso();
                tfCodCur.setText(cursos.get(modelRow).getCcodcurso());
                tfCodCur.setEnabled(false);
                tfNomCur.setText(cursos.get(modelRow).getCnomcurso());
                short numExa = cursos.get(modelRow).getNnumexa();
                String numExaSt = String.valueOf(numExa);
                tfNumExa.setText(numExaSt);
            }            
        });
        
        //Para añadir al combobox las columnas de la tabla
        for(int i = 0; i < JTableCursos.getColumnCount(); i++){
            cbCursos.addItem(JTableCursos.getColumnName(i).toString());
        }
        
        //cargamos los datos de la bdd
        actualizarTablaCursos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btAnadir = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btConsulta = new javax.swing.JButton();
        cbCursos = new javax.swing.JComboBox<>();
        tfConsulta = new javax.swing.JTextField();
        btAceptar = new javax.swing.JButton();
        tfCodCur = new javax.swing.JTextField();
        tfNomCur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTableCursos = new javax.swing.JTable();
        tfNumExa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        btAnadir.setText("Añadir");
        btAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnadirActionPerformed(evt);
            }
        });

        btModificar.setText("Modificar");
        btModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarActionPerformed(evt);
            }
        });

        btConsulta.setText("Consulta");
        btConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultaActionPerformed(evt);
            }
        });

        btAceptar.setText("ACEPTAR");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo Curso");

        jLabel2.setText("Nombre Curso");

        jLabel3.setText("(Pulse consulta sin nada escrito para volver a ver todos los alumnos)");

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

        jLabel4.setText("Nº Examenes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btConsulta)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btAnadir)
                        .addGap(18, 18, 18)
                        .addComponent(btModificar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNomCur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfCodCur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNumExa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAceptar)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfCodCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomCur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNumExa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAnadir)
                            .addComponent(btModificar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btConsulta)
                            .addComponent(tfConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(btAceptar)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Eliminar
    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        //Comprobamos que ha seleccionado al menos uno
        if(JTableCursos.getSelectedRowCount() > 0){
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar?");
            if(opcion == 0){
                //Puede dar excepcion al intentar eliminar un curso que ya este en la tabla matriculas
                try{
                    //Eliminamos el curso de la bdd
                    cbdd.eliminarCurso(codCursoPulsado);
                    //Actualizamos la tabla
                actualizarTablaCursos();
                }catch(org.hibernate.exception.ConstraintViolationException ex){ 
                    JOptionPane.showMessageDialog(this, "Curso registrado en matriculas, imposible de eliminar");
                }    
                //Limpiamos los campos
                limpiarCampos();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Pinche un curso en la tabla para eliminarlo");
        }
        tfCodCur.setEnabled(true);
    }//GEN-LAST:event_btEliminarActionPerformed
    //Consulta
    private void btConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultaActionPerformed
        tfCodCur.setEnabled(true);
        //Selecciona por que columna quiere buscar
        int pulsado = cbCursos.getSelectedIndex();
        if(pulsado >= 0){
            //Aplico los filtros
            String filtrar = tfConsulta.getText();
            TableRowSorter modeloOrdenado = new TableRowSorter<TableModel>(modelo);
            JTableCursos.setRowSorter(modeloOrdenado);
            modeloOrdenado.setRowFilter(RowFilter.regexFilter(filtrar, pulsado));
            //Limpio los campos
            limpiarCampos();
        }    
        else{
            JOptionPane.showMessageDialog(this, "Seleccione un elemento del combo box para saber por cual quiere filtrar");
        }
    }//GEN-LAST:event_btConsultaActionPerformed
    //Añadir
    private void btAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnadirActionPerformed
        //Obtenemos los datos de los textField
        String codCur = tfCodCur.getText();
        String nomCur = tfNomCur.getText();
        String numExam = tfNumExa.getText();
        //Comprobamos que no ha dejado cosas vacías
        if(codCur.equals("") == false && nomCur.equals("") == false && numExam.equals("") == false){
            //Puede dar excepcion de PK violated si el usuario introduce un codCurso que ya existia
            try{    
                //Añadimos
                cbdd.anadirCurso(codCur, nomCur, numExam);
                //Actualizamos la tabla
                actualizarTablaCursos();
                //Limpio los campos
                limpiarCampos();
            }catch(org.hibernate.exception.ConstraintViolationException ex){
                JOptionPane.showMessageDialog(this, "Ya existe un curso con ese codigo");
            }    
        }    
        else{
            JOptionPane.showMessageDialog(this, "No deje campos vacíos");
        }
        
    }//GEN-LAST:event_btAnadirActionPerformed
    //Modificar
    private void btModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarActionPerformed
        //Comprobamos que ha seleccionado al menos uno
        if(JTableCursos.getSelectedRowCount() > 0){
            //Obtenemos los datos de los textField
            String codCur = tfCodCur.getText();
            String nomCur = tfNomCur.getText();
            String numExam = tfNumExa.getText();
            //Comprobamos que no ha dejado cosas vacías
            if(codCur.equals("") == false && nomCur.equals("") == false && numExam.equals("") == false){
                //Modificamos el curso en la bdd
                cbdd.modificarCurso(codCur, nomCur, numExam);
                //Actualizamos la tabla
                actualizarTablaCursos();
                //Limpiamos campos
                limpiarCampos();
                tfCodCur.setEnabled(true);
            }    
            else{
                JOptionPane.showMessageDialog(this, "No deje campos vacíos");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Pinche un curso en la tabla para modificarlo");
        }
    }//GEN-LAST:event_btModificarActionPerformed
    //Aceptar
    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
        //Cerramos el dialog
        this.dispose();
    }//GEN-LAST:event_btAceptarActionPerformed

    //Métodos auxiliares
    public void actualizarTablaCursos(){
        //Limpiamos la tabla
        modelo.setRowCount(0);
        //Obtenemos todos los Cursos
        cursos = cbdd.consultarTodosCursos();
        //Los añadimos a la tabla
        for (Cursos aux: cursos){
            modelo.addRow(new Object[]{aux.getCcodcurso(), aux.getCnomcurso(), aux.getNnumexa()});            
        }
    }
    public void limpiarCampos(){
        tfCodCur.setText("");
        tfNomCur.setText("");
        tfConsulta.setText("");
        tfNumExa.setText("");
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
            java.util.logging.Logger.getLogger(DialogCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogCursos dialog = new DialogCursos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableCursos;
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btAnadir;
    private javax.swing.JButton btConsulta;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btModificar;
    private javax.swing.JComboBox<String> cbCursos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tfCodCur;
    private javax.swing.JTextField tfConsulta;
    private javax.swing.JTextField tfNomCur;
    private javax.swing.JTextField tfNumExa;
    // End of variables declaration//GEN-END:variables
}
