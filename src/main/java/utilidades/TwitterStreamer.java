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

import dao.TwitterDAO;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterStreamer {

	private static Authentication auth;
	private static String consumerKey = "bGPtpsnIx0eQJuVtXtHncfZUA";
	private static String consumerSecret = "dAg6JZB84XXlKn21VGqxGSofaJtkIaD8c9pUrqCJJbyWSMZ2bD";
	private static String token = "2334095631-QwupYxwRjThfNx7s4j24Vo28ylS64NFs7LA4Fqh";
	private static String tokenSecret = "4XVrQTuvs02XO6WTqhwJz8Pq2P9m4B00JI4X0lVZnafV9";
	private static String consumerKeyA = System.getenv("TWITTER_CON_KEY");
	private static String consumerSecretA = System.getenv("TWITTER_CON_SEC");
	private static String tokenA = System.getenv("TWITTER_TOK");
	private static String tokenSecretA = System.getenv("TWITTER_TOK_SEC");
	private static String empresaId;
	private static StatusListener statusListener = new StatusListener() {

		@Override
		public void onDeletionNotice(StatusDeletionNotice sdn) {
			System.out.println(sdn);
		}

		@Override
		public void onException(Exception e) {
			System.out.println(e);
		}

		@Override
		public void onScrubGeo(long lat, long lon) {
			System.out.println(lat + "+" + lon);
		}

		@Override
		public void onStallWarning(StallWarning sw) {
			System.out.println(sw);
		}

		@Override
		public void onStatus(Status s) {
			TwitterDAO.handleNewStatus(s, empresaId);
			ResponseMonitor.logTweet(s, empresaId);
		}

		@Override
		public void onTrackLimitationNotice(int tln) {
			System.out.println(tln);
		}
	};

	private static Twitter twitter;

	private static Twitter4jStatusClient twitter4jStatusClient;

	public static Twitter getTwitterWriter() {
		if (twitter == null) {
			twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer(consumerKey, consumerSecret);
			AccessToken accessToken = new AccessToken(token, tokenSecret);
			twitter.setOAuthAccessToken(accessToken);
		}
		return twitter;
	}

	public static void startListening(List<String> trackTerms, String empresaId) throws InterruptedException {
		if(twitter4jStatusClient != null) {
			twitter4jStatusClient.stop();
		}
		try {
			TwitterStreamer.empresaId = empresaId;
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1000);
			StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
			hosebirdEndpoint.trackTerms(trackTerms);
			hosebirdEndpoint.languages(Lists.newArrayList("es"));
			auth = new OAuth1(consumerKey, consumerSecret, token, tokenSecret);

			BasicClient client = new ClientBuilder().hosts(Constants.STREAM_HOST).endpoint(hosebirdEndpoint)
					.authentication(auth).processor(new StringDelimitedProcessor(queue)).build();
			int numProcessingThreads = 4;
			ExecutorService service = Executors.newFixedThreadPool(numProcessingThreads);
			twitter4jStatusClient = new Twitter4jStatusClient(client, queue, Lists.newArrayList(statusListener),
					service);

			twitter4jStatusClient.connect();
			System.out.println("Connected Listener, vigilante mode on: " + trackTerms.toString());
			for (int threads = 0; threads < numProcessingThreads; threads++) {
				// This must be called once per processing thread
				twitter4jStatusClient.process();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stopListening() {
		twitter4jStatusClient.stop();
	}
}
