/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;
import Aplicacion.*;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class VInstalaciones extends javax.swing.JDialog {
    FachadaAplicacion fachadaAp;
    ModeloTablaTrabajadores modTablaTrabajadores;
    ModeloTablaEquipo modTablaEquipos;

    /**
     * Creates new form VInstalaciones
     */
    public VInstalaciones(java.awt.Frame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fachadaAp = fa;
        modTablaTrabajadores = new ModeloTablaTrabajadores();
        modTablaEquipos = new ModeloTablaEquipo();
        initComponents();
        tablaTrabajadores.setModel(modTablaTrabajadores);
        tablaTrabajadores.setFillsViewportHeight(true);
        tablaEquipo.setModel(modTablaEquipos);
        tablaEquipo.setFillsViewportHeight(true);
        //Lógica para que combobox muestre los id's de las salas existentes
        cargarSalas();
        configurarTablaEquipo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTituloVentana = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelSalas = new javax.swing.JPanel();
        labelFraseSala = new javax.swing.JLabel();
        comboBoxSala = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTrabajadores = new javax.swing.JTable();
        panelEquipo = new javax.swing.JPanel();
        labelFraseEquipo = new javax.swing.JLabel();
        comboBoxEquipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEquipo = new javax.swing.JTable();
        botonAnadir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        labelNuevo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombretextfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tipotextfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        modelotextfield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        preciotextfield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        marcatextfield = new javax.swing.JTextField();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTituloVentana.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        labelTituloVentana.setText("Instalaciones");

        labelFraseSala.setText("Trabajadores asignados a la sala:");

        comboBoxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSalaActionPerformed(evt);
            }
        });

        tablaTrabajadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Cargo", "Sueldo"
            }
        ));
        jScrollPane1.setViewportView(tablaTrabajadores);

        javax.swing.GroupLayout panelSalasLayout = new javax.swing.GroupLayout(panelSalas);
        panelSalas.setLayout(panelSalasLayout);
        panelSalasLayout.setHorizontalGroup(
            panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalasLayout.createSequentialGroup()
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(labelFraseSala)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSalasLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        panelSalasLayout.setVerticalGroup(
            panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSalasLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelSalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFraseSala)
                    .addComponent(comboBoxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Salas", panelSalas);

        labelFraseEquipo.setText("Equipo asignado a la sala:");

        comboBoxEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEquipoActionPerformed(evt);
            }
        });

        tablaEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo", "Modelo"
            }
        ));
        jScrollPane2.setViewportView(tablaEquipo);

        botonAnadir.setText("Añadir");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });

        botonGuardar.setText("Editar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        labelNuevo.setText("Nuevo:");

        jLabel2.setText("Nombre");

        nombretextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretextfieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo");

        jLabel4.setText("Modelo");

        jLabel5.setText("Precio [€.cents]");

        jLabel1.setText("Marca");

        javax.swing.GroupLayout panelEquipoLayout = new javax.swing.GroupLayout(panelEquipo);
        panelEquipo.setLayout(panelEquipoLayout);
        panelEquipoLayout.setHorizontalGroup(
            panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEquipoLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(labelFraseEquipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelEquipoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelNuevo)
                        .addGroup(panelEquipoLayout.createSequentialGroup()
                            .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelEquipoLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nombretextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3))
                                .addGroup(panelEquipoLayout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(marcatextfield)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEquipoLayout.createSequentialGroup()
                                    .addComponent(tipotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel5))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEquipoLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(modelotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEquipoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(preciotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEquipoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonGuardar)
                            .addComponent(botonAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
        );
        panelEquipoLayout.setVerticalGroup(
            panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEquipoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFraseEquipo)
                    .addComponent(comboBoxEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEquipoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(botonAnadir)
                        .addGap(40, 40, 40)
                        .addComponent(botonGuardar)
                        .addGap(50, 50, 50)
                        .addComponent(botonBorrar))
                    .addGroup(panelEquipoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(labelNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombretextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tipotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(preciotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelotextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(marcatextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        jTabbedPane1.addTab("Equipo", panelEquipo);

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTituloVentana)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonSalir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTituloVentana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(botonSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void comboBoxSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSalaActionPerformed
        // TODO add your handling code here:
        String salaSeleccionada = (String) comboBoxSala.getSelectedItem();
        if (salaSeleccionada != null) {
            int idSalaInt = Integer.parseInt(salaSeleccionada); // Convertir idSala a entero
            cargarTrabajadoresSala(idSalaInt);
        }
    }//GEN-LAST:event_comboBoxSalaActionPerformed

    private void comboBoxEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEquipoActionPerformed
        // TODO add your handling code here:
        String salaSeleccionada = (String) comboBoxEquipo.getSelectedItem();
        if (salaSeleccionada != null) {
            int idSalaInt = Integer.parseInt(salaSeleccionada); // Convertir idSala a entero
            cargarEquiposSala(idSalaInt);
        }
    }//GEN-LAST:event_comboBoxEquipoActionPerformed

    private void nombretextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretextfieldActionPerformed
        //IGNORAR
    }//GEN-LAST:event_nombretextfieldActionPerformed

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        // Validar que todos los campos estén completos
        if (nombretextfield.getText().isEmpty() || tipotextfield.getText().isEmpty() || 
            preciotextfield.getText().isEmpty() || marcatextfield.getText().isEmpty() || modelotextfield.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener los datos del formulario
            String nombre = nombretextfield.getText();
            String tipo = tipotextfield.getText();
            String modelo = modelotextfield.getText();
            String marca = marcatextfield.getText();
            double precio = Double.parseDouble(preciotextfield.getText()); // Precio del equipo
            int idSala = Integer.parseInt((String) comboBoxEquipo.getSelectedItem()); // ID de la sala seleccionada

            // Llamar a la fachada para añadir el equipo
            boolean exito = fachadaAp.anadirEquipoSala(idSala, nombre, tipo, modelo, precio, marca);

            if (exito) {
                // Actualizar la tabla de equipos
                cargarEquiposSala(idSala);
                javax.swing.JOptionPane.showMessageDialog(this, "Equipo añadido correctamente.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al añadir el equipo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos en los campos numéricos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        // Validar que haya un equipo seleccionado
        int selectedRow = tablaEquipo.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un equipo para eliminar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener el ID del equipo seleccionado
            int idEquipo = Integer.parseInt(tablaEquipo.getValueAt(selectedRow, 0).toString());
            int idSala = Integer.parseInt((String) comboBoxEquipo.getSelectedItem()); // ID de la sala seleccionada

            // Confirmar la eliminación
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este equipo?", "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm != javax.swing.JOptionPane.YES_OPTION) {
                return;
            }

            // Llamar a la fachada para eliminar el equipo
            boolean exito = fachadaAp.eliminarEquipoSala(idEquipo);

            if (exito) {
                // Actualizar la tabla de equipos
                cargarEquiposSala(idSala);
                javax.swing.JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar el equipo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al procesar los datos del equipo seleccionado.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        // Validar que todos los campos estén completos
        if (nombretextfield.getText().isEmpty() || tipotextfield.getText().isEmpty() || 
            preciotextfield.getText().isEmpty() || marcatextfield.getText().isEmpty() || modelotextfield.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener los datos del formulario
            String nombre = nombretextfield.getText();
            String tipo = tipotextfield.getText();
            String modelo = modelotextfield.getText();
            String marca = marcatextfield.getText();
            double precio = Double.parseDouble(preciotextfield.getText());
            int idSala = Integer.parseInt((String) comboBoxEquipo.getSelectedItem());
            int selectedRow = tablaEquipo.getSelectedRow();

            if (selectedRow == -1) {
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione un equipo para editar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el ID del equipo seleccionado
            int idEquipo = Integer.parseInt(tablaEquipo.getValueAt(selectedRow, 0).toString());

            // Llamar a la fachada para actualizar el equipo
            boolean exito = fachadaAp.editarEquipoSala(idEquipo, idSala, nombre, tipo, modelo, precio, marca);

            if (exito) {
                // Actualizar la tabla de equipos
                cargarEquiposSala(idSala);
                javax.swing.JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el equipo.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos en los campos numéricos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonGuardarActionPerformed
    
    private void cargarSalas() {
        List<Integer> salas = fachadaAp.obtenerSalas(); // Obtener las salas desde la fachada

        comboBoxSala.removeAllItems();
        comboBoxEquipo.removeAllItems();

        for (Integer sala : salas) {
            comboBoxSala.addItem(sala.toString());
            comboBoxEquipo.addItem(sala.toString());
        }

        // Seleccionar la primera sala por defecto y cargar los datos
        if (!salas.isEmpty()) {
            comboBoxSala.setSelectedIndex(0);
            comboBoxEquipo.setSelectedIndex(0);
            cargarTrabajadoresSala(salas.get(0));
            cargarEquiposSala(salas.get(0));
        }
    }
    
    /*
    private void cargarTrabajadoresSala(String idSala) {
        List<Trabajador> trabajadores = fachadaAp.obtenerTrabajadoresSala(idSala); // Obtener trabajadores desde la fachada
        modTablaTrabajadores.setFilas(trabajadores); // Actualizar el modelo de la tabla
    }*/
    
    private void cargarTrabajadoresSala(Integer idSala) {
        try {
            //int idSalaInt = Integer.parseInt(idSala); // Convertir idSala a entero
            List<Trabajador> trabajadores = fachadaAp.obtenerTrabajadoresSala(idSala); // Cambiar el método para aceptar un entero
            modTablaTrabajadores.setFilas(trabajadores); // Actualizar el modelo de la tabla
        } catch (NumberFormatException e) {
            System.err.println("Error: idSala no es un número válido.");
        }
    }   
    
    /*
    private void cargarEquiposSala(String idSala) {
        List<Equipo> equipos = fachadaAp.obtenerEquiposSala(idSala); // Obtener equipos desde la fachada
        modTablaEquipos.setFilas(equipos); // Actualizar el modelo de la tabla
    }*/
    
    private void cargarEquiposSala(Integer idSala) {
        try {
            //int idSalaInt = Integer.parseInt(idSala); // Convertir idSala a entero
            List<Equipo> equipos = fachadaAp.obtenerEquiposSala(idSala); // Cambiar el método para aceptar un entero
            modTablaEquipos.setFilas(equipos); // Actualizar el modelo de la tabla
        } catch (NumberFormatException e) {
            System.err.println("Error: idSala no es un número válido.");
        }
    }
    
    private void configurarTablaEquipo() {
        // Agregar un listener para detectar selección en la tabla
        tablaEquipo.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tablaEquipo.getSelectedRow() != -1) {
                int selectedRow = tablaEquipo.getSelectedRow();
                try {
                    // Obtener el ID del equipo seleccionado desde la tabla
                    int idEquipo = Integer.parseInt(tablaEquipo.getValueAt(selectedRow, 0).toString());

                    // Consultar la base de datos para obtener todos los atributos del equipo
                    Equipo equipo = fachadaAp.obtenerEquipoPorId(idEquipo);

                    // Mostrar los datos en los TextField
                    if (equipo != null) {
                        nombretextfield.setText(equipo.getNombre());
                        tipotextfield.setText(equipo.getTipo());
                        modelotextfield.setText(equipo.getModelo());
                        preciotextfield.setText(String.valueOf(equipo.getPrecio()));
                        marcatextfield.setText(equipo.getMarca());
                    }
                } catch (Exception e) {
                    System.err.println("Error al cargar los datos del equipo seleccionado: " + e.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JComboBox<String> comboBoxEquipo;
    private javax.swing.JComboBox<String> comboBoxSala;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelFraseEquipo;
    private javax.swing.JLabel labelFraseSala;
    private javax.swing.JLabel labelNuevo;
    private javax.swing.JLabel labelTituloVentana;
    private javax.swing.JTextField marcatextfield;
    private javax.swing.JTextField modelotextfield;
    private javax.swing.JTextField nombretextfield;
    private javax.swing.JPanel panelEquipo;
    private javax.swing.JPanel panelSalas;
    private javax.swing.JTextField preciotextfield;
    private javax.swing.JTable tablaEquipo;
    private javax.swing.JTable tablaTrabajadores;
    private javax.swing.JTextField tipotextfield;
    // End of variables declaration//GEN-END:variables
}
