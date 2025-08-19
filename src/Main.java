// Entry point kept in the default package as requested.
// It just delegates to app.Principal to keep the domain packages organized.
public class Main {
    public static void main(String[] args) {
        app.Principal.main(args);
    }
}
