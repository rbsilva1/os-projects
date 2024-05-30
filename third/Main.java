package third;

public class Main {
    
    public static void main(String[] args) {
        
        Table mesa = new Table();

        for( int i = 0; i < 5; i ++ ) {
            new Philosofer(i, mesa).start();
        }
    }
}
