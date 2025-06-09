package com.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost"; // ou IP do servidor
        final int PORTA = 12345;

        try (
            Socket socket = new Socket(SERVIDOR, PORTA);
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado ao servidor de mensagens!");

            boolean continuar = true;
            while (continuar) {
                System.out.print("Digite o número da mensagem (0 para aleatória): ");
                int numero = scanner.nextInt();
                scanner.nextLine(); // limpar o buffer

                System.out.print("Deseja encerrar a conexão após essa mensagem? (true/false): ");
                boolean encerrar = scanner.nextBoolean();
                scanner.nextLine(); // limpar o buffer

                // Envia requisição
                saida.writeInt(numero);
                saida.writeBoolean(encerrar);

                // Recebe resposta
                String tipo = entrada.readUTF();
                String mensagem = entrada.readUTF();

                if (tipo.equals("OK")) {
                    System.out.println("Mensagem recebida: " + mensagem);
                } else if (tipo.equals("ERRO")) {
                    System.out.println("Erro: " + mensagem);
                }

                if (encerrar) {
                    continuar = false;
                }
            }

            System.out.println("Conexão encerrada.");
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o servidor: " + e.getMessage());
        }
    }
}
