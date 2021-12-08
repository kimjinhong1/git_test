package chap01;

public class Greeter {
	
	private String name;
	
	public String greet() {
		return name + "님 안녕";
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
