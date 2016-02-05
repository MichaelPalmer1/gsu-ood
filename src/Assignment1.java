/**
 * Michael Palmer
 * CSCI 5335 A
 * Assignment 1
 *
 * Test class for the dog abstract class and the relevant interfaces
 *
 */
public class Assignment1 {
    public static void main(String[] args) {
        // German Shepherd (deep woof, no wag)
        GermanShepherd germanShepherd = new GermanShepherd();
        germanShepherd.performSpeak();
        germanShepherd.performWag();
        // Change speak behavior to normal
        germanShepherd.setSpeakBehavior(new NormalWoof());
        germanShepherd.performSpeak();
        System.out.println();

        // Golden Retriever (normal woof, excited wag)
        GoldenRetriever goldenRetriever = new GoldenRetriever();
        goldenRetriever.performSpeak();
        goldenRetriever.performWag();
        System.out.println();

        // Chihuahua (high pitched woof, scared wag)
        Chihuahua chihuahua = new Chihuahua();
        chihuahua.performSpeak();
        chihuahua.performWag();
        // Change wag behavior to excited
        chihuahua.setWagBehavior(new WagExcited());
        chihuahua.performWag();
    }
}

/**
 * Abstract Dog class
 * Utilizes the interfaaces SpeakBehavior and WagBehavior
 */
abstract class Dog {

    protected SpeakBehavior speakBehavior;
    protected WagBehavior wagBehavior;

    /**
     * Constructor for a Dog
     */
    public Dog() {
        System.out.println("I am a dog");
    }

    /**
     * Perform the speak behavior
     */
    public void performSpeak() {
        speakBehavior.speak();
    }

    /**
     * Perform the wag behavior
     */
    public void performWag() {
        wagBehavior.wag();
    }

    /**
     * Set the speak behavior
     * @param speakBehavior SpeakBehavior to change to
     */
    public void setSpeakBehavior(SpeakBehavior speakBehavior) {
        this.speakBehavior = speakBehavior;
    }

    /**
     * Set the wag behavior
     * @param wagBehavior WagBehavior to change to
     */
    public void setWagBehavior(WagBehavior wagBehavior) {
        this.wagBehavior = wagBehavior;
    }
}

/**
 * Golden Retriever class
 */
class GoldenRetriever extends Dog {
    /**
     * Constructor for a Golden Retriever
     * Default speak behavior: Normal
     * Default wag behavior: Excited
     */
    public GoldenRetriever() {
        System.out.println("I am a Golden Retriever");
        speakBehavior = new NormalWoof();
        wagBehavior = new WagExcited();
    }
}

/**
 * German Shepherd class
 */
class GermanShepherd extends Dog {
    /**
     * Constructor for a German Shepherd
     * Default speak behavior: Deep
     * Default wag behavior: No wag
     */
    public GermanShepherd() {
        System.out.println("I am a German Shepherd");
        speakBehavior = new DeepWoof();
        wagBehavior = new NoWag();
    }

}

/**
 * Chihuahua class
 */
class Chihuahua extends Dog {
    /**
     * Constructor for a Chihuahua
     * Default speak behavior: High pitched
     * Default wag behavior: Scared
     */
    public Chihuahua() {
        System.out.println("I am a Chihuahua");
        speakBehavior = new HighPitchWoof();
        wagBehavior = new WagScared();
    }

}

/**
 * Speak Behavior interface
 */
interface SpeakBehavior {
    void speak();
}

/**
 * Deep Woof behavior, implements SpeakBehavior interface
 */
class DeepWoof implements SpeakBehavior {
    @Override
    public void speak() {
        System.out.println("My woof is deep");
    }
}

/**
 * Normal Woof behavior, implements SpeakBehavior interface
 */
class NormalWoof implements SpeakBehavior {
    @Override
    public void speak() {
        System.out.println("My woof is normal");
    }
}

/**
 * High Pitched Woof behavior, implements SpeakBehavior interface
 */
class HighPitchWoof implements SpeakBehavior {
    @Override
    public void speak() {
        System.out.println("My woof is high pitched");
    }
}

/**
 * Wag Behavior interface
 */
interface WagBehavior {
    void wag();
}

/**
 * Excited Wag behavior, implements WagBehavior interface
 */
class WagExcited implements WagBehavior {
    @Override
    public void wag() {
        System.out.println("I am wagging excited");
    }
}

/**
 * Scared Wag behavior, implements WagBehavior interface
 */
class WagScared implements WagBehavior {
    @Override
    public void wag() {
        System.out.println("I am wagging scared");
    }
}

/**
 * No Wag behavior, implements WagBehavior interface
 */
class NoWag implements WagBehavior {
    @Override
    public void wag() {
        System.out.println("I am not wagging");
    }
}
