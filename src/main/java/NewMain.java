
import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Console;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NewMain {

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese la dimension del universo");
        int n = scan.nextInt();
        int generation = 1;
        char[][] currentGen = new char[n][n];
        char[][] newGen = new char[n][n];
        int cont = 0;
        int cont2 = 0;
        Random random = new Random();
        System.out.println("Ingrese cantidad de generaciones");
        int g = scan.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (random.nextBoolean() == true) {
                    currentGen[i][j] = 'O';

                } else {
                    currentGen[i][j] = ' ';
                }
            }

        }

        for (int i = 0; i < g; i++) {
            System.out.println("Generacion #" + generation);
            System.out.println("Alive : " + countLive(currentGen, n, n));
            mostrarMatriz(currentGen, n, n);
            newGen = nextGen(currentGen);

            Thread.sleep(1000);

            clearConsole();

            currentGen = newGen.clone();
            generation++;

        }

    }

    public final static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void mostrarMatriz(char[][] matriz, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println("");
        }
    }

    public static int countLive(char[][] matriz, int x, int y) {
        int cont = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matriz[i][j] == 'O') {
                    cont++;
                }
            }
        }
        return cont;
    }

    public static int countNeight(char[][] matriz, int x, int y) {
        //chequeo esquinas 
        int lifeCounter = 0;
        int size = matriz.length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                int row = (x + i + size) % size;
                int col = (y + j + size) % size;

                if (matriz[row][col] == 'O') {
                    lifeCounter++;

                }
            }
        }
        if (matriz[x][y] == 'O') {
            lifeCounter--;
        }
        return lifeCounter;
    }

    public static char[][] nextGen(char[][] matrix1) {
        int size = matrix1.length;
        char[][] nextGen = new char[size][size];
        int cont = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int life = countNeight(matrix1, i, j);
                if (life == 3 && matrix1[i][j] == ' ') {

                    nextGen[i][j] = 'O';
                } else if ((life < 2 || life > 3) && matrix1[i][j] == 'O') {
                    nextGen[i][j] = ' ';

                } else {
                    nextGen[i][j] = matrix1[i][j];
                }

            }

        }
        return nextGen;
    }
}
