import static java.lang.IO.*;

void main() {

    // ===== Estrutura de dados =====
    String[] nomes = new String[10];
    int[] idades = new int[10];
    String[] cursos = new String[10];
    int totalAlunos = 0;

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