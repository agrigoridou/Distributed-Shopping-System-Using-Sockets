public class Main {
    public static void main(String[] args) {
        ProductServer server = new ProductServer(6000);
        server.start();
    }
}