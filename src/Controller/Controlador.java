package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Cercano;
import Model.Trabajo;
import Model.Persistence.AmigoDAO;
import Vista.Ventana;

public class Controlador implements ActionListener {

	Ventana ventana;
	AmigoDAO amigo;
	ArrayList<Cercano> cercano = new ArrayList<Cercano>();
	ArrayList<Trabajo> trabajo = new ArrayList<Trabajo>();

	public Controlador() {

		this.ventana = new Ventana();
		amigo = new AmigoDAO();
		actionListener(this);

	}

	private void actionListener(ActionListener escuchador) {

		ventana.getPanel().getBoton_registrar().addActionListener(escuchador);
		ventana.panel_inicial.boton_amigos.addActionListener(escuchador);
		ventana.panel_inicial.boton_trabajo.addActionListener(escuchador);
		ventana.panel_trabajo.boton_registrar.addActionListener(escuchador);

		ventana.boton_regresar.addActionListener(escuchador);
		ventana.panel_correoc.boton_a�adir.addActionListener(escuchador);
		ventana.panel_correot.boton_a�adir1.addActionListener(escuchador);
		ventana.panel_correoc.boton_eliminar.addActionListener(escuchador);
		ventana.panel_correot.boton_eliminar.addActionListener(escuchador);
		ventana.panel_correoc.boton_modificar.addActionListener(escuchador);
		ventana.panel_correot.boton_modificar.addActionListener(escuchador);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(ventana.panel_correoc.boton_modificar== e.getSource()) {
			
			String correo=ventana.panel_correoc.campo_correo.getText();
			Cercano aux = amigo.buscarContactoCercano(correo,cercano);
			if (ventana.panel_correoc.campo_correo.getText().contentEquals("")) {
				ventana.mostrarMensajes("DEBE DIGITAR UN CORREO");	
			} else if(aux==null) {
				ventana.mostrarMensajes("EL CONTACTO NO EXISTE");
			}else {
				cercano.remove(aux);
				ventana.panel.setVisible(true);
				ventana.panel_correoc.setVisible(false);
				ventana.panel.campo_nombre.setText("");
				ventana.panel.campo_pais.setText("");
				ventana.panel.campo_telefono.setText("");
				ventana.panel.campo_correo.setText("");	
			}
		}
		if(ventana.panel_correot.boton_modificar== e.getSource()) {
			
			String correo=ventana.panel_correot.campo_correot.getText();
			Trabajo aux = amigo.buscarContactoTrabajo(correo,trabajo);
			if (ventana.panel_correot.campo_correot.getText().contentEquals("")) {
				ventana.mostrarMensajes("DEBE DIGITAR UN CORREO");	
			} else if(aux==null) {
				ventana.mostrarMensajes("EL CONTACTO NO EXISTE");
			}else {
				trabajo.remove(aux);
				ventana.panel_trabajo.setVisible(true);
				ventana.panel_correot.setVisible(false);
				ventana.panel_trabajo.campo_correo.setText("");
				ventana.panel_trabajo.campo_empresa .setText("");
				ventana.panel_trabajo.campo_nombre .setText("");
				ventana.panel_trabajo.campo_pais .setText("");	
				ventana.panel_trabajo.campo_telefono.setText("");	
				
			}
			
		}
		
		
		if (ventana.panel_inicial.boton_amigos == e.getSource()) {
			ventana.panel_inicial.setVisible(false);
			ventana.panel_correoc.setVisible(true);

		}
		if (ventana.panel_correoc.boton_a�adir == e.getSource()) {
			ventana.panel_correoc.setVisible(false);
			ventana.panel.setVisible(true);
		}
		if (ventana.getPanel().getBoton_registrar() == e.getSource()) {
			
			if (amigo.agregarContactoCercano(ventana.panel.campo_nombre.getText(), ventana.panel.campo_pais.getText(),
					ventana.panel.campo_telefono.getText(), ventana.panel.campo_correo.getText(), cercano)) {
				ventana.mostrarMensajes("Contacto a�adido");
			} else {
				ventana.mostrarMensajes("Esta persona ya existe");
			}
			ventana.panel_inicial.setVisible(true);
			ventana.panel.setVisible(false);
		}

		if (ventana.panel_inicial.boton_trabajo == e.getSource()) {
			ventana.panel_inicial.setVisible(false);
			ventana.panel_correot.setVisible(true);
		}

		if (ventana.panel_correot.boton_a�adir1 == e.getSource()) {
			ventana.panel_correot.setVisible(false);
			ventana.panel_trabajo.setVisible(true);
		}
		if (ventana.panel_trabajo.boton_registrar == e.getSource()) {
			if (amigo.agregarContactoTrabajo(ventana.panel_trabajo.campo_nombre.getText(),
					ventana.panel_trabajo.campo_empresa.getText(), ventana.panel_trabajo.campo_pais.getText(),
					ventana.panel_trabajo.campo_telefono.getText(), ventana.panel_trabajo.campo_correo.getText(),
					trabajo)) {
				ventana.mostrarMensajes("Contacto a�adido");
			} else {
				ventana.mostrarMensajes("Esta persona ya existe");
			}
			ventana.panel_inicial.setVisible(true);
			ventana.panel_trabajo.setVisible(false);
		}

		if (ventana.panel_correoc.boton_eliminar == e.getSource()) {

			String mensaje = "";
			String correo = ventana.panel_correoc.campo_correo.getText();

			if (amigo.buscarContactoCercano(correo, cercano) != null) {
				if (amigo.eliminarContactoCercano(correo, cercano)) {
					mensaje = "Se ha eliminado el contacto";
					ventana.mostrarMensajes(mensaje);
				} else {
					mensaje = "No se ha eliminado al usuario";
					ventana.mostrarMensajes(mensaje);
				}
			} else {
				mensaje = "El contacto con este correo no existe";
				ventana.mostrarMensajes(mensaje);
			}

		}
		
		if(ventana.panel_correot.boton_eliminar== e.getSource()) {
			String mensaje = "";
			String correo= ventana.panel_correot.campo_correot.getText();
			
			if (amigo.buscarContactoTrabajo(correo, trabajo)!= null) {
				if(amigo.eliminarContactoTrabajo(correo, trabajo)) {
					mensaje="Se ha eliminado el contacto";
					ventana.mostrarMensajes(mensaje);
				}else {
					mensaje = "No se ha eliminado el usuario";
					ventana.mostrarMensajes(mensaje);
				}
			}else {
				mensaje="El contacto con este correo no existe";
				ventana.mostrarMensajes(mensaje);
			}
		}

		if (ventana.boton_regresar == e.getSource()) {
			ventana.panel_inicial.setVisible(true);
			ventana.panel_correoc.setVisible(false);
			ventana.panel_correot.setVisible(false);
			ventana.panel_trabajo.setVisible(false);
			ventana.panel.setVisible(false); 
			//hola
		}

	}

}
