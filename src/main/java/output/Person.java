import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("pet")
	private Object pet;
	@JsonProperty("hobbies")
	private Object[] hobbies;
	@JsonProperty("is_cool")
	private boolean isCool;
	@JsonProperty("age")
	private int age;
	@JsonProperty("fish")
	private Object fish;
	@JsonProperty("rating")
	private double rating;
	@JsonProperty("colors")
	private Object[] colors;
}
