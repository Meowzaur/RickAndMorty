package apimodels;

public class Personaje {

	private int id;
	private String name;
	private String status;
	private String image;

	public Personaje(int id, String name, String status, String image) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

}
