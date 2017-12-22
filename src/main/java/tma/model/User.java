package tma.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="taikhoan")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String idUser;
	
	private String username;

	private int password;
	
	
	public User(){
			
		}
	
	

	public User(String idUser, String username, int password) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
	}




	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getPassword() {
		return password;
	}



	public void setPassword(int password) {
		this.password = password;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "SmartPhone [idSmartPhone=" + idUser + ", nameSmartPhone=" + username + ", priceSmartPhone=" + password + "]";
	}
}