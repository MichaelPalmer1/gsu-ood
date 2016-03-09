/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * March 6, 2016
 * Assignment 8 - Facade pattern
 */
public class Assignment8 {
    public static void main(String[] args) {
        DatabaseServer databaseServer = new DatabaseServer();
        WebServer webServer = new WebServer();
        CacheServer cacheServer = new CacheServer();
        WebsiteFacade websiteFacade = new WebsiteFacade(databaseServer, webServer, cacheServer);
        websiteFacade.loadPage("Homepage");
        System.out.println("------------");
        websiteFacade.loadPage("About Us");
        System.out.println("------------");
        websiteFacade.stop();
    }
}

/**
 * Website facade
 */
class WebsiteFacade {
    private DatabaseServer databaseServer;
    private WebServer webServer;
    private CacheServer cacheServer;
    private boolean started = false;

    /**
     * Website constructor
     */
    public WebsiteFacade(DatabaseServer databaseServer, WebServer webServer, CacheServer cacheServer) {
        this.databaseServer = databaseServer;
        this.webServer = webServer;
        this.cacheServer = cacheServer;
    }

    /**
     * Start the website
     */
    public void start() {
        if (!started) {
            System.out.println("Starting website...");
            databaseServer.start();
            webServer.start();
            cacheServer.start();
            started = true;
        } else {
            System.out.println("Website already started");
        }
    }

    /**
     * Stop the website
     */
    public void stop() {
        if (started) {
            System.out.println("Stopping website...");
            cacheServer.stop();
            webServer.stop();
            databaseServer.stop();
            started = false;
        } else {
            System.out.println("Website is not running");
        }
    }

    /**
     * Load a web page, starting the website if necessary
     * @param pageName Page to load
     */
    public void loadPage(String pageName) {
        if (!started) {
            start();
        }
        cacheServer.checkCache(pageName);
        databaseServer.query(pageName);
        webServer.loadPage(pageName);
        webServer.addVisit();
    }
}

/**
 * Server start/stop functionality
 */
abstract class Servers {
    /**
     * Start the server
     */
    public void start() {
        System.out.println(this.getClass().getSimpleName() + " started");
    }

    /**
     * Stop the server
     */
    public void stop() {
        System.out.println(this.getClass().getSimpleName() + " stopped");
    }
}

/**
 * Web Server
 */
class WebServer extends Servers {
    private int visits = 0;

    /**
     * Increment the visit counter
     */
    public void addVisit() {
        visits++;
        System.out.printf("Website visits: %d\n", visits);
    }

    /**
     * Load a page
     * @param pageName Page to load
     */
    public void loadPage(String pageName) {
        System.out.printf("Loading page '%s'\n", pageName);
    }
}

/**
 * Database Server
 */
class DatabaseServer extends Servers {
    /**
     * Start the database server and perform startup operations
     */
    @Override
    public void start() {
        super.start();
        System.out.println("Performing startup queries");
    }

    /**
     * Finish all queries and stop the database
     */
    @Override
    public void stop() {
        System.out.println("Finished all database queries");
        super.stop();
    }

    /**
     * Perform queries for a page
     * @param page Page to perform queries for
     */
    public void query(String page) {
        System.out.printf("Querying database for page '%s'\n", page);
    }
}

/**
 * Cache Server
 */
class CacheServer extends Servers {
    /**
     * Start the cache server and load cache from file system
     */
    @Override
    public void start() {
        super.start();
        System.out.println("Loading cache from file system");
    }

    /**
     * Save cache to file system and stop the cache server
     */
    @Override
    public void stop() {
        System.out.println("Saved the cache to file system");
        super.stop();
    }

    /**
     * Check the cache for a page
     * @param page Page to look for
     */
    public void checkCache(String page) {
        System.out.printf("Checking cache for page '%s'\n", page);
    }
}
