package second;

public class Corredor extends Thread {
    private String nome;
    private Equipe equipe;
    private boolean finalCorrida;

    public Corredor(String nome, Equipe equipe) {
        this.nome = nome;
        this.equipe = equipe;
        this.finalCorrida = false;
    }

    @Override
    public void run() {
        while (!finalCorrida) {
            try {
                equipe.iniciarCorrida(this);
            } catch (Exception e) {
                finalCorrida = true;
            }
        }
    }

    public String getNome() {
        return nome;
    }
}
