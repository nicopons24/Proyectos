package tanytrans.model;

public class Empleado {

	private int idEmpleado, telefono;
	private String nombre, apellidos, dni;
	
	public Empleado() {
		idEmpleado = -1;
		nombre = "";
		apellidos = "";
		dni = "";
		telefono = -1;
	}

	public Empleado(int idEmpleado, String nombre,
			String apellidos, String dni, int telefono) {
		this.idEmpleado = idEmpleado;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		return nombre+" "+apellidos;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
