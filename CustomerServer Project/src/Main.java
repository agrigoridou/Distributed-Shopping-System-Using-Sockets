public class Main {
    public static void main(String[] args) {
        CustomerServer server = new CustomerServer(5000);
        server.start();
    }
}