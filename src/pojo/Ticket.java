package pojo;

public class Ticket {
	private int id;
	private String name;
	private int vpid;
	private String infor;
	private int price;
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
	public int getVpid() {
		return vpid;
	}
	public void setVpid(int vpid) {
		this.vpid = vpid;
	}
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", name=" + name + ", vpid=" + vpid + ", infor=" + infor + ", price=" + price + "]";
	}
	
}
