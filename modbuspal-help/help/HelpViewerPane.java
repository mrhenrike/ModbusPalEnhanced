/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HelpViewerPane.java
 *
 * Created on 20 Feb 2023
 */

package modbuspal.help;

import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;

/**
 * A very basic HTMl renderer for browsing the help of modbuspal
 * @author nnovic
 */
public class HelpViewerPane
extends javax.swing.JPanel
{
    private final JEditorPane myHTMLviewer;
    private final URL myURL;


    /** Creates new form HelpViewerPane 
     * @param page the url of the page to display inside this pane
     * @throws IOException
     */
    public HelpViewerPane(URL page) throws IOException
    {
        myURL = page;
        myHTMLviewer = new JEditorPane(page);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = myHTMLviewer;

        setLayout(new java.awt.BorderLayout());

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jScrollPane1.setViewportView(jEditorPane1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * adds a HyperlinkListener to the html renderer
     * @param l the listener to add 
     */
    public void addHyperlinkListener(HyperlinkListener l)
    {
        myHTMLviewer.addHyperlinkListener(l);
    }

    /**
     * removes a HyperlinkListener to the html renderer
     * @param l the listener to remove
     */
    public void removeHyperlinkListener(HyperlinkListener l)
    {
        myHTMLviewer.removeHyperlinkListener(l);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}