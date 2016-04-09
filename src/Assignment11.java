/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * April 3, 2016
 * Assignment 11 - Template pattern
 */
public class Assignment11 {
    public static void main(String[] args) {
        // Website deployment
        Website website = new Website();
        System.out.println("Initiating website deployment...");
        website.performDeployment();

        // App deployment
        App app = new App(true);
        System.out.println("\nInitiating app deployment...");
        app.performDeployment();
    }
}

/**
 * Deployment template
 */
abstract class Deployment {
    /**
     * Perform the deployment
     */
    public final void performDeployment() {
        // Checkout code
        checkout();

        // Hook to perform customizations if needed
        if (hasCustomizations()) {
            addCustomizations();
        }

        // Perform the build
        build();

        // Run tests
        test();

        // Deploy to production
        deploy();
    }

    /**
     * Check out the code from the repository
     */
    public void checkout() {
        System.out.println("Checking out code base from repository...");
    }

    /**
     * Check if customizations need to be applied to the code
     * @return boolean
     */
    public boolean hasCustomizations() {
        return false;
    }

    /**
     * Abstract method where customizations can be defined
     */
    public abstract void addCustomizations();

    /**
     * Abstract method where the build steps are defined
     */
    public abstract void build();

    /**
     * Perform tests
     */
    public void test() {
        System.out.println("Performing tests...");
        System.out.println("All tests PASSED");
    }

    /**
     * Deploy to production
     */
    public void deploy() {
        System.out.println("Deploying to production...");
    }
}

/**
 * App deployment
 */
class App extends Deployment {
    private boolean customizations;

    /**
     * Create a new instance of the App, specifying if there are customizations
     * @param customizations App customizations (true/false)
     */
    public App(boolean customizations) {
        this.customizations = customizations;
    }

    /**
     * Override the parent method so that customizations can be applied
     * @return boolean
     */
    @Override
    public boolean hasCustomizations() {
        return this.customizations;
    }

    /**
     * Override the parent method with the customizations to be added
     */
    @Override
    public void addCustomizations() {
        System.out.println("Adding customizations...");
    }

    /**
     * Define the build steps for the app
     */
    @Override
    public void build() {
        System.out.println("Performing app build...");
    }
}

/**
 * Website deployment
 */
class Website extends Deployment {

    /**
     * Override the parent method so that no customizations are added
     */
    @Override
    public void addCustomizations() {
        // No customizations needed for website deployment
    }

    /**
     * Define the build steps for the website
     */
    @Override
    public void build() {
        System.out.println("Installing dependencies...");
        System.out.println("Performing website build...");
    }
}
