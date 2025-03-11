import java.util.*;
import java.util.concurrent.*;

class WebCrawler implements Runnable {
    private static final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();
    private final String url;

    public WebCrawler(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        if (visitedUrls.add(url)) {    //Prevent duplicate crawling
            System.out.println(Thread.currentThread().getName() + " crawling: " + url);

              //Simulate downloading a page (replace with real HTTP request)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println("Finished processing: " + url);
        }
    }
}

public class MultiThreadedCrawler {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<String> urls = Arrays.asList(
                "https://example.com",
                "https://google.com",
                "https://github.com",
                "https://example.com"      //Duplicate URL to test thread safety
        );

        for (String url : urls) {
            executor.execute(new WebCrawler(url));
        }

        executor.shutdown();
    }
}
