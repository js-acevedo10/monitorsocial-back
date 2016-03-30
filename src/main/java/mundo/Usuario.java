package mundo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Usuario")
public class Usuario {
	@Id ObjectId id;
	private String name, username, password, email, city, state, country, twitterId;
	private List<String> keyWords;
	private boolean streaming;
	public Usuario(String name, String username, String password, String email, String city, String state, String country, String twitterId) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.city = city;
		this.state = state;
		this.country = country;
		this.twitterId = twitterId;
		this.keyWords = new ArrayList<String>();
		this.streaming = false;
	}
	public Usuario(){
		//MANDATORY
	}
	public void addKeyWord(String keyWord) {
		this.keyWords.add(keyWord);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public void setStreaming(boolean streaming) {
		this.streaming = streaming;
	}
	public String getId() {
		return id.toString();
	}
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public List<String> getKeyWords() {
		return keyWords;
	}
	public boolean isStreaming() {
		return streaming;
	}
}