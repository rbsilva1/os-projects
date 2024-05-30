package second;

public class App {
    // Definindo cores para impressão
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {
        String[] barreiras = {"A", "B", "C", "D"};
        Corredor[] corredoresBrasil = new Corredor[3];
        Corredor[] corredoresArgentina = new Corredor[3];

        Equipe brasil = new Equipe("Brasil", corredoresBrasil, barreiras, GREEN);
        Equipe argentina = new Equipe("Argentina", corredoresArgentina, barreiras, RED);

        for (int i = 0; i < corredoresBrasil.length; i++) {
            corredoresBrasil[i] = new Corredor("Brasil " + (i + 1), brasil);
            corredoresBrasil[i].start();
        }

        for (int i = 0; i < corredoresArgentina.length; i++) {
            corredoresArgentina[i] = new Corredor("Argentina " + (i + 1), argentina);
            corredoresArgentina[i].start();
        }
        
        // Aguarda a conclusão de todas as equipes
        try {
            brasil.aguardarConclusao();
            argentina.aguardarConclusao();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprime a mensagem final após a conclusão de todas as threads dos corredores
        System.out.println(RESET + "FIM DA CORRIDA!!");
    }
}