import static java.lang.IO.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

void main() {

    // ===== Estrutura de dados =====
    String[] nomes = new String[10];
    int[] idades = new int[10];
    String[] cursos = new String[10];
    int totalAlunos;

    // ===== Persistência em arquivo =====
    String arquivoDados = "alunos.csv";
    totalAlunos = carregarAlunos(arquivoDados, nomes, idades, cursos);

    boolean continuar = true;

    while (continuar) {

        // ===== Exibição do menu =====
        println("===== SISTEMA DE CADASTRO DE ALUNOS =====");
        println("");
        println("1 - Cadastrar aluno");
        println("2 - Listar alunos");
        println("3 - Buscar aluno pelo nome");

        println("4 - Remover aluno");
        println("5 - Sair");
        println("");
        int opcao = Integer.parseInt(readln("Escolha uma opção: "));

        switch (opcao) {

            // ===== Opção 1 - Cadastrar aluno =====
            case 1 -> {
                if (totalAlunos >= 10) {
                    println("Limite de 10 alunos atingido. Não é possível cadastrar novos alunos.");
                } else {
                    String nome = readln("Digite o nome do aluno: ");

                    if (nome.isBlank()) {
                        println("ERRO: o nome do aluno não pode ficar vazio.");
                    } else {
                        int idade = Integer.parseInt(readln("Digite a idade do aluno: "));
                        String curso = readln("Digite o curso do aluno: ");

                        nomes[totalAlunos] = nome;
                        idades[totalAlunos] = idade;
                        cursos[totalAlunos] = curso;
                        totalAlunos++;

                        salvarAlunos(arquivoDados, nomes, idades, cursos, totalAlunos);

                        println("Aluno cadastrado com sucesso!");
                    }
                }
            }

            // ===== Opção 2 - Listar alunos =====
            case 2 -> {
                println("===== LISTA DE ALUNOS =====");
                println("");

                if (totalAlunos == 0) {
                    println("Nenhum aluno cadastrado.");
                } else {
                    for (int i = 0; i < totalAlunos; i++) {
                        println("Aluno " + (i + 1));
                        println("Nome: " + nomes[i]);
                        println("Idade: " + idades[i]);
                        println("Curso: " + cursos[i]);
                        println("");
                    }
                }
            }

            // ===== Opção 3 - Buscar aluno pelo nome =====
            case 3 -> {
                String nomeBusca = readln("Digite o nome do aluno que deseja buscar: ");
                int posicaoEncontrada = -1;

                for (int i = 0; i < totalAlunos; i++) {
                    if (nomes[i].equalsIgnoreCase(nomeBusca)) {
                        posicaoEncontrada = i;
                        break;
                    }
                }

                if (posicaoEncontrada == -1) {
                    println("Aluno não encontrado.");
                } else {
                    println("Nome: " + nomes[posicaoEncontrada]);
                    println("Idade: " + idades[posicaoEncontrada]);
                    println("Curso: " + cursos[posicaoEncontrada]);
                }
            }

            // ===== Opção 4 - Remover aluno =====
            case 4 -> {
                String nomeRemover = readln("Digite o nome do aluno que deseja remover: ");
                int posicaoEncontrada = -1;

                for (int i = 0; i < totalAlunos; i++) {
                    if (nomes[i].equalsIgnoreCase(nomeRemover)) {
                        posicaoEncontrada = i;
                        break;
                    }
                }

                if (posicaoEncontrada == -1) {
                    println("Aluno não encontrado.");
                } else {
                    println("Nome: " + nomes[posicaoEncontrada]);
                    println("Idade: " + idades[posicaoEncontrada]);
                    println("Curso: " + cursos[posicaoEncontrada]);

                    String confirmacao = readln("Deseja realmente remover este aluno? (S/N) ");

                    if (confirmacao.equalsIgnoreCase("S")) {
                        for (int i = posicaoEncontrada; i < totalAlunos - 1; i++) {
                            nomes[i] = nomes[i + 1];
                            idades[i] = idades[i + 1];
                            cursos[i] = cursos[i + 1];
                        }

                        nomes[totalAlunos - 1] = null;
                        idades[totalAlunos - 1] = 0;
                        cursos[totalAlunos - 1] = null;

                        totalAlunos--;

                        salvarAlunos(arquivoDados, nomes, idades, cursos, totalAlunos);

                        println("Aluno removido com sucesso.");
                    }
                }
            }

            // ===== Opção 5 - Sair =====
            case 5 -> {
                continuar = false;
                println("Sistema encerrado.");
            }

            // ===== Opção inválida =====
            default -> println("Opção inválida. Tente novamente.");
        }

        println("");
    }
}

// ===== Carrega os alunos a partir do arquivo CSV (banco de dados) =====
int carregarAlunos(String caminho, String[] nomes, int[] idades, String[] cursos) {
    int total = 0;
    File arquivo = new File(caminho);

    if (!arquivo.exists()) {
        return total;
    }

    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
        String linha;

        while ((linha = leitor.readLine()) != null && total < nomes.length) {
            String[] campos = linha.split(",", 3);

            if (campos.length == 3) {
                nomes[total] = campos[0];
                idades[total] = Integer.parseInt(campos[1]);
                cursos[total] = campos[2];
                total++;
            }
        }
    } catch (IOException erro) {
        println("Não foi possível carregar o arquivo de dados: " + erro.getMessage());
    }

    return total;
}

// ===== Salva os alunos no arquivo CSV (banco de dados) =====
void salvarAlunos(String caminho, String[] nomes, int[] idades, String[] cursos, int totalAlunos) {
    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
        for (int i = 0; i < totalAlunos; i++) {
            escritor.write(nomes[i] + "," + idades[i] + "," + cursos[i]);
            escritor.newLine();
        }
    } catch (IOException erro) {
        println("Não foi possível salvar o arquivo de dados: " + erro.getMessage());
    }
}