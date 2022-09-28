package antifraud.model.access;

public class Access {

    public static final String LOCK = "LOCK";
    public static final String UNLOCK = "UNLOCK";
    public static final String LOCK_MESSAGE = "locked";
    public static final String UNLOCK_MESSAGE = "unlocked";

    private Access() {
        throw new IllegalStateException("Util class");
    }
}
