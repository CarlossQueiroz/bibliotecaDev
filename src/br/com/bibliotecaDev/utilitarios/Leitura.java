package br.com.bibliotecaDev.utilitarios;

import java.util.Scanner;

public class Leitura {
    private Scanner scanner;

    public Leitura() {
        this.scanner = new Scanner(System.in);
    }

    public String lerString() {
        return scanner.nextLine();
    }

    public int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número inteiro válido.");
            }
        }
    }

    public double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número decimal válido.");
            }
        }
    }

    public void fecharScanner() {
        scanner.close();
    }

}
