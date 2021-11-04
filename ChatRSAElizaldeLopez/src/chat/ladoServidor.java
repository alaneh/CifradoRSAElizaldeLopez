
package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import static javax.swing.JOptionPane.showMessageDialog;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class ladoServidor extends javax.swing.JFrame {

    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private Random r;
    static ServerSocket ss;
    static ServerSocket ss2;
    static ServerSocket ss3;
    static Socket s;
    static Socket s2;
    static Socket s3;
    static DataInputStream dis;
    static DataInputStream disN;
    static DataInputStream disD;
    static DataOutputStream dos;
    static DataOutputStream dosN;
    static DataOutputStream dosD;

    /**
     * Creates new form ladoServidor
     */
    public ladoServidor() {
        initComponents();
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public ladoServidor(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public BigInteger bigIntObtenerD() {
        return (d);
    }

    public BigInteger bigIntObtenerN() {
        return (N);
    }
    // Encrypt message

    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypt message
    static public byte[] decrypt(byte[] message, BigInteger valD, BigInteger valN) {
        return (new BigInteger(message)).modPow(valD, valN).toByteArray();
    }

    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtLadoServidorVentana = new javax.swing.JTextArea();
        txtMensajeServer = new javax.swing.JTextField();
        btnEnviarServidor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtLadoServidorVentana.setColumns(20);
        txtLadoServidorVentana.setRows(5);
        jScrollPane1.setViewportView(txtLadoServidorVentana);

        txtMensajeServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMensajeServerActionPerformed(evt);
            }
        });

        btnEnviarServidor.setBackground(new java.awt.Color(87, 94, 216));
        btnEnviarServidor.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviarServidor.setText("Enviar");
        btnEnviarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarServidorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CHAT DEL SERVIDOR");

        jLabel2.setText("Escribe tu mensaje debajo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMensajeServer, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarServidor))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensajeServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviarServidor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnEnviarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarServidorActionPerformed
        try {

            String mensajecliente = "";
            mensajecliente = txtMensajeServer.getText();
            // String enc = "";
            //encriptarMensaje em = new encriptarMensaje(mensajecliente);
            // byte[] msjen = em.encrypt(mensajecliente.getBytes());
            //String res = new String(em.bytesToString(msjen));
            txtMensajeServer.setText("");
            //showMessageDialog(null, bytesToString(mensajecliente.getBytes()));
            byte[] arres = encrypt(mensajecliente.getBytes());
            byte[] desencriptado = decrypt(arres, bigIntObtenerD(), bigIntObtenerN());
            System.out.println("Bytes enviados: " + bytesToString(arres));
            System.out.println("Decrypted String: " + new String(desencriptado));
            //showMessageDialog(null, Arrays.toString(arres));
            dos.writeUTF(Arrays.toString(arres));
            System.out.println("Este es el valor de N: " + bigIntObtenerN().toString());
            System.out.println("Este es el valor de d: " + bigIntObtenerD().toString());
            dosN.writeUTF(bigIntObtenerN().toString());
            dosD.writeUTF(bigIntObtenerD().toString());
            txtLadoServidorVentana.setText(txtLadoServidorVentana.getText() + "\n (Descifrado) Usted: " + mensajecliente);
            txtLadoServidorVentana.setText(txtLadoServidorVentana.getText() + "\n (Cifrado) Usted: " + bytesToString(desencriptado));
        } catch (Exception e) {

        }
    
// TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarServidorActionPerformed
    
    private void txtMensajeServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMensajeServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensajeServerActionPerformed
    /**
     * /* @param args the command line arguments
     *
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(ladoServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ladoServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ladoServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ladoServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ladoServidor().setVisible(true);
            }
        });

        try {
            String msjentrada = "";
            String msjRsaD = "";
            String msjRsaN = "";
            ss = new ServerSocket(1449);
            ss2 = new ServerSocket(1450);
            ss3 = new ServerSocket(1451);
            s = ss.accept();
            s2 = ss2.accept();
            s3 = ss3.accept();
            dis = new DataInputStream(s.getInputStream());
            disN = new DataInputStream(s2.getInputStream());
            disD = new DataInputStream(s3.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            dosN = new DataOutputStream(s2.getOutputStream());
            dosD = new DataOutputStream(s3.getOutputStream());
            while (!msjentrada.equals("Exit")) {

                msjentrada = dis.readUTF();
                System.out.println("Este es el mensaje del cliente:" + msjentrada);
                msjRsaN = disN.readUTF();
                msjRsaD = disD.readUTF();
                BigInteger rsaN = new BigInteger(msjRsaN);
                BigInteger rsaD = new BigInteger(msjRsaD);
                String joinedMinusBrackets = msjentrada.substring(1, msjentrada.length() - 1);
                // byte[] bdes = msjentrada.getBytes();
                String[] resplit = joinedMinusBrackets.split(", ");
                byte[] arrenc = new byte[resplit.length];
                for (int i = 0; i < resplit.length; ++i) {
                    arrenc[i] = Byte.parseByte(resplit[i]);
                }
                System.out.println("Bytes recibidos: " + arrenc);
                //showMessageDialog(null, Arrays.toString(arrenc));
                System.out.println("Recibí este valor de D: " + msjRsaD);
                System.out.println("Recibí este valor de N: " + msjRsaN);
                byte[] desencriptado = decrypt(arrenc, rsaD, rsaN);
                System.out.println("Desencriptando bytes: " + bytesToString(desencriptado));
                System.out.println("Cadena desencriptada: " + new String(desencriptado));
                String msjdesenc = new String(desencriptado);
                txtLadoServidorVentana.setText(txtLadoServidorVentana.getText() + "\n (Descifrado) Cliente: " + msjdesenc);
                //bytesToString(desencriptado)
                txtLadoServidorVentana.setText(txtLadoServidorVentana.getText() + "\n (Cifrado) Cliente: " + bytesToString(desencriptado));
                
            }

        } catch (Exception e) {

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea txtLadoServidorVentana;
    private javax.swing.JTextField txtMensajeServer;
    // End of variables declaration//GEN-END:variables
}
