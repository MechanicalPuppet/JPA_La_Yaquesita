/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.OrdenHasPlatilloJpaController;
import Controladores.OrdenJpaController;
import Controladores.PlatilloJpaController;
import Controladores.UsuariosJpaController;
import Entidades.Ingredientes;
import Entidades.Orden;
import Entidades.OrdenHasPlatillo;
import Entidades.Platillo;
import Entidades.Usuarios;
import FachadaLogica.FachadaLogica;
import Vistas.Paneles.PanelOrden;
import Vistas.Paneles.TabbedPanelMenu;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author Jbran
 */
public class FmPrincipal extends javax.swing.JFrame {

    String notasOrden;
    ArrayList<Platillo> platillos;
    FachadaLogica fachadaLogica;


    /**
     * Creates new form NewJFrame
     */
    public FmPrincipal() {

        //this.add(new PanelOrden());
        //this.add(new TabbedPanelMenu());
        initComponents();
        this.setSize(1920, 1080);
        tbtnHotdogs.setSelected(true);
        btn1.setText("Yaqui");
        btn2.setText("Cuate");
        notasOrden = "";
        platillos = new ArrayList<>();
        fachadaLogica = new FachadaLogica();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanelRecibo = new javax.swing.JPanel();
        jlblOrden = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanelOrden = new javax.swing.JPanel();
        tbtnHotdogs = new javax.swing.JToggleButton();
        tbtnBebidas = new javax.swing.JToggleButton();
        tbtnExtras = new javax.swing.JToggleButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(253, 232, 189));
        setMaximumSize(new java.awt.Dimension(1366, 726));
        setMinimumSize(new java.awt.Dimension(1366, 726));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(248, 227, 186));

        jPanelRecibo.setBackground(new java.awt.Color(254, 244, 222));
        jPanelRecibo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jlblOrden.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jlblOrden.setText("Orden: 001");

        btnSiguiente.setBackground(new java.awt.Color(247, 157, 68));
        btnSiguiente.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSiguiente.setText("Guardar");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout jPanelReciboLayout = new javax.swing.GroupLayout(jPanelRecibo);
        jPanelRecibo.setLayout(jPanelReciboLayout);
        jPanelReciboLayout.setHorizontalGroup(
            jPanelReciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReciboLayout.createSequentialGroup()
                .addGroup(jPanelReciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReciboLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                    .addGroup(jPanelReciboLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jlblOrden)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReciboLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelReciboLayout.setVerticalGroup(
            jPanelReciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReciboLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblOrden)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelOrden.setBackground(new java.awt.Color(254, 244, 222));
        jPanelOrden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbtnHotdogs.setBackground(new java.awt.Color(240, 60, 31));
        tbtnHotdogs.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        tbtnHotdogs.setForeground(new java.awt.Color(240, 211, 161));
        tbtnHotdogs.setText("HotDogs");
        tbtnHotdogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnHotdogsActionPerformed(evt);
            }
        });

        tbtnBebidas.setBackground(new java.awt.Color(198, 60, 31));
        tbtnBebidas.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        tbtnBebidas.setForeground(new java.awt.Color(240, 211, 161));
        tbtnBebidas.setText("Bebidas");
        tbtnBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnBebidasActionPerformed(evt);
            }
        });

        tbtnExtras.setBackground(new java.awt.Color(198, 60, 31));
        tbtnExtras.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        tbtnExtras.setForeground(new java.awt.Color(240, 211, 161));
        tbtnExtras.setText("Extras");
        tbtnExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtnExtrasActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(245, 133, 25));
        btn1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn1.setForeground(new java.awt.Color(91, 52, 46));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(245, 133, 25));
        btn2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn2.setForeground(new java.awt.Color(91, 52, 46));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOrdenLayout = new javax.swing.GroupLayout(jPanelOrden);
        jPanelOrden.setLayout(jPanelOrdenLayout);
        jPanelOrdenLayout.setHorizontalGroup(
            jPanelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOrdenLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOrdenLayout.createSequentialGroup()
                        .addComponent(tbtnHotdogs)
                        .addGap(10, 10, 10)
                        .addComponent(tbtnBebidas)
                        .addGap(18, 18, 18)
                        .addComponent(tbtnExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelOrdenLayout.createSequentialGroup()
                        .addComponent(btn1)
                        .addGap(18, 18, 18)
                        .addComponent(btn2)))
                .addContainerGap(538, Short.MAX_VALUE))
        );
        jPanelOrdenLayout.setVerticalGroup(
            jPanelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOrdenLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbtnHotdogs, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnBebidas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtnExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanelRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelRecibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbtnHotdogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnHotdogsActionPerformed
        tbtnHotdogs.setSelected(true);
        tbtnExtras.setSelected(false);
        tbtnBebidas.setSelected(false);

        btn1.setText("Yaqui");
        btn2.setText("Cuate");
    }//GEN-LAST:event_tbtnHotdogsActionPerformed

    private void tbtnBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnBebidasActionPerformed
        tbtnHotdogs.setSelected(false);
        tbtnExtras.setSelected(false);
        tbtnBebidas.setSelected(true);
        btn1.setText("Coca L");
        btn2.setText("Coca B");
    }//GEN-LAST:event_tbtnBebidasActionPerformed

    private void tbtnExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtnExtrasActionPerformed
        tbtnHotdogs.setSelected(false);
        tbtnExtras.setSelected(true);
        tbtnBebidas.setSelected(false);
        btn1.setText("Queso de Nacho");
        btn2.setText("Salsa Valiente");
    }//GEN-LAST:event_tbtnExtrasActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if (btn1.getText().equalsIgnoreCase("Yaqui")) {
            Yaqui yaqui = new Yaqui(this, true, this);
            yaqui.setVisible(true);

        } else if(btn1.getText().equalsIgnoreCase("Coca L")){
            CocaL cocaL = new CocaL(this, true, this);
            cocaL.setVisible(true);
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (btn2.getText().equalsIgnoreCase("Cuate")) {
            Cuate cuate = new Cuate(this, true, this);
            cuate.setVisible(true);
        } else if (btn2.getText().equalsIgnoreCase("Jarritos")){
            Jarritos jarritos = new Jarritos(this, true, this);
            jarritos.setVisible(true);
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        fachadaLogica.guardarOrden(this.platillos, this.notasOrden);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    public ArrayList getPlatillos() {
        return platillos;
    }

    public float calcularOrden() {
        float total = 0F;

        for (Platillo platillo : platillos) {
            total += platillo.getCosto();
        }

        return total;
    }







    public void notasOrden(Platillo platillo) {

        notasOrden = notasOrden.concat(platillo.toString() + "\n");
        txtArea.setText(notasOrden);
    }
    
    public void notasOrden(String string){
       notasOrden = notasOrden.concat(string + "\n");
       txtArea.setText(notasOrden);
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelOrden;
    private javax.swing.JPanel jPanelRecibo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlblOrden;
    private javax.swing.JToggleButton tbtnBebidas;
    private javax.swing.JToggleButton tbtnExtras;
    private javax.swing.JToggleButton tbtnHotdogs;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
