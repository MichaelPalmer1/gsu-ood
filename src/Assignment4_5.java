import java.util.Scanner;

/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * February 14, 2016
 * Assignment 4-5 - Factory and Singleton patterns
 */
public class Assignment4_5 {
    public static void main(String[] args) {
        // Open the input scanner
        Scanner scan = new Scanner(System.in);

        // Keep listening until user exits
        while (true) {
            // Prompt for query type
            System.out.print("Enter query type (select|insert|update|delete) or press enter to exit: ");

            // Exit if no query type is specified
            String input = scan.nextLine().toUpperCase();
            if (!input.equals("SELECT") && !input.equals("INSERT") &&
                    !input.equals("UPDATE") && !input.equals("DELETE")) {
                System.out.println("Exiting...");
                break;
            }

            // Perform the query
            Database.i().query(input);
            System.out.println();
        }

        // Close the input scanner
        scan.close();
    }
}

/**
 * Singleton Database class
 */
class Database {
    private static Database database;
    QueryFactory query;

    /**
     * Private constructor - only callable from within this class
     */
    private Database() {
        System.out.println("Connecting to database...");
    }

    /**
     * Return a single instance of the database. If one does not exist yet, create it.
     * @return Single instance of Database
     */
    public static synchronized Database i() {
        if (database == null) {
            database = new Database();
        } else {
            System.out.println("Already connected, using existing connection...");
        }
        return database;
    }

    /**
     * Perform the query using the appropriate query factory
     * @param queryType String that specified the query type
     */
    public void query(String queryType) {
        // Assign the appropriate query type (SELECT / INSERT / UPDATE / DELETE)
        queryType = queryType.toUpperCase();
        switch (queryType) {
            case "SELECT":
                query = new SelectQuery();
                break;
            case "INSERT":
                query = new InsertQuery();
                break;
            case "UPDATE":
                query = new UpdateQuery();
                break;
            case "DELETE":
                query = new DeleteQuery();
                break;
            default:
                return;
        }

        // Perform the query
        query.query();
    }
}

/**
 * Query Factory
 */
interface QueryFactory {
    void query();
}

/**
 * Select query
 */
class SelectQuery implements QueryFactory {
    public void query() {
        System.out.println("SELECT * FROM example");
    }
}

/**
 * Insert query
 */
class InsertQuery implements QueryFactory {
    public void query() {
        System.out.println("INSERT INTO example VALUES('john')");
    }
}

/**
 * Update query
 */
class UpdateQuery implements QueryFactory {
    public void query() {
        System.out.println("UPDATE example SET name = 'john' WHERE id = 8");
    }
}

/**
 * Delete query
 */
class DeleteQuery implements QueryFactory {
    public void query() {
        System.out.println("DELETE * FROM example WHERE id < 10");
    }
}
