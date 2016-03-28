package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.core.Response;

import org.bson.Document;

import com.google.gson.Gson;

/**
 * Esta clase sirve para enviar respuestas REST sin tener que armarlas siempre, dependiendo de las necesidades <br>
 * vamos a tener o no que separar los métodos y los headers.
 * @author JuanSantiagoAcev
 */
public class ResponseMonitor {

	public static Set<String> positiveList, negativeList;

	/**
	 * El asterisco significa que el reques puede venir de cualquier url, se reemplaza por una url en específico
	 */
	public final static String ORIGIN = "*";

	/**
	 * Los headers son las operaciones
	 */
	public final static String HEADERS = "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept";

	/**
	 * Método que construye la respuesta de cualquier método REST
	 * @param respuesta - el objeto que se vaya a enviar (debe ser String, JSON o un objeto anotado con JAX-RS)
	 * @return la respuesta lista para ser enviada
	 */
	public static Response buildResponse(Object respuesta, Response.Status status) {
		return Response.status(status)
				.entity(respuesta)
				.header("Access-Control-Allow-Headers", HEADERS)
				.header("Access-Control-Allow-Origin", ORIGIN)
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD, OPTIONS")
				.build();
	}

	/**
	 * Método que transforma una lista de documentos en una lista de la clase que entra como parametro
	 * @param documentos - la lista de documentos original
	 * @param c - la clase a la que se quiere transformar la lista
	 * @return la lista con los objetos de la clase que se necesitan
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Object> transformDocumentListToObjectList(List<Document> documentos, Class c) {
		List<Object> rta = new ArrayList<Object>();
		for (Document document : documentos) {
			Gson gson = new Gson();
			String json = document.toJson();
			Object o = gson.fromJson(json, c);
			rta.add(o);
		}
		return rta;
	}

	//------------------------------------------------------------------------------------------------
	//------------------------UTILIDADES PARA VERIFICACION DE OBJETOS---------------------------------
	//------------------------------------------------------------------------------------------------

	public static void loadLists() {
		negativeList = new HashSet<String>();
		positiveList = new HashSet<String>();
		insertFromFile("src/nspa.txt", negativeList);
		insertFromFile("src/pspa.txt", positiveList);
	}

	public static int classifyText(String msg) {
		if(positiveList == null || positiveList.isEmpty() || negativeList == null || negativeList.isEmpty()) {
			loadLists();
		}
		//Delimeters need to be further extended. 
		StringTokenizer st = new StringTokenizer(msg,"[,. #]+"); 
		int positive =0 ,negative =0; 
		while(st.hasMoreTokens()) { 
			String next = st.nextToken().toLowerCase();
			positive += (positiveList.contains(next) ? 1: 0); 
			negative += (negativeList.contains(next) ? 1: 0); 
		} 
		return positive-negative; 
	} 

	/**
	 * Método que revisa la nulidad de un objeto
	 * @param o - el objeto
	 * @return false si es nulo, true si no es nulo
	 */
	public static boolean nullCheck(ArrayList<Object> os) {
		for (Object object : os) {
			if(object == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método que revisa un String
	 * @param s - el String
	 * @return false si está vacío, true si no está vacío
	 */
	public static boolean stringCheck(ArrayList<String> s) {
		for (String string : s) {
			if(string.equals("") || string.equals(" ")) {
				return false;
			}
		}
		return true;
	}
	
	public static void insertFromFile(String filename, Set<String> list) { 
		try  
		{
			InputReader in = new InputReader(filename); 
			while(in.hasMoreTokens()) {
				list.add(in.nextToken()); 
			} 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//IO 
class InputReader { 
	BufferedReader reader; 
	StringTokenizer tokenizer; 

	InputReader() { 
		reader = new BufferedReader(new InputStreamReader(System.in)); 
	}

	InputReader(String fileName) throws FileNotFoundException { 
		reader = new BufferedReader(new FileReader(new File(fileName))); 
	}

	String readLine() throws IOException { 
		return reader.readLine(); 
	}

	String nextToken() throws IOException { 
		while (tokenizer == null || !tokenizer.hasMoreTokens()) 
			tokenizer = new StringTokenizer(readLine(),"[-]"); 
		return tokenizer.nextToken(); 
	}

	boolean hasMoreTokens() throws IOException { 
		while (tokenizer == null || !tokenizer.hasMoreTokens()) { 
			String s = readLine(); 
			if (s == null) 
				return false; 
			tokenizer = new StringTokenizer(s); 
		} 
		return true; 
	}

	int nextInt() throws NumberFormatException, IOException { 
		return Integer.parseInt(nextToken()); 
	} 

	long nextLong() throws NumberFormatException, IOException { 
		return Long.parseLong(nextToken()); 
	} 

	double nextDouble() throws NumberFormatException, IOException { 
		return Double.parseDouble(nextToken()); 
	} 
}
