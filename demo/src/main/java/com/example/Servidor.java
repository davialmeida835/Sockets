package com.example;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Servidor {
    private static List<String> mensagens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final int PORTA = 12345;

        // Carrega mensagens do arquivo
        InputStream is = Servidor.class.getResourceAsStream("/com/example/mensagens.txt");
if (is == null) {
    System.out.println("Arquivo mensagens.txt não encontrado no classpath");
    return;
}
try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
    String linha;
    while ((linha = reader.readLine()) != null) {
        if (!linha.trim().isEmpty()) {
            mensagens.add(linha);
        }
    }
}


        System.out.println("Servidor iniciado. Aguardando conexões na porta " + PORTA + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clienteSocket.getInetAddress());

                // Cria uma thread para atender esse cliente
                Thread t = new Thread(new ClienteHandler(clienteSocket, mensagens));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

   private static void carregarMensagens(String caminho) throws IOException {
    mensagens = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(caminho), StandardCharsets.UTF_8))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                mensagens.add(linha);
            }
        }
    }
}

}
