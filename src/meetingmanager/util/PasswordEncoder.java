package meetingmanager.util;

import java.math.BigInteger;
import java.security.*;

public class PasswordEncoder {

	public static String Encode(String str){
		if(str==null){
			return null;
		}
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++)
			System.out.println(Encode(""+i));
	}

}
