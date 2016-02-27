import java.util.Scanner;

/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * February 21, 2016
 * Assignment 6 - Command pattern
 */
public class Assignment6 {
    public static void main(String[] args) {
        // Create the web server service
        Service service = new Service("Web Server");

        // Create the scanner and listen for commands
        Scanner scanner = new Scanner(System.in);
        commandInput:
        while (true) {
            // Prompt for a command
            System.out.print("Enter a command [start|stop|restart|reload|exit]: ");

            // Process the command
            String cmd = scanner.next().toLowerCase();
            switch (cmd) {
                case "start":
                    // When issued, the command will start the service
                    Server.setCommand(new StartCommand(service));
                    break;
                case "stop":
                    // When issued, the command will stop the service
                    Server.setCommand(new StopCommand(service));
                    break;
                case "restart":
                    // When issued, the command will restart the service
                    Server.setCommand(new RestartCommand(service));
                    break;
                case "reload":
                    // When issued, the command will reload the service
                    Server.setCommand(new ReloadCommand(service));
                    break;
                default:
                    // Invalid command / exit command received
                    System.out.println("Exiting...");
                    break commandInput;
            }

            // Execute the command
            Server.executeCommand();
            System.out.println();
        }

        // Close scanner
        scanner.close();
    }
}

/**
 * Command interface
 */
interface Command {
    void execute();
}

/**
 * Server class that is representative of a server console
 */
class Server {
    private static Command command;

    /**
     * Set the command that should be executed
     * @param command Command to execute
     */
    public static void setCommand(Command command) {
        Server.command = command;
    }

    /**
     * Execute the command
     */
    public static void executeCommand() {
        command.execute();
    }
}

/**
 * Service class
 */
class Service {
    private String name;
    private boolean started = false;

    /**
     * Constructor
     * @param name Service name
     */
    public Service(String name) {
        this.name = name;
    }

    /**
     * Start the service if it is not already running
     */
    public void start() {
        if (!started) {
            System.out.printf("%s service is starting...\n", name);
            System.out.printf("%s service started\n", name);
            started = true;
        } else {
            System.out.printf("%s service is already running\n", name);
        }
    }

    /**
     * Stop the service if it is running
     */
    public void stop() {
        if (started) {
            System.out.printf("%s service is stopping...\n", name);
            System.out.printf("%s service stopped\n", name);
            started = false;
        } else {
            System.out.printf("%s service is not running\n", name);
        }
    }

    /**
     * Restart the service if it is running
     */
    public void restart() {
        if (started) {
            stop();
            System.out.println();
            start();
        } else {
            System.out.printf("%s service is not running\n", name);
        }
    }

    /**
     * Reload the service if it is running
     */
    public void reload() {
        if (started) {
            System.out.printf("%s service is reloading...\n", name);
            System.out.printf("%s service reloaded successfully\n", name);
        } else {
            System.out.printf("%s service is not running\n", name);
        }
    }
}

/**
 * Start command class
 */
class StartCommand implements Command {
    private Service service;

    /**
     * Constructor
     * @param service Service to start
     */
    public StartCommand(Service service) {
        this.service = service;
    }

    /**
     * Start the service
     */
    public void execute() {
        service.start();
    }
}

/**
 * Stop command class
 */
class StopCommand implements Command {
    private Service service;

    /**
     * Constructor
     * @param service Service to stop
     */
    public StopCommand(Service service) {
        this.service = service;
    }

    /**
     * Stop the service
     */
    public void execute() {
        service.stop();
    }
}

/**
 * Restart command class
 */
class RestartCommand implements Command {
    private Service service;

    /**
     * Constructor
     * @param service Service to restart
     */
    public RestartCommand(Service service) {
        this.service = service;
    }

    /**
     * Restart the service
     */
    public void execute() {
        service.restart();
    }
}

/**
 * Reload command class
 */
class ReloadCommand implements Command {
    private Service service;

    /**
     * Constructor
     * @param service Service to reload
     */
    public ReloadCommand(Service service) {
        this.service = service;
    }

    /**
     * Reload the service
     */
    public void execute() {
        service.reload();
    }
}