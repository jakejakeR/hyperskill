package antifraud.model;

public class RoleStr {
    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String MERCHANT = "MERCHANT";
    public static final String SUPPORT = "SUPPORT";

    private RoleStr() {
        throw new IllegalStateException("Util class");
    }
}
