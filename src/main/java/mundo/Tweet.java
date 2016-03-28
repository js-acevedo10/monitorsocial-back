package mundo;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("tweets")
public class Tweet {
	@Id ObjectId id;
	private String sender, mensaje, userId;
	private boolean unread;
	private double sentimiento;
	private int categoria;
	public Tweet(String mensaje, String sender, String userId) {
		this.mensaje = mensaje;
		this.sender = sender;
		this.userId = userId;
		this.unread = true;
		this.sentimiento = 0;
	}
	public Tweet() {
		//MANDATORY
	}
	public String getId() {
		return id.toString();
	}
	public String getSender() {
		return sender;
	}
	public String getMensaje() {
		return mensaje;
	}
	public String getUserId() {
		return userId;
	}
	public boolean isUnread() {
		return unread;
	}
	public double getSentimiento() {
		return sentimiento;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	public void setSentimiento(double sentimiento) {
		this.sentimiento = sentimiento;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
}
