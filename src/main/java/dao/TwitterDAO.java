package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import utilidades.ResponseMonitor;
import utilidades.TwitterStreamer;

public class TwitterDAO {
	
	private static String json = "";
	private static Response.Status status = Response.Status.OK;
	
	public static Response addTwitterTerm(String twitterTerm) {
		List<String> trackTerms = new ArrayList<String>();
		trackTerms.add("@nike");
		try {
			TwitterStreamer.oauth("bGPtpsnIx0eQJuVtXtHncfZUA", "dAg6JZB84XXlKn21VGqxGSofaJtkIaD8c9pUrqCJJbyWSMZ2bD", "2334095631-QwupYxwRjThfNx7s4j24Vo28ylS64NFs7LA4Fqh", "4XVrQTuvs02XO6WTqhwJz8Pq2P9m4B00JI4X0lVZnafV9", trackTerms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ResponseMonitor.buildResponse(json, status);
	}
}
