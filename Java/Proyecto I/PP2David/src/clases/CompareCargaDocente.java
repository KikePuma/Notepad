package clases;
import java.util.*;

public class CompareCargaDocente implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		String[] datos1 = o1.split(" ");
		String[] datos2 = o2.split(" ");
		Integer n1 = Integer.parseInt(datos1[0]);
		Integer n2 = Integer.parseInt(datos2[0]);
		int resultado = n1.compareTo(n2);
		if (resultado == 0){
			Integer i1 = Integer.parseInt(datos1[1]);
			Integer i2 = Integer.parseInt(datos2[1]);
			return resultado = i1.compareTo(i2);
		}else
			return -resultado;

	}

}
