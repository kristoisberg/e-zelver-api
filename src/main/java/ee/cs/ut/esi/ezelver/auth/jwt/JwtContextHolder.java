package ee.cs.ut.esi.ezelver.auth.jwt;

public class JwtContextHolder {
    private static final ThreadLocal<JwtContext> contexts = new ThreadLocal<>();

    public static void set(JwtContext context) {
        contexts.set(context);
    }

    public static JwtContext get() {
        return contexts.get();
    }

    public static boolean isPresent() {
        return contexts.get() != null;
    }

    public static void clear() {
        contexts.remove();
    }
}
