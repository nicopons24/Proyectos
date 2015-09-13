package tanytrans.config;

import java.util.Calendar;
import java.util.Date;

import tanytrans.model.Viaje;

public class Calculos {

	private static Calculos instance = null;
	
	private Calculos() {
		
	}
	
	public static String toSqlDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String fecha = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
		return fecha;
	}
	
	public static String[] separateDate(String s) {
		String[] i = new String[3];
		int a = s.indexOf("-");
		int b = s.indexOf("-", a+1);
		i[0] = s.substring(0, a);// year
		int m = Integer.parseInt(s.substring(a+1, b))-1;// month
		if (m > 0 && m < 9) {i[1] = "0"+m; }
		else { i[1] = ""+m;}
		int d = Integer.parseInt(s.substring(b+1));// day
		if (d > 0 && d < 9) {i[2] = "0"+d; }
		else { i[2] = ""+d;}
		return i;
	}
	
	public static double calculaImporte(Viaje v) {
		double precio = v.getPrecio();
		double iva = v.getIva();
		return precio + iva;
	}
	
	public static boolean isNumeric(String s) {
		try {  
			Double.parseDouble(s);  
		}  
		catch(NumberFormatException nfe) {  
		    return false;  
		}  
		return true;
	}
	
	public static String numFacturaFormat(int num) {
		return String.format("%010d", num);
	}
	
	public static String priceFormat(double num) {
		return String.format("%.2f", num)+" €";
	}
	
	public static boolean isWindows(String os) {
		return (os.indexOf("win") >= 0);
	}

	public static boolean isMac(String os) {
		return (os.indexOf("mac") >= 0);
	}

	public static boolean isUnix(String os) {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 );
	}
	
	public static Calculos getInstance() {
		if (instance == null) {instance = new Calculos();}
		return instance;
	}
}
