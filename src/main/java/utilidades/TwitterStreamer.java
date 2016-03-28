package utilidades;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.twitter.hbc.twitter4j.Twitter4jStatusClient;

import mundo.Tweet;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterStreamer {
	
	private static Twitter4jStatusClient twitter4jStatusClient;
	private static String userId;
	
	private static StatusListener statusListener = new StatusListener() {
		
		@Override
		public void onException(Exception e) {
			System.out.println(e);
		}
		
		@Override
		public void onTrackLimitationNotice(int tln) {
			System.out.println(tln);
		}
		
		@Override
		public void onStatus(Status s) {
			if(!s.getText().contains("RT") && !s.getText().contains("https://t.co")) {
				Tweet un = new Tweet(s.getText(), s.getUser().getScreenName(), userId);
				ResponseMonitor.classifyTweet(un);
				MorphiaDB.getDatastore().save(un);
			}
		}
		
		@Override
		public void onStallWarning(StallWarning sw) {
			System.out.println(sw);
		}
		
		@Override
		public void onScrubGeo(long lat, long lon) {
			System.out.println(lat + "+" + lon);
		}
		
		@Override
		public void onDeletionNotice(StatusDeletionNotice sdn) {
			System.out.println(sdn);
		}
	};
	
	public static void startListening(String consumerKey, String consumerSecret, String token, String tokenSecret, List<String> trackTerms, String userId) throws InterruptedException {
		TwitterStreamer.userId = userId;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
		hosebirdEndpoint.trackTerms(trackTerms);
		hosebirdEndpoint.languages(Lists.newArrayList("es"));
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, tokenSecret);
		
		BasicClient client = new ClientBuilder()
				.hosts(Constants.STREAM_HOST)
				.endpoint(hosebirdEndpoint)
				.authentication(auth)
				.processor(new StringDelimitedProcessor(queue))
				.build();
		int numProcessingThreads = 4;
		ExecutorService service = Executors.newFixedThreadPool(numProcessingThreads);
		twitter4jStatusClient = new Twitter4jStatusClient(client, queue, Lists.newArrayList(statusListener), service);
		
		twitter4jStatusClient.connect();
		System.out.println("Connected Listener, vigilante mode on: " + trackTerms.toString());
		for (int threads = 0; threads < numProcessingThreads; threads++) {
			// This must be called once per processing thread
			twitter4jStatusClient.process();
		}
	}
	
	public static void stopListening() {
		twitter4jStatusClient.stop();
	}
}
