package mundo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import twitter4j.Status;

@Entity("tweets")
public class TwitterStatus {
	@Id ObjectId id;
	private long statusId, inReplyToStatusId, inReplyToUserId;
	private Date createdAt;
	private String text, source, placeCountryCode, placeCountry, placeType, placeFullName, placeStreetAddress;
	private boolean truncated, favorited, retweeted, retweet;
	private int favoriteCount, retweetCount, categoria;
	private double sentimiento;
	@Reference
	private TwitterUser user;
	
	public TwitterStatus() {
		//MANDATORY
	}
	
	public TwitterStatus(Status status, TwitterUser user) {
		statusId = status.getId();
		inReplyToStatusId = status.getInReplyToStatusId();
		inReplyToUserId = status.getInReplyToUserId();
		createdAt = status.getCreatedAt();
		text = status.getText();
		source = status.getSource();
		if(status.getPlace() != null) {
			placeCountryCode = status.getPlace().getCountryCode();
			placeCountry = status.getPlace().getCountry();
			placeType = status.getPlace().getPlaceType();
			placeFullName = status.getPlace().getFullName();
			placeStreetAddress = status.getPlace().getStreetAddress();
		}
		truncated = status.isTruncated();
		favorited = status.isFavorited();
		retweeted = status.isRetweeted();
		retweet = status.isRetweet();
		favoriteCount = status.getFavoriteCount();
		retweetCount = status.getRetweetCount();
		this.user = user;
	}

	public long getStatusId() {
		return statusId;
	}

	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}

	public String getPlaceCountryCode() {
		return placeCountryCode;
	}

	public String getPlaceCountry() {
		return placeCountry;
	}

	public String getPlaceType() {
		return placeType;
	}

	public String getPlaceFullName() {
		return placeFullName;
	}

	public String getPlaceStreetAddress() {
		return placeStreetAddress;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public boolean isRetweeted() {
		return retweeted;
	}

	public boolean isRetweet() {
		return retweet;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public TwitterUser getUser() {
		return user;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPlaceCountryCode(String placeCountryCode) {
		this.placeCountryCode = placeCountryCode;
	}

	public void setPlaceCountry(String placeCountry) {
		this.placeCountry = placeCountry;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public void setPlaceFullName(String placeFullName) {
		this.placeFullName = placeFullName;
	}

	public void setPlaceStreetAddress(String placeStreetAddress) {
		this.placeStreetAddress = placeStreetAddress;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}

	public void setRetweet(boolean retweet) {
		this.retweet = retweet;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public void setUser(TwitterUser user) {
		this.user = user;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public double getSentimiento() {
		return sentimiento;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public void setSentimiento(double sentimiento) {
		this.sentimiento = sentimiento;
	}
}
