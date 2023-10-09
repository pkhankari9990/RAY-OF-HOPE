/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.AdopterRegisteration;

import Business.Adopter.Adopter;
import Business.Adopter.AdopterDirectory;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organizations.AdopterOrganization;
import Business.Organizations.Organization;
import Business.Person.PersonDirectory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import Business.WorkQueue.AdopterWorkStatusCheckRequest;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 * Check Status J Panel
 * @author khyati
 */
public class CheckStatusJPanel extends javax.swing.JPanel {

    /**
     * Creates new form checkStatusJPanel
     */
    
    JPanel userProcessContainer;
    UserAccount account;
    Enterprise enterprise;
    EcoSystem business;
    AdopterDirectory adopterdirectory;
    AdopterOrganization adopterorganization;
    Adopter adopter;
    String bgcstatus,financestatus;
    int uid;
    PersonDirectory persondirectory;
    
    // Check Status JPanel
    public CheckStatusJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, AdopterDirectory adopterdirectory, int uid, PersonDirectory persondirectory) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.adopterdirectory = adopterdirectory;
        this.account = account;
        this.enterprise = enterprise;
        this.business = business;
        this.adopterorganization = (AdopterOrganization) organization;
        this.uid = uid;
        this.persondirectory = persondirectory;
        for (Adopter a : adopterdirectory.getAdoptersList()) {
            if (a.getUsername().equals(account.getUsername())) {
                adopter = a;
            }
        }
        populateTable();
    }
    
    private void populateTable() {
        DefaultTableModel dtms = (DefaultTableModel) tblReq.getModel();
        dtms.setRowCount(0);

        for (WorkRequest req : adopterorganization.getWorkQueue().getWorkRequestList()) {
            if (req instanceof AdopterWorkStatusCheckRequest) {
                if (req.getUserId() == adopter.getUserId()) {
                    Object[] row = new Object[dtms.getColumnCount()];
                    row[0] = req;
                    row[1] = ((AdopterWorkStatusCheckRequest) req).getBgcStatus();
                    row[2] = ((AdopterWorkStatusCheckRequest) req).getFinanceStatus();
                    row[3] = ((AdopterWorkStatusCheckRequest) req).getPersonCareStatus();
                    dtms.addRow(row);

                    bgcstatus = ((AdopterWorkStatusCheckRequest) req).getBgcStatus();
                    financestatus = ((AdopterWorkStatusCheckRequest) req).getFinanceStatus();
                }
            }
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tblRequest = new javax.swing.JScrollPane();
        tblReq = new javax.swing.JTable();
        btnProceed = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(246, 226, 187));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(71, 52, 58));
        jLabel1.setText("Track Your Progress");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 260, 30));

        tblReq.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblReq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Message", "Verification Status", "Finance Check Status", "Person Care Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRequest.setViewportView(tblReq);

        add(tblRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 860, 130));

        btnProceed.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnProceed.setText("Proceed");
        btnProceed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedActionPerformed(evt);
            }
        });
        add(btnProceed, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 150, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1810720.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 920, 400));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/364424375b4363112bce7cccd18a2d40.gif"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 920, 400));
    }// </editor-fold>//GEN-END:initComponents

    private void btnProceedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedActionPerformed
        // TODO add your handling code here:
        if (tblReq.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "Adoption request is still in process with the Investigation team");
        } else if ("Approved".equals(bgcstatus) && "Approved".equals((financestatus))) {
            PersonSelectionJPanel csjp = new PersonSelectionJPanel(userProcessContainer, account, adopterorganization, enterprise, business, adopterdirectory, uid, persondirectory);
            this.userProcessContainer.add("PersonSelectionJPanel", csjp);
            CardLayout layout = (CardLayout) this.userProcessContainer.getLayout();
            layout.next(userProcessContainer);
        } else if ("Pending".equals(bgcstatus) || "Pending".equals(financestatus)) {
            JOptionPane.showMessageDialog(null, "Adoption request is still in process with the Investigation team");
        } else
            JOptionPane.showMessageDialog(null, "Adoption request denied by Investigation Team");
        
    }//GEN-LAST:event_btnProceedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProceed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTable tblReq;
    private javax.swing.JScrollPane tblRequest;
    // End of variables declaration//GEN-END:variables
}