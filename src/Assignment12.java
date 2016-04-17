import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * April 10, 2016
 * Assignment 12 - MVC pattern
 */
public class Assignment12 {
    public static void main(String[] args) {
        MasterModelInterface model = new MasterModel();
        ControllerInterface controller = new Controller(model);

        // Start the job
        controller.startJob();
        System.out.printf("Job Status: %s\n", model.getJobStatus());

        System.out.println();

        // Stop the job
        controller.stopJob();
        System.out.printf("Job Status: %s\n", model.getJobStatus());
    }
}

/**
 * Master Model Interface
 */
interface MasterModelInterface {
    /**
     * Start job
     */
    void startJob();

    /**
     * Stop job
     */
    void stopJob();

    /**
     * Set the job status
     * @param status Job status
     */
    void setJobStatus(String status);

    /**
     * Get the job status
     * @return Job status
     */
    String getJobStatus();

    /**
     * Add a slave
     * @param slave Slave
     */
    void addSlave(Slave slave);

    /**
     * Remove a slave
     * @param slave Slave
     */
    void removeSlave(Slave slave);
}

/**
 * Master Model
 */
class MasterModel extends Observable implements MasterModelInterface {
    private ArrayList<Slave> slaves = new ArrayList<>();
    private String jobStatus;

    /**
     * Start the job
     */
    @Override
    public void startJob() {
        System.out.println("Job started");
        this.setChanged();
        this.notifyObservers("started");
    }

    /**
     * Stop the job
     */
    @Override
    public void stopJob() {
        System.out.println("Job stopped");
        this.setChanged();
        this.notifyObservers("stopped");
    }

    @Override
    public void setJobStatus(String status) {
        System.out.printf("Job status changed to %s\n", status);
        this.jobStatus = status;
    }

    /**
     * Get the job status
     * @return Job status
     */
    @Override
    public String getJobStatus() {
        return this.jobStatus;
    }

    /**
     * Add a slave
     * @param slave Slave
     */
    @Override
    public void addSlave(Slave slave) {
        slaves.add(slave);
        this.addObserver(slave);
        System.out.println("Slave added");
    }

    /**
     * Remove a slave
     * @param slave Slave
     */
    @Override
    public void removeSlave(Slave slave) {
        slaves.remove(slave);
        this.deleteObserver(slave);
        System.out.println("Slave removed");
    }
}

/**
 * Slave class
 */
class Slave implements Observer {
    private ControllerInterface controller;
    private MasterModelInterface model;

    /**
     * Constructor for a Slave
     * @param controller Controller Interface
     * @param model Master Model
     */
    public Slave(ControllerInterface controller, MasterModelInterface model) {
        this.controller = controller;
        this.model = model;
        model.addSlave(this);
    }

    /**
     * Perform update
     * @param o Observable object
     * @param arg Argument
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.printf("Slave updating...%s\n", arg);
    }
}

/**
 * Controller Interface
 */
interface ControllerInterface {
    void startJob();
    void stopJob();
    void setJobStatus(String status);
}

/**
 * Controller class
 */
class Controller implements ControllerInterface {
    private MasterModelInterface model;
    private Slave slave;

    /**
     * Controller constructor
     * @param model Model Interface
     */
    public Controller(MasterModelInterface model) {
        this.model = model;
        slave = new Slave(this, model);
    }

    /**
     * Start the job
     */
    @Override
    public void startJob() {
        model.startJob();
        setJobStatus("Running");
    }

    /**
     * Stop the job
     */
    @Override
    public void stopJob() {
        model.stopJob();
        setJobStatus("Stopped");
    }

    /**
     * Set the job status
     * @param status Job status
     */
    @Override
    public void setJobStatus(String status) {
        model.setJobStatus(status);
    }
}