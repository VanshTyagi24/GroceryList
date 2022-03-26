package ca.sheridancollege.beans;
import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userId;
	private String userName;
	private String encryptedPassword;
	
}
