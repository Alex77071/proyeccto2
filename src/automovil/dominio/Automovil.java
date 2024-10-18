package automovil.dominio;


/*Práctica 1
Paradigmas de Programación II
Iván Alexander Cortés Pérez
Grupo 512*/
import java.util.*;

public class Automovil {
	// Número libre (1).
	private int kilometraje;

	// Número con rango (2).
	private double precio;

	// Texto libre (3).
	private String modelo;

	// Texto con formato predefinido (4).
	/*Carácter 1 al 3: Identifica al fabricante y el país de origen
	 *Carácter 4 al 9: Describen atributos del vehículo como el modelo, tipo de motor, y tipo de carrocería
	 *Carácter 10: Año de fabricación. Utiliza una letra o un número para indicar el año de producción del vehículo
	 *Carácter 11: Planta de ensamblaje.
	 *Carácter 12 al 17: Número de serie único del vehículo. 
	 */
	private String numSerie;

	// Fecha (5).
	private Date fechaFabricacion;

	// Dato obtenido de opciones mutuamente excluyentes fijas (6).
	// 1. Diesel
	// 2. Gasolina
	// 3. Eléctrico
	private String tipoCombustible;

	// Dato obtenido de opciones mutuamente excluyentes dinámicas (7).
	private String marcaAuto;

	// Dato multivalorado de opciones no excluyentes fijas (8).
	// 1. Frontales
	// 2. Laterales
	// 3. Cortina
	private ArrayList<String> sistemaSeguridad;

	// Dato multivalorado de opciones no excluyentes dinámicas (9).
	// 1. Aire Acondicionado
	// 2. Asientos de Piel
	// 3. Faros Niebla
	private ArrayList<String> caracteristicasOpcionales;

	// Ruta (10).
	private String ruta;

	// Métodos get por defecto para cada variable.

	public int getKilometraje() {
		return kilometraje;
	}

	public double getPrecio() {
		return precio;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public String getMarcaAuto() {
		return marcaAuto;
	}

	public ArrayList<String> getSistemaSeguridad() {
		return sistemaSeguridad;
	}

	public ArrayList<String> getCaracteristicasOpcionales() {
		return caracteristicasOpcionales;
	}

	public String getRuta() {
		return ruta;
	}
 // Corregido los get por defecto
	
	
	
// Para las variables 5-10, crear métodos set por defecto.	
	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public void setMarcaAuto(String marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

	public void setSistemaSeguridad(ArrayList<String> sistemaSeguridad) {
		this.sistemaSeguridad = sistemaSeguridad;
	}

	public void setCaracteristicasOpcionales(ArrayList<String> caracteristicasOpcionales) {
		this.caracteristicasOpcionales = caracteristicasOpcionales;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	// Transformará el dato String al tipo de dato de la variable y llamará al
	// segundo set.
	public void setKilometraje(String kilometrajeStr) {
		kilometrajeStr = kilometrajeStr.trim();
		setKilometraje(Integer.parseInt(kilometrajeStr));
	}
	
	public void setKilometraje(int kilometraje) {
		if (kilometraje < 0) {
			throw new IllegalArgumentException();
		}
		this.kilometraje = kilometraje;
	}
	
	// Transformará el dato String al tipo de dato de la variable y llamará al
	// segundo set. (variable 2).
	
	public void setPrecio(String precioStr) {
		precioStr = precioStr.trim();
		setPrecio(Double.parseDouble(precioStr));
	}
	
	public void setPrecio(double precio) {
		if (precio > 250000
				&& precio < 1000000) {
			this.precio = precio;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	// Para la variable 3 crear un método set que quite los espacios de los extremos
	// y valide que no esté vacío el dato.
	
	public void setModelo(String modelo) {
		modelo = modelo.trim();
		// Validación de modelo no vacío
		if (modelo.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.modelo = modelo.trim();
	}
	
	
	// Para la variable 4 crear un método set que quite los espacios de los
	// extremos y valide el formato del dato.
	public void setNumSerie(String numSerie) {
		numSerie = numSerie.trim();
		// Validación de formato de número de serie
		if (!numSerie.matches("[A-Za-z0-9]{12}")) {
			throw new IllegalArgumentException();
		}
		this.numSerie = numSerie;
	}
	

	// Constructor sin parámetros que establezca valores por defecto para cada
	// variable.
	public Automovil() {
		this.kilometraje = 0;
		this.precio = 0.0;
		this.modelo = "";
		this.numSerie = "";
		this.tipoCombustible = "";
		this.marcaAuto = "";
		this.fechaFabricacion = null;
		this.sistemaSeguridad = null;
		this.caracteristicasOpcionales = null;
		this.ruta = "";
	}



	

	// Método toString que regresará la representación en cadena de la entidad.
	public String toString() {
		return numSerie + marcaAuto + precio;
	}
	


}