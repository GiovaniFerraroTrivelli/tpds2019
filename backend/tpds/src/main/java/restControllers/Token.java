package restControllers;

import org.apache.commons.lang3.RandomStringUtils;

public class Token {
	public String token;

	public Token() {
		this.token = RandomStringUtils.random(32, true, true);
	}

	public Token(String arg) {
		token = arg;
	}
	
	public String toString() {
		return this.token;
	}
}
