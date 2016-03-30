package mundo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import twitter4j.Status;
import utilidades.Constantes;

@Entity("TwitterStatuses")
public class TwitterStatus {
	@Id ObjectId id;
	private long statusId, inReplyToStatusId, inReplyToUserId, userId;
	private Date createdAt;
	private String text, source, placeCountryCode, placeCountry, placeType, placeFullName, placeStreetAddress, empresaId, userName, userScreenName, userProfilePicUrl, userMinProfilePicUrl;
	private boolean truncated, favorited, retweeted, retweet, unread;
	private int favoriteCount, retweetCount, categoria;
	private double sentimiento;
	
	public TwitterStatus() {
		//MANDATORY
	}
	
	public TwitterStatus(Status status, String empresaId) {
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
		unread = true;
		categoria = Constantes.TWEET_TIPO_OTROS;
		userId = status.getUser().getId();
		userName = status.getUser().getName();
		userScreenName = status.getUser().getScreenName();
		userProfilePicUrl = status.getUser().getProfileImageURL();
		userMinProfilePicUrl = status.getUser().getMiniProfileImageURL();
		this.empresaId = empresaId;
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

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public String getUserProfilePicUrl() {
		return userProfilePicUrl;
	}

	public void setUserProfilePicUrl(String userProfilePicUrl) {
		this.userProfilePicUrl = userProfilePicUrl;
	}

	public String getUserMinProfilePicUrl() {
		return userMinProfilePicUrl;
	}

	public void setUserMinProfilePicUrl(String userMinProfilePicUrl) {
		this.userMinProfilePicUrl = userMinProfilePicUrl;
	}
	
}
