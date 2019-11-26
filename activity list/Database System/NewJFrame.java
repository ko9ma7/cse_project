package dbp2014136124;

import java.awt.Color;
import java.awt.Container;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author 지명화
 */

public class NewJFrame extends javax.swing.JFrame {
    
    Connection con = MyConnection.makeConnection();
    
    JTextField[] Tf = new JTextField[10];
    JLabel[] Lf = new JLabel[10];

    public NewJFrame() {
        initComponents();
        addNodes();
        
        Tf[0] = jTextField1;
        Tf[1] = jTextField2;
        Tf[2] = jTextField3;
        Tf[3] = jTextField4;
        Tf[4] = jTextField5;
        Tf[5] = jTextField6;
        Tf[6] = jTextField7;
        Tf[7] = jTextField8;
        Tf[8] = jTextField9;
        Tf[9] = jTextField10;
       
        Lf[0] = jLabel1;
        Lf[1] = jLabel2;
        Lf[2] = jLabel3;
        Lf[3] = jLabel4;
        Lf[4] = jLabel5;
        Lf[5] = jLabe6;
        Lf[6] = jLabel7;
        Lf[7] = jLabel8;
        Lf[8] = jLabel9;
        Lf[9] = jLabel10;
        
        this.getContentPane().setBackground(new Color(238,238,182));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabe6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MyDatabase");
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTree1.setBackground(new java.awt.Color(238, 238, 182));
        jTree1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jTree1.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTree1.setForeground(new java.awt.Color(255, 255, 255));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel2.setBackground(new java.awt.Color(238, 238, 182));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jButton1.setText("New");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jButton4.setText("Delete");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("맑은 고딕", 1, 14)); // NOI18N
        jButton5.setText("Print");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(238, 238, 182));

        jScrollPane2.setBackground(new java.awt.Color(238, 238, 182));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTable1.setBackground(new java.awt.Color(238, 238, 182));
        jTable1.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(238, 238, 182));

        jTextField1.setBackground(new java.awt.Color(238, 238, 182));
        jTextField1.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField1.setText("jTextField1");
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField2.setBackground(new java.awt.Color(238, 238, 182));
        jTextField2.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField2.setText("jTextField2");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField3.setBackground(new java.awt.Color(238, 238, 182));
        jTextField3.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField3.setText("jTextField3");
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField4.setBackground(new java.awt.Color(238, 238, 182));
        jTextField4.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField4.setText("jTextField4");
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField5.setBackground(new java.awt.Color(238, 238, 182));
        jTextField5.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField5.setText("jTextField5");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setBackground(new java.awt.Color(238, 238, 182));
        jTextField6.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField6.setText("jTextField6");
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField7.setBackground(new java.awt.Color(238, 238, 182));
        jTextField7.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField7.setText("jTextField7");
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField8.setBackground(new java.awt.Color(238, 238, 182));
        jTextField8.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField8.setText("jTextField8");
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField9.setBackground(new java.awt.Color(238, 238, 182));
        jTextField9.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField9.setText("jTextField9");
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jTextField10.setBackground(new java.awt.Color(238, 238, 182));
        jTextField10.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jTextField10.setText("jTextField10");
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel5.setText("jLabel5");

        jLabe6.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabe6.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel8.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel9.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabe6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5)
                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabe6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        String ns = mySelectedNode();
        
        if (ns == "About") {
            AboutFrame af = new AboutFrame();
            af.setVisible(true);
        }
        
        showData(ns);
        showFields();
    }//GEN-LAST:event_jTree1ValueChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        showFields();
    }//GEN-LAST:event_jTable1MouseClicked

    
   /*
    *  Buttons Event Method
    */ 
    // 1. New Button Event Method
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearTextField();
//      jTable1.setRowSelectionAllowed(false);
//      jTable1.setColumnSelectionAllowed(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    // 2. Add Button Event Method
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String st = mySelectedNode();
        if (st == Nodes.Certificate.toString()) addCertificateSP();
        else if (st == Nodes.Grade.toString()) addGradeSP();
        else if (st == Nodes.ForiegnLanguage.toString()) addLanguageSP();
        else if (st == Nodes.Student.toString()) addStudentSP();
        else if (st == Nodes.Intern.toString()) addInternSP();
    }//GEN-LAST:event_jButton2ActionPerformed
    // 3. Update Button Event Method
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String st = mySelectedNode();
        if (st == Nodes.Certificate.toString()) updateCertificateSP();
        else if (st == Nodes.Grade.toString()) updateGradeSP();
        else if (st == Nodes.ForiegnLanguage.toString()) updateLanguageSP();
        else if (st == Nodes.Student.toString()) updateStudentSP();
        else if (st == Nodes.Intern.toString()) updateInternSP();
    }//GEN-LAST:event_jButton3ActionPerformed
    // 4. Delete Button Event Method
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String st = mySelectedNode();
        if (st == Nodes.Certificate.toString()) deleteCertificateSP();
        else if (st == Nodes.Grade.toString()) deleteGradeSP();
        else if (st == Nodes.ForiegnLanguage.toString()) deleteLanguageSP();
        else if (st == Nodes.Student.toString()) deleteStudentSP();
        else if (st == Nodes.Intern.toString()) deleteInternSP();
    }//GEN-LAST:event_jButton4ActionPerformed
    // 5. Print Button Event Method
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//      runReport();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    /*
     * 1. Student Stored Procedure Function
    */
    // Insert record using Stored Procedure
    private void addStudentSP(){
        String st = mySelectedNode();
        try{
            String sql = "{call usp_insert_Student(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, jTextField1.getText());
            cst.setString(2, jTextField2.getText());
            cst.setString(3, jTextField3.getText());
            cst.setString(4, jTextField4.getText());
            cst.setString(5, jTextField5.getText());
            cst.setString(6, jTextField6.getText());
            cst.setString(7, jTextField7.getText());
            cst.setString(8, jTextField8.getText());
            cst.setString(9, jTextField9.getText());
            cst.setString(10, jTextField10.getText());
       
            cst.execute();
            jTextArea1.append("Record is added"+ "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){ 
            jTextArea1.append(e.getMessage() + "\n");
        }
    }
    // Delete record using Stored Procedure
    private void deleteStudentSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_delete_Student(?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, id);
            cst.execute();
            jTextArea1.append( " row is deleted" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) { 
            jTextArea1.append("Problem in deleteStudentSP() " + "\n");
        }
    }
    // Update record using Stored Procdure
    private void updateStudentSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_update_Student(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cst= con.prepareCall(sql);
        
            cst.setString(1, id); // studentID
            cst.setString(2, jTable1.getModel().getValueAt(rownum, 1).toString()); // name
            cst.setInt(3, Integer.parseInt(jTable1.getModel().getValueAt(rownum, 2).toString())); // age
            cst.setString(4, jTable1.getModel().getValueAt(rownum, 3).toString()); // sex
            cst.setInt(5, Integer.parseInt(jTable1.getModel().getValueAt(rownum, 4).toString())); // year
            cst.setString(6, jTable1.getModel().getValueAt(rownum, 5).toString()); // subject
            cst.setString(7, jTable1.getModel().getValueAt(rownum, 6).toString()); // grade_id
            cst.setString(8, jTable1.getModel().getValueAt(rownum, 7).toString()); // certificate_id
            cst.setString(9, jTable1.getModel().getValueAt(rownum, 8).toString()); // language_id
            cst.setString(10, jTable1.getModel().getValueAt(rownum, 9).toString()); // intern_id
            
            cst.execute();
            jTextArea1.append( " row is updated" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e) {
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) {
            jTextArea1.append("Problem in updateStudentSP() " + "\n");
        }
    }
    
    /*
     * 2. Certificate Stored Procedure Function
    */
    // Insert record using Stored Procedure
    private void addCertificateSP(){
        String st = mySelectedNode();
        try{
            String sql = "{call usp_insert_Certificate(?,?,?,?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, jTextField1.getText());
            cst.setString(2, jTextField2.getText());
            cst.setString(3, jTextField3.getText());
            cst.setString(4, jTextField4.getText());
       
            cst.execute();
            jTextArea1.append("Record is added"+ "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){ 
            jTextArea1.append(e.getMessage() + "\n");
        }
    }
    // Delete record using Stored Procedure
    private void deleteCertificateSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_delete_Certificate(?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(id));
            cst.execute();
            jTextArea1.append( " row is deleted" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) { 
            jTextArea1.append("Problem in deleteCertificateSP() " + "\n");
        }
    }
    // Update record using Stored Procdure
    private void updateCertificateSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_update_Certificate(?,?,?,?)}";
            CallableStatement cst= con.prepareCall(sql);
        
            cst.setInt(1, Integer.parseInt(id)); // certificateID
            cst.setString(2, jTable1.getModel().getValueAt(rownum, 1).toString()); // certificateName
            cst.setString(3, jTable1.getModel().getValueAt(rownum, 2).toString()); // certificateType
            cst.setInt(4, Integer.parseInt(jTable1.getModel().getValueAt(rownum, 3).toString())); // certificateValidYear
            
            cst.execute();
            jTextArea1.append( " row is updated" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e) {
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) {
            jTextArea1.append("Problem in updateCertificateSP() " + "\n");
        }
    }
    
    /*
     * 3. Grade Stored Procedure Function
    */
    // Insert record using Stored Procedure
    private void addGradeSP(){
        String st = mySelectedNode();
        try{
            String sql = "{call usp_insert_Grade(?,?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(jTextField1.getText()));
            cst.setFloat(2, Float.parseFloat(jTextField2.getText()));
       
            cst.execute();
            jTextArea1.append("Record is added"+ "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){ 
            jTextArea1.append(e.getMessage() + "\n");
        }
    }
    
    // Delete record using Stored Procedure
    private void deleteGradeSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_delete_Grade(?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(id));
            cst.execute();
            jTextArea1.append( " row is deleted" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) { 
            jTextArea1.append("Problem in deleteGradeSP() " + "\n");
        }
    }
    // Update record using Stored Procdure
    private void updateGradeSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_update_Grade(?,?)}";
            CallableStatement cst= con.prepareCall(sql);
        
            cst.setInt(1, Integer.parseInt(id)); // gradeID
            cst.setFloat(2, Float.parseFloat(jTable1.getModel().getValueAt(rownum, 1).toString())); // gradeAverage
            
            cst.execute();
            jTextArea1.append( " row is updated" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e) {
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) {
            jTextArea1.append("Problem in updateGradeSP() " + "\n");
        }
    }    
    
    /*
     * 4. Language Stored Procedure Function
    */
    // Insert record using Stored Procedure
    private void addLanguageSP(){
        String st = mySelectedNode();
        try{
            String sql = "{call usp_insert_Language(?,?,?,?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(jTextField1.getText()));
            cst.setString(2, jTextField2.getText());
            cst.setString(3, jTextField3.getText());
            cst.setInt(4, Integer.parseInt(jTextField4.getText()));
       
            cst.execute();
            jTextArea1.append("Record is added"+ "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){ 
            jTextArea1.append(e.getMessage() + "\n");
        }
    }
    // Delete record using Stored Procedure
    private void deleteLanguageSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_delete_Language(?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(id));
            cst.execute();
            jTextArea1.append( " row is deleted" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) { 
            jTextArea1.append("Problem in deleteLanguageSP() " + "\n");
        }
    }
    // Update record using Stored Procdure
    private void updateLanguageSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_update_Language(?,?,?,?)}";
            CallableStatement cst= con.prepareCall(sql);
        
            cst.setInt(1, Integer.parseInt(id)); // languageID
            cst.setString(2, jTable1.getModel().getValueAt(rownum, 1).toString()); // languageName
            cst.setString(3, jTable1.getModel().getValueAt(rownum, 2).toString()); // languageType
            cst.setInt(4, Integer.parseInt(jTable1.getModel().getValueAt(rownum, 3).toString())); // languageValidYear
            
            cst.execute();
            jTextArea1.append( " row is updated" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e) {
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) {
            jTextArea1.append("Problem in updateLanguageSP() " + "\n");
        }
    }
    
    /*
     * 5. Intern Stored Procedure Function
    */
    // Insert record using Stored Procedure
    private void addInternSP(){
        String st = mySelectedNode();
        try{
            String sql = "{call usp_insert_Intern(?,?,?,?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(jTextField1.getText()));
            cst.setString(2, jTextField2.getText());
            cst.setString(3, jTextField3.getText());
            cst.setString(4, jTextField4.getText());
       
            cst.execute();
            jTextArea1.append("Record is added"+ "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){ 
            jTextArea1.append(e.getMessage() + "\n");
        }
    }
    // Delete record using Stored Procedure
    private void deleteInternSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_delete_Intern(?)}";
            CallableStatement cst = con.prepareCall(sql);
            cst.setInt(1, Integer.parseInt(id));
            cst.execute();
            jTextArea1.append( " row is deleted" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e){
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) { 
            jTextArea1.append("Problem in deleteInternSP() " + "\n");
        }
    }
    // Update record using Stored Procdure
    private void updateInternSP(){
        String st = mySelectedNode();
        try{
            int rownum = jTable1.getSelectedRow();
            String id = jTable1.getModel().getValueAt(rownum, 0).toString();
            String sql="{call usp_update_Intern(?,?,?,?)}";
            CallableStatement cst= con.prepareCall(sql);
        
            cst.setInt(1, Integer.parseInt(id)); // internID
            cst.setString(2, jTable1.getModel().getValueAt(rownum, 1).toString()); // internType
            cst.setString(3, jTable1.getModel().getValueAt(rownum, 2).toString()); // period
            cst.setString(4, jTable1.getModel().getValueAt(rownum, 3).toString()); // location
            
            cst.execute();
            jTextArea1.append( " row is updated" + "\n");
            cst.close();
            showData(st);
        } catch(SQLException e) {
            jTextArea1.append(e.getMessage() + "\n");
        } catch(Exception e) {
            jTextArea1.append("Problem in updateInternSP() " + "\n");
        }
    }
    
    /*
     * *  6. Run Report
     */
    /*
    private void runReport11(){
        try{
            String rp = "C:\\Users\\mtariqm\\JaspersoftWorkspace\\MyReports\\myReport02.jrxml";
            JasperReport report = JasperCompileManager.compileReport(rp);
            JasperPrint myprint = JasperFillManager.fillReport(report,null,con);
            JasperViewer.viewReport(myprint);
        } catch(Exception e) { 
            jTextArea1.append("Problem in runReport() " + "\n");
        }
    }
    */
    
    // Function to showData into the fields
    private void showFields() {
        this.clearFields();
        int selectedRow = jTable1.getSelectedRow();
        int noc = jTable1.getColumnCount();
        
        for (int i = 0; i < noc; i++) {
            if (jTable1.getValueAt(selectedRow, i) == null) {
                Tf[i].setText(" ");
            } else {
                Tf[i].setText(jTable1.getValueAt(selectedRow, i).toString());
                Tf[i].setVisible(true);
            }
            
            Lf[i].setText(jTable1.getColumnName(i));
            Lf[i].setVisible(rootPaneCheckingEnabled);
        }
    }
   
    // Function to clear all fields
    private void clearFields() {
        for (int i = 0; i < Tf.length; i++) {
            Tf[i].setText("");
            Tf[i].setVisible(true);
            
            Lf[i].setText("");
            Lf[i].setVisible(true);
        }
    }
    
    public void clearTextField() {
        clearFields();
        
        int selectedRow = jTable1.getSelectedRow();
        int noc = jTable1.getColumnCount();
        
        for (int i = 0; i < noc; i++) {
            Tf[i].setText("");
            Tf[i].setVisible(true);
            
            Lf[i].setText(jTable1.getColumnName(i));
            Lf[i].setVisible(true);
        }
    }

    public void addNodes() {
        DefaultTreeModel tm = (DefaultTreeModel)jTree1.getModel(); // changing model
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(Nodes.newMyDatabase);
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode(Nodes.Tables);
        
        tm.setRoot(root);
        root.add(tables);
        
        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode(Nodes.Student);
        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode(Nodes.Certificate);
        DefaultMutableTreeNode t3 = new DefaultMutableTreeNode(Nodes.Grade);
        DefaultMutableTreeNode t4 = new DefaultMutableTreeNode(Nodes.ForiegnLanguage);
        DefaultMutableTreeNode t5 = new DefaultMutableTreeNode(Nodes.Intern);
        tables.add(t1);
        tables.add(t2);
        tables.add(t3);
        tables.add(t4);
        tables.add(t5);
        
        DefaultMutableTreeNode abt = new DefaultMutableTreeNode("About");
        root.add(abt);
        
        tm.reload(); // model will be updated
    }
        
    private String mySelectedNode() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
        String s = node.getUserObject().toString();
        return s;
    }

    private void showData(String s) {
        String query = " select * from " + s + ";";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery(); 
            jTable1.setModel(rsToTableModel(rs));  // model = number of data
            
            jTable1.setRowSelectionInterval(0, 0);
        } catch(Exception e) {
            System.err.println(" " + "Problem with showData()...");
        }
    }

    private TableModel rsToTableModel(ResultSet rs) {
            try {
                ResultSetMetaData  rsmd = rs.getMetaData();
                // data for columns
                int numberOfColumns = rsmd.getColumnCount();
                Vector cnames = new Vector();
                for (int i = 1; i <= numberOfColumns; i++) {
                    cnames.addElement(rsmd.getColumnLabel(i));
                }
                
                // data for rows
                Vector rows = new Vector();
                while(rs.next()) {
                    Vector newRow = new Vector();
                    for (int i = 1; i <= numberOfColumns; i++) {
                        newRow.addElement(rs.getObject(i));
                    }
                    rows.addElement(newRow);
                    
                }
                
                return new DefaultTableModel(rows, cnames);
                 
            } catch(Exception e) {
                System.err.println(" " + "Problem with rsToTableModel()...");
                return null;
            }
           
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabe6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}