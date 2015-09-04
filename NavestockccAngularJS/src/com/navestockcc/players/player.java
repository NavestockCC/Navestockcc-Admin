package com.navestockcc.players;

public class player {

	
	private int idPlayer;
	private String Firstname;
	private String LastName;
	private String Name;
	private String emailaddress;
	private String NickName;
	
	public player(){};

	public player(int idPlyer, String Frstname, String LstName, String NckName, String emailaddr){
		this.idPlayer = idPlyer;
		this.Firstname = Frstname;
		this.LastName = LstName;
		this.NickName = NckName;
		this.emailaddress = emailaddr;
		this.Name = Frstname + " " + LstName;
	}
	public int getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
}
