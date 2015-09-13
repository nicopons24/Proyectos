package tanytrans.reports.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import tanytrans.config.Calculos;
import tanytrans.model.Cliente;
import tanytrans.model.Factura;
import tanytrans.model.MiEmpresa;
import tanytrans.model.Viaje;

public class FacturaDatasource implements JRDataSource {
	
	private static final String DIR_EMP = "dirEmpresa";
	private static final String LOC_EMP = "locEmpresa";
	private static final String PAIS_EMP = "paisEmpresa";
	private static final String NUM_EMP = "numEmpresa";
	private static final String TEL1 = "tel1";
	private static final String TEL2 = "tel2";
	private static final String FAX = "fax";
	private static final String EMAIL = "email";
	private static final String LOC_PAIS_EMP = "locPaisEmpresa";
	private static final String BANCO_EMP = "bancoEmpresa";
	private static final String IBAN_EMP = "ibanEmpresa";
	private static final String TIT_EMP = "titularEmpresa";
	private static final String NOM_EMP = "nomEmpresa";
	private static final String NUM_FAC = "numFactura";
	private static final String FECHA = "fecha";
	private static final String LOC_FAC = "locFactura";
	private static final String NOM_CLIE = "nomCliente";
	private static final String DIR_CLIE = "dirCliente";
	private static final String LOC_PAIS_CLIE = "locPaisCliente";
	private static final String NUM_CLIE = "numCliente";
	private static final String REF_VIAJE = "refViaje";
	private static final String MAT_VIAJE = "matriculaViaje";
	private static final String PRECIO_VIAJE = "precioViaje";
	private static final String IVA_VIAJE = "ivaViaje";
	private static final String TOTAL_VIAJE = "totalViaje";
	private static final String TOTAL_FACTURA = "totalFactura";
	
	private MiEmpresa empresa;
	private Factura factura;
	private Cliente cliente;
	private List<Viaje> viajes;
	private int index = -1;

	public FacturaDatasource() {
		empresa = MiEmpresa.getInstance();
		factura = new Factura();
		cliente = new Cliente();
		viajes = new ArrayList<Viaje>();
	}
	
	public FacturaDatasource(Factura f, Cliente c) {
		empresa = MiEmpresa.getInstance();
		factura = f;
		cliente = c;
		viajes = f.getViajes();
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;
		String name = field.getName();
			if (DIR_EMP.equals(name)) { o = empresa.getDireccion(); }
			if (LOC_EMP.equals(name)) { o = empresa.getCp() + " - " + empresa.getLocalidad().toUpperCase(); }
			if (PAIS_EMP.equals(name)) { o = empresa.getPais().toUpperCase(); }
			if (NUM_EMP.equals(name)) { o = empresa.getIdEmpresa(); }
			if (PAIS_EMP.equals(name)) { o = empresa.getPais().toUpperCase(); }
			if (TEL1.equals(name)) { o = empresa.getTel1(); }
			if (TEL2.equals(name)) { o = empresa.getTel2(); }
			if (FAX.equals(name)) { o = empresa.getFax(); }
			if (EMAIL.equals(name)) { o = empresa.getEmail(); }
			if (NOM_EMP.equals(name)) { o = empresa.getNombre(); }
			if (LOC_PAIS_EMP.equals(name)) { o = empresa.getCp()+" - "+empresa.getLocalidad()+" ("+empresa.getPais()+")"; }
			if (BANCO_EMP.equals(name)) { o = empresa.getBanco(); }
			if (TIT_EMP.equals(name)) { o = empresa.getPropietario().toUpperCase(); }
			if (IBAN_EMP.equals(name)) { o = empresa.getIban(); }
			if (NOM_CLIE.equals(name)) { o = cliente.getNombre(); }
			if (DIR_CLIE.equals(name)) { o = cliente.getDireccion(); }
			if (LOC_PAIS_CLIE.equals(name)) { o = cliente.getCp()+" - "+cliente.getLocalidad()+" "+cliente.getPais().toUpperCase(); }
			if (NUM_CLIE.equals(name)) { o = cliente.getNum(); }
			if (NUM_FAC.equals(name)) { o = factura.getNumFacturaString(); }
			if (LOC_FAC.equals(name)) { o = factura.getLocalidad().toUpperCase(); }
			if (TOTAL_FACTURA.equals(name)) { o = factura.getImporteString(); }
			if (FECHA.equals(name)) {
				String[] f = Calculos.separateDate(factura.getFecha());
				o = f[2]+"/"+f[1]+"/"+f[0];
			}
		if (REF_VIAJE.equals(name)) { o = viajes.get(index).getRefViaje()+""; }
		if (MAT_VIAJE.equals(name)) { o = viajes.get(index).getMatricula(); }
		if (PRECIO_VIAJE.equals(name)) { o = viajes.get(index).getPrecioString(); }
		if (IVA_VIAJE.equals(name)) { o = viajes.get(index).getIvaString(); }
		if (TOTAL_VIAJE.equals(name)) { 
			double d = Calculos.calculaImporte(viajes.get(index));
			o = Calculos.priceFormat(d); 
		}
		return o;
	}

	@Override
	public boolean next() throws JRException {
		return ++index < viajes.size();
	}
}
