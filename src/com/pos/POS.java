/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.JDialog;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import javax.swing.UIManager;

/**
 *
 * @author AYO
 */
public class POS extends javax.swing.JFrame{

    private final String title = "My Store";
    private String username, tag, idstock;
    private DocumentListener documentListener, documentListener2, documentListener3,
            documentListener4, documentListener5, documentListener6, documentListener7;
    private boolean isQtyChanged = false;
    private int qqtty;
    private String defuQty;

    /**
     * Creates new form POS
     */
    public POS() {
        setMinimumSize(new Dimension(1380,10));
        setTitle(title);
        initComponents();
        ((JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setEditable(false);
        table1Width();
        table2Width();
        table3Width();
        table4Width();
        table5Width();
        table6Width();
        table7Width();
        table8Width();
        table9Width();
        loginPanel();
        getContentPane().setBackground(Color.WHITE);
        addDocumentListener();
        addDocumentListener2();
        addDocumentListener3();
        addDocumentListener4();
        addDocumentListener5();
        addDocumentListener6();
        addDocumentListener7();
        signInBtnHotkey();
        loadOrderHotkey();
        saveOrderHotkey();
        saveTransHotkey();
        refreshTransHotkey();
        advanceSearchHotkey();
        
    }
    
    private void addDocumentListener(){
        documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
              search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            
            private void search(){
                filterTable(jTable2, jTextField7.getText());
            }
        };
    }
    
    private void addDocumentListener2(){
        documentListener2 = new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                discountEvent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                discountEvent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                discountEvent();
            }
            
            private void discountEvent(){
                double a = ParseDouble(jTextField26.getText());
                double b = Double.parseDouble(jTextField5.getText());
                double x = Double.parseDouble(jTextField4.getText());
                double y = Double.parseDouble(jSpinner1.getValue().toString());
                b = y * x;
                b = b - a;
                String c = String.valueOf(b);
                jTextField5.setText(c);
            }
            
        };
    }
    
    private void addDocumentListener3(){
        documentListener3 = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchQuary();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchQuary();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchQuary();
            }
            
            private void searchQuary(){
                filterTable(jTable5, jTextField14.getText());
            }
        };
    }
        
        private void addDocumentListener4(){
            documentListener4 = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    change();
                }
                
                private void change(){
                    double a = ParseDouble(jTextField18.getText());
                    double b = Double.parseDouble(jTextField28.getText());
                    String c = String.valueOf(a - b);
                    jTextField29.setText(c);
                }
            };
        }
        
        private void addDocumentListener5(){
            documentListener5 = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    change();
                }
                private void change(){
                    if (jRadioButton1.isSelected()) {
                        ResultSet rs;
                        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
                        model.setRowCount(0);
                        int i = model.getRowCount() + 1;
                        try {
                               Statement st = connect().createStatement();
                               rs = st.executeQuery("Select itemName, category, qty, unitPrice, discount, totalPrice,"
                                       + " DATE_FORMAT(date, '%e %b %Y  %h:%i %p')date, cashier from transaction "
                                       + "where itemName LIKE '"+jTextField9.getText()+"%'");
                                while (rs.next()) {                
                                      model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"),
                                          rs.getInt("qty"), rs.getString("unitPrice"), rs.getString("discount"), 
                                          rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                                      i++;
                                }
                            jTable3.setModel(model);
                    } catch (SQLException e) {
                            JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transaction Filter - Item Name", JOptionPane.ERROR_MESSAGE);
                    }
                    }else{
                        getTrans();
                    }
                }
            };
        }
        
        private void addDocumentListener6(){
            documentListener6 = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    change();
                }
                
                private void change(){
                    filterTable(jTable8, jTextField10.getText());
                }
            };
        }
        
        private void addDocumentListener7(){
            documentListener7 = new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    change();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    change();
                }
                
                private void change(){
                    isQtyChanged=true;
                }
            };
        }
        

        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jDialog2 = new javax.swing.JDialog();
        jTextField14 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jDialog3 = new javax.swing.JDialog();
        jLabel21 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jDialog4 = new javax.swing.JDialog();
        jTextField20 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jDialog5 = new javax.swing.JDialog();
        jLabel24 = new javax.swing.JLabel();
        jRadioButton9 = new javax.swing.JRadioButton();
        jTextField25 = new javax.swing.JTextField();
        jRadioButton10 = new javax.swing.JRadioButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jRadioButton11 = new javax.swing.JRadioButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jRadioButton12 = new javax.swing.JRadioButton();
        jButton15 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        jDialog6 = new javax.swing.JDialog();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jDialog7 = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jTextField10 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jDialog8 = new javax.swing.JDialog();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jDialog9 = new javax.swing.JDialog();
        jTextField18 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jDialog10 = new javax.swing.JDialog();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jDialog11 = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jSeparator6 = new javax.swing.JSeparator();
        cap = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField7 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        categoryPanel = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        transacFilterPanel = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jTextField9 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton17 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        jRadioButton5 = new javax.swing.JRadioButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        reportFilterPanel = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        jDialog1.setTitle("Sign Up");
        jDialog1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jTextField12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jTextField13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Phone Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton8.setText("Sign Up");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton9.setText("Cancel");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Create Account");

        jPasswordField2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPasswordField2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        jPasswordField3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPasswordField3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Re-type Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 12))); // NOI18N

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField12)
                            .addComponent(jTextField11)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jDialog2.setTitle("Advance Search");
        jDialog2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog2ComponentShown(evt);
            }
        });

        jTextField14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jButton10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton10.setText("Select Item");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTable5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Code", "Item Name", "QTY.", "Unit Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable5.setRowHeight(30);
        jTable5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable5.setShowGrid(true);
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(25, 25, 25))
            .addComponent(jScrollPane6)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDialog3.setTitle("New Stock");
        jDialog3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog3ComponentShown(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Add New Stock");

        jTextField15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField15.setToolTipText("Item name (important)");
        jTextField15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Category" }));
        jComboBox2.setToolTipText("category for item (important)");

        jTextField16.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField16.setText("0");
        jTextField16.setToolTipText("Item quantity (importamt)");
        jTextField16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QTY.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
        });

        jTextField17.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField17.setText("0.00");
        jTextField17.setToolTipText("Item price (important)");
        jTextField17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField17KeyPressed(evt);
            }
        });

        jTextField19.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jTextField19.setToolTipText("Item barcode number (not important)");
        jTextField19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField19KeyPressed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton11.setText("Save");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton12.setText("Cancel");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField27.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField27.setText("0.00");
        jTextField27.setToolTipText("item cost price (important)");
        jTextField27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField27KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jButton11)
                        .addGap(49, 49, 49)
                        .addComponent(jButton12))
                    .addGroup(jDialog3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog3Layout.createSequentialGroup()
                                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDialog3Layout.createSequentialGroup()
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel21)
                .addGap(64, 64, 64)
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jDialog4.setTitle("Update Stock");
        jDialog4.setType(java.awt.Window.Type.POPUP);
        jDialog4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog4ComponentShown(evt);
            }
        });

        jTextField20.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jTextField20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel22.setText("Update Stock");

        jTextField21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jComboBox3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jTextField22.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QTY.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField22KeyPressed(evt);
            }
        });

        jTextField23.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField23.setText("0");
        jTextField23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField23KeyPressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Copy Item Code");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton13.setText("Update");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton14.setText("Cancel");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jTextField24.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField24.setText("0");
        jTextField24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField24KeyPressed(evt);
            }
        });

        jTextField30.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField30.setText("0");
        jTextField30.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remove QTY.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField30KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(168, 168, 168))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jDialog4Layout.createSequentialGroup()
                        .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog4Layout.createSequentialGroup()
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jButton13)
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel23)
                .addGap(27, 27, 27)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jDialog5.setTitle("Advance Filter");
        jDialog5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog5ComponentShown(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Advance Filter");

        buttonGroup3.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton9.setText("Item Name");

        jTextField25.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        buttonGroup3.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton10.setText("Category");

        jComboBox4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        buttonGroup3.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton11.setText("Cashier");

        jComboBox5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        buttonGroup3.add(jRadioButton12);
        jRadioButton12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton12.setText("Price Range");

        jButton15.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton15.setText("Save");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jComboBox7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "₦5 - ₦500", "₦505 - ₦1,000", "₦1,005 - ₦5,000", "₦5,005 - ₦10,000", "₦10,005 - ₦20,000", "₦20,005 - ₦50,000", "₦50,005 - ₦100,000", "Over ₦100,000", " " }));

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog5Layout.createSequentialGroup()
                .addGroup(jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton11)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jRadioButton9)
                                .addComponent(jTextField25)
                                .addComponent(jRadioButton10)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton12)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDialog5Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jButton15)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addGap(43, 43, 43)
                .addComponent(jRadioButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jRadioButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialog6.setTitle("Withdraw");
        jDialog6.setBackground(new java.awt.Color(255, 255, 255));
        jDialog6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog6ComponentShown(evt);
            }
        });

        jTable7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Code", "Item Name", "Category", "QTY.", "Unit Price", "Total Price", "Date", "Withdrawn By", "withdrawal ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable7.setRowHeight(30);
        jScrollPane8.setViewportView(jTable7);

        jButton18.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton18.setText("New Withdrawal");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton19.setText("Remove Item");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog6Layout = new javax.swing.GroupLayout(jDialog6.getContentPane());
        jDialog6.getContentPane().setLayout(jDialog6Layout);
        jDialog6Layout.setHorizontalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addGap(43, 43, 43)
                .addComponent(jButton19)
                .addGap(104, 104, 104))
        );
        jDialog6Layout.setVerticalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog6Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDialog7.setTitle("New Withdrawal");
        jDialog7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog7ComponentShown(evt);
            }
        });

        jTable8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Code", "Item Name", "Category", "Unit Price", "QTY."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable8.setRowHeight(30);
        jScrollPane9.setViewportView(jTable8);

        jTextField10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jButton20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton20.setText("Select Items");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jSpinner2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSpinner2KeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("QTY.");

        jButton23.setText("All Items");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog7Layout = new javax.swing.GroupLayout(jDialog7.getContentPane());
        jDialog7.getContentPane().setLayout(jDialog7Layout);
        jDialog7Layout.setHorizontalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jDialog7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jButton20)
                .addGap(62, 62, 62))
        );
        jDialog7Layout.setVerticalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog7Layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog7Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDialog8.setTitle("Stocks/Transactions Review");
        jDialog8.setResizable(false);
        jDialog8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog8ComponentShown(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Stock Status & Sales Review");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Current Stock Cost Price");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Current Stock Selling Price");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Current Stock Markup Price");

        jLabel37.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Sold Stock Cost Price");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Sold Stock Selling  Price");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Sold Stock Quantity");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Current Stock Quantity");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Sold Stock Markup Price");

        jButton24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton24.setText("...");
        jButton24.setToolTipText("Advance Sales Review");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog8Layout = new javax.swing.GroupLayout(jDialog8.getContentPane());
        jDialog8.getContentPane().setLayout(jDialog8Layout);
        jDialog8Layout.setHorizontalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog8Layout.createSequentialGroup()
                .addComponent(jSeparator4)
                .addContainerGap())
            .addGroup(jDialog8Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel33)
                .addGap(0, 91, Short.MAX_VALUE))
            .addGroup(jDialog8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel42)
                    .addComponent(jLabel39)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(jLabel41))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton24)
                .addGap(32, 32, 32))
        );
        jDialog8Layout.setVerticalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(26, 26, 26)
                .addComponent(jLabel35)
                .addGap(28, 28, 28)
                .addComponent(jLabel36)
                .addGap(28, 28, 28)
                .addComponent(jLabel41)
                .addGap(26, 26, 26)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addGap(26, 26, 26)
                .addComponent(jLabel38)
                .addGap(31, 31, 31)
                .addComponent(jLabel42)
                .addGap(28, 28, 28)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialog9.setTitle("Save Transaction");
        jDialog9.setBackground(new java.awt.Color(204, 204, 204));
        jDialog9.setUndecorated(true);
        jDialog9.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog9ComponentShown(evt);
            }
        });

        jTextField18.setBackground(new java.awt.Color(204, 204, 204));
        jTextField18.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setText("0.00");
        jTextField18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amount Received", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField18KeyPressed(evt);
            }
        });

        jTextField28.setEditable(false);
        jTextField28.setBackground(new java.awt.Color(204, 204, 204));
        jTextField28.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setText("0.00");
        jTextField28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jTextField29.setEditable(false);
        jTextField29.setBackground(new java.awt.Color(204, 204, 204));
        jTextField29.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("0.00");
        jTextField29.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jButton21.setBackground(new java.awt.Color(204, 204, 204));
        jButton21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(204, 204, 204));
        jButton22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton22.setText("Exit");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog9Layout = new javax.swing.GroupLayout(jDialog9.getContentPane());
        jDialog9.getContentPane().setLayout(jDialog9Layout);
        jDialog9Layout.setHorizontalGroup(
            jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog9Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(jDialog9Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jButton21)
                .addGap(29, 29, 29)
                .addComponent(jButton22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog9Layout.setVerticalGroup(
            jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jDialog10.setTitle("Administrator");
        jDialog10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jDialog10ComponentShown(evt);
            }
        });

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Address", "Phone Number", "Last Seen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.setRowHeight(30);
        jScrollPane10.setViewportView(jTable9);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setToolTipText("username");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Change Username");
        jLabel29.setToolTipText("username");
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel29MouseExited(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Change Password");
        jLabel30.setToolTipText("username");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jDialog10Layout = new javax.swing.GroupLayout(jDialog10.getContentPane());
        jDialog10.getContentPane().setLayout(jDialog10Layout);
        jDialog10Layout.setHorizontalGroup(
            jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(jDialog10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jDialog10Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog10Layout.setVerticalGroup(
            jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Advance Sales Review");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("From:");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("To:");

        jDateChooser4.setDateFormatString("d MMM yyyy");
        jDateChooser4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jDateChooser5.setDateFormatString("d MMM yyyy");
        jDateChooser5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        cap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton25.setText("View");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Sold Stock Cost Price");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Sold Stock Selling  Price");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Sold Stock Markup Price");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Sold Stock Quantity");

        javax.swing.GroupLayout jDialog11Layout = new javax.swing.GroupLayout(jDialog11.getContentPane());
        jDialog11.getContentPane().setLayout(jDialog11Layout);
        jDialog11Layout.setHorizontalGroup(
            jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addGap(267, 267, 267))
            .addGroup(jDialog11Layout.createSequentialGroup()
                .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog11Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog11Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog11Layout.setVerticalGroup(
            jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addGap(27, 27, 27)
                .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cap, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addGap(31, 31, 31)
                .addComponent(jLabel44)
                .addGap(26, 26, 26)
                .addComponent(jLabel45)
                .addGap(28, 28, 28)
                .addComponent(jLabel46)
                .addGap(41, 41, 41))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1201, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Point Of Sale");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Stock Inventory");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Category");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Transaction");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Report");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Re-Order");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(2554, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Code", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jTextField3.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.00");
        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("0.00");
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Qty.");

        jSpinner1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner1.setToolTipText("Number of Item(s)");
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel10.setText("Grand Total:");

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Code", "Item Name", "QTY.", "Unit Price", "Discount", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(30);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField6.setText("0.00");
        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Load Order (F1)");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Save Order (F3)");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Save Trans. (F5)");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Refresh Trans. (F7)");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Adv. Search (F9)");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Remove Item");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jTextField26.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField26.setText("0.00");
        jTextField26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Discount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        jTextField26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField26KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel4ComponentShown(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Name", "Category", "QTY.", "Current QTY.", "Price", "Cost Price", "Markup", "Date In", "Item Code", "Date Updated", "Updated By", "Total Sold Price", "Total Sold Markup", "Capital", "Total Sold QTYs.", "itemID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setRowHeight(30);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        jTextField7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setText("All Items");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton4.setText("Advance Filter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton5.setText("Add Stock");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton6.setText("Update Stock");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addGap(203, 203, 203))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        categoryPanel.setBackground(new java.awt.Color(255, 255, 255));
        categoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter New Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        categoryPanel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jTextField8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(198, 198, 198))
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel6ComponentShown(evt);
            }
        });

        transacFilterPanel.setBackground(new java.awt.Color(255, 255, 255));
        transacFilterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton1.setText("Item Name");

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton2.setText("Category");

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton3.setText("Cashier");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton4.setText("Date");

        jTextField9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTextField9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jDateChooser1.setDateFormatString("d MMM yyyy");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jButton17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton17.setText("View");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton5.setText("Date Range");

        jDateChooser2.setDateFormatString("d MMM yyyy");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jDateChooser3.setDateFormatString("d MMM yyyy");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Show all Transactions");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel40MouseExited(evt);
            }
        });

        javax.swing.GroupLayout transacFilterPanelLayout = new javax.swing.GroupLayout(transacFilterPanel);
        transacFilterPanel.setLayout(transacFilterPanelLayout);
        transacFilterPanelLayout.setHorizontalGroup(
            transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(transacFilterPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jRadioButton2)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(transacFilterPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1)
                        .addGap(378, 378, 378)
                        .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jRadioButton3))
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(transacFilterPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addGap(61, 61, 61)))
                .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButton5)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        transacFilterPanelLayout.setVerticalGroup(
            transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transacFilterPanelLayout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transacFilterPanelLayout.createSequentialGroup()
                        .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(transacFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jButton17))
                            .addGroup(transacFilterPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Name", "Category", "QTY.", "Unit Price", "Discount", "Total Price", "Date", "Cashier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setRowHeight(30);
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(transacFilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(transacFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        reportFilterPanel.setBackground(new java.awt.Color(255, 255, 255));
        reportFilterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Report Filter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        reportFilterPanel.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton6.setText("Daily Cash Sales");

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton7.setText("Monthly Cash Sales");

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jRadioButton8.setText("Yearly Cash Sales");

        jButton16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton16.setText("View");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportFilterPanelLayout = new javax.swing.GroupLayout(reportFilterPanel);
        reportFilterPanel.setLayout(reportFilterPanelLayout);
        reportFilterPanelLayout.setHorizontalGroup(
            reportFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportFilterPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(reportFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton6)
                    .addGroup(reportFilterPanelLayout.createSequentialGroup()
                        .addGroup(reportFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton8)
                            .addComponent(jRadioButton7))
                        .addGap(104, 104, 104)
                        .addComponent(jButton16)))
                .addContainerGap(507, Short.MAX_VALUE))
        );
        reportFilterPanelLayout.setVerticalGroup(
            reportFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportFilterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(reportFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton8)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Date", "Amount Sold", "Markup", "Discount", "Cost Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.setRowHeight(30);
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(reportFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(reportFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Re-Order Quantity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
        jPanel8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel8ComponentShown(evt);
            }
        });

        jTable6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S/N", "Item Code", "Item Name", "Category", "QTY. Left", "QTY. Sold"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable6.setRowHeight(30);
        jScrollPane7.setViewportView(jTable6);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("List of Re-Order Items:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel26))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel9.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jPasswordField1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jPasswordField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign In");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sign Up");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Forget Password");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jCheckBox1.setText("visible");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(165, 165, 165))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel3)
                        .addContainerGap(139, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jPanel9.add(jPanel1);
        jPanel1.setBounds(440, 120, 500, 361);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/20160115-things-never-to-but-at-supermarket.jpg"))); // NOI18N
        jPanel9.add(jLabel25);
        jLabel25.setBounds(0, 0, 1040, 560);

        jMenu2.setText("ADMIN");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu4.setText("Withdraw");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Reviews");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu3.setText("Logout");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //this event method handles the Sign in button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           signIn();
    }//GEN-LAST:event_jButton1ActionPerformed

    //mouse click event to open the Point of Sales panel in the dash board panel
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        pOSPanel();
    }//GEN-LAST:event_jLabel4MouseClicked

    //mouse click event to open the Stock Inventory panel in the dash board panel
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        stockInventoryPanel();
    }//GEN-LAST:event_jLabel5MouseClicked

    //mouse click event to open the Category panel in the dash board panel    
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        categoryPanel();
    }//GEN-LAST:event_jLabel6MouseClicked

    //mouse click event to open the Transaction panel in the dash board panel    
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        transactionPanel();
    }//GEN-LAST:event_jLabel7MouseClicked

    //mouse click event to open the Report panel in the dash board panel    
    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        reportPanel();
    }//GEN-LAST:event_jLabel8MouseClicked

    //mouse click event to open the Report panel in the dash board panel
    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        jLabel4.setBackground(new java.awt.Color(153,153,255));
    }//GEN-LAST:event_jLabel4MouseEntered

    //mouse click event to open the Report panel in the dash board panel
    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        jLabel4.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel4MouseExited

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        jLabel5.setBackground(new java.awt.Color(153,153,255));        
    }//GEN-LAST:event_jLabel5MouseEntered

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel5MouseExited

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
         jLabel6.setBackground(new java.awt.Color(153,153,255));
    }//GEN-LAST:event_jLabel6MouseEntered

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel6MouseExited

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setBackground(new java.awt.Color(153,153,255));
    }//GEN-LAST:event_jLabel7MouseEntered

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel7MouseExited

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setBackground(new java.awt.Color(153,153,255));
    }//GEN-LAST:event_jLabel8MouseEntered

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel8MouseExited

    //the quit button to close the app
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    //Sign out method
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout", "Sign Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (a==JOptionPane.YES_OPTION) {
            jPasswordField1.setText("");
            loginPanel();
            updateLastSeen();
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    //to open the create account dialog through the sign in panel
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        jDialog1.dispose();
        openDialog(jDialog1);
    }//GEN-LAST:event_jLabel2MouseClicked

    //to open the advance search dialog through the pos panel
    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        jDialog2.dispose();
        openDialog(jDialog2);
    }//GEN-LAST:event_jLabel19MouseClicked

    //to open the add new stock dialog through the stock inventory panel
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jDialog3.dispose();
        openDialog(jDialog3);
    }//GEN-LAST:event_jButton5ActionPerformed

    //to open the update dialog through the stock inventory panel
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jDialog4.dispose();
        openDialog(jDialog4);
    }//GEN-LAST:event_jButton6ActionPerformed

    //to copy the the item code to the system clipboard. At the update dialog
    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
       StringSelection stringSelection = new StringSelection((jTextField20.getText()));
        Clipboard clipboard = getToolkit().getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }//GEN-LAST:event_jLabel23MouseClicked

    //to open the advance filter through the stock inventory panel
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jDialog5.dispose();
        openDialog(jDialog5);
    }//GEN-LAST:event_jButton4ActionPerformed

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        jLabel12.setBackground(new java.awt.Color(153,153,255));
    }//GEN-LAST:event_jLabel12MouseEntered

    //event to handle the mouse hover of the dash board panel Label
    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        jLabel12.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel12MouseExited

    //to open the Re-Order panel
    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        reOrderPanel();
    }//GEN-LAST:event_jLabel12MouseClicked

    //to open the Withdrawal dialog
    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        jDialog6.dispose();
        openDialog(jDialog6);
    }//GEN-LAST:event_jMenu4MouseClicked

    //to open the new withdrawal dialog
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        jDialog7.dispose();
        openDialog(jDialog7);
    }//GEN-LAST:event_jButton18ActionPerformed

    //to open the market status dialog
    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        jDialog8.dispose();
        openDialog(jDialog8);
    }//GEN-LAST:event_jMenu5MouseClicked

    //to close the create account dialog
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jDialog1.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    //create employee account button
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (jTextField11.getText().isBlank() || jTextField12.getText().isBlank() || jTextField13.getText().isBlank()) {
            JOptionPane.showMessageDialog(jDialog1, "Enter all details to create account", "Create Account", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (jPasswordField2.getPassword().length<6) {
            JOptionPane.showMessageDialog(jDialog1, "Password must be at least 6 characters", "Create Account", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (!Arrays.equals(jPasswordField2.getPassword(), jPasswordField3.getPassword())) {
            JOptionPane.showMessageDialog(jDialog1, "Password does not match", "Create Account", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
      
    char [] a = jPasswordField2.getPassword();
    String passwordString = new String(a);
            
    PreparedStatement st;
        try {
             st = connect().prepareStatement("insert into users (name, address, phone_number, password, tag, date_created) "
                     + " VALUES (?, ?, ?, ?, ?, ?)");
             st.setString(1, jTextField11.getText().trim());
             st.setString(2, jTextField12.getText().trim());
             st.setString(3, jTextField13.getText().trim());
             st.setString(4, passwordString);
             st.setString(5, "Employee");
             st.setString(6, dateTime());
             st.executeUpdate();
             JOptionPane.showMessageDialog(jDialog1, "Account Created Successfully", "Create Account", JOptionPane.INFORMATION_MESSAGE);
             jTextField11.setText("");
             jTextField12.setText("");
             jTextField13.setText("");
             jPasswordField2.setText("");
             jPasswordField3.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog1, e.getMessage(), "Create Account", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    //to add new category to the category db
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTextField8.getText().isBlank()) {
            JOptionPane.showMessageDialog(jPanel5, "Enter category name", "Category", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String [] val = jTextField8.getText().split(",");
     
     for(String a : val){
        try {
            PreparedStatement st = connect().prepareStatement("Insert into category (name, description) VALUES (?, ?)");
            st.setString(1, a.trim());
            st.setString(2, "");
            st.executeUpdate();
            jTextField8.setText("");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel5, e.getMessage(), "Category", JOptionPane.ERROR_MESSAGE);
        }
     }
    }//GEN-LAST:event_jButton7ActionPerformed

    //to fetch the categories to the combobox in the add new stock panel
    private void jDialog3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog3ComponentShown
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * From category");
            
            while (rs.next()) {                
                jComboBox2.addItem(rs.getString("name"));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog3, e.getMessage(), "Add New Stock", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jDialog3ComponentShown

    //to avoid invalid input for the qty textbox at the add new stock dialog
    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        numberOnly(jTextField16, evt);
    }//GEN-LAST:event_jTextField16KeyPressed

    //to avoid inalid price input (Selling price) at the add new stock dialog
    private void jTextField17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyPressed
        priceInput(jTextField17, evt);
    }//GEN-LAST:event_jTextField17KeyPressed

    //to avoid inalid input for the item code textbox at the add new stock dialog    
    private void jTextField19KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyPressed
        numberOnly(jTextField19, evt);
    }//GEN-LAST:event_jTextField19KeyPressed

    //to close the add new stock dialog
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jDialog3.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    //to insert new stock to the stock db
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jTextField15.getText().isBlank() || jTextField16.getText().isBlank() || jTextField17.getText().isBlank() || jTextField27.getText().isBlank() ||jComboBox2.getSelectedItem().equals("Choose Category")) {
            JOptionPane.showMessageDialog(jDialog3, "Fill up important details of the item", "Add New Item", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
          
        int qty = Integer.parseInt(jTextField16.getText().trim());
        int code = Integer.parseInt(jTextField19.getText().trim());
        double price = Double.parseDouble(jTextField17.getText().trim());
        double cost = Double.parseDouble(jTextField27.getText().trim());
        double markup = Double.parseDouble(jTextField17.getText().trim()) - Double.parseDouble(jTextField27.getText().trim());
        try {
            PreparedStatement st = connect().prepareStatement("insert into stock (itemCode, itemName, category, initQty, qty, price, costPrice, markup, "
                    + "dateIn, dateUpdated, updatedBy, totalSoldPrice, totalMarkup, capital, totalQtySold) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?)");
            st.setInt(1, code);
            st.setString(2, jTextField15.getText().trim());
            st.setString(3, jComboBox2.getSelectedItem().toString());
            st.setInt(4, qty);
            st.setInt(5, qty);
            st.setDouble(6, price);
            st.setDouble(7, cost);
            st.setDouble(8, markup);
            st.setString(9, date());
            st.setString(10, dateTime());
            st.setString(11, username);
            st.setDouble(12, 0);
            st.setDouble(13, 0);
            st.setInt(14, 0);
            st.setInt(15, 0);
            st.executeUpdate();
            jTextField15.setText("");
            jTextField16.setText("");
            jTextField17.setText("");
            jTextField19.setText("");
            jTextField27.setText("");
            jComboBox2.setSelectedIndex(0);
            jTextField15.requestFocusInWindow();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog3, e.getMessage(), "Add New Stock", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    //to avoid inalid price input (Cost price) at the add new stock dialog
    private void jTextField27KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField27KeyPressed
            priceInput(jTextField27, evt);
    }//GEN-LAST:event_jTextField27KeyPressed

    //what happens when the stock inventory panel opens
    private void jPanel4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel4ComponentShown
        jTextField7.getDocument().addDocumentListener(documentListener);
        getStockDetails();
    }//GEN-LAST:event_jPanel4ComponentShown

    //what happens when the advance filter dialog opens
    private void jDialog5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog5ComponentShown
        ResultSet rs,r;
        try {
            Statement st = connect().createStatement();
            Statement s = connect().createStatement();
            rs = st.executeQuery("Select * from category");
            r = s.executeQuery("Select  * from users");
            while(rs.next()){
                jComboBox4.addItem(rs.getString("name"));
            }
            st.close();
            rs.close();
            while (r.next()) {                
                jComboBox5.addItem(r.getString("name"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog5, e.getMessage(), "Advance Filter", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jDialog5ComponentShown

    //all items button at the stock inventory panel
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        getStockDetails();
    }//GEN-LAST:event_jButton3ActionPerformed

    //filter the table after advance filter option has been selected
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        getStockDetails();
        if(jRadioButton12.isSelected()){
            priceRange();
            jDialog5.dispose();
            return;
        }
        
        filterTable(jTable2, getFilterString());
        jDialog5.dispose();
    }//GEN-LAST:event_jButton15ActionPerformed

    //get the categories ready for updating a particular stock item
    private void jDialog4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog4ComponentShown
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
        jTextField30.setText("0");
        
        jTextField22.getDocument().addDocumentListener(documentListener7);
        ResultSet rs;
       jComboBox3.removeAllItems();
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * From category");
            
            while (rs.next()) {                
                jComboBox3.addItem(rs.getString("name"));
            }
            st.close();
            rs.close();
            jComboBox3.setSelectedIndex(0);
            getSelectRowDetails();
            isQtyChanged=false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog3, e.getMessage(), "Update Stock", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jDialog4ComponentShown

    //close the update dialog
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        jDialog4.dispose();
        isQtyChanged=false;
    }//GEN-LAST:event_jButton14ActionPerformed

    //button to handle stock update in the stock db
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
                if (jTextField21.getText().isBlank() || jTextField23.getText().isBlank() || jTextField24.getText().isBlank()) {
            JOptionPane.showMessageDialog(jDialog4, "Cost Price/Sales Price/Item name\ncan't be empty", "Update Stock", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            double p = Double.parseDouble(jTextField23.getText().replace(",", ""));
            double c = Double.parseDouble(jTextField24.getText().replace(",", ""));
            double m = p - c;
            int rq = ParseInteger(jTextField30.getText());            
            int q = ParseInteger(jTextField22.getText());
            int qt = ParseInteger(defuQty);
            Statement st = connect().createStatement();
            q = q-rq;
            st.executeUpdate("Update stock set itemCode='"+jTextField20.getText()+"', "
                    + "itemName='"+jTextField21.getText()+"', category='"+jComboBox3.getSelectedItem().toString()+"', "
                            + "price='"+p+"', costPrice='"+c+"', markup='"+m+"', qty = '"+q+"', "
                                    + "dateUpdated = '"+dateTime()+"', updatedBy='"+username+"' where idstock='"+idstock+"'");
            if(qt != ParseInteger(jTextField22.getText())){
                stockIn();
            }
            
            jTextField20.setText("");
            jTextField21.setText("");
            jTextField22.setText("");
            jTextField23.setText("");
            jTextField24.setText("");
            jTextField30.setText("0");
            jComboBox3.setSelectedIndex(0);
            isQtyChanged=false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog4, e.getMessage(), "Update Stocke", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    //to calculate the total price as the quantity changes
    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        int a = ParseInteger(((JSpinner)evt.getSource()).getValue().toString());
        double b = ParseDouble(jTextField4.getText());
        double x = ParseDouble(jTextField26.getText());
        b = b * a;
        b = b - x;
        jTextField5.setText(String.valueOf(b));
    }//GEN-LAST:event_jSpinner1StateChanged

    //load order
    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        getItemPriceDetails();
    }//GEN-LAST:event_jLabel15MouseClicked

    //save order and load it to the table inthe point of sales panel
    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        saveOrder();
    }//GEN-LAST:event_jLabel16MouseClicked

    //what happens when the point of sales opens
    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown
        ((JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setEditable(false);
        jTextField26.getDocument().addDocumentListener(documentListener2);
        jTextField2.requestFocusInWindow();
    }//GEN-LAST:event_jPanel3ComponentShown

    // to avoid invalid input the discount textbox
    private void jTextField26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyPressed
        priceInput(jTextField26, evt);
    }//GEN-LAST:event_jTextField26KeyPressed

    //what happens when the select new withdrawal item dialog opens
    private void jDialog7ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog7ComponentShown
        jTextField10.requestFocusInWindow();
        jTextField10.getDocument().addDocumentListener(documentListener6);
        getStocksForWithdraw();
    }//GEN-LAST:event_jDialog7ComponentShown

    //what happens when the advance search dialog opens in through the pos panel
    private void jDialog2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog2ComponentShown
        jTextField14.getDocument().addDocumentListener(documentListener3);
        ResultSet rs;
        try {
            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * from stock");
            while(rs.next()){
                model.addRow(new Object[]{jTable5.getRowCount()+1, rs.getString("itemCode"), rs.getString("itemName"), 
                rs.getInt("qty"),  rs.getString("price")});
            }
            jTable8.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog2, e.getMessage(), "Advance Search", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jDialog2ComponentShown

    //select item and paste it details to the required textboxes in the pos panel through the advance search
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int a= jTable5.getSelectedRow();
        if (a<0) {
            return;
        }
        jTextField2.setText(jTable5.getModel().getValueAt(jTable5.convertRowIndexToModel(a), 1).toString());
        jTextField3.setText(jTable5.getModel().getValueAt(jTable5.convertRowIndexToModel(a), 2).toString());
        jTextField4.setText(jTable5.getModel().getValueAt(jTable5.convertRowIndexToModel(a), 4).toString());
        String e = jTable5.getModel().getValueAt(jTable5.convertRowIndexToModel(a), 3).toString();
        int spin = Integer.parseInt(e);
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, spin, 1));
        double b = Double.parseDouble(jTextField4.getText());
        double c = Double.parseDouble(jTextField26.getText());
        int x = Integer.parseInt(jSpinner1.getValue().toString());
        b = b * x;  
        b = b - c;
        String y = String.valueOf(b);
        jTextField5.setText(y);
        jDialog2.dispose();
        jTextField14.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    //remove selected item from the table is pos panel
    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        removeeItem();
    }//GEN-LAST:event_jLabel20MouseClicked

    //refresh transaction panel for new transaction
    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        refreshTrans();
    }//GEN-LAST:event_jLabel18MouseClicked

    //to open the save transaction panel
    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        saveTrans();
    }//GEN-LAST:event_jLabel17MouseClicked

    //close the save transaction panel
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        jDialog9.dispose();
        jTextField18.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
    }//GEN-LAST:event_jButton22ActionPerformed

    //what happens when the save transaction opens
    private void jDialog9ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog9ComponentShown
        jDialog9.getContentPane().setBackground(new java.awt.Color(204,204,204));
        jTextField28.setText(jTextField6.getText());
        jTextField18.getDocument().addDocumentListener(documentListener4);
    }//GEN-LAST:event_jDialog9ComponentShown

    //save new transactions to the transaction db
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
      double am_re =ParseDouble(jTextField18.getText());
      double cost = ParseDouble(jTextField28.getText());
        if (am_re < cost) {
            JOptionPane.showMessageDialog(jDialog9, "Not enough payment fee",
                    "Save Transaction", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int s = JOptionPane.showConfirmDialog(jDialog9, "Are you sure you want to save this transaction", "Save Transaction", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE);
     
        if (s == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            ResultSet rs;
            try {
                Statement st = connect().createStatement();
                rs = st.executeQuery("Select * from stock where itemCode = '"+jTable1.getValueAt(i, 1)+"' or itemName = '"+jTable1.getValueAt(i, 2)+"'");
                while (rs.next()) {                    
                    try {
                        PreparedStatement ps = connect().prepareStatement("Insert into transaction (itemCode, itemName, category, qty, costPrice, "
                                + "unitPrice, totalPrice, date, cashier, markup, totalMarkup, discount) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        ps.setInt(1, rs.getInt("itemCode"));
                        ps.setString(2, jTable1.getValueAt(i, 2).toString());
                        ps.setString(3, rs.getString("category"));
                        int q = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
                        ps.setInt(4, q);
                        ps.setDouble(5, rs.getDouble("costPrice"));
                        double pri = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
                        ps.setDouble(6, pri);
                        double topr = Double.parseDouble(jTable1.getValueAt(i, 6).toString());
                        double discou = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
                        ps.setDouble(7, topr);
                        ps.setString(8, dateTime());
                        ps.setString(9, username);
                        ps.setDouble(10, rs.getDouble("markup"));
                        ps.setDouble(11, rs.getDouble("markup") * q - discou);
                        ps.setDouble(12, discou);
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(jDialog9, e.getMessage(), "Transaction Insert", JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        double topr = Double.parseDouble(jTable1.getValueAt(i, 6).toString());
                        double discount = Double.parseDouble(jTable1.getValueAt(i, 5).toString());
                    int q = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
                    double tomar = q*rs.getDouble("markup") - discount;
                    PreparedStatement ps = connect().prepareStatement("Update stock set qty=qty-'"+q+"', totalSoldPrice=totalSoldPrice+'"+topr+"', "
                            + "totalMarkup= totalMarkup+'"+tomar+"', capital = totalSoldPrice-totalMarkup, totalQtySold= totalQtySold+'"+q+"' where itemCode='"+rs.getInt("itemCode")+"'"
                                    + " or itemName='"+jTable1.getValueAt(i, 2).toString()+"'");
                    ps.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(jDialog9, e.getMessage(), "Stock Update", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jDialog9, e.getMessage(), "Get Markup Price", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        jDialog9.dispose();
        jTextField18.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
        refreshTrans();
        } 
    }//GEN-LAST:event_jButton21ActionPerformed

    //to avoid invalid input in the amount recieved panel
    private void jTextField18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyPressed
        priceInput(jTextField18, evt);
    }//GEN-LAST:event_jTextField18KeyPressed

    //what happens when the transactions panel opens
    private void jPanel6ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel6ComponentShown
       buttonGroup2.clearSelection();
       jTextField9.getDocument().addDocumentListener(documentListener5);
       getCategory_Cashier();
       getTrans();
    }//GEN-LAST:event_jPanel6ComponentShown

    //to filter the table in the transaction panel based on date and date range input only
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        transFilter();
    }//GEN-LAST:event_jButton17ActionPerformed

    //filter the transaction table whenever the category combobox item changes
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jRadioButton2.isSelected()) {
            ResultSet rs;
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            int i = model.getRowCount() + 1;
            try {
                Statement st = connect().createStatement();
                rs = st.executeQuery("Select itemName, category, qty, unitPrice, discount, totalPrice, DATE_FORMAT(date, '%e %b %Y  %h:%i %p') "
                    + "date, cashier from transaction where category='"+jComboBox1.getSelectedItem().toString()+"'");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), rs.getString("unitPrice"), 
                rs.getString("discount"), rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                i++;
            }
            jTable3.setModel(model);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transaction Filter - Category", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            getTrans();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    //filter the transaction table whenever the cashier combobox item changes
    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        if (jRadioButton3.isSelected()) {
            ResultSet rs;
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            int i = model.getRowCount() + 1;
            try {
                Statement st = connect().createStatement();
                rs = st.executeQuery("Select itemName, category, qty, format(unitPrice,2)unitPrice, format(discount,2)discount,"
                        + " format(totalPrice,2)totalPrice, DATE_FORMAT(date, '%e %b %Y  %h:%i %p') "
                    + "date, cashier from transaction where cashier='"+jComboBox6.getSelectedItem().toString()+"'");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), rs.getString("unitPrice"), 
                rs.getString("discount"), rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                i++;
            }
            jTable3.setModel(model);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transaction Filter - Cashier", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            getTrans();
        }
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    //show all transaction label mouse hover 
    private void jLabel40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseEntered
        jLabel40.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel40MouseEntered

    //show all transaction label mouse hover
    private void jLabel40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseExited
        jLabel40.setForeground(Color.black);
    }//GEN-LAST:event_jLabel40MouseExited

    //what happens when the re-order panel opens
    private void jPanel8ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel8ComponentShown
        ResultSet rs;
        try {
            DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
            model.setRowCount(0);
            int i = model.getRowCount() + 1;
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * from stock where qty < 10");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getInt("itemCode"), rs.getString("itemName"), rs.getString("category"), rs.getInt("qty")
                , rs.getInt("initQty") - rs.getInt("qty")});
                i++;
            }
            jTable6.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel8, e.getMessage(), "Re-Order Items", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jPanel8ComponentShown

    //what happens when the withdrawal dialog opens
    private void jDialog6ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog6ComponentShown
        getWithdrawnItems();
    }//GEN-LAST:event_jDialog6ComponentShown

    //show all transactions
    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        buttonGroup2.clearSelection();
        getTrans();
    }//GEN-LAST:event_jLabel40MouseClicked

    //all item button in the withdrawal dialog
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        jTextField10.setText("");
        getStocksForWithdraw();
    }//GEN-LAST:event_jButton23ActionPerformed

    //getting all selected items for withdraw and save and updating the withdrawal and stock db
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if (!(jTable8.getSelectedRow() == -1)) {
            for(int i:jTable8.getSelectedRows()){
               ResultSet rs;
                try {
                    Statement s = connect().createStatement();
                    rs = s.executeQuery("Select costPrice, qty from stock where "
                            + "itemCode = '"+jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 1).toString()+"' or"
                                    + " itemName = '"+jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 2).toString()+"'");
                            int qt = Integer.parseInt(jSpinner2.getValue().toString());
                    
                    while (rs.next()) {
                        if (qt>rs.getInt("qty")) {
                        JOptionPane.showMessageDialog(jDialog7, "Item: "+jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 2).toString().toUpperCase()+""
                                + "\nis not up to "+qt+"\nAvailable: "+rs.getInt("qty"), "Insufficient Quantity", JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    }
                        try {
                            PreparedStatement st = connect().prepareStatement("Insert into withdrawals (itemCode, itemName, category, qty, costPrice"
                                + ", unitPrice, totalPrice, date, cashier) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                            int cod = Integer.parseInt(jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 1).toString());
                            st.setInt(1, cod);
                            st.setString(2, jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 2).toString());
                            st.setString(3, jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 3).toString());
                            int q = Integer.parseInt(jSpinner2.getValue().toString());
                            st.setInt(4, q);
                            st.setDouble(5, rs.getDouble("costPrice"));
                            double unit = Double.parseDouble(jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 4).toString().replace(",", ""));
                            st.setDouble(6, unit);
                            st.setDouble(7, unit * q);
                            st.setString(8, dateTime());
                            st.setString(9, username);
                            st.executeUpdate();
                            
                            try{
                            PreparedStatement up = connect().prepareStatement("Update stock set qty = qty - '"+q+"' where "
                                    + "itemCode = '"+jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 1).toString()+"' or"
                                    + " itemName = '"+jTable8.getModel().getValueAt(jTable8.convertRowIndexToModel(i), 2).toString()+"'");
                            up.executeUpdate();
                            }catch(SQLException ex){
                                JOptionPane.showMessageDialog(jDialog7, ex.getMessage(), "Withdrawal Insert", JOptionPane.ERROR_MESSAGE);         
                            }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(jDialog7, e.getMessage(), "Withdrawal Insert", JOptionPane.ERROR_MESSAGE);
                }
              
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(jDialog7, e.getMessage(), "Withdrawal Select Cost Price", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        getStocksForWithdraw();
        }
        jTable8.clearSelection();
        getWithdrawnItems();
    }//GEN-LAST:event_jButton20ActionPerformed

    //to validate users price input
    private void jTextField22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField22KeyPressed
        priceInput(jTextField22, evt);
    }//GEN-LAST:event_jTextField22KeyPressed

    //to validate users price input
    private void jTextField23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyPressed
        priceInput(jTextField23, evt);
    }//GEN-LAST:event_jTextField23KeyPressed

    //to validate users price input
    private void jTextField24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField24KeyPressed
        priceInput(jTextField24, evt);
    }//GEN-LAST:event_jTextField24KeyPressed

    //filter transaction by daily, monthly, yearly according to the users selection
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        reportFilter();
    }//GEN-LAST:event_jButton16ActionPerformed

    //opens the review dialog
    private void jDialog8ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog8ComponentShown
        reviews();
    }//GEN-LAST:event_jDialog8ComponentShown

    //mouse hover
    private void jLabel29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseEntered
        jLabel29.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel29MouseEntered

    //mouse hover
    private void jLabel29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseExited
        jLabel29.setForeground(Color.black);
    }//GEN-LAST:event_jLabel29MouseExited

    //mouse hover
    private void jLabel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseEntered
        jLabel30.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel30MouseEntered

    //mouse hover
    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
        jLabel30.setForeground(Color.black);
    }//GEN-LAST:event_jLabel30MouseExited

    //what happens when the admin dialog opens
    private void jDialog10ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jDialog10ComponentShown
        getAdminDetail();
        getEmployees();
    }//GEN-LAST:event_jDialog10ComponentShown

    //opens Admin dialog
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        openDialog(jDialog10);
    }//GEN-LAST:event_jMenu2MouseClicked

    //handles admin change username
    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
      try{
        String a =  JOptionPane.showInputDialog(jDialog10, "Enter Password", "Change Username", JOptionPane.INFORMATION_MESSAGE);
      ResultSet rs;
      boolean isF = false;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select password from users where tag = 'Admin' limit 1;");
            while(rs.next()){
            if (a.equals(rs.getString("password"))) {
                isF = true;
                String an =  JOptionPane.showInputDialog(jDialog10, "Enter new username", "Change Username", JOptionPane.INFORMATION_MESSAGE);
                if (! an.isBlank()) {
                    try {
                         PreparedStatement ps = connect().prepareStatement("Update users set name ='"+an+"' where tag='Admin'");
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(jDialog10, "Username changed successfully", "Change Username", JOptionPane.INFORMATION_MESSAGE);
                    getAdminDetail();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Change Username", JOptionPane.ERROR_MESSAGE);
                    }
                   
                }else{
                   JOptionPane.showMessageDialog(jDialog10, "Username can't be empty", "Change Username", JOptionPane.INFORMATION_MESSAGE); 
                }
            }
        }
            if(!isF){
                JOptionPane.showMessageDialog(jDialog10, "Password is incorrect", "Change Username", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
               JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Change Username", JOptionPane.ERROR_MESSAGE);
        }
      } catch (NullPointerException e){}
    }//GEN-LAST:event_jLabel29MouseClicked

    //handles admin change password
    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        try{
        String a =  JOptionPane.showInputDialog(jDialog10, "Enter Current Password", "Change Password", JOptionPane.INFORMATION_MESSAGE);
      ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select password from users where tag = 'Admin'");
            while(rs.next()){
            if (a.equals(rs.getString("password"))) {
                String an =  JOptionPane.showInputDialog(jDialog10, "Enter new password", "Change Password", JOptionPane.INFORMATION_MESSAGE);
                if (! an.isBlank() && !(an.length()<6)) {
                    try {
                         PreparedStatement ps = connect().prepareStatement("Update users set password ='"+an+"' where tag='Admin'");
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(jDialog10, "Password changed successfully", "Change Password", JOptionPane.INFORMATION_MESSAGE);
                    getAdminDetail();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Change Password", JOptionPane.ERROR_MESSAGE);
                    }
                   
                }else{
                   JOptionPane.showMessageDialog(jDialog10, "Password can't be empty or can't be less than 6 characters", "Change Password", JOptionPane.INFORMATION_MESSAGE); 
                }
            }else{
                JOptionPane.showMessageDialog(jDialog10, "Password is incorrect", "Change Password", JOptionPane.ERROR_MESSAGE);
            }
        }
        } catch (SQLException e) {
               JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Change Password", JOptionPane.ERROR_MESSAGE);
        }
        }catch (NullPointerException e){}
    }//GEN-LAST:event_jLabel30MouseClicked

    //handles forget password
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try{
            String a = JOptionPane.showInputDialog(jPanel1, "Enter your username", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
            ResultSet rs;
            try {
                Statement st = connect().createStatement();
                rs = st.executeQuery("Select * from users where name = '"+a+"'");
                while (rs.next()) {                    
                    if (a.equalsIgnoreCase(rs.getString("name"))) {
                        String b = JOptionPane.showInputDialog(jPanel1, "Enter your address for validation", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                        if (b.equalsIgnoreCase(rs.getString("address"))) {
                            String c = JOptionPane.showInputDialog(jPanel1, "Enter new password", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                            if (!c.isBlank() && !(c.length()<6)) {
                                PreparedStatement ps = connect().prepareStatement("Update users set password ='"+c+"' where name='"+a+"'");
                                ps.executeUpdate();
                                JOptionPane.showMessageDialog(jPanel1, "Password changed successfully", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(jPanel1, "Password can't be empty or less thsn 6 characters", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                        } else {
                            JOptionPane.showMessageDialog(jPanel1, "Address is incorrect", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(jPanel1, "Username: "+a+"\ndoesn't exist", "Forget Password", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jPanel1, e.getMessage(), "Forget Password", JOptionPane.ERROR_MESSAGE);
            }
        }catch (NullPointerException e){}
    }//GEN-LAST:event_jLabel3MouseClicked

    //update last seen when the app is closing
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        updateLastSeen();
    }//GEN-LAST:event_formWindowClosing

    //remove item from the withdrawal table
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        returnWithdrawnItem();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        try {
            JCheckBox c = (JCheckBox) evt.getSource();
        if (jCheckBox1.isSelected()) {
            jPasswordField1.setEchoChar(c.isSelected() ? '\u0000' : (Character)
        UIManager.get("PasswordField.echoChar"));
        }else{
            jPasswordField1.setEchoChar(!c.isSelected() ? '*' :(Character) 
        UIManager.get("PasswordField.echoChar"));
        }     
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
    try{
        if (jDateChooser4.getDate().equals(null) || jDateChooser5.getDate().equals(null)) {
        }
                 
      }catch (NullPointerException e){
            return;
    }
        ResultSet rs;
        
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("SELECT sum(qty) q, format(sum(qty * costPrice),2) cp, format(sum(totalPrice),2) sp,"
                    + " DATE_FORMAT('"+formattedDate2(jDateChooser4.getDate())+"', '%W %D %M %Y') start,"
                            + " DATE_FORMAT('"+formattedDate2(jDateChooser5.getDate())+"', '%W %D %M %Y') end,"
                    + " format(sum(totalMarkup),2)markup from transaction where CAST(date AS DATE) between"
                                    + " '"+formattedDate2(jDateChooser4.getDate())+"' and '"+formattedDate2(jDateChooser5.getDate())+"'");
            while (rs.next()) {                
                cap.setText("Transaction Review From: "+rs.getString("start")+",  To: "+rs.getString("end"));
                jLabel43.setText("Sold Stock Cost Price:  ₦"+rs.getString("cp")); 
                jLabel44.setText("Sold Stock Selling Price:  ₦"+rs.getString("sp"));
                jLabel45.setText("Sold Stock Markup Price:  ₦"+rs.getString("markup"));
                jLabel46.setText("Sold Stock Quantity:  "+rs.getInt("q"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog8, e.getMessage(), "Transaction Status Review", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
            openDialog(jDialog11);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jSpinner2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner2KeyPressed
        numberOnly(jSpinner2, evt);
    }//GEN-LAST:event_jSpinner2KeyPressed

    private void jTextField30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField30KeyPressed
        numberOnly(jTextField30, evt);
    }//GEN-LAST:event_jTextField30KeyPressed

    private void closePanels(){
        jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jPanel9.setVisible(false);
        jPanel7.setVisible(false);
        jPanel8.setVisible(false);
    }
    
    private void loginPanel(){
        setTitle(title+" - Sign In");
        jPanel9.setSize(getSize());
        jLabel25.setSize(getSize());
        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 236));
        setLocationRelativeTo(null);
        jMenuBar1.setVisible(false);
        closePanels();
        jPanel9.setVisible(true);
        jPanel1.setVisible(true);
    }
    
    private void pOSPanel(){
        jMenuBar1.setVisible(true);
        closePanels();
        setTitle(title+" - Point Of Sales");
        jPanel2.setVisible(true);
        jPanel3.setVisible(true);
        jTextField2.requestFocusInWindow();
    }
    
    private void stockInventoryPanel(){
        closePanels();
        setTitle(title+" - Stock Inventory");
        jPanel2.setVisible(true);
        jPanel4.setVisible(true);
        jTextField7.requestFocusInWindow();
    }
    
    private void categoryPanel(){
        closePanels();
        setTitle(title+" - Category");
        jPanel2.setVisible(true);
        jPanel5.setVisible(true);
    }
    
    private void transactionPanel(){
        closePanels();
        setTitle(title+" - Transaction");
        jPanel2.setVisible(true);
        jPanel6.setVisible(true);
    }
    
    private void reportPanel(){
        closePanels();
        setTitle(title+" - Report");
        jPanel2.setVisible(true);
        jPanel7.setVisible(true);
    } 
    
    private void reOrderPanel(){
        closePanels();
        setTitle(title+" - Re-Order");
        jPanel2.setVisible(true);
        jPanel8.setVisible(true);
    }
    
    //open any dialog method
    private void openDialog(JDialog jDialog){
        jDialog.dispose();
        jDialog.getContentPane().setBackground(Color.WHITE);
        jDialog.setLocation(300, 80);
        jDialog.setVisible(true);
        jDialog.pack();
    }
    
    private void table1Width(){
    jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
    jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
    jTable1.getColumnModel().getColumn(2).setPreferredWidth(260);
    jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
    jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
    jTable1.getColumnModel().getColumn(5).setPreferredWidth(152);
    }
    
    private void table2Width(){
    jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
    jTable2.getColumnModel().getColumn(1).setPreferredWidth(200);
    jTable2.getColumnModel().getColumn(2).setPreferredWidth(180);
    jTable2.getColumnModel().getColumn(3).setPreferredWidth(70);
    jTable2.getColumnModel().getColumn(4).setPreferredWidth(100);
    jTable2.getColumnModel().getColumn(5).setPreferredWidth(80);
    jTable2.getColumnModel().getColumn(6).setPreferredWidth(100);
    jTable2.getColumnModel().getColumn(7).setPreferredWidth(100);
    jTable2.getColumnModel().getColumn(8).setPreferredWidth(180);
    jTable2.getColumnModel().getColumn(9).setPreferredWidth(150);
    jTable2.getColumnModel().getColumn(10).setPreferredWidth(180);
    jTable2.getColumnModel().getColumn(11).setPreferredWidth(180);
    jTable2.getColumnModel().getColumn(12).setPreferredWidth(120);
    jTable2.getColumnModel().getColumn(13).setPreferredWidth(130);
    jTable2.getColumnModel().getColumn(14).setPreferredWidth(100);
    jTable2.getColumnModel().getColumn(15).setPreferredWidth(130);
    jTable2.getColumnModel().getColumn(16).setPreferredWidth(70);    
    }
    
    private void table3Width(){
    jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
    jTable3.getColumnModel().getColumn(1).setPreferredWidth(250);
    jTable3.getColumnModel().getColumn(2).setPreferredWidth(180);
    jTable3.getColumnModel().getColumn(3).setPreferredWidth(60);
    jTable3.getColumnModel().getColumn(4).setPreferredWidth(80);
    jTable3.getColumnModel().getColumn(5).setPreferredWidth(80);
    jTable3.getColumnModel().getColumn(6).setPreferredWidth(100);
    jTable3.getColumnModel().getColumn(7).setPreferredWidth(220);
    jTable3.getColumnModel().getColumn(8).setPreferredWidth(150);
    }
    
    private void table4Width(){
    jTable4.getColumnModel().getColumn(0).setPreferredWidth(45);
    jTable4.getColumnModel().getColumn(1).setPreferredWidth(160);
    jTable4.getColumnModel().getColumn(2).setPreferredWidth(160);
    jTable4.getColumnModel().getColumn(3).setPreferredWidth(160);
    jTable4.getColumnModel().getColumn(4).setPreferredWidth(160);
    jTable4.getColumnModel().getColumn(5).setPreferredWidth(160);
    }
    
    private void table5Width(){
        jTable6.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable6.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable6.getColumnModel().getColumn(2).setPreferredWidth(250);
        jTable6.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable6.getColumnModel().getColumn(4).setPreferredWidth(85);
        jTable6.getColumnModel().getColumn(5).setPreferredWidth(88);
        }
    
    private void table6Width(){
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable5.getColumnModel().getColumn(1).setPreferredWidth(120);
        jTable5.getColumnModel().getColumn(2).setPreferredWidth(220);
        jTable5.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable5.getColumnModel().getColumn(4).setPreferredWidth(80);
    }
    
    private void table7Width(){
    jTable7.getColumnModel().getColumn(0).setPreferredWidth(50);
    jTable7.getColumnModel().getColumn(1).setPreferredWidth(200);
    jTable7.getColumnModel().getColumn(2).setPreferredWidth(250);
    jTable7.getColumnModel().getColumn(3).setPreferredWidth(200);
    jTable7.getColumnModel().getColumn(4).setPreferredWidth(70);
    jTable7.getColumnModel().getColumn(5).setPreferredWidth(120);
    jTable7.getColumnModel().getColumn(6).setPreferredWidth(120);
    jTable7.getColumnModel().getColumn(7).setPreferredWidth(156);
    jTable7.getColumnModel().getColumn(8).setPreferredWidth(180);
    jTable7.getColumnModel().getColumn(9).setPreferredWidth(80);
    }
    
    private void table8Width(){
    jTable8.getColumnModel().getColumn(0).setPreferredWidth(50);
    jTable8.getColumnModel().getColumn(1).setPreferredWidth(120);
    jTable8.getColumnModel().getColumn(2).setPreferredWidth(200);
    jTable8.getColumnModel().getColumn(3).setPreferredWidth(160);
    jTable8.getColumnModel().getColumn(4).setPreferredWidth(130);
    jTable8.getColumnModel().getColumn(5).setPreferredWidth(80);
    }

    private void table9Width(){
    jTable9.getColumnModel().getColumn(0).setPreferredWidth(150);
    jTable9.getColumnModel().getColumn(1).setPreferredWidth(100);
    jTable9.getColumnModel().getColumn(2).setPreferredWidth(80);
    jTable9.getColumnModel().getColumn(3).setPreferredWidth(160);
    }

    private void signIn(){
        if (jTextField1.getText().isBlank() || jPasswordField1.getPassword().length==0) {
            return;
        }
    char [] a = jPasswordField1.getPassword();
            String pass = new String(a);
            boolean isCorrect = false;
            
    ResultSet rs;
        try {
            Statement st;
            st = connect().createStatement();
            rs = st.executeQuery("select * from users");
            
            while (rs.next()) {                
                if (rs.getString("name").equalsIgnoreCase(jTextField1.getText().trim()) && rs.getString("password").equals(pass)) {
                    if (rs.getString("tag").equalsIgnoreCase("developer") || rs.getString("tag").equalsIgnoreCase("Admin")) {
                        jLabel4.setVisible(true);
                        jLabel5.setVisible(true);
                        jLabel6.setVisible(true);
                        jLabel7.setVisible(true);
                        jLabel8.setVisible(true);
                        jMenu2.setVisible(true);
                        jMenu4.setVisible(true);
                        jMenu5.setVisible(true);
                        jLabel12.setVisible(true);
                        jPanel2.setVisible(true);
                        jSeparator5.setVisible(true);
                        isCorrect=true;
                        
                    }else{
                        jLabel4.setVisible(true);
                        jLabel5.setVisible(true);
                        jLabel6.setVisible(false);
                        jLabel7.setVisible(false);
                        jLabel8.setVisible(false);
                        jMenu2.setVisible(false);
                        jMenu4.setVisible(false);
                        jMenu5.setVisible(false);
                        jLabel12.setVisible(false);
                        jPanel2.setVisible(true);
                        jSeparator5.setVisible(false);
                        isCorrect=true;

                    }
                        username = rs.getString("name");
                        tag = rs.getString("tag");
                        pOSPanel();
                        updateLastSeen();
                }
            }
            if (!isCorrect) {
                JOptionPane.showMessageDialog(jPanel1, "Password or Username is incorrect", "Sign In", JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel1, e.getMessage(), "Sign In", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException ex){}
    }
    
    private void signInBtnHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        };
        
        jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), advSearch);
        jButton1.getActionMap().put(advSearch, advSearch);
    }
    
    //set textfield to a only numbers and backspace for correction
    private void numberOnly(JTextField textField, java.awt.event.KeyEvent evt){
        if ((evt.getKeyChar()>='0') && (evt.getKeyChar()<='9')) {
            textField.setEditable(true);
        }else if(evt.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ){
            textField.setEditable(true);
        }
        else{
            textField.setEditable(false);
        }
    }
    
    private void numberOnly(JSpinner textField, java.awt.event.KeyEvent evt){
        if ((evt.getKeyChar()>='0') && (evt.getKeyChar()<='9')) {
           ((JSpinner.DefaultEditor)textField.getEditor()).getTextField().setEditable(true);
        }else if(evt.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ){
            ((JSpinner.DefaultEditor)textField.getEditor()).getTextField().setEditable(true);
        }
        else{
            ((JSpinner.DefaultEditor)textField.getEditor()).getTextField().setEditable(false);        }
    }
    
    //set textfield to accept only money valid digit and backspace for correction
    private void priceInput(JTextField textField, java.awt.event.KeyEvent evt){
        if ((evt.getKeyChar()>='0') && (evt.getKeyChar()<='9')) {
            textField.setEditable(true);
        }else if(evt.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ){
            textField.setEditable(true);
        }else if (evt.getKeyChar()=='.') {
          textField.setEditable(true);  
        }
        else{
            textField.setEditable(false);
        }
    }
    
    //fetch all stock details whenever the stock inventory panel is visible
    private  void getStockDetails(){
        jTextField7.setText("");
        filterTable(jTable2, "");
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select idStock, itemCode, itemName, category, initQty, qty, format(price,2)price, format(costPrice,2)costPrice,"
                    + " format(markup,2)markup, DATE_FORMAT(dateIn, '%W %D %M %Y') dateIn, DATE_FORMAT(dateUpdated, '%D %b %Y  %h:%i %p') dateUpdated, "
                    + "updatedBy, format(totalSoldPrice,2)totalSoldPrice, format(totalMarkup,2)totalMarkup, format(capital,2)capital, totalQtySold from stock");
            int a=1;
            while (rs.next()) {
            String i,l,b,c,k,f,g,h,m,n,p;
            int aa,d,e,j,o;
            
            aa = rs.getInt("idStock");
            b = rs.getString("itemName");
            c = rs.getString("category");
            d = rs.getInt("initQty");
            e = rs.getInt("qty");
            f = rs.getString("price");
            g = rs.getString("costPrice");
            h = rs.getString("markup");
            i = rs.getString("dateIn");
            j = rs.getInt("itemCode");
            k = rs.getString("dateUpdated");
            l = rs.getString("updatedBy");
            m = rs.getString("totalSoldPrice");
            n = rs.getString("totalMarkup");
            p = rs.getString("capital");
            o = rs.getInt("totalQtySold");
        
            model.addRow(new Object[]{a,b,c,d,e,f,g,h,i,j,k,l,m,n,p,o,aa});
            a++;
            }
            jTable2.setModel(model);
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel4, e.getMessage(), "Stock Inventory", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // filter for any table
    private void filterTable(JTable table, String quary){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel> (model);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)"+quary));
    }
    
    //filter method for the table in stock inventory panel
    private String getFilterString(){
        if (jRadioButton9.isSelected()) {
            return jTextField25.getText();
        }else if (jRadioButton10.isSelected()) {
            return jComboBox4.getSelectedItem().toString();
        }else if (jRadioButton11.isSelected()) {
            return jComboBox5.getSelectedItem().toString();
        }
        return null;
    }
    
    private void priceRange(){
        if(jComboBox7.getSelectedIndex()==7){
            ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        try{
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select idStock, itemCode, itemName, category, initQty, qty, format(price,2)price,"
                    + " format(costPrice,2)costPrice, format(markup,2)markup, DATE_FORMAT(dateIn, '%W %D %M %Y') dateIn,"
                    + " DATE_FORMAT(dateUpdated, '%D %b %Y  %h:%i %p') dateUpdated, updatedBy, "
                    + "format(totalSoldPrice,2)totalSoldPrice, format(totalMarkup,2)totalMarkup, format(capital,2)capital,"
                    + " totalQtySold from stock where price > '100000'");
            int i =1;
            while(rs.next()){
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getString("initQty")
                , rs.getString("qty"), rs.getString("price"), rs.getString("costPrice"), rs.getString("markup"),
                 rs.getString("dateIn"), rs.getString("itemCode"), rs.getString("dateUpdated"), rs.getString("updatedBy"), 
                rs.getString("totalSoldPrice"), rs.getString("totalMarkup"), rs.getString("capital"), rs.getString("totalQtySold"),
                rs.getString("idstock")});
                i++;
            }
            jTable2.setModel(model);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(jDialog5, e.getMessage(), "Price Range", ERROR);
        }
        jDialog5.dispose();
        return;
        }
        String val = jComboBox7.getSelectedItem().toString().replace("₦", "");
        val = val.replace(",", "");
        String argu [] = val.split("-");
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        try{
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select idStock, itemCode, itemName, category, initQty, qty, format(price,2)price,"
                    + " format(costPrice,2)costPrice, format(markup,2)markup, DATE_FORMAT(dateIn, '%W %D %M %Y') dateIn,"
                    + " DATE_FORMAT(dateUpdated, '%D %b %Y  %h:%i %p') dateUpdated, updatedBy, "
                    + "format(totalSoldPrice,2)totalSoldPrice, format(totalMarkup,2)totalMarkup, format(capital,2)capital,"
                    + " totalQtySold from stock where price between '"+argu[0]+"' and '"+argu[1]+"'");
            int i =1;
            while(rs.next()){
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getString("initQty")
                , rs.getString("qty"), rs.getString("price"), rs.getString("costPrice"), rs.getString("markup"),
                 rs.getString("dateIn"), rs.getString("itemCode"), rs.getString("dateUpdated"), rs.getString("updatedBy"), 
                rs.getString("totalSoldPrice"), rs.getString("totalMarkup"), rs.getString("capital"), rs.getString("totalQtySold"),
                rs.getString("idstock")});
                i++;
            }
            jTable2.setModel(model);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(jDialog5, e.getMessage(), "Price Range", ERROR);
        }
    }
    
    //get Details of the selected row from table in the stock inventory panel for update purpose
    private void getSelectRowDetails(){
            int a= jTable2.getSelectedRow();
            if (a<0) {
            return;
        }
            qqtty = Integer.parseInt(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(jTable2.getSelectedRow()), 4).toString());
            idstock = jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 16).toString();
            jTextField20.setText(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 9).toString());
            jTextField21.setText(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 1).toString());            
            jTextField22.setText(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 4).toString());
            defuQty = jTextField22.getText();
            jTextField23.setText(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 5).toString());
            jTextField24.setText(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 6).toString());
            jComboBox3.setSelectedItem(jTable2.getModel().getValueAt(jTable2.convertRowIndexToModel(a), 2).toString());
    }
    
    //to check if an item qty get updated and insert the details to the stock_in details for of a receipt record hidden from the users
    private void stockIn(){
        if (isQtyChanged && ! jTextField22.getText().isBlank()) {
            
            try {
            int qty = ParseInteger(jTextField22.getText());
            Statement st = connect().createStatement();
            
            st.executeUpdate("Update stock set initQty= '"+qty+"', qty= '"+qty+"', totalSoldPrice='0.00', totalMarkup='0.00'"
                    + ", capital='0.00', totalQtySold='0', dateIn ='"+date()+"' where idstock='"+idstock+"'");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog4, e.getMessage(), "Update Stocke", JOptionPane.ERROR_MESSAGE);
        }
            
            PreparedStatement ps;
            try {
                ps = connect().prepareStatement("Insert into stock_in (itemCode, itemName, category, initQty, newQty, costPrice, totalCostPrice, "
                    + "sellingPrice, markup, date, cashier) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), ?)");
                double cos = Double.parseDouble(jTextField24.getText().replace(",", ""));
                double sel = Double.parseDouble(jTextField23.getText().replace(",", ""));
                int cod = Integer.parseInt(jTextField20.getText());
                int nq = Integer.parseInt(jTextField22.getText());
              
                ps.setInt(1, cod);
                ps.setString(2, jTextField21.getText());
                ps.setString(3, jComboBox3.getSelectedItem().toString());
                ps.setInt(4, qqtty);
                ps.setInt(5, nq);
                ps.setDouble(6, cos);
                ps.setDouble(7, cos * nq);
                ps.setDouble(8, sel);
                ps.setDouble(9, sel - cos);
                ps.setString(10, dateTime());
                ps.setString(11, username);
                
                ps.executeUpdate();
                        
                isQtyChanged=false;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jDialog4, e.getMessage(), "Stock In Receipt", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
    //returns the current time in string
    private String time(){
       Date dt = new Date();
      SimpleDateFormat  tim = new SimpleDateFormat("kk:mm:ss");
      String timeString = tim.format(dt);
      return timeString;
    }
    
    //returns the current date in string
    private String date(){
        Date dt = new Date();
    
      SimpleDateFormat  dat = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = dat.format(dt);
      return dateString;
    }
    
    //returns the current date and time in string
    private String dateTime(){
        Date dt = new Date();
    
      SimpleDateFormat  dat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat  tim = new SimpleDateFormat("H:mm:ss");
      String date = dat.format(dt);
      String time = tim.format(dt);
      String dateTimeString = date+" "+time;
      return dateTimeString;
    }
    
    private int getQtyForValidation(String name, JTable table){
        int qty = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i =0; i < model.getRowCount(); i++){
            if(model.getValueAt(i, 2).equals(name)){
                qty = ParseInteger(model.getValueAt(i, 3).toString()) + qty;
                System.out.println(qty);
            }
        }
        return qty;
    }
        
    //fetch item price details from the stock db table
    private void getItemPriceDetails(){
        if (jTextField2.getText().isBlank() && jTextField3.getText().isBlank()) {
            return;
        }
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * from stock where itemCode = '"+jTextField2.getText()+"' or "
                    + "itemName = '"+jTextField3.getText()+"'");
            if (rs.next()) {                
                if(rs.getInt("qty") == 0){
                    JOptionPane.showMessageDialog(jPanel3, rs.getString("itemName")+" is OUT OF STOCK", "Out Of Stock", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(rs.getInt("qty") < getQtyForValidation(rs.getString("itemName"), jTable1) + 1){
                   JOptionPane.showMessageDialog(jPanel3, rs.getString("itemName")+" is OUT OF STOCK", "Out Of Stock", JOptionPane.INFORMATION_MESSAGE);
                   return; 
                }
                jTextField2.setText(rs.getString("itemCode"));               
                jTextField3.setText(rs.getString("itemName"));
                jTextField4.setText(rs.getString("price"));
                jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, rs.getInt("qty"), 1));
                int a = Integer.parseInt(jSpinner1.getValue().toString());
                double b = Double.parseDouble(jTextField4.getText());
                double x = Double.parseDouble(jTextField26.getText());
                b = b * a;
                b = b - x;
                b = Math.round(b * 100.0) / 100.0;
                String c = String.valueOf(b);
                jTextField5.setText(c);
                ((JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setEditable(false);
            }else{
                JOptionPane.showMessageDialog(jPanel3, "Item Not Found, check if\nit is written properly", "Item Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel3, e.getMessage(), "Item Price Details", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //add item to be sold details to the table in the transaction panel
    private void saveOrder(){
        if (jTextField2.getText().isBlank() || jTextField3.getText().isBlank()) {
            return;
        }
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("select qty from stock where itemName = '"+jTextField3.getText()+"'");
            while(rs.next()){
                int q = ParseInteger(jSpinner1.getValue().toString());
                if(rs.getInt("qty") < getQtyForValidation(jTextField3.getText(), jTable1) + q){
                   JOptionPane.showMessageDialog(jPanel3, jTextField3.getText()+"is OUT OF STOCK", "Out Of Stock", JOptionPane.INFORMATION_MESSAGE);
                   return; 
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel3, e.getMessage(), "Insufficient Quantity", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int a = jTable1.getRowCount() + 1;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{a, jTextField2.getText(), jTextField3.getText(), jSpinner1.getValue().toString() , jTextField4.getText(), 
        jTextField26.getText(), jTextField5.getText()});
        
        double b = Double.parseDouble(jTextField5.getText());
        double c = Double.parseDouble(jTextField6.getText());
        c = c + b;
        String x = String.valueOf(c);
        jTextField6.setText(x);
        jTextField26.setText("0.00");
        jTextField2.setText("");
        jTextField2.requestFocusInWindow();
        jTextField3.setText("");
        jTextField4.setText("0.00");
        jTextField5.setText("0.00");
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        ((JSpinner.DefaultEditor)jSpinner1.getEditor()).getTextField().setEditable(false);
    } 
    
    //method to handle error whenever string is being cast to double
    private double ParseDouble(String string){
        if (string != null && string.length()>0) {
            try {
                return Double.parseDouble(string);
            } catch (Exception e) {
                return -1;
            }
        }
        else return 0.00;
    }
    
    private int ParseInteger(String string){
        if (string != null && string.length()>0) {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        else return 0;
    }
    
    //remove selected item from the table in the point of sales panel
    private void removeeItem(){
        if (jTable1.getSelectedRow() == -1) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if (jTable1.getSelectedRow()!=-1) {
        model.removeRow(jTable1.getSelectedRow());
        }
        
        int x = 0;
        double sub = 0.00;
        for (int i = 1; i <= model.getRowCount(); i++) {
            model.setValueAt(i, x, 0);
            sub =Double.parseDouble(model.getValueAt(x, 6).toString()) + sub;
            x++;
        }
        String subb = String.valueOf(sub);
        jTextField6.setText(subb);
        jTable1.setModel(model);
        jTextField2.requestFocusInWindow();
    }
    
    //show the payment dialog for customers
    private void saveTrans(){
        if (jTable1.getRowCount()<1) {
            return;
        }
        jDialog9.dispose();
        openDialog(jDialog9);
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    }
    
    //restore to default every thing at the point of sale panel
    private void refreshTrans(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTable1.setModel(model);
        jTextField6.setText("0.00");
        jTextField26.setText("0.00");
        jTextField2.setText("");
        jTextField2.requestFocusInWindow();
        jTextField3.setText("");
        jTextField4.setText("0.00");
        jTextField5.setText("0.00");
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    }
    
    //shortcut for load order label
    private void loadOrderHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getItemPriceDetails();
            }
        };
        
        jLabel15.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), advSearch);
        jLabel15.getActionMap().put(advSearch, advSearch);
        
        jLabel15.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), advSearch);
        jLabel15.getActionMap().put(advSearch, advSearch);
    }
    
    //shortcut for save order label
    private void saveOrderHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrder();
            }
        };
        
        jLabel16.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), advSearch);
        jLabel16.getActionMap().put(advSearch, advSearch);
        
        jLabel16.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), advSearch);
        jLabel16.getActionMap().put(advSearch, advSearch);
    }
    
    //shortcut for save transaction label
    private void saveTransHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTrans();
            }
        };
        
        jLabel17.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F5"), advSearch);
        jLabel17.getActionMap().put(advSearch, advSearch);
    }
    
    //shortcut for refresh transaction label
    private void refreshTransHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTrans();
            }
        };
        
        jLabel18.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F7"), advSearch);
        jLabel18.getActionMap().put(advSearch, advSearch);
    }
    
    //shortcut for advance search label
    private void advanceSearchHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialog(jDialog2);
            }
        };
        
        jLabel19.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F9"), advSearch);
        jLabel19.getActionMap().put(advSearch, advSearch);
    }
    
    //shortcut for remove item label
    private void removeItemHotkey(){
        Action advSearch = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeeItem();
            }
        };
        
        jLabel20.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F11"), advSearch);
        jLabel20.getActionMap().put(advSearch, advSearch);
    }
    
    //fetch record of categories and cashier whenevr the transaction panel is visible
    private void getCategory_Cashier(){
        ResultSet rs;
        jComboBox1.removeAllItems();
        jComboBox6.removeAllItems();
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select * from category");
            while (rs.next()) {                
                jComboBox1.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Retrieve Category & Cashier", JOptionPane.ERROR_MESSAGE);
        }
        
        ResultSet rst;
        try {
            Statement st = connect().createStatement();
            rst = st.executeQuery("Select * from users");
            while (rst.next()) {                
                jComboBox6.addItem(rst.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Retrieve Category & Cashier", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //fetch record from the transaction db table
    private void getTrans(){
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        int i = model.getRowCount() + 1;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select itemName, category, qty, format(unitPrice,2)unitPrice, "
                    + "format(discount,2)discount, format(totalPrice,2)totalPrice, DATE_FORMAT(date, '%W %D %b %y  %h:%i %p') "
                    + "date, cashier from transaction");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), "₦"+rs.getString("unitPrice"), 
                "₦"+rs.getString("discount"), "₦"+rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                i++;
            }
            jTable3.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transactions Records", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //transaction panel filter for Date and Date Range Radio button
    private void transFilter(){
        if (jRadioButton4.isSelected()) {
            try {
                dateFilter();
            } catch (NullPointerException e) {
            }

        }else if (jRadioButton5.isSelected()) {
            try {
            dateRange(jDateChooser2.getDate(), jDateChooser3.getDate());                
            } catch (NullPointerException e) {
            }
        }else {
            getTrans();
        }

    }
    
    //just a logical method :) to make the date match with the one in the db table
    private String formattedDate(Date date){
      SimpleDateFormat  dat = new SimpleDateFormat("d MMM yyyy");
      String dateString = dat.format(date);
      return dateString;
    }
    
    //just a logical method :) to make the date match with the one in the db table
    private String formattedDate2(Date date){
        try{
      SimpleDateFormat  dat = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = dat.format(date);
      return dateString;
    }catch (NullPointerException e){
        return null;
    }
    }
    
    // date filter in the transaction panel
    private void dateFilter(){
            ResultSet rs;
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            int i = model.getRowCount() + 1;
            try {
                Statement st = connect().createStatement();
                rs = st.executeQuery("Select itemName, category, qty, format(unitPrice,2)unitPrice, "
                        + "format(discount,2)discount, format(totalPrice,2)totalPrice, DATE_FORMAT(date, '%e %b %Y  %h:%i %p') "
                    + "date, cashier from transaction where DATE(date)='"+formattedDate2(jDateChooser1.getDate())+"'");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), "₦"+rs.getString("unitPrice"), 
                "₦"+rs.getString("discount"), "₦"+rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                i++;
            }
            jTable3.setModel(model);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transaction Filter - Date", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    //method for date range filter in transaction panel
    private void dateRange(Date date1, Date date2){
            ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        int i = model.getRowCount() + 1;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select itemName, category, qty, format(unitPrice,2)unitPrice, "
                    + "format(discount,2)discount, format(totalPrice,2)totalPrice, DATE_FORMAT(date, '%e %b %Y  %h:%i %p') "
                    + "date, cashier from transaction where CAST(date AS DATE) between '"+formattedDate2(date1)+"' and"
                    + " '"+formattedDate2(date2)+"'");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), "₦"+rs.getString("unitPrice"), 
                "₦"+rs.getString("discount"), "₦"+rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier")});
                i++;
            }
            jTable3.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jPanel6, e.getMessage(), "Transactions Filter - Date Range", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //fetch records of withdrawn items
    private void getWithdrawnItems(){
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        model.setRowCount(0);
        int i = model.getRowCount() + 1;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select itemCode, itemName, category, qty, format(unitPrice,2)unitPrice, "
                    + "format(totalPrice,2)totalPrice, DATE_FORMAT(date, '%e %b %Y  %h:%i %p') "
                    + "date, cashier, idwithdrawals from withdrawals");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getInt("itemCode"), rs.getString("itemName"), rs.getString("category"), rs.getInt("qty"), 
                "₦"+rs.getString("unitPrice"), "₦"+rs.getString("totalPrice"), rs.getString("date"), rs.getString("cashier"), rs.getInt("idwithdrawals")});
                i++;
            }
            jTable7.setModel(model);
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog6, e.getMessage(), "Withdrawn Items", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    //remove withdrawn item and return it to the db
    private void returnWithdrawnItem(){
        if (!(jTable7.getSelectedRow()==-1)) {
            for (int a : jTable7.getSelectedRows()) {
            int qty = Integer.parseInt(jTable7.getModel().getValueAt(jTable7.convertRowIndexToModel(a), 4).toString());
            int code = Integer.parseInt(jTable7.getModel().getValueAt(jTable7.convertRowIndexToModel(a), 1).toString());
            String name = jTable7.getModel().getValueAt(jTable7.convertRowIndexToModel(a), 2).toString();
                try {
                    PreparedStatement ps = connect().prepareStatement("Update stock set qty = qty +'"+qty+"'"
                            + " where itemCode ='"+code+"' or itemName ='"+name+"'");
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(jDialog6, ex.getMessage(), "Remove Item", JOptionPane.ERROR_MESSAGE);
                }
             int idwid = Integer.parseInt(jTable7.getModel().getValueAt(jTable7.convertRowIndexToModel(a), 9).toString());
                try {
                    PreparedStatement st = connect().prepareStatement("Delete from withdrawals where idwithdrawals ='"+idwid+"'");
                    st.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
                    model.removeRow(a);
                    jTable7.setModel(model);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(jDialog6, ex.getMessage(), "Remove Item", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(jDialog6, "Item(s) removed successfully", "Remove Item", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //fetch records for user to select item(s) to withdraw
    private void getStocksForWithdraw(){
        DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
        model.setRowCount(0);
        int i = model.getRowCount() + 1;
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select itemCode, itemName, category, format(price,2)price, qty from stock");
            while (rs.next()) {                
                model.addRow(new Object[]{i, rs.getInt("itemCode"), rs.getString("itemName"), rs.getString("category"), rs.getString("price"), 
                rs.getInt("qty")});
                i++;
            }
            jTable8.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog7, e.getMessage(), "New Withdrawal", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //daily report
    private void dailyReport(){
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("SELECT date_format(date, '%a %D, %b %Y') as date_List, format(sum(totalPrice),2) as Total_Price,"
                    + " format(sum(totalMarkup),2) as Total_Markup, format(sum(discount),2) as Total_discount, format(sum(costPrice * qty),2) as cp"
                    + " FROM transaction group by date_List order by MIN(date)");
            while (rs.next()) {                
                int i =0;
                i = model.getRowCount() + 1;
                model.addRow(new Object[]{i, rs.getString("date_List"), "₦"+rs.getString("Total_Price"), "₦"+rs.getString("Total_Markup")
                , "₦"+rs.getString("Total_Discount"), "₦"+rs.getString("cp")});
            }
            jTable4.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reportFilterPanel, e.getMessage(), "Daily Report", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //monthly report
    private void monthlyReport(){
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("SELECT date_format(date, '%M %Y') date_List, format(sum(totalPrice),2) as Total_Price,"
                    + " format(sum(totalMarkup),2) as Total_Markup, format(sum(discount),2) as Total_Discount, format(sum(costPrice * qty),2) as cp"
                    + " FROM transaction group by date_List order by MIN(date)");
            while (rs.next()) {                
                int i =0;
                i = model.getRowCount() + 1;
                model.addRow(new Object[]{i, rs.getString("date_List"), "₦"+rs.getString("Total_Price"), "₦"+rs.getString("Total_Markup")
                , "₦"+rs.getString("Total_Discount"), "₦"+rs.getString("cp")});
            }
            jTable4.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reportFilterPanel, e.getMessage(), "Monthly Report", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //yearly report
    private void yearlyReport(){
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("SELECT year(date) date_List, format(sum(totalPrice),2) as Total_Price,"
                    + " format(sum(totalMarkup),2) as Total_Markup, format(sum(discount),2)"
                    + " as Total_Discount, format(sum(costPrice * qty),2) as cp FROM transaction group by date_List order by MIN(date)");
            while (rs.next()) {                
                int i =0;
                i = model.getRowCount() + 1;
                model.addRow(new Object[]{i, rs.getString("date_List"), "₦"+rs.getString("Total_Price"), "₦"+rs.getString("Total_Markup")
                , "₦"+rs.getString("Total_Discount"), "₦"+rs.getString("cp")});
            }
            jTable4.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reportFilterPanel, e.getMessage(), "Yearly Report", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //report function
    private void reportFilter(){
        if (jRadioButton6.isSelected()) {
            dailyReport();
        }else if (jRadioButton7.isSelected()) {
            monthlyReport();
        }else if (jRadioButton8.isSelected()) {
            yearlyReport();
        }
    }
    
    //Avaliable stocks review
    private void getStocksReview(){
        ResultSet rs;
        
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("select sum(qty)q, format(sum(qty * costPrice),2) cp, format(sum(qty * price),2)sp,"
                    + " format(sum(qty * markup),2)markup from stock");
            while (rs.next()) {                
                jLabel34.setText("Current Stock Cost Price:  ₦ "+rs.getString("cp")); 
                jLabel35.setText("Current Stock Selling Price:  ₦ "+rs.getString("sp"));
                jLabel36.setText("Current Stock Markup Price:  ₦ "+rs.getString("markup"));
                jLabel41.setText("Current Stock Quantity:  "+rs.getInt("q"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog8, e.getMessage(), "Stock Status Review", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //review of all transactions
    private void getTransReview(){
        ResultSet rs;
        
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("SELECT sum(qty) q, format(sum(qty * costPrice),2) cp, format(sum(totalPrice),2) sp,"
                    + " format(sum(totalMarkup),2)markup from transaction");
            while (rs.next()) {                
                jLabel37.setText("Sold Stock Cost Price:  ₦ "+rs.getString("cp")); 
                jLabel38.setText("Sold Stock Selling Price:  ₦ "+rs.getString("sp"));
                jLabel42.setText("Sold Stock Markup Price:  ₦ "+rs.getString("markup"));
                jLabel39.setText("Sold Stock Quantity:  "+rs.getInt("q"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog8, e.getMessage(), "Transaction Status Review", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //review button function
    private void reviews(){
        getStocksReview();
        getTransReview();
    }
    
    //get employess details for admin supervision
    private void getEmployees(){
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select name, address, phone_number, DATE_FORMAT(last_seen, '%h:%i %p, %a %d %b %Y')last_seen from users where tag='Employee'");
            DefaultTableModel model = (DefaultTableModel) jTable9.getModel();
            model.setRowCount(0);
            while (rs.next()) {                
                model.addRow(new Object[]{rs.getString("name"), rs.getString("address"), rs.getString("phone_number"), rs.getString("last_seen")});
            }
            jTable9.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Get Employees", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //get admin detail
    private void getAdminDetail(){
        ResultSet rs;
        try {
            Statement st = connect().createStatement();
            rs = st.executeQuery("Select name from users where tag = 'Admin'");
            while (rs.next()) {                
                jLabel27.setText(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jDialog10, e.getMessage(), "Get Admin Details", JOptionPane.ERROR_MESSAGE);           
        }
    }
    
    //update users last seen
    private void updateLastSeen(){
        try {
            PreparedStatement ps;
            ps = connectQuiet().prepareStatement("Update users set last_seen =? where name='"+username+"'");
            ps.setString(1, dateTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Update Last Seen", JOptionPane.ERROR_MESSAGE);
        }catch (NullPointerException e){}
    }
    
    //connection the the db
    public Connection connect() throws SQLException {
		 Connection conn;
		try { Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_inventory?serverTimezone=UTC", "root", "");
		} catch(ClassNotFoundException| NullPointerException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error in server connection", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
		
		return conn;
	}
    
    //connection the the db the doesn't notify if an error occur
    public Connection connectQuiet() throws SQLException {
		 Connection conn;
		try { Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_inventory?serverTimezone=UTC", "root", "");
		} catch(ClassNotFoundException| NullPointerException | SQLException e) {
                    return null;
                }
		
		return conn;
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
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel cap;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog10;
    private javax.swing.JDialog jDialog11;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JDialog jDialog6;
    private javax.swing.JDialog jDialog7;
    private javax.swing.JDialog jDialog8;
    private javax.swing.JDialog jDialog9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel reportFilterPanel;
    private javax.swing.JPanel transacFilterPanel;
    // End of variables declaration//GEN-END:variables

/**
 *
 *@author AYO
 *   
    */
}
