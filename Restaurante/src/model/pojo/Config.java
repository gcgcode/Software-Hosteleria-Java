package model.pojo;

public class Config {

	private String URL;
	private String USER;
	private String PASS;

	public Config(String uRL, String uSER, String pASS) {
		URL = uRL;
		USER = uSER;
		PASS = pASS;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getPASS() {
		return PASS;
	}

	public void setPASS(String pASS) {
		PASS = pASS;
	}

	@Override
	public String toString() {
		return "Config [URL=" + URL + ", USER=" + USER + ", PASS=" + PASS + "]";
	}
	
	

}
