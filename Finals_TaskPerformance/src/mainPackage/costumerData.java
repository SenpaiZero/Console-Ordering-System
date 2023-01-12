package mainPackage;

public class costumerData {
	//User personal information
	private static String userName, password, contactNumber, birthDay; 
	private static String total;
	
	
	public String getTotal()
	{
		return total;
	}

	public static void setTotal(String totals)
	{
		total = totals;
	}

	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) 
	{
		this.contactNumber = contactNumber;
	}
	
	public void setBirthday(String birthDay)
	{
		this.birthDay = birthDay;
	}
	
	public String getBirthday()
	{
		return birthDay;
	}
}
