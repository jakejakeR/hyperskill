package antifraud.model.role;

public class Role {
    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String MERCHANT = "MERCHANT";
    public static final String SUPPORT = "SUPPORT";

    private Role() {
        throw new IllegalStateException("Util class");
    }
}
