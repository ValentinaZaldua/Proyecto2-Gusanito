package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchivoSerializable {

	public static void almacenar(String nombreArchivo, Object obj) throws FileNotFoundException, IOException {
		ObjectOutputStream objetoSalida = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
		objetoSalida.writeObject(obj);
		objetoSalida.close();
	}
	
	public static Object cargar(String nombreArchivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objetoEntrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
		return objetoEntrada.readObject();
	}
	
}
	