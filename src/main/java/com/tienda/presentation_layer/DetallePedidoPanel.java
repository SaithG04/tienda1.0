package com.tienda.presentation_layer;

import javax.swing.*;

/**
 *
 * @author isai_
 */
public class DetallePedidoPanel extends javax.swing.JPanel {

    private static volatile DetallePedidoPanel instance;

    /**
     * Creates new form DetallePedidoPanel
     */
    public DetallePedidoPanel() {
        initComponents();
    }

    @SuppressWarnings("DoubleCheckedLocking")
    public static DetallePedidoPanel getInstance() {
        if (instance == null) {
            synchronized (DetallePedidoPanel.class) { // Sincronización para hilos
                if (instance == null) {
                    instance = new DetallePedidoPanel();
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

        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbObservaciones = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbProveedor = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lbFechaPedido = new javax.swing.JLabel();
        lbFechaEntrega = new javax.swing.JLabel();
        lbCantidad = new javax.swing.JLabel();
        lbUnidad = new javax.swing.JLabel();
        lbMontoTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 204));
        setMaximumSize(new java.awt.Dimension(620, 600));
        setMinimumSize(new java.awt.Dimension(620, 600));
        setPreferredSize(new java.awt.Dimension(620, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Swis721 BT", 1, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(153, 255, 153));
        btnRegresar.setText("REGRESAR");
        add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 240, 40));

        jLabel1.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("OBSERVACIONES:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 140, 30));

        jLabel2.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ID TRANSACCION:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 30));

        jLabel3.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("ESTADO:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, 30));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ESTADO:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, 30));

        jLabel5.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("PROVEEDOR:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 140, 30));

        jLabel6.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PRODUCTO:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 140, 30));

        jLabel7.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("FECHA DE PEDIDO:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 140, 30));

        jLabel8.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("FECHA DE ENTREGA:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 140, 30));

        jLabel9.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("CANTIDAD:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 140, 30));

        jLabel10.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("UNIDAD");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 140, 30));

        jLabel11.setFont(new java.awt.Font("Swis721 Lt BT", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("MONTO TOTAL:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 140, 30));

        lbObservaciones.setForeground(new java.awt.Color(0, 0, 0));
        lbObservaciones.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(lbObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 350, 120));

        lbId.setForeground(new java.awt.Color(0, 0, 0));
        add(lbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 350, 30));

        lbEstado.setForeground(new java.awt.Color(0, 0, 0));
        add(lbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 350, 30));

        lbProveedor.setForeground(new java.awt.Color(0, 0, 0));
        add(lbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 350, 30));

        lblProducto.setForeground(new java.awt.Color(0, 0, 0));
        add(lblProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 350, 30));

        lbFechaPedido.setForeground(new java.awt.Color(0, 0, 0));
        add(lbFechaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 350, 30));

        lbFechaEntrega.setForeground(new java.awt.Color(0, 0, 0));
        add(lbFechaEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 350, 30));

        lbCantidad.setForeground(new java.awt.Color(0, 0, 0));
        add(lbCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 350, 30));

        lbUnidad.setForeground(new java.awt.Color(0, 0, 0));
        add(lbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 350, 30));

        lbMontoTotal.setForeground(new java.awt.Color(0, 0, 0));
        add(lbMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 350, 30));
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public JLabel getLbCantidad() {
        return lbCantidad;
    }

    public JLabel getLbEstado() {
        return lbEstado;
    }

    public JLabel getLbFechaEntrega() {
        return lbFechaEntrega;
    }

    public JLabel getLbFechaPedido() {
        return lbFechaPedido;
    }

    public JLabel getLbId() {
        return lbId;
    }

    public JLabel getLbMontoTotal() {
        return lbMontoTotal;
    }

    public JLabel getLbObservaciones() {
        return lbObservaciones;
    }

    public JLabel getLbProveedor() {
        return lbProveedor;
    }

    public JLabel getLbUnidad() {
        return lbUnidad;
    }

    public JLabel getLblProducto() {
        return lblProducto;
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbCantidad;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbFechaEntrega;
    private javax.swing.JLabel lbFechaPedido;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbMontoTotal;
    private javax.swing.JLabel lbObservaciones;
    private javax.swing.JLabel lbProveedor;
    private javax.swing.JLabel lbUnidad;
    private javax.swing.JLabel lblProducto;
    // End of variables declaration//GEN-END:variables
}
