package script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class post {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//get the path for each json file...
		List<Path> pathList;
		try(Stream<Path> paths = Files.walk(Paths.get("json"))){
			  
			pathList=paths.filter(Files::isRegularFile).collect(Collectors.toList());
		}
		

		for(int i=0; i<pathList.size(); i++) {
			//get the json object
			JSONParser parser = new JSONParser();
			JSONObject json;
			String directory=pathList.get(i).toString();
			System.out.println("Post:"+directory);
			json = (JSONObject) parser.parse(new FileReader(directory));
			
			// handle post here...
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			try {
			    HttpPost post = new HttpPost("https://r3.smarthealthit.org/Appointment");
			    post.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));
			    post.addHeader("Accept","application/json");
			    HttpResponse response=httpClient.execute(post);
			    //get the response message...
			       BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			       String line;
			       while(null !=(line=rd.readLine())){

			       System.out.println(line);
			       
			       }
			       System.out.println("");
			
			} catch (Exception ex) {
			    // handle exception here
			} finally {
			    httpClient.close();
			}
			
			
		}
		
		



		

	}

}
