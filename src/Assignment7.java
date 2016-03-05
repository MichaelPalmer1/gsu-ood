/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * February 28, 2016
 * Assignment 7 - Adapter pattern
 */
public class Assignment7 {
    public static void main(String[] args) {
        LocalTransaction local = new LocalTransaction(100);
        System.out.println("Local Amount: " + local.getAmount());

        TransactionAdapter adapter = new TransactionAdapter(new ExternalTransaction(100));
        System.out.println("External Amount: " + adapter.getAmount());
    }
}

/**
 * Transaction interface
 */
interface Transaction {
    /**
     * Get the transaction amount
     * @return Amount
     */
    double getAmount();

    /**
     * Add tax to the transaction
     */
    void tax();
}

/**
 * Abstract Transaction class
 */
abstract class AbstractTransaction implements Transaction {
    protected double amount;

    /**
     * Creates a new transaction with the specified amount
     * @param amount Transaction amount
     */
    public AbstractTransaction(double amount) {
        this.amount = amount;
    }

    /**
     * Get the transaction amount
     * @return Amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Apply standard tax to the transaction
     */
    public void tax() {
        amount *= 1.07;
    }
}

/**
 * Local Transaction class
 */
class LocalTransaction extends AbstractTransaction {

    /**
     * Create a new transaction with the specified amount
     * and apply the tax
     * @param amount Transaction amount
     */
    public LocalTransaction(double amount) {
        super(amount);
        tax();
    }

    /**
     * Apply local tax to the transaction
     */
    @Override
    public void tax() {
        amount *= 1.06;
    }
}

/**
 * External Transaction class
 */
class ExternalTransaction {
    private double amount;
    private static final double CONVERSION_RATE = 0.5;

    /**
     * Create a new external transaction with the specified amount
     * @param amount Transaction amount
     */
    public ExternalTransaction(double amount) {
        this.amount = amount;
    }

    /**
     * Get the conversion rate
     * @return Conversion rate
     */
    public double getConversionRate() {
        return CONVERSION_RATE;
    }

    /**
     * Get the transaction amount
     * @return Amount
     */
    public double getExternalAmount() {
        return amount;
    }
}

/**
 * Transaction Adapter class
 */
class TransactionAdapter extends AbstractTransaction {
    private ExternalTransaction externalTransaction;

    /**
     * Create a new transaction adapter for the specified External Transaction
     * @param externalTransaction External Transaction to adapt
     */
    public TransactionAdapter(ExternalTransaction externalTransaction) {
        super(externalTransaction.getExternalAmount());
        this.externalTransaction = externalTransaction;
    }

    /**
     * Get the transaction amount, applying the conversion rate
     * @return Amount
     */
    @Override
    public double getAmount() {
        return super.getAmount() * externalTransaction.getConversionRate();
    }
}
