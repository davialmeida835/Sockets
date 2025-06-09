package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteHandler implements Runnable {
    private Socket clienteSocket;
    private List<String> mensagens;
    private Random random = new Random();

    public ClienteHandler(Socket socket, List<String> mensagens) {
        this.clienteSocket = socket;
        this.mensagens = mensagens;
    }

    @Override
    public void run() {
        try (
            DataInputStream entrada = new DataInputStream(clienteSocket.getInputStream());
            DataOutputStream saida = new DataOutputStream(clienteSocket.getOutputStream())
        ) {
            boolean continuar = true;

            while (continuar) {
                int numero = entrada.readInt();
                boolean encerrar = entrada.readBoolean();

                if (numero == 0) {
                    int aleatorio = random.nextInt(mensagens.size());
                    enviarResposta(saida, "OK", mensagens.get(aleatorio));
                } else if (numero >= 1 && numero <= mensagens.size()) {
                    enviarResposta(saida, "OK", mensagens.get(numero - 1));
                } else {
                    enviarResposta(saida, "ERRO", "Número inválido. Informe um número entre 0 e " + mensagens.size());
                }

                if (encerrar) {
                    continuar = false;
                }
            }

            System.out.println("Cliente desconectado.");
            clienteSocket.close();

        } catch (IOException e) {
            System.out.println("Erro com cliente: " + e.getMessage());
        }
    }

    private void enviarResposta(DataOutputStream saida, String tipo, String mensagem) throws IOException {
        saida.writeUTF(tipo);
        saida.writeUTF(mensagem);
    }
}
