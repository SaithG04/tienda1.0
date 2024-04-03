package com.tienda.presentation_layer;

import javax.swing.*;

/**
 *
 * @author isai_
 */
public class TransaccionFrame extends javax.swing.JFrame {

    private static volatile TransaccionFrame instance;

    /**
     * Creates new form crearContra
     */
    private TransaccionFrame() {
        initComponents();
    }

    public static TransaccionFrame getInstance() {
        if (instance == null) {
            synchronized (TransaccionFrame.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new TransaccionFrame();
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
        miVerDetalle = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbTransacciones = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        lblRecuperar3 = new javax.swing.JLabel();

        miVerDetalle.setText("jMenuItem1");
        jpmOptions.add(miVerDetalle);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Usuarios");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(850, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Swis721 BT", 1, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(153, 255, 153));
        btnRegresar.setText("REGRESAR");
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 240, 40));

        jtbTransacciones.setFont(new java.awt.Font("Swis721 Cn BT", 0, 13)); // NOI18N
        jtbTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbTransacciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 790, 470));

        btnRefresh.setBackground(new java.awt.Color(0, 0, 0));
        btnRefresh.setFont(new java.awt.Font("Swis721 BT", 0, 11)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(204, 204, 204));
        btnRefresh.setText("Refrescar tabla");
        jPanel1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 160, 40));

        lblRecuperar3.setBackground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setFont(new java.awt.Font("BankGothic Lt BT", 1, 12)); // NOI18N
        lblRecuperar3.setForeground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecuperar3.setText("Tienda-version-1.1 -SNAPSHOT");
        jPanel1.add(lblRecuperar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 220, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JTable getJtbTransacciones() {
        return jtbTransacciones;
    }

    public JMenuItem getMiVerDetalle() {
        return miVerDetalle;
    }

    public JPopupMenu getJpmOptions() {
        return jpmOptions;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu jpmOptions;
    private javax.swing.JTable jtbTransacciones;
    private javax.swing.JLabel lblRecuperar3;
    private javax.swing.JMenuItem miVerDetalle;
    // End of variables declaration//GEN-END:variables
}