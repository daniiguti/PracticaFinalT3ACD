package Vistas;

import Controladores.ControladorBDD_HIB;
import Controladores.NewHibernateUtil;
import Modelos.Alumnos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.hibernate.Query;
import org.hibernate.Session;

public class DialogAlumnos extends javax.swing.JDialog {
    
    DefaultTableModel modelo;
    String codAluPulsado = "";
    List<Alumnos> alumnos;
    ControladorBDD_HIB cbdd;
    
    public DialogAlumnos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("ALUMNOS");
        this.setLocationRelativeTo(null);
        modelo = (DefaultTableModel) JTableAlumnos.getModel();
        cbdd = new ControladorBDD_HIB();
        
        //Listener JTable, nos devuelve el pulsado real, aunque filtremos
        JTableAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int rowAlumnos = JTableAlumnos.rowAtPoint(evt.getPoint()); 
                int modelRow = JTableAlumnos.convertRowIndexToModel(rowAlumnos);
                codAluPulsado = alumnos.get(modelRow).getCcodalu();
                tfCodAlu.setText(alumnos.get(modelRow).getCcodalu());
                tfCodAlu.setEnabled(false);
                tfNomAlu.setText(alumnos.get(modelRow).getCnomalu());
            }            
        });
        
        //Para añadir al combobox las columnas de la tabla
        for(int i = 0; i < JTableAlumnos.getColumnCount(); i++){
            cbAlumnos.addItem(JTableAlumnos.getColumnName(i).toString());
        }
        
        //cargamos los datos de la bdd
        actualizarTablaAlumnos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableAlumnos = new javax.swing.JTable();
        btEliminar = new javax.swing.JButton();
        btAnadir = new javax.swing.JButton();
        btModificar = new javax.swing.JButton();
        btConsulta = new javax.swing.JButton();
        cbAlumnos = new javax.swing.JComboBox<>();
        tfConsulta = new javax.swing.JTextField();
        btAceptar = new javax.swing.JButton();
        tfCodAlu = new javax.swing.JTextField();
        tfNomAlu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel1.setText("Codigo Alumno");

        jLabel2.setText("Nombre Alumno");

        jLabel3.setText("(Pulse consulta sin nada escrito para volver a ver todos los alumnos)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAceptar)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btConsulta)
                            .addComponent(btEliminar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfNomAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(26, 26, 26)
                                                .addComponent(tfCodAlu, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(31, 31, 31))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btAnadir)
                                        .addGap(29, 29, 29)
                                        .addComponent(btModificar)
                                        .addGap(16, 16, 16))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbAlumnos, 0, 125, Short.MAX_VALUE)
                                    .addComponent(tfConsulta))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodAlu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEliminar)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btConsulta)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNomAlu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAnadir)
                            .addComponent(btModificar))
                        .addGap(52, 52, 52)))
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addComponent(btAceptar)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Eliminar
    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        //Comprobamos que ha seleccionado al menos uno
        if(JTableAlumnos.getSelectedRowCount() > 0){
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar?");
            if(opcion == 0){
                //Puede dar excepcion al intentar eliminar un alumno que ya este en la tabla matriculas
                try{
                    //eliminamos el alumno de la bdd
                    cbdd.eliminarAlumno(codAluPulsado);
                    //Actualizamos la tabla
                    actualizarTablaAlumnos();
                }catch(org.hibernate.exception.ConstraintViolationException ex){ 
                    JOptionPane.showMessageDialog(this, "Alumno registrado en matriculas, imposible de eliminar");
                }    
                //Limpiamos los campos
                limpiarCampos();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Pinche un alumno en la tabla para eliminarlo");
        }
        tfCodAlu.setEnabled(true);
    }//GEN-LAST:event_btEliminarActionPerformed
    //Consulta
    private void btConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultaActionPerformed
        tfCodAlu.setEnabled(true);
        //Selecciona por que columna quiere buscar
        int pulsado = cbAlumnos.getSelectedIndex();
        if(pulsado >= 0){
            //Aplico los filtros
            String filtrar = tfConsulta.getText();
            TableRowSorter modeloOrdenado = new TableRowSorter<TableModel>(modelo);
            JTableAlumnos.setRowSorter(modeloOrdenado);
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
        String codAlu = tfCodAlu.getText();
        String nomAlu = tfNomAlu.getText();
        //Comprobamos que no ha dejado cosas vacías
        if(codAlu.equals("") == false && nomAlu.equals("") == false){
            //Puede dar excepcion de PK violated si el usuario introduce un codAlu que ya existia
            try{    
                //Añadimos
                cbdd.anadirAlumno(codAlu, nomAlu);
                //Actualizamos la tabla
                actualizarTablaAlumnos();
                //Limpio los campos
                limpiarCampos();
            }catch(org.hibernate.exception.ConstraintViolationException ex){
                JOptionPane.showMessageDialog(this, "Ya existe un alumno con ese codigo");
            }    
        }    
        else{
            JOptionPane.showMessageDialog(this, "No deje campos vacíos");
        }
        
    }//GEN-LAST:event_btAnadirActionPerformed
    //Modificar
    private void btModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModificarActionPerformed
        //Comprobamos que ha seleccionado al menos uno
        if(JTableAlumnos.getSelectedRowCount() > 0){
            //Obtenemos los datos de los textField
            String codAlu = tfCodAlu.getText();
            String nomAlu = tfNomAlu.getText();
            //Comprobamos que no ha dejado cosas vacías
            if(codAlu.equals("") == false && nomAlu.equals("") == false){
                //Modificamos el alumno en la bdd
                cbdd.modificarAlumno(codAlu, nomAlu);
                //Actualizamos la tabla
                actualizarTablaAlumnos();
                //Limpiamos campos
                limpiarCampos();
                tfCodAlu.setEnabled(true);
            }    
            else{
                JOptionPane.showMessageDialog(this, "No deje campos vacíos");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Pinche un alumno en la tabla para modificarlo");
        }
    }//GEN-LAST:event_btModificarActionPerformed
    //Aceptar
    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
        //Cerramos el dialog
        this.dispose();
    }//GEN-LAST:event_btAceptarActionPerformed

    //Métodos auxiliares
    public void actualizarTablaAlumnos(){
        //Limpiamos la tabla
        modelo.setRowCount(0);
        //Obtenemos todos los Alumnos
        alumnos = cbdd.consultarTodosAlumnos();
        //Los añadimos a la tabla
        for (Alumnos aux: alumnos){
            modelo.addRow(new Object[]{aux.getCcodalu(), aux.getCnomalu()});            
        }
    }
    
    public void limpiarCampos(){
        tfCodAlu.setText("");
        tfNomAlu.setText("");
        tfConsulta.setText("");
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
            java.util.logging.Logger.getLogger(DialogAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogAlumnos dialog = new DialogAlumnos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable JTableAlumnos;
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btAnadir;
    private javax.swing.JButton btConsulta;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btModificar;
    private javax.swing.JComboBox<String> cbAlumnos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfCodAlu;
    private javax.swing.JTextField tfConsulta;
    private javax.swing.JTextField tfNomAlu;
    // End of variables declaration//GEN-END:variables
}
