package com.tienda.presentation_layer;

import javax.swing.*;

/**
 *
 * @author isai_
 */
public class ProveedorPanel extends javax.swing.JPanel {

    private static volatile ProveedorPanel instance;

    /**
     * Creates new form ProveedorPanel
     */
    public ProveedorPanel() {
        initComponents();
    }

    @SuppressWarnings("DoubleCheckedLocking")
    public static ProveedorPanel getInstance() {
        if (instance == null) {
            synchronized (ProveedorPanel.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new ProveedorPanel();
                }
            }
        }
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmOptions = new javax.swing.JPopupMenu();
        miEliminar = new javax.swing.JMenuItem();
        bgEstado = new javax.swing.ButtonGroup();
        btnRegistrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProveedores = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        txtRuc = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        rbInactivo = new javax.swing.JRadioButton();
        rbActivo = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtWeb = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtContacto = new javax.swing.JTextArea();
        lblRecuperar3 = new javax.swing.JLabel();

        miEliminar.setText("jMenuItem1");
        jpmOptions.add(miEliminar);

        setBackground(new java.awt.Color(153, 255, 204));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrar.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(153, 255, 153));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setPreferredSize(new java.awt.Dimension(850, 550));
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, 130, 60));

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(153, 255, 153));
        btnRegresar.setText("REGRESAR");
        add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 310, 130, 60));

        jtbProveedores.setFont(new java.awt.Font("Swis721 Cn BT", 0, 13)); // NOI18N
        jtbProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbProveedores);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 1190, 230));

        btnLimpiar.setBackground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(153, 255, 153));
        btnLimpiar.setText("LIMPIAR");
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 220, 130, 60));

        btnModificar.setBackground(new java.awt.Color(0, 0, 0));
        btnModificar.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(153, 255, 153));
        btnModificar.setText("MODIFICAR");
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 130, 130, 60));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Swis721 Ex BT", 0, 11)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(204, 204, 204));
        btnRefresh.setText("Refrescar tabla");
        add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 680, 160, -1));
        add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 390, 50));
        add(txtRazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 390, 50));
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 390, 50));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 390, 120));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("OBSERVACIONES:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 130, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("RUC:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("RAZÓN SOCIAL:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 100, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("DESCRIPCION:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 100, 50));
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 390, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("DIRECCIÓN:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 100, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TELEFONO:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 100, 50));
        add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 390, 50));

        add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, 230, 50));

        rbInactivo.setForeground(new java.awt.Color(0, 0, 0));
        rbInactivo.setText("Inactivo");
        add(rbInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 120, -1));

        rbActivo.setForeground(new java.awt.Color(0, 0, 0));
        rbActivo.setText("Activo");
        add(rbActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 240, 120, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane3.setViewportView(txtObservaciones);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 370, 140));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("CONTACTO:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 100, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("CORREO:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 100, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("WEB:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("CATEGORÍA");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 100, 50));
        add(txtWeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 360, 50));

        txtContacto.setColumns(20);
        txtContacto.setRows(5);
        jScrollPane4.setViewportView(txtContacto);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, 360, 150));

        lblRecuperar3.setBackground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setFont(new java.awt.Font("BankGothic Lt BT", 1, 12)); // NOI18N
        lblRecuperar3.setForeground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecuperar3.setText("Tienda-version-1.1 -SNAPSHOT");
        add(lblRecuperar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 220, 40));
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JTable getJtbProveedores() {
        return jtbProveedores;
    }

    public JMenuItem getMiEliminar() {
        return miEliminar;
    }

    public JPopupMenu getJpmOptions() {
        return jpmOptions;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public ButtonGroup getBgEstado() {
        return bgEstado;
    }

    public JComboBox<String> getCbCategoria() {
        return cbCategoria;
    }

    public JRadioButton getRbActivo() {
        return rbActivo;
    }

    public JRadioButton getRbInactivo() {
        return rbInactivo;
    }

    public JTextArea getTxtContacto() {
        return txtContacto;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public JTextField getTxtRuc() {
        return txtRuc;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtWeb() {
        return txtWeb;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEstado;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu jpmOptions;
    private javax.swing.JTable jtbProveedores;
    private javax.swing.JLabel lblRecuperar3;
    private javax.swing.JMenuItem miEliminar;
    private javax.swing.JRadioButton rbActivo;
    private javax.swing.JRadioButton rbInactivo;
    private javax.swing.JTextArea txtContacto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtWeb;
    // End of variables declaration//GEN-END:variables
}