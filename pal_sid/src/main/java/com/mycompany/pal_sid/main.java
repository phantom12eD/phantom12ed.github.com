/*
Aplicacion para una veterinaria con una base de datos en  mysql
*/

package com.mycompany.pal_sid;

import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyRep;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class main extends javax.swing.JFrame {
    String filtro;
    /**
     * Creates new form main
     */
    public void limpiar_jtext(){
      nombre_de_mascota.setText("");
        Sexo_de_mascota.setText("");
        Edad_de_mascota.setText("");
        caracteristica_de_mascota.setText("");
        Nombre_de_propietario.setText("");
        Apellido_materno_del_propietario.setText("");
        Apellido_paterno_de_prpietario.setText("");
        Telefono_del_propietario.setText("");
        Calle_del_propietario.setText("");
        Municipio_del_propietario.setText("");
        Colonia_del_propietario.setText("");
        NoInterior_del_propietraio.setText("");
        NOexterior_del_propietario.setText("");
        Id_usuario.setText("");
        Id_mascota.setText("");
    }
    public void pasar_datosdt_de_mascota(){
    nombre_mascota = nombre_de_mascota.getText();
    sexo_mascota = Sexo_de_mascota.getText();
    edad_mascota = Edad_de_mascota.getText();
    caracteristica_mascota = caracteristica_de_mascota.getText();
    }
    public void pasar_datosjt_del_propietario(){
       nombre_propietario = Nombre_de_propietario.getText();
       apellidopaterno_propietario = Apellido_paterno_de_prpietario.getText();
       apellidomaterno_propietario = Apellido_materno_del_propietario.getText();
       numerotelefono_propietario = Telefono_del_propietario.getText();
       municipio_propietario = Municipio_del_propietario.getText();
       colomia_propietario = Colonia_del_propietario.getText();
       calle_propietario = Calle_del_propietario.getText();
       noexterioer_propietario = NOexterior_del_propietario.getText();
       nointerior_propietario = NoInterior_del_propietraio.getText();
     
    }
    public void pasar_datos_basedatos_mascota(){
    try {
            // Establecer la conexión con la base de datos
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            
            // Crear la instrucción SQL para insertar datos
            String sql = "INSERT INTO Mascotas ( ID_Propietario,Nombre_Mascota, Sexo_Mascota, Edad_Mascota, Caract_Mascota) VALUES (?, ?, ?, ?,?)";
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1,aux_ID);
                statement.setString(2,nombre_mascota);
                statement.setString(3, sexo_mascota);
                statement.setString(4, edad_mascota);
                statement.setString(5,caracteristica_mascota);
                int filasInsertadas = statement.executeUpdate();
                 System.out.println("Se han insertado " + filasInsertadas + " filas.");
           
                JOptionPane.showMessageDialog(null, "Se guardo la información de manera correcta", "Exito!", JOptionPane.INFORMATION_MESSAGE, icono);
                // Cerrar la conexión con la base de datos
              conexion.close();
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public void pasar_datos_basedatos_propietario(){
 
     try {
            // Establecer la conexión con la base de datos
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
           
            // Crear la instrucción SQL para insertar datos
            String sql = "INSERT INTO Propietario (Nombre_Propietario,AP_Propietario,AM_Propietario, Telefono_Propietario,Municipio_Propietario,Colonia_Propietario,Calle_Propietario,NoExterior_Propietario,NOInterior_Propietario) VALUES (?, ?, ?, ?,?,?,?,?,?)";
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1,nombre_propietario);
                statement.setString(2, apellidopaterno_propietario);
                statement.setString(3, apellidomaterno_propietario);
                statement.setString(4,numerotelefono_propietario);
                statement.setString(5,municipio_propietario);
                statement.setString(6,colomia_propietario);
                statement.setString(7,calle_propietario);
                statement.setString(8,noexterioer_propietario);
                statement.setString(9,nointerior_propietario);
                  int filasInsertadas = statement.executeUpdate();
                  System.out.println("Se han insertado " + filasInsertadas + " filas.");
                 
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
         try {
            // Establecer la conexión con la base de datos
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            // Crear la instrucción SQL para insertar datos
            String sql ="select ID_Propietario from Propietario order by ID_Propietario asc" ;
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                ResultSet auxid = statement.executeQuery(sql);
                while(auxid.next()){
                aux_ID =auxid.getString("ID_Propietario");
                }
                System.out.println(aux_ID);
                
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void busqueda_usuarios(){
        ResultSet resul = null;
        String aux_ap;
      try {
            // Establecer la conexión con la base de datos
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            
            // Crear la instrucción SQL para insertar datos
            String sql = "SELECT * FROM Propietario WHERE Nombre_Propietario LIKE % "+ filtro +"%";
            try (
                
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                resul = statement.executeQuery();
                ResultSetMetaData rsmt = resul.getMetaData();
                int cantdecolum = rsmt.getColumnCount();
                
                aux_ap= resul.getObject(1).toString();
                Nombre_de_propietario.setText(aux_ap);
                int filasInsertadas = statement.executeUpdate();
                // Imprimir el número de filas afectadas
                System.out.println("Se han insertado " + filasInsertadas + " filas.");
                // Cerrar la conexión con la base de datos
               
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    private void Limpiar() {
        for (int i = 0; i < Lista_propietarios.getRowCount(); i++) {
            ModeloTabla.removeRow(i);
            i -= 1;
        }
    }
    public void pasar_datos_tabla_propietario(){
        Lista_propietarios.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent Mouse_evt){
          JTable table = (JTable) Mouse_evt.getSource();
          Point point = Mouse_evt.getPoint();
          int row = table.rowAtPoint(point);
          if (Mouse_evt.getClickCount() == 1) {
              Id_usuario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 0).toString());
              Nombre_de_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 1).toString());
              Apellido_paterno_de_prpietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 2).toString());
              Apellido_materno_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(),3).toString());
              Telefono_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 4).toString());
              Municipio_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 5).toString());
              Colonia_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 6).toString());
              Calle_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 7).toString());
              NOexterior_del_propietario.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 8).toString());
              NoInterior_del_propietraio.setText(Lista_propietarios.getValueAt(Lista_propietarios.getSelectedRow(), 9).toString());
          }
      }
    });
    }
    public void modificar(){
      try {
            // Establecer la conexión con la base de datos
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            
            // Crear la instrucción SQL para insertar datos
            String sql = "UPDATE propietario SET Nombre_Propietario = '"+Nombre_de_propietario.getText()+"', AP_Propietario = '"+
                    Apellido_paterno_de_prpietario.getText()+
                    "', AM_Propietario = '"+Apellido_materno_del_propietario.getText()
                    +"', Telefono_Propietario = '"+Telefono_del_propietario.getText()+
                    "', Municipio_Propietario = '"+Municipio_del_propietario.getText()+
                    "', Colonia_Propietario = '"+Colonia_del_propietario.getText()+
                    "', Calle_Propietario ='" +Calle_del_propietario.getText() +
                    "', NoExterior_Propietario ='"+NOexterior_del_propietario.getText()
                    +"', NOInterior_Propietario ='" +NoInterior_del_propietraio.getText()
                    +"' WHERE ID_Propietario ="+Id_usuario.getText();
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                
                int filasInsertadas = statement.executeUpdate();
                if(filasInsertadas > 0){
                JOptionPane.showMessageDialog(null, "Ha modificado correctamente los datos", "Exito!", JOptionPane.INFORMATION_MESSAGE, icono);
                }
                
                // Imprimir el número de filas afectadas
                // System.out.println("Se han insertado " + filasInsertadas + " filas.");
                // Cerrar la conexión con la base de datos
              
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    public void llenar_datos_tablamascota(){
        DefaultTableModel ModeloTabla1 = new DefaultTableModel();
        ModeloTabla1.addColumn("ID de mascota");
        ModeloTabla1.addColumn("Nombre");
        ModeloTabla1.addColumn("Sexo");
        ModeloTabla1.addColumn("Edad");
        ModeloTabla1.addColumn("Carateristica");
    try {
                String sql = "SELECT 	ID_mascota,Nombre_Mascota , Sexo_Mascota, Edad_Mascota,Caract_Mascota FROM Mascotas WHERE ID_Propietario ='"+ Id_usuario.getText() + "%'" ;
            // Establecer la conexión con la base de datos
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            
            // Crear la instrucción SQL para insertar datos
           
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                    ResultSet resul = statement.executeQuery(sql);
               ResultSetMetaData rsmt = resul.getMetaData();
            int cantdecolum = rsmt.getColumnCount();

            while (resul.next()) {
                Object[] row = new Object[cantdecolum];
                for (int i = 0; i < cantdecolum; i++) {
                    row[i] = resul.getObject(i + 1);
                }
                ModeloTabla1.addRow(row);
                lista_mascotas.setModel(ModeloTabla1);
            }

         
                // Cerrar la conexión con la base de datos
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void pasar_datos_tabla_mascotas(){
        lista_mascotas.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent Mouse_evt){
          JTable table = (JTable) Mouse_evt.getSource();
          Point point = Mouse_evt.getPoint();
          int row = table.rowAtPoint(point);
          if (Mouse_evt.getClickCount() == 1) {
              Id_mascota.setText(lista_mascotas.getValueAt(lista_mascotas.getSelectedRow(), 0).toString());
              nombre_de_mascota.setText(lista_mascotas.getValueAt(lista_mascotas.getSelectedRow(), 1).toString());
              Sexo_de_mascota.setText(lista_mascotas.getValueAt(lista_mascotas.getSelectedRow(), 2).toString());
              Edad_de_mascota.setText(lista_mascotas.getValueAt(lista_mascotas.getSelectedRow(),3).toString());
              caracteristica_de_mascota.setText(lista_mascotas.getValueAt(lista_mascotas.getSelectedRow(), 4).toString());
              
          }
      }
    });

    }
    String aux_ID = null;
    //variables usadas para concatenar la informacion de los textfield
    String nombre_mascota,sexo_mascota,edad_mascota,caracteristica_mascota;
    //variables usadas para concatenar la informacion del  propietario
      String nombre_propietario,apellidopaterno_propietario,apellidomaterno_propietario,numerotelefono_propietario,municipio_propietario,colomia_propietario,calle_propietario,noexterioer_propietario,nointerior_propietario;
       Icon icono = new ImageIcon("C:\\Users\\danie\\OneDrive\\Escritorio\\Nueva carpeta\\cheque.png");
      DefaultTableModel ModeloTabla;
      public main() {
        this.setTitle("Veterinaria");
        initComponents();
        Modificar_info_propietario.setVisible(false);
        Añadir_mascota.setVisible(false);
        panel_propietario.hide();
        
       ModeloTabla = new DefaultTableModel();
        ModeloTabla.addColumn("Codigo");
        ModeloTabla.addColumn("Nombre");
        ModeloTabla.addColumn("Apellido Paterno");
        ModeloTabla.addColumn("Apellido materno");
        ModeloTabla.addColumn("Telefono");
        ModeloTabla.addColumn("Municipio");
        ModeloTabla.addColumn("colonia");
        ModeloTabla.addColumn("Calle");
        ModeloTabla.addColumn("No.Exterior");
        ModeloTabla.addColumn("No.interior");
     


        pasar_datos_tabla_mascotas();
          pasar_datos_tabla_propietario();
      
     //lista_propietarios.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Apellido_materno_del_propietario = new javax.swing.JTextField();
        Nombre_de_propietario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nombre_de_mascota = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Edad_de_mascota = new javax.swing.JTextField();
        Sexo_de_mascota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Apellido_paterno_de_prpietario = new javax.swing.JTextField();
        Telefono_del_propietario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        usuario_viejo = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        Id_usuario = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Guardar_mascota = new javax.swing.JButton();
        NoInterior_del_propietraio = new javax.swing.JTextField();
        NOexterior_del_propietario = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        Calle_del_propietario = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        Colonia_del_propietario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Municipio_del_propietario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Modificar_info_propietario = new javax.swing.JButton();
        panel_general = new javax.swing.JPanel();
        panel_propietario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lista_propietarios = new javax.swing.JTable();
        panel_mascotas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_mascotas = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        Id_mascota = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        caracteristica_de_mascota = new javax.swing.JTextArea();
        Añadir_mascota = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Apellido_materno_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Apellido_materno_del_propietarioActionPerformed(evt);
            }
        });

        Nombre_de_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nombre_de_propietarioActionPerformed(evt);
            }
        });
        Nombre_de_propietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nombre_de_propietarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Nombre_de_propietarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Nombre_de_propietarioKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("Propietario");

        jLabel11.setText("Nombre");

        jLabel12.setText("Sexo");

        jLabel13.setText("Edad");

        jLabel14.setText("Caracteristica particular");

        nombre_de_mascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_de_mascotaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel15.setText("Mascota");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\danie\\Downloads\\perropanzon_500x (1).jpg")); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel4.setText("Nombre");

        jLabel5.setText("Aprellido paterno");

        jLabel6.setText("Apellido materno");

        Apellido_paterno_de_prpietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Apellido_paterno_de_prpietarioActionPerformed(evt);
            }
        });

        Telefono_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Telefono_del_propietarioActionPerformed(evt);
            }
        });
        Telefono_del_propietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Telefono_del_propietarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Telefono_del_propietarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Telefono_del_propietarioKeyTyped(evt);
            }
        });

        jLabel7.setText("Telefono ");

        usuario_viejo.setText("usuario registrado");
        usuario_viejo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario_viejoActionPerformed(evt);
            }
        });

        jLabel18.setText("ID de Usuario");

        Id_usuario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Id_usuarioPropertyChange(evt);
            }
        });

        jLabel17.setText("NO.Interior");

        Guardar_mascota.setIcon(new javax.swing.ImageIcon("C:\\Users\\danie\\Downloads\\2286815 (1).png")); // NOI18N
        Guardar_mascota.setText("Guardar");
        Guardar_mascota.setHideActionText(true);
        Guardar_mascota.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Guardar_mascota.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Guardar_mascota.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Guardar_mascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar_mascotaActionPerformed(evt);
            }
        });

        NoInterior_del_propietraio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoInterior_del_propietraioActionPerformed(evt);
            }
        });

        NOexterior_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOexterior_del_propietarioActionPerformed(evt);
            }
        });

        jButton7.setText("Enfermeda");

        jLabel16.setText("NO.Exterior");

        Calle_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Calle_del_propietarioActionPerformed(evt);
            }
        });

        jButton5.setText("Medicamento");

        jLabel10.setText("Calle");

        Colonia_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Colonia_del_propietarioActionPerformed(evt);
            }
        });

        jLabel9.setText("Colonia");

        Municipio_del_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Municipio_del_propietarioActionPerformed(evt);
            }
        });

        jLabel8.setText("Municipio");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Direccion");

        jButton6.setText("Consultar datos de paciente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Modificar_info_propietario.setText("Modificar");
        Modificar_info_propietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modificar_info_propietarioActionPerformed(evt);
            }
        });

        Lista_propietarios = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        Lista_propietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Lista_propietarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Lista_propietariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Lista_propietarios);

        javax.swing.GroupLayout panel_propietarioLayout = new javax.swing.GroupLayout(panel_propietario);
        panel_propietario.setLayout(panel_propietarioLayout);
        panel_propietarioLayout.setHorizontalGroup(
            panel_propietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propietarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 979, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_propietarioLayout.setVerticalGroup(
            panel_propietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_propietarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lista_mascotas = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int col){
                return false;
            }
        };
        lista_mascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        lista_mascotas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(lista_mascotas);

        javax.swing.GroupLayout panel_mascotasLayout = new javax.swing.GroupLayout(panel_mascotas);
        panel_mascotas.setLayout(panel_mascotasLayout);
        panel_mascotasLayout.setHorizontalGroup(
            panel_mascotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mascotasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panel_mascotasLayout.setVerticalGroup(
            panel_mascotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mascotasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_generalLayout = new javax.swing.GroupLayout(panel_general);
        panel_general.setLayout(panel_generalLayout);
        panel_generalLayout.setHorizontalGroup(
            panel_generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_generalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_propietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_mascotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_generalLayout.setVerticalGroup(
            panel_generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_generalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_mascotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel19.setText("ID de Mascota ");

        caracteristica_de_mascota.setColumns(20);
        caracteristica_de_mascota.setRows(5);
        jScrollPane3.setViewportView(caracteristica_de_mascota);

        Añadir_mascota.setText("Añadir Mascota");
        Añadir_mascota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir_mascotaActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nombre_de_mascota)
                                            .addComponent(Sexo_de_mascota)
                                            .addComponent(Edad_de_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(usuario_viejo)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Id_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Nombre_de_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(33, 33, 33))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(Apellido_paterno_de_prpietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Apellido_materno_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Telefono_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Id_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(Añadir_mascota)))
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Municipio_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Colonia_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Calle_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(NOexterior_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(NoInterior_del_propietraio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(Modificar_info_propietario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Guardar_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(60, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel3)
                            .addComponent(usuario_viejo))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(13, 13, 13)
                                        .addComponent(Id_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7)))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Nombre_de_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Apellido_materno_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Apellido_paterno_de_prpietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Telefono_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Id_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel19)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(nombre_de_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(Sexo_de_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(Edad_de_mascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NoInterior_del_propietraio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Calle_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Colonia_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NOexterior_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Municipio_del_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Guardar_mascota)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton6)
                                .addComponent(jButton5)
                                .addComponent(jButton7)
                                .addComponent(Modificar_info_propietario))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(Añadir_mascota)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Calle_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Calle_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Calle_del_propietarioActionPerformed

    private void Apellido_materno_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Apellido_materno_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apellido_materno_del_propietarioActionPerformed

    private void Colonia_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Colonia_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Colonia_del_propietarioActionPerformed

    private void Nombre_de_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nombre_de_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre_de_propietarioActionPerformed

    private void NOexterior_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOexterior_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NOexterior_del_propietarioActionPerformed

    private void Municipio_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Municipio_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Municipio_del_propietarioActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       String Id_M;
       
       Id_M=Id_mascota.getText();
       if ((Id_M.isEmpty())) {
            JOptionPane.showMessageDialog(rootPane, "Datos de Mascota faltantes, seleccione una mascota");
        }
       else{
        NewJFrame consulta = new NewJFrame(Id_M);
       consulta.setVisible(true);
       main.this.dispose();
       }
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void nombre_de_mascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_de_mascotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_de_mascotaActionPerformed

    private void Guardar_mascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar_mascotaActionPerformed
         try {
        if (    Nombre_de_propietario.getText().isEmpty() || 
                Apellido_paterno_de_prpietario.getText().isEmpty() || 
                Apellido_materno_del_propietario.getText().isEmpty() || 
                Telefono_del_propietario.getText().isEmpty()||
                Municipio_del_propietario.getText().isEmpty()|| 
                Colonia_del_propietario.getText().isEmpty()|| 
                Calle_del_propietario.getText().isEmpty()|| 
                NOexterior_del_propietario.getText().isEmpty() ||
                nombre_de_mascota.getText().isEmpty() ||
                Sexo_de_mascota.getText().isEmpty() || 
                Edad_de_mascota.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "No puede dejar los campos vacios", "Error!", JOptionPane.ERROR_MESSAGE);
        } 
        else {
        pasar_datosjt_del_propietario();
        pasar_datosdt_de_mascota();
        pasar_datos_basedatos_propietario();
        pasar_datos_basedatos_mascota();
            }    
        } catch (Exception e) {
        }
           
       
       
         
        limpiar_jtext();
    }//GEN-LAST:event_Guardar_mascotaActionPerformed

    private void Apellido_paterno_de_prpietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Apellido_paterno_de_prpietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apellido_paterno_de_prpietarioActionPerformed

    private void Telefono_del_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Telefono_del_propietarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Telefono_del_propietarioActionPerformed

    private void NoInterior_del_propietraioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoInterior_del_propietraioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoInterior_del_propietraioActionPerformed

    private void Telefono_del_propietarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Telefono_del_propietarioKeyTyped
        
    }//GEN-LAST:event_Telefono_del_propietarioKeyTyped

    private void usuario_viejoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuario_viejoActionPerformed
       
        if (usuario_viejo.isSelected()== true ) {
            Añadir_mascota.setVisible(true);
            Guardar_mascota.setVisible(false);//no estoy hablando
            Modificar_info_propietario.setVisible(true);
            panel_propietario.show();
            Lista_propietarios.setVisible(true);
            String labelText = Id_mascota.getText();
            if (labelText.isBlank() ) {
                
            }
            else{
                Añadir_mascota.setVisible(false);
            }
        }
        else{
           
           Modificar_info_propietario.setVisible(false);
         Añadir_mascota.setVisible(false);
            Guardar_mascota.setVisible(true);
            panel_propietario.hide();
            Lista_propietarios.setVisible(false);
            limpiar_jtext();
        }
    }//GEN-LAST:event_usuario_viejoActionPerformed

    private void Nombre_de_propietarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_de_propietarioKeyTyped
  
    }//GEN-LAST:event_Nombre_de_propietarioKeyTyped

    private void Nombre_de_propietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_de_propietarioKeyReleased
       filtro = Nombre_de_propietario.getText();
        Limpiar();
        ResultSet resul = null;
        String aux_ap;
        Statement st;
        
        if (usuario_viejo.isSelected()==true) {
        
            try {
                String sql = "SELECT * FROM Propietario WHERE Nombre_Propietario LIKE '"+ filtro + "%'" ;
            // Establecer la conexión con la base de datos
           Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Veterinaria", "root", "javer123");
            
            // Crear la instrucción SQL para insertar datos
           
            try (
                PreparedStatement statement = conexion.prepareStatement(sql)) {
                resul = statement.executeQuery(sql);
               ResultSetMetaData rsmt = resul.getMetaData();
            int cantdecolum = rsmt.getColumnCount();

            while (resul.next()) {
                Object[] row = new Object[cantdecolum];
                for (int i = 0; i < cantdecolum; i++) {
                    row[i] = resul.getObject(i + 1);
                }
                ModeloTabla.addRow(row);
                Lista_propietarios.setModel(ModeloTabla);
            }

            conexion.close();
                // Cerrar la conexión con la base de datos
            }    
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        }//--------------
    }//GEN-LAST:event_Nombre_de_propietarioKeyReleased

    private void Nombre_de_propietarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nombre_de_propietarioKeyPressed
      
        
    }//GEN-LAST:event_Nombre_de_propietarioKeyPressed

    private void Modificar_info_propietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modificar_info_propietarioActionPerformed
        nombre_propietario = Nombre_de_propietario.getText();
        
        modificar();
    }//GEN-LAST:event_Modificar_info_propietarioActionPerformed

    private void Añadir_mascotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir_mascotaActionPerformed
        pasar_datosdt_de_mascota();
        aux_ID = Id_usuario.getText();
       pasar_datos_basedatos_mascota();
        limpiar_jtext();
    }//GEN-LAST:event_Añadir_mascotaActionPerformed

    private void Lista_propietariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Lista_propietariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Lista_propietariosMouseClicked

    private void Id_usuarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Id_usuarioPropertyChange
        if ("".equals(Id_usuario.getText())) {
            panel_mascotas.hide();
        } else{
            panel_mascotas.show();
            panel_propietario.hide();
            llenar_datos_tablamascota();
        }
        
    }//GEN-LAST:event_Id_usuarioPropertyChange

    private void Telefono_del_propietarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Telefono_del_propietarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Telefono_del_propietarioKeyPressed

    private void Telefono_del_propietarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Telefono_del_propietarioKeyReleased
        char validar = evt.getKeyChar();
        String cadena = Telefono_del_propietario.getText();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo Ingrese Numeros", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {

        }

        if (cadena.length() == 2 || cadena.length() == 5 || cadena.length() == 8 || cadena.length() == 11) {
            cadena = cadena + "-";
            Telefono_del_propietario.setText(cadena);

        }

        int length = cadena.length();

        if (length == 2 || length == 5 || length == 8 || length == 11) {
            cadena = cadena + "-";
            Telefono_del_propietario.setText(cadena);
        }

        if (length > 13) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Excediste el número de dígitos", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        if (length > 0 && evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            // Verificar si el último caracter es un guion medio
            if (cadena.charAt(length - 1) == '-') {
                cadena = cadena.substring(0, length - 1);
                Telefono_del_propietario.setText(cadena);
            }
        }
    }//GEN-LAST:event_Telefono_del_propietarioKeyReleased

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido_materno_del_propietario;
    private javax.swing.JTextField Apellido_paterno_de_prpietario;
    private javax.swing.JButton Añadir_mascota;
    private javax.swing.JTextField Calle_del_propietario;
    private javax.swing.JTextField Colonia_del_propietario;
    private javax.swing.JTextField Edad_de_mascota;
    private javax.swing.JButton Guardar_mascota;
    private javax.swing.JLabel Id_mascota;
    private javax.swing.JLabel Id_usuario;
    private javax.swing.JTable Lista_propietarios;
    private javax.swing.JButton Modificar_info_propietario;
    private javax.swing.JTextField Municipio_del_propietario;
    private javax.swing.JTextField NOexterior_del_propietario;
    private javax.swing.JTextField NoInterior_del_propietraio;
    private javax.swing.JTextField Nombre_de_propietario;
    private javax.swing.JTextField Sexo_de_mascota;
    private javax.swing.JTextField Telefono_del_propietario;
    private javax.swing.JTextArea caracteristica_de_mascota;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable lista_mascotas;
    private javax.swing.JTextField nombre_de_mascota;
    private javax.swing.JPanel panel_general;
    private javax.swing.JPanel panel_mascotas;
    private javax.swing.JPanel panel_propietario;
    private javax.swing.JCheckBox usuario_viejo;
    // End of variables declaration//GEN-END:variables
}

