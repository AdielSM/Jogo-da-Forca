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

    /**
     * Construtor da classe JogoDaForca.
     * Carrega as palavras e dicas a partir de um arquivo de texto.
     *
     * @throws Exception se ocorrer um erro ao carregar as palavras.
     */
    public JogoDaForca() throws Exception {
        this.palavras = new ArrayList<>();
        this.dicas = new ArrayList<>();
        this.letrasDigitadas = new ArrayList<>();
        this.carregarPalavras();
    }

    /**
     * Carrega as palavras e dicas a partir de um arquivo de texto.
     * O arquivo de texto deve estar no formato "palavra;dica" por linha.
     * As palavras e dicas são armazenadas nas listas 'palavras' e 'dicas',
     * respectivamente.
     *
     * @throws Exception se o arquivo de texto não for encontrado.
     */
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

    public int getAcertos() {
        return this.acertos;
    }

    public int getNumeroPenalidades() {
        return this.erros;
    }

    /**
     * Retorna o resultado do jogo.
     *
     * @return O resultado do jogo, que pode ser um dos seguintes valores:
     *         - "Você venceu!" se o jogador acertou todas as letras da palavra.
     *         - "Você perdeu!" se o jogador errou 6 vezes.
     *         - "Jogo em andamento." se o jogo ainda não terminou.
     */
    public String getResultado() {
        String resultado = "";
        if (this.acertos == palavraSorteada.length()) {
            resultado = "Você venceu!";
        } else if (this.erros == 6) {
            resultado = "Você perdeu!";
        } else {
            resultado = "Jogo em andamento.";
        }

        return resultado;
    }

    /**
     * Verifica se o jogo terminou.
     *
     * @return true se o jogo terminou, false caso contrário.
     */
    public boolean terminou() {
        return this.acertos == palavraSorteada.length() || this.erros == 6;
    }

    /**
     * Inicia o jogo da forca, selecionando uma palavra aleatória e sua dica correspondente.
     */
    public void iniciar() {
        Random rand = new Random();
        int numRandom = rand.nextInt(this.palavras.size());
        this.palavraSorteada = this.palavras.get(numRandom);
        this.dica = this.dicas.get(numRandom);
    }

    /**
     * Retorna as ocorrências de uma letra na palavra sorteada.
     *
     * @param letra a letra a ser verificada
     * @return um ArrayList contendo as posições da letra na palavra sorteada
     * @throws Exception se a letra informada não for válida
     */
    public ArrayList<Integer> getOcorrencias(String letra) throws Exception {
        letra = letra.toUpperCase();

        if (letra.length() != 1) {
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

    /**
     * Retorna a palavra sorteada modificada de acordo com as letras adivinhadas.
     *
     * @return a palavra sorteada com letras adivinhadas reveladas e as demais substituídas por asteriscos
     */
    public String getPalavraAdvinhada() {
        StringBuilder palavraAdvinhada = new StringBuilder();
        for (int i = 0; i < this.palavraSorteada.length(); i++) {
            if (this.letrasDigitadas.contains(String.valueOf(this.palavraSorteada.charAt(i)))) {
                palavraAdvinhada.append(this.palavraSorteada.charAt(i));
            } else {
                palavraAdvinhada.append("*");
            }
        }
        return palavraAdvinhada.toString();
    }
}
