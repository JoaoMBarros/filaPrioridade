package filaPrioridade;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class App {

	static ImageIcon iconMenu = new ImageIcon(App.class.getResource("menu.png"));
	static ImageIcon iconErro = new ImageIcon(App.class.getResource("erro.png"));
	static ImageIcon iconPorcentagem = new ImageIcon(App.class.getResource("iconPorcentagem.png"));
	static ImageIcon iconSucesso = new ImageIcon(App.class.getResource("sucesso.png"));
	static ImageIcon iconPrioridade = new ImageIcon(App.class.getResource("iconPrioridade.png"));

    public static void main(String[] args) {

        fila<String> fila = new fila<String>();
        Scanner read = new Scanner(System.in);

        iconMenu.setImage(iconMenu.getImage().getScaledInstance(50, 50, 100));
        iconSucesso.setImage(iconSucesso.getImage().getScaledInstance(50, 50, 100));
        iconErro.setImage(iconErro.getImage().getScaledInstance(50, 50, 100));
        iconPorcentagem.setImage(iconPorcentagem.getImage().getScaledInstance(50, 50, 100));
        iconPrioridade.setImage(iconPrioridade.getImage().getScaledInstance(50, 50, 100));

        String menu = """
                Qual opcao voce deseja?
                1 - Nova pessoa na fila
                2 - Atender nova pessoa
                3 - Listar pessoas da fila
                4 - Estatisticas
                5 - Sair""";
        String[] prioridades = {"1 - Prioridade normal","2 - Idosos com idade igual ou superior a 60 anos",
                "3 - Portadores de deficiencia", "4 - Gestantes",
                "5 - Lactantes", "6 - Pessoas acompanhadas por crianças de colo"};

        int idade = 0;
        int atendimentos = 0;
        int atendimentosPrioritarios = 0;
        int atendimentosNormal = 0;
        boolean sair = false;

        do {
            int escolha = menu(menu);
            switch (escolha) {
                case 1:
                    String nome = (String) JOptionPane.showInputDialog(null, "Digite o nome", "Nome", JOptionPane.INFORMATION_MESSAGE, iconMenu,
                            null, "");
                        if(nome == null){
                            break;
                        }else if ("".equals(nome.trim())) {
                            JOptionPane.showMessageDialog(null, "Insira um nome valido", "Erro",
                                    JOptionPane.INFORMATION_MESSAGE, iconErro);
                            break;
                        }
                    String prioridade = (String) JOptionPane.showInputDialog(null, "Escolha uma prioridade", "Prioridades",
                            JOptionPane.QUESTION_MESSAGE, iconPrioridade, prioridades, null);

                    if (prioridade.equalsIgnoreCase("1 - Prioridade normal")) {
                        fila.enqueue(nome, 1);
                        atendimentosNormal++;
                        JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                        break;
                    } else if (prioridade.equalsIgnoreCase("2 - Idosos com idade igual ou superior a 60 anos")) {
                        String aux = (String) JOptionPane.showInputDialog(null, "Digite a idade", "Idade", JOptionPane.INFORMATION_MESSAGE, iconPrioridade,
                                null, "");
                        if(aux == null){
                            break;
                        }
                        try {
                            idade = Integer.parseInt(aux);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Digite um valor inteiro", "Erro", JOptionPane.INFORMATION_MESSAGE, iconErro);
                        }
                            atendimentosPrioritarios++;
                        if (idade >= 100) {
                            fila.enqueue(nome, 6);
                            JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                            break;
                        } else if (idade >= 90) {
                            fila.enqueue(nome, 5);
                            JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                            break;
                        } else if (idade >= 80) {
                            fila.enqueue(nome, 4);
                            JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                            break;
                        } else if (idade >= 70) {
                            fila.enqueue(nome, 3);
                            JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                            break;
                        } else if (idade >= 60) {
                            fila.enqueue(nome, 2);
                            JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                            break;
                        }
                    } else {
                        fila.enqueue(nome, 2);
                        atendimentosPrioritarios++;
                        JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                        break;
                    }
                    break;
                case 2:
                    if (!fila.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Pessoa atendida: " + fila.dequeue(), "Sucesso", JOptionPane.INFORMATION_MESSAGE, iconSucesso);
                        atendimentos++;
                    } else{
                        JOptionPane.showMessageDialog(null, "Fila vazia", "Erro", JOptionPane.INFORMATION_MESSAGE, iconErro);
                    }
                    break;
                case 3:
                    if (!fila.isEmpty()) {
                        JOptionPane.showMessageDialog(null, fila.showQueue(), "Pessoas na fila", JOptionPane.INFORMATION_MESSAGE, null);
                    } else{
                        JOptionPane.showMessageDialog(null, "Fila vazia", "Erro", JOptionPane.INFORMATION_MESSAGE, iconErro);
                    }
                    break;
                case 4:
                    if(atendimentos != 0) {
                        double porcentagemPrioritaria = (double) (atendimentosPrioritarios * 100) / atendimentos;
                        double porcentagemNormal = (double) (atendimentosNormal * 100) / atendimentos;
                        DecimalFormat df = new DecimalFormat("#.##");
                        String porcentagemNormalString = df.format(porcentagemNormal);
                        String porcentagemPrioritariaString = df.format(porcentagemPrioritaria);
                        JOptionPane.showMessageDialog(null, "Porcentagem de atendimentos prioritarios: " + porcentagemPrioritariaString +
                                        "%" + "\n" + "Porcentagem de atendimentos normais: " + porcentagemNormalString + "%", "Porcentagem",
                                JOptionPane.INFORMATION_MESSAGE, iconPorcentagem);
                    }else {
                        JOptionPane.showMessageDialog(null, "Nao houveram atendimentos ate o momento", "Atendimentos", JOptionPane.INFORMATION_MESSAGE, iconMenu);
                    }
                    break;
                case 5:
                    if (fila.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Quantidade de pessoas atendidas: " + atendimentos, "Ate amanha", JOptionPane.INFORMATION_MESSAGE, iconMenu);
                        read.close();
                        sair = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Fila ainda com pessoas!", "Erro",
                                JOptionPane.INFORMATION_MESSAGE, iconErro);
                    }
            }
        }while(!sair);
    }


    private static int menu(String menu) {

        int opcao = 0;
        boolean inputAceito = false;

        while (!inputAceito) {

            String aux = (String) JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.INFORMATION_MESSAGE,
                    iconMenu, null, "");

            if (aux == null) {
                System.exit(0);
            }
            try {
                opcao = Integer.parseInt(aux);

                if (opcao < 1 || opcao > 5) {
                    JOptionPane.showMessageDialog(null, "Opcao invalida", "Erro", JOptionPane.INFORMATION_MESSAGE, iconErro);
                } else {
                    inputAceito = true;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor inteiro", "Erro", JOptionPane.INFORMATION_MESSAGE,
                        iconErro);
            }
        }
        return opcao;
    }
}