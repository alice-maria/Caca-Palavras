import java.util.Scanner;

public class CacaPalavras{
    private CacaPalavras() {
        Scanner teclado = new Scanner(System.in);
        
        String [][] palavras = new String[5][2];
        char [][] mapa = new char[10][5];
        
        palavrasEntrada(palavras);
        mapaEntrada(mapa);
        mapaPesquisa(palavras, mapa);
        
        int opcao = 0;
        do {
            System.out.println("_____ Menu: Caça Palavras _____");
            System.out.println("1. listar palavras");
            System.out.println("2. listar mapa");
            System.out.println("3. listar respostas");
            System.out.println("4. sair");
            System.out.print(" __ opção: ");
            opcao = teclado.nextInt();
            switch(opcao) {
                case 1: palavasImprimir(palavras);
                    break;
                case 2: mapaImprimir(mapa);
                    break;
                case 3: palavrasRespostas(palavras);
                    break;
                case 4: break;
                default:
                    System.out.println("Opção ERRADA!...");
                    break;
            }
        } while (opcao != 4); // vai repetir enquanto a resposta for diferente de 4, pq 4 é a opção de saída
    }
    
    private void palavrasEntrada(String [][] palavras) {
        palavras[0][0] = "IFELSE";  
        palavras[1][0] = "FORA";  
        palavras[2][0] = "WHILE";  
        palavras[3][0] = "OBJETO";  
        palavras[4][0] = "VETOR";
    }
    
    private void mapaEntrada(char[][] mapa) {
        mapa[ 0][ 0]='C';  mapa[ 0][ 1]='C';  mapa[ 0][ 2]='Q';  mapa[ 0][ 3]='W';  mapa[ 0][ 4]='E';
        mapa[ 1][ 0]='I';  mapa[ 1][ 1]='X';  mapa[ 1][ 2]='F';  mapa[ 1][ 3]='O';  mapa[ 1][ 4]='R';
        mapa[ 2][ 0]='F';  mapa[ 2][ 1]='F';  mapa[ 2][ 2]='R';  mapa[ 2][ 3]='G';  mapa[ 2][ 4]='F';
        mapa[ 3][ 0]='E';  mapa[ 3][ 1]='L';  mapa[ 3][ 2]='I';  mapa[ 3][ 3]='H';  mapa[ 3][ 4]='W';
        mapa[ 4][ 0]='L';  mapa[ 4][ 1]='S';  mapa[ 4][ 2]='F';  mapa[ 4][ 3]='O';  mapa[ 4][ 4]='U';
        mapa[ 5][ 0]='S';  mapa[ 5][ 1]='D';  mapa[ 5][ 2]='G';  mapa[ 5][ 3]='T';  mapa[ 5][ 4]='S';
        mapa[ 6][ 0]='E';  mapa[ 6][ 1]='J';  mapa[ 6][ 2]='H';  mapa[ 6][ 3]='E';  mapa[ 6][ 4]='T';
        mapa[ 7][ 0]='I';  mapa[ 7][ 1]='I';  mapa[ 7][ 2]='I';  mapa[ 7][ 3]='J';  mapa[ 7][ 4]='M';
        mapa[ 8][ 0]='X';  mapa[ 8][ 1]='C';  mapa[ 8][ 2]='K';  mapa[ 8][ 3]='B';  mapa[ 8][ 4]='G';
        mapa[ 9][ 0]='V';  mapa[ 9][ 1]='E';  mapa[ 9][ 2]='T';  mapa[ 9][ 3]='O';  mapa[ 9][ 4]='R';
    }
    
    private void palavasImprimir(String [][] palavras) { // método para as palavras aparecem para o leitor
        for(int i = 0; i < palavras.length; i++) {
            System.out.println(palavras[i][0]); // vai repeir as linhas até 4, a coluna não tem nada nas palavras por isso é 0
        }
    }
    
    private void palavrasRespostas(String [][] palavras) {
        for (int i = 0; i < palavras.length; i++) {
            System.out.println(palavras[i][1] + " - " + palavras[i][0]); // pq dess 1 palavras[i][1]
        }
    }

    private void mapaImprimir(char[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            System.out.println("---------------------");
            System.out.print("| ");
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " | ");
            }
            System.out.println("");
        }
        System.out.println("---------------------");
    }

    private void mapaPesquisa(String [][] palavras, char[][] mapa) {
        for (int ip = 0; ip < palavras.length; ip++){
            boolean achou = false;
            for (int lin = 0; lin < mapa.length; lin++) {
                int posicao = achaPalavra(palavras[ip][0], palavraHorizontal(lin, mapa));
                if (posicao != -1) {
                    palavras[ip][1] =  "[" + lin + "," + posicao + "]";
                    achou = true;
                }
            }
            for (int col = 0; col < mapa[0].length; col++) {
                int posicao = achaPalavra(palavras[ip][0], palavraVertical(col, mapa));
                if (posicao != -1) {
                    palavras[ip][1] =  "[" + posicao + "," + col + "]";
                    achou = true;
                }
            }
            if (!achou) {
                palavras[ip][1] =  "Palavra NÃO encontrada";
            }
        }
    }

    private String palavraHorizontal(int linha, char[][] mapa) {
        String result = "";
        for (int col = 0; col < mapa[linha].length; col++) {
            result += mapa[linha][col];
        }
        return result;
    }
    
    private String palavraVertical(int coluna, char[][] mapa) {
        String result = "";
        for (int lin = 0; lin < mapa.length; lin++) {
            result += mapa[lin][coluna];
        }
        return result;
    }

    private int achaPalavra(String palavraBusca, String palavra) {
        int ipb = 0;
        if (palavraBusca.length() > palavra.length()) {
            return -1;
        }
        for (int ip = 0; ip < palavra.length(); ip++) {
            if (palavraBusca.charAt(ipb) == palavra.charAt(ip)) {
                ipb++;
            } else {
                ipb = 0;
            }
            if (palavraBusca.length() == ipb) {
                return ip - palavraBusca.length() + 1;
            }
        }
        ipb = 0;
        for (int ip = palavra.length() - 1; ip >= 0; ip--) {
            if (palavraBusca.charAt(ipb) == palavra.charAt(ip)) {
                ipb++;
            } else {
                ipb = 0;
            }
            if (palavraBusca.length() == ipb) {
                return ip + palavraBusca.length() - 1;
            }
        }
        return -1;
    }

    
    
    public static void main(String[] args) {
        new CacaPalavras();
    }
}
