/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RandomControlPanel.java
 *
 * Created on 20 Feb 2023
 */

package modbuspal.generator.random;

/**
 * the control panel for the random generator
 * @author nnovic
 */
public class RandomControlPanel
extends javax.swing.JPanel
{
    private RandomGenerator generator;
    
    /** Creates new form RandomControlPanel 
     * @param gen the random generator whose parameters are being displayed by this component
     */
    public RandomControlPanel(RandomGenerator gen)
    {
        generator = gen;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        minTextField = new javax.swing.JTextField();
        maxTextField = new javax.swing.JTextField();
        minRelativeCheckBox = new javax.swing.JCheckBox();
        maxRelativeCheckBox = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Minimum value:");
        add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setText("Maximum value:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jLabel2, gridBagConstraints);

        minTextField.setText(String.valueOf(generator.minValue));
        minTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        minTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                minTextFieldFocusLost(evt);
            }
        });
        add(minTextField, new java.awt.GridBagConstraints());

        maxTextField.setText(String.valueOf(generator.maxValue));
        maxTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        maxTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                maxTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(maxTextField, gridBagConstraints);

        minRelativeCheckBox.setSelected(generator.relativeMin);
        minRelativeCheckBox.setText("relative");
        minRelativeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minRelativeCheckBoxActionPerformed(evt);
            }
        });
        add(minRelativeCheckBox, new java.awt.GridBagConstraints());

        maxRelativeCheckBox.setSelected(generator.relativeMax);
        maxRelativeCheckBox.setText("relative");
        maxRelativeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxRelativeCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        add(maxRelativeCheckBox, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void maxTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maxTextFieldFocusLost
        generator.maxValue = Double.parseDouble( maxTextField.getText() );
}//GEN-LAST:event_maxTextFieldFocusLost

    private void minTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_minTextFieldFocusLost
        generator.minValue = Double.parseDouble( minTextField.getText() );
}//GEN-LAST:event_minTextFieldFocusLost

    private void minRelativeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minRelativeCheckBoxActionPerformed
        generator.relativeMin = minRelativeCheckBox.isSelected();
}//GEN-LAST:event_minRelativeCheckBoxActionPerformed

    private void maxRelativeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxRelativeCheckBoxActionPerformed
        generator.relativeMax = maxRelativeCheckBox.isSelected();
}//GEN-LAST:event_maxRelativeCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    javax.swing.JCheckBox maxRelativeCheckBox;
    javax.swing.JTextField maxTextField;
    javax.swing.JCheckBox minRelativeCheckBox;
    javax.swing.JTextField minTextField;
    // End of variables declaration//GEN-END:variables

}
