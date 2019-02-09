package script;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;


public class delete {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//delete the appointment...
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpDelete delete = new HttpDelete("https://r3.smarthealthit.org/Appointment/219637");
			delete.setHeader("Accept", "application/json");
			HttpResponse response = httpClient.execute(delete);
			//print the response...
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		       String line;
		       while(null !=(line=rd.readLine())){

		       System.out.println(line);
		       }
			
		}catch (Exception ex) {
		    // handle exception here
		} finally {
		    httpClient.close();
		}
	}
	
	

}
