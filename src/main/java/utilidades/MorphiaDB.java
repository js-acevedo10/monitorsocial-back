package utilidades;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MorphiaDB {
	
	private static String MONGO_URI = "mongodb://monitorsocial:-Ecvyfawaze123@ds023118.mlab.com:23118/monitor-social-crm"; 
	
	final static Morphia morphia = new Morphia();
	static Datastore datastore;
	static Boolean c = false;
	
	public static Datastore getDatastore() {
		if(datastore == null) {
			morphia.mapPackage("mundo");
			datastore = morphia.createDatastore(new MongoClient(MONGO_URI), "monitor-social-crm");
		}
		return datastore;
	}
}
