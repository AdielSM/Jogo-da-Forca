import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class JogoDaForca {

    private final ArrayList<String> palavras;
    private final ArrayList<String> dicas;
    private final ArrayList<String> letrasDigitadas;
    private String palavraSorteada;
    private String dica;
    private int acertos;
    private int erros;

    public JogoDaForca() throws Exception {
        this.palavras = new ArrayList<>();
        this.dicas = new ArrayList<>();
        this.letrasDigitadas = new ArrayList<>();
        carregarPalavras();
    }

    private void carregarPalavras() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/palavras.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                this.palavras.add(dados[0]);
                this.dicas.add(dados[1]);
            }
            reader.close();
        } catch (IOException e) {
            throw new Exception("Arquivo de texto não encontrado.");
        }
    }

    public String getDica() {
        return this.dica;
    }

    public int getTamanho() {
        return this.palavraSorteada.length();
    }

    public boolean terminou(){
        return this.acertos == 6 || this.erros == 6;
    }

    public void iniciar(){
        Random rand = new Random();
        int numRandom = rand.nextInt(this.palavras.size());
        this.palavraSorteada = this.palavras.get(numRandom);
        this.dica = this.dicas.get(numRandom);
    }

    public ArrayList<Integer> getOcorrencias(String letra) throws Exception {
        letra = letra.toUpperCase();

        if(letra.length() != 1){
            throw new Exception("Apenas uma letra deve ser informada.");
        }
        if (this.letrasDigitadas.contains(letra)) {
            throw new Exception("Letra já digitada.");
        }
        this.letrasDigitadas.add(letra);

        ArrayList<Integer> ocorrencias = new ArrayList<>();
        for (int i = 0; i < this.palavraSorteada.length(); i++) {
            if (this.palavraSorteada.charAt(i) == letra.charAt(0)) {
                ocorrencias.add(i);
                this.acertos++;
            }
        }
        if (ocorrencias.isEmpty()) {
            this.erros++;
        }
        return ocorrencias;
    }
}
