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
		String fecha = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		return fecha;
	}
	
	public int[] separateDate(String s) {
		int[] i = new int[3];
		int a = s.indexOf("-");
		int b = s.indexOf("-", a+1);
		i[0] = Integer.parseInt(s.substring(0, a));
		i[1] = Integer.parseInt(s.substring(a+1, b))-1;
		i[2] = Integer.parseInt(s.substring(b+1));
		return i;
	}
	
	public double calculaImporte(Viaje v) {
		double precio = v.getPrecio();
		double iva = (precio * v.getIva())/100;
		return precio + iva;
	}
	
	public boolean isNumeric(String s) {
		try {  
			Double.parseDouble(s);  
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
