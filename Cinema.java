package cinema;
import java.io.*;
public class Cinema {
    public static void menu(int rows, int columns,char [][] cinema,int[] statistics) throws IOException {
        InputStreamReader inp = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(inp);
        System.out.print("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
        switch (Integer.parseInt(bf.readLine())) {
            case 1:
                fillBoard(rows,columns,cinema);
                drawBoard(rows,columns,cinema);
                menu(rows,columns,cinema,statistics);
            case 2:
                tickets(cinema,rows,columns,statistics);
                menu(rows,columns,cinema,statistics);
            case 3:
                stats(statistics,rows,columns);
                menu(rows,columns,cinema,statistics);
            case 0:
                break;
            default:
                System.out.print("Wrong input!\n");
                menu(rows,columns,cinema,statistics);
        }
    }
    public static void stats(int[] statistic,int rows,int columns){
        double percentage =0;
        percentage = ((double)statistic[0]/(double)statistic[3])*100;
        System.out.print(String.format("\nNumber of purchased tickets: %d " +
                        "\nPercentage: %.2f%%"+
                        "\nCurrent income: $%d"+
                        "\nTotal income: $%d\n"
                ,statistic[0],percentage,statistic[1],incomeTotal(rows,columns)));
    }
    public  static int incomeTotal (int rows,int seats) {
        int income = 0;
        if (rows * seats <= 60) {
            income = rows * seats * 10;
        } else if ((rows * seats % 2) == 1) {
            income = (rows / 2) * seats * 10 + ((rows / 2) + 1) * seats * 8;
        } else  {
            income = (rows / 2) * seats * 10 + (rows / 2)  * seats * 8;
        } return income;
    }
    public static void tickets ( char[][] cinema, int rows, int columns,int[]statistics) throws IOException {
        statistics[2] = incomeTotal(rows,columns);
        statistics[3] = rows * columns;
        int row;
        int seat;
        InputStreamReader inp = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(inp);
        System.out.print("\nEnter a row number:\n");
         row = Integer.parseInt(bf.readLine());
        System.out.print("Enter a seat number in that row:\n");
        seat = Integer.parseInt(bf.readLine());
        int price = 10;
        if (row <=rows && seat <=columns) {
            if (cinema[row - 1][seat - 1] != 66) {
                cinema[row - 1][seat - 1] = 'B';
                statistics[0]++;
                statistics[1] += 10;
                if ((rows * columns % 2) == 1 && (rows * columns) > 60 && row >= (rows) / 2 + 1) {
                    price = 8;
                    statistics[1] -= 2;
                } else if ((rows * columns % 2) == 0 && (rows * columns) > 60 && row >= (rows) / 2) {
                    price = 8;
                    statistics[1] -= 2;
                }
                System.out.println("Ticket price: $" + price);
            } else {
                System.out.println("That ticket has already been purchased!");
                tickets(cinema,rows,columns,statistics);
            }
        } else {
            System.out.println("Wrong input!");
            tickets(cinema,rows,columns,statistics);
        }
    }
    public static void fillBoard(int rows, int columns,char [][] cinema) {
        //FILL BOARD
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                    if (cinema[i][j]!=66) {
                        cinema[i][j] ='S';
                    }
            }
        }
    }
    public static void drawBoard(int rows, int columns,char [][] cinema) {
        int countColumns = 1;
        int countRows = 1;
        System.out.println("\nCinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= columns; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (j == 0) {
                    System.out.print(countColumns + " ");
                    countColumns++;
                } else if (i == 0) {
                    System.out.print(countRows + " ");
                    countRows++;
                } else {
                    System.out.print(cinema[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        InputStreamReader inp = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(inp);
        System.out.print("Enter the number of rows:\n");
        int rows = Integer.parseInt(bf.readLine());
        System.out.print("Enter the number of seats in each row:\n");
        int columns = Integer.parseInt(bf.readLine());
        char[][] cinema = new char[rows][columns];
        int[] statistics = {0,0,0,1};
        menu(rows,columns,cinema,statistics);
    }
}