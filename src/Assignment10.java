import java.util.ArrayList;

/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * March 27, 2016
 * Assignment 10 - Composite pattern
 */
public class Assignment10 {
    public static void main(String[] args) {
        // Create the default theme
        Theme theme = new Theme("Default");

        // Add some theme items
        theme.add(new ThemeItem("textColor", "color", "red"));
        theme.add(new ThemeItem("fontSize", "font-size", "12pt"));
        theme.add(new ThemeItem("bgColor", "background-color", "white"));

        // Output the first theme item
        System.out.println(theme.get(0));

        // Output theme and its theme items
        System.out.println(theme);
        theme.items().forEach(System.out::println);
    }
}

/**
 * Theme Component class
 */
abstract class ThemeComponent {
    /**
     * Add a Theme Component
     * @param themeComponent Theme Component to add
     */
    public void add(ThemeComponent themeComponent) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a Theme Component
     * @param themeComponent Theme Component to remove
     */
    public void remove(ThemeComponent themeComponent) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get a Theme Component
     * @param i index to retrieve
     * @return Theme Component
     */
    public ThemeComponent get(int i) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the list of Theme Components
     * @return ArrayList of Theme Components
     */
    public ArrayList<ThemeComponent> items() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the component name
     * @return Component name
     */
    public String getName() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the component type
     * @return Component type
     */
    public String getType() {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the component value
     * @return Component value
     */
    public String getValue() {
        throw new UnsupportedOperationException();
    }
}

/**
 * Theme class
 */
class Theme extends ThemeComponent {
    private String name;
    private ArrayList<ThemeComponent> items = new ArrayList<>();

    /**
     * Theme Constructor
     * @param name Theme name
     */
    Theme(String name) {
        this.name = name;
    }

    /**
     * Add a theme component to the theme
     * @param themeComponent Theme Component to add
     */
    public void add(ThemeComponent themeComponent) {
        this.items.add(themeComponent);
    }

    /**
     * Remove a theme component from the theme
     * @param themeComponent Theme Component to remove
     */
    public void remove(ThemeComponent themeComponent) {
        this.items.remove(themeComponent);
    }

    /**
     * Get a theme component
     * @param i index to retrieve
     * @return Theme Component
     */
    public ThemeComponent get(int i) {
        return this.items.get(i);
    }

    /**
     * Get the list of theme components for this theme
     * @return ArrayList of Theme Components
     */
    public ArrayList<ThemeComponent> items() {
        return this.items;
    }

    /**
     * Get the theme name
     * @return String containing the theme name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get a String representation of the Theme
     * @return String
     */
    public String toString() {
        return String.format("Theme Name: %s", getName());
    }
}

/**
 * Theme Item class
 */
class ThemeItem extends ThemeComponent {
    private String name, type, value;

    /**
     * ThemeItem Constructor
     * @param name Item name
     * @param type Item type
     * @param value Item value
     */
    ThemeItem(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * Get the item name
     * @return String containing the item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the item type
     * @return String containing the item type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the item value
     * @return String containing the item value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Get a String representation of the Theme Item
     * @return String
     */
    public String toString() {
        return String.format("Theme Item: %s (type: %s) = %s", getName(), getType(), getValue());
    }
}
