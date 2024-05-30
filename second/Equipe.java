package second;

public class Equipe {
    private String nome;
    private Corredor[] corredores;
    private String[] barreiras;
    private int corredoresNaBarreira;
    private int barreiraAtual;
    private String cor;

    public Equipe(String nome, Corredor[] corredores, String[] barreiras, String cor) {
        this.nome = nome;
        this.corredores = corredores;
        this.barreiras = barreiras;
        this.corredoresNaBarreira = 0;
        this.barreiraAtual = 0;
        this.cor = cor;
    }

    public synchronized void iniciarCorrida(Corredor corredor) throws InterruptedException {
        System.out.println(cor + "Corredor " + corredor.getNome() + " chegou à barreira " + barreiras[barreiraAtual]);
        corredoresNaBarreira++;
        // Thread.sleep(3000);

        if (corredoresNaBarreira >= corredores.length) {
            System.out.println(cor + "Todos os corredores da equipe " + nome + " chegaram na barreira "
                    + barreiras[barreiraAtual]);
            corredoresNaBarreira = 0;
            barreiraAtual++;
            if (barreiraAtual != barreiras.length) {
                System.out.println(cor + "Equipe " + nome + " seguindo para a barreira " + barreiras[barreiraAtual]);
            }
            notifyAll(); // Notifica outras threads esperando neste objeto
        } else {
            wait(); // Espera até que todos os corredores cheguem
        }
    }

    public synchronized void aguardarConclusao() throws InterruptedException {
        while (barreiraAtual < barreiras.length) {
            wait(); // Espera até que todos os corredores completem a corrida
        }
    }

    public String getCor() {
        return cor;
    }
}