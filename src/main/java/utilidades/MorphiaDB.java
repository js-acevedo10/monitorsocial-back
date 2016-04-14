package utilidades;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.DefaultCreator;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MorphiaDB {
	
	private static String MONGO_URI = "mongodb://monitorsocial:-Ecvyfawaze123@ds023118.mlab.com:23118/monitor-social-crm"; 
	
	final static Morphia morphia = new Morphia();
	static Datastore datastore;
	static Boolean c = false;
	
	public static Datastore getDatastore() {
		if(datastore == null) {
			morphia.mapPackage("mundo");
			MongoClientURI mouri = new MongoClientURI(MONGO_URI);
			datastore = morphia.createDatastore(new MongoClient(mouri), "monitor-social-crm");
		}
		datastore.ensureCaps();
		datastore.ensureIndexes();
		return datastore;
	}
}
