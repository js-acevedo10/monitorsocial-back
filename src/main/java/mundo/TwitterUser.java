package mundo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import mundo.casos.Caso;
import twitter4j.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity("TwitterUsers")
public class TwitterUser {
	
	@Id ObjectId id;
	private long twitterId;
	private String name, screenName, location, description, profileImageUrl, miniProfileImageUrl, url, timeZone;
	private boolean defaultProfileImage, verified;
	private int followersCount, friendsCount;
	@Reference private List<TwitterStatus> statuses;
	@Reference private List<Caso> casos;
	
	public TwitterUser() {
		//MANDATORY
	}
	
	public TwitterUser(User user) {
		twitterId = user.getId();
		name = user.getName();
		screenName = user.getScreenName();
		location = user.getLocation();
		description = user.getDescription();
		profileImageUrl = user.getOriginalProfileImageURL();
		miniProfileImageUrl = user.getMiniProfileImageURL();
		url = user.getURL();
		timeZone = user.getTimeZone();
		defaultProfileImage = user.isDefaultProfileImage();
		verified = user.isVerified();
		followersCount = user.getFollowersCount();
		friendsCount = user.getFriendsCount();
		statuses = new ArrayList<TwitterStatus>();
		casos = new ArrayList<Caso>();
	}

	public long getTwitterId() {
		return twitterId;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getMiniProfileImageUrl() {
		return miniProfileImageUrl;
	}

	public String getUrl() {
		return url;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public boolean isDefaultProfileImage() {
		return defaultProfileImage;
	}

	public boolean isVerified() {
		return verified;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public void setTwitterId(long twitterId) {
		this.twitterId = twitterId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setMiniProfileImageUrl(String miniProfileImageUrl) {
		this.miniProfileImageUrl = miniProfileImageUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setDefaultProfileImage(boolean defaultProfileImage) {
		this.defaultProfileImage = defaultProfileImage;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	public List<TwitterStatus> getStatuses() {
		return statuses;
	}

	public List<Caso> getCasos() {
		return casos;
	}

	public void setStatuses(List<TwitterStatus> statuses) {
		this.statuses = statuses;
	}
	
	public void addStatus(TwitterStatus status) {
		this.statuses.add(status);
	}

	public void setCasos(List<Caso> casos) {
		this.casos = casos;
	}
	
	public void addCaso(Caso caso) {
		this.casos.add(caso);
	}
}
