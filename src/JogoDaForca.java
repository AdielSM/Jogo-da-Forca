import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JogoDaForca {

    private final ArrayList<String> palavras;
    private final ArrayList<String> dicas;

    public JogoDaForca() throws Exception {
        this.palavras = new ArrayList<>();
        this.dicas = new ArrayList<>();
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



}
