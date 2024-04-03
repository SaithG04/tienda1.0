package com.tienda.presentation_layer;

import javax.swing.*;

/**
 *
 * @author isai_
 */
public class DetallePedidoFrame extends javax.swing.JFrame {

    private static volatile DetallePedidoFrame instance;

    /**
     * Creates new form crearContra
     */
    private DetallePedidoFrame() {
        initComponents();
    }

    public static DetallePedidoFrame getInstance() {
        if (instance == null) {
            synchronized (DetallePedidoFrame.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new DetallePedidoFrame();
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

        jPanel1 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDetallePedido = new javax.swing.JTable();
        lblRecuperar3 = new javax.swing.JLabel();

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
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 240, 40));

        jtbDetallePedido.setFont(new java.awt.Font("Swis721 Cn BT", 0, 13)); // NOI18N
        jtbDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbDetallePedido);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 790, 130));

        lblRecuperar3.setBackground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setFont(new java.awt.Font("BankGothic Lt BT", 1, 12)); // NOI18N
        lblRecuperar3.setForeground(new java.awt.Color(0, 0, 0));
        lblRecuperar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecuperar3.setText("Tienda-version-1.1 -SNAPSHOT");
        jPanel1.add(lblRecuperar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 220, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JTable getJtbDetallePedido() {
        return jtbDetallePedido;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbDetallePedido;
    private javax.swing.JLabel lblRecuperar3;
    // End of variables declaration//GEN-END:variables
}
