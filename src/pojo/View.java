package pojo;

public class View {
	private int id;
	private String name;
	private String infor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	@Override
	public String toString() {
		return "View [id=" + id + ", name=" + name + ", infor=" + infor + "]";
	}
	
}
