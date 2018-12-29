package pojo;

public class User {
	private int account;
	private String password;
	private int right;
	
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + ", right=" + right + "]";
	}
	
}
