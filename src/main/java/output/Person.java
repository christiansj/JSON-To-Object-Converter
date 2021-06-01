import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	@JsonProperty("id")
	private int id;
	@JsonProperty("id_str")
	private int idStr;
	@JsonProperty("name")
	private String name;
	@JsonProperty("screen_name")
	private String screenName;
	@JsonProperty("location")
	private String location;
	@JsonProperty("url")
	private String url;
	@JsonProperty("description")
	private String description;
	@JsonProperty("translator_type")
	private String translatorType;
	@JsonProperty("protect")
	private boolean protect;
	@JsonProperty("verified")
	private boolean verified;
	@JsonProperty("followers_count")
	private int followersCount;
	@JsonProperty("friends_count")
	private int friendsCount;
	@JsonProperty("listed_count")
	private int listedCount;
	@JsonProperty("favourites_count")
	private int favouritesCount;
	@JsonProperty("statuses_count")
	private int statusesCount;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("utc_offset")
	private int utcOffset;
	@JsonProperty("time_zone")
	private String timeZone;
	@JsonProperty("geo_enabled")
	private boolean geoEnabled;
	@JsonProperty("lang")
	private String lang;
	@JsonProperty("contributors_enabled")
	private boolean contributorsEnabled;
	@JsonProperty("is_translator")
	private boolean isTranslator;
	@JsonProperty("profile_background_color")
	private int profileBackgroundColor;
	@JsonProperty("profile_background_image_url")
	private String profileBackgroundImageUrl;
	@JsonProperty("profile_background_image_url_https")
	private String profileBackgroundImageUrlHttps;
	@JsonProperty("profile_background_tile")
	private boolean profileBackgroundTile;
	@JsonProperty("profile_link_color")
	private String profileLinkColor;
	@JsonProperty("profile_sidebar_border_color")
	private int profileSidebarBorderColor;
	@JsonProperty("profile_sidebar_fill_color")
	private int profileSidebarFillColor;
	@JsonProperty("profile_text_color")
	private int profileTextColor;
	@JsonProperty("profile_use_background_image")
	private boolean profileUseBackgroundImage;
	@JsonProperty("profile_image_url")
	private String profileImageUrl;
	@JsonProperty("profile_image_url_https")
	private String profileImageUrlHttps;
	@JsonProperty("profile_banner_url")
	private String profileBannerUrl;
	@JsonProperty("default_profile")
	private boolean defaultProfile;
	@JsonProperty("default_profile_image")
	private boolean defaultProfileImage;
	@JsonProperty("following")
	private Object following;
	@JsonProperty("follow_request_sent")
	private Object followRequestSent;
	@JsonProperty("notifications")
	private Object notifications;
}
