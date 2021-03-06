package utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.core.Response;

import org.bson.Document;

import com.google.gson.Gson;

import mundo.TwitterStatus;
import twitter4j.Status;

/**
 * Esta clase sirve para enviar respuestas REST sin tener que armarlas siempre, dependiendo de las necesidades <br>
 * vamos a tener o no que separar los métodos y los headers.
 * @author JuanSantiagoAcev
 */
public class ResponseMonitor {

	public static HashMap<String, String> positiveList, negativeList;
	public static List<String> soporteList, quejaList, peticionList, reclamoList; 

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
		negativeList = new HashMap<String, String>();
		positiveList = new HashMap<String, String>();
		insertFromCSVToHashMap("src/negSent.csv", negativeList);
		insertFromCSVToHashMap("src/posSent.csv", positiveList);
		soporteList = new ArrayList<String>();
		quejaList = new ArrayList<String>();
		peticionList = new ArrayList<String>();
		reclamoList = new ArrayList<String>();
		insertFromCSVToList("src/soporteList.csv", soporteList);
		insertFromCSVToList("src/quejaList.csv", quejaList);
		insertFromCSVToList("src/peticionList.csv", peticionList);
		insertFromCSVToList("src/reclamoList.csv", reclamoList);
	}
	
	public static void classifyTweet(TwitterStatus tweet) {
		if(positiveList == null || positiveList.isEmpty() 
				|| negativeList == null || negativeList.isEmpty()
				|| soporteList == null || soporteList.isEmpty()
				|| quejaList == null || quejaList.isEmpty()
				|| peticionList == null || peticionList.isEmpty()
				|| reclamoList == null || reclamoList.isEmpty()) {
			loadLists();
		}
		StringTokenizer st = new StringTokenizer(tweet.getText(), "[,. #]+-:=()");
		String origText = tweet.getText().toLowerCase();
		double positive = 0, negative = 0;
		String cat = Constantes.TWEET_TIPO_OTROS;
		while(st.hasMoreTokens()) {
			String next = st.nextToken().toLowerCase();
			if(soporteList.get(0).equals(next)) {
				cat = Constantes.TWEET_TIPO_SOPORTE;
			} else if(quejaList.contains(next)) {
				cat = Constantes.TWEET_TIPO_QUEJA;
			} else if(peticionList.contains(next)) {
				cat = Constantes.TWEET_TIPO_PETICION;
			} else if(reclamoList.contains(next)) {
				cat = Constantes.TWEET_TIPO_RECLAMO;
			} else {
				positive += Double.parseDouble(positiveList.get(next) != null?positiveList.get(next):"0");
				negative += Double.parseDouble(negativeList.get(next) != null?negativeList.get(next):"0");				
			}
		}
		tweet.setSentimiento(positive-negative==0?5:positive-negative);
		tweet.setCategoria(cat);
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
	
	public static void insertFromCSVToList(String filename, List<String> list) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String line = null;
			while((line = br.readLine()) != null) {
				String st[] = line.split(",");
				for (String string : st) {
					list.add(string);
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertFromCSVToHashMap(String filename, HashMap<String, String> list) { 
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "ISO-8859-3"));
			String line = null;
			while((line = br.readLine()) != null) {
				String str[] = line.split(",");
				list.put(str[0], str[1]);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logTweet(Status s, String empId) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/tweets/teaching.txt", true));
			writer.write(s.getText());
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		loadLists();
	}
}
