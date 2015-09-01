package tanytrans.config;

import java.util.Calendar;
import java.util.Date;

import tanytrans.model.Viaje;

public class Calculos {

	private static Calculos instance = null;
	
	private Calculos() {
		
	}
	
	public String toSqlDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String fecha = cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		return fecha;
	}
	
	public double calculaImporte(Viaje v) {
		double precio = v.getPrecio();
		double iva = (precio * v.getIva())/100;
		return precio + iva;
	}
	
	public boolean isNumeric(String s) {
		try {  
			double d = Double.parseDouble(s);  
		}  
		catch(NumberFormatException nfe) {  
		    return false;  
		}  
		return true;
	}
	
	public static Calculos getInstance() {
		if (instance == null) {instance = new Calculos();}
		return instance;
	}
}
