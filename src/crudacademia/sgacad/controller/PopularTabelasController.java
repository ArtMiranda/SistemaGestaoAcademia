package sgacad.controller;

import java.util.Calendar;
import java.util.Date;

import sgacad.model.DivisaoTreino;
import sgacad.model.DivisaoTreinoMusculo;
import sgacad.model.Exercicio;
import sgacad.model.ExercicioAplicacao;
import sgacad.model.Pessoa;
import sgacad.model.Treino;
import sgacad.model.TreinoAplicacao;
import sgacad.view.DivisaoTreinoMusculoView;
import sgacad.view.DivisaoTreinoView;
import sgacad.view.ExercicioAplicacaoView;
import sgacad.view.ExercicioView;
import sgacad.view.MensalidadeVigenteView;
import sgacad.view.PessoaView;
import sgacad.view.TreinoAplicacaoView;
import sgacad.view.TreinoView;

public class PopularTabelasController {

    public static void main(String[] args) {

        // Controllers
        ExercicioController exercicioController = new ExercicioController();
        ExercicioAplicacaoController exercicioAplicacaoController = new ExercicioAplicacaoController();
        TreinoController treinoController = new TreinoController();
        DivisaoTreinoController divisaoTreinoController = new DivisaoTreinoController();
        DivisaoTreinoMusculoController divisaoTreinoMusculoController = new DivisaoTreinoMusculoController();
        TreinoAplicacaoController treinoAplicacaoController = new TreinoAplicacaoController();
        PessoaController pessoaController = new PessoaController();


        // Alunos data
        Pessoa[] alunos = {
            pessoaController.criarPessoa(0, "Pablo", 'M', getDate(1990, 3, 20), "pablo", "pablo", "Aluno"),
            pessoaController.criarPessoa(1, "Elaine", 'F', getDate(1992, 8, 25), "elaine", "elaine", "Aluno"),
            pessoaController.criarPessoa(2, "Alexandre", 'M', getDate(1995, 11, 12), "alexandre", "alexandre", "Aluno"),
            pessoaController.criarPessoa(3, "Michelle", 'F', getDate(1988, 4, 5), "michelle", "michelle", "Aluno"),
            pessoaController.criarPessoa(4, "Fernando", 'M', getDate(1993, 9, 30), "fernando", "fernando", "Aluno"),
            pessoaController.criarPessoa(5, "Nathalie", 'f', getDate(1997, 12, 18), "nathalie", "nathalie", "Aluno")
        };

        Pessoa[] professores = {
            pessoaController.criarPessoa(0, "Eduardo", 'M', getDate(1985, 5, 15), "eduardo", "eduardo", "Professor/Instrutor"),
            pessoaController.criarPessoa(1, "Daniela", 'F', getDate(1978, 10, 8), "daniela", "daniela", "Professor/Instrutor")
        };

        Pessoa administrador = pessoaController.criarPessoa(1, "Jose Carlos", 'M', getDate(1980, 1, 1), "josecarlos", "josecarlos", "Administrador");
    
        // Exercicio data
        Exercicio[] exercicios = {
                exercicioController.geraExercicio(0, "Supino", "Supino com barra"),
                exercicioController.geraExercicio(1, "Agachamento", "Agachamento livre"),
                exercicioController.geraExercicio(2, "Levantamento Terra", "Levantamento terra com barra"),
                exercicioController.geraExercicio(3, "Flexao", "Flexao de braco"),
                exercicioController.geraExercicio(4, "Rosca Direta", "Rosca direta com halteres"),
                exercicioController.geraExercicio(5, "Cadeira Extensora", "Extensao de pernas na cadeira extensora"),
                exercicioController.geraExercicio(6, "Puxada Frontal", "Puxada frontal na barra"),
                exercicioController.geraExercicio(7, "Desenvolvimento de Ombros",
                        "Desenvolvimento de ombros com halteres")
        };

        // ExercicioAplicacao data
        ExercicioAplicacao[] exerciciosAplicacao = {
                exercicioAplicacaoController.geraExercicioAplicacao(0, "Supino", "Supino reto com peso"),
                exercicioAplicacaoController.geraExercicioAplicacao(1, "Agachamento", "Agachamento reto com peso"),
                exercicioAplicacaoController.geraExercicioAplicacao(2, "Levantamento Terra",
                        "Levantamento terra com barra pesada"),
                exercicioAplicacaoController.geraExercicioAplicacao(3, "Flexao", "Flexao normal sem peso"),
                exercicioAplicacaoController.geraExercicioAplicacao(4, "Rosca Direta",
                        "Rosca direta com halteres pesados"),
                exercicioAplicacaoController.geraExercicioAplicacao(5, "Cadeira Extensora",
                        "Extensao de pernas com carga"),
                exercicioAplicacaoController.geraExercicioAplicacao(6, "Puxada Frontal", "Puxada frontal com peso"),
                exercicioAplicacaoController.geraExercicioAplicacao(7, "Desenvolvimento de Ombros",
                        "Desenvolvimento de ombros com halteres pesados")
        };

        // Treino data
        Treino[] treinos = {
                treinoController.geraTreino(0, "Fortificacao", getDate(2025, 1, 15), getDate(2025, 6, 15)),
                treinoController.geraTreino(1, "Preparacao para competicao", getDate(2025, 3, 10),
                        getDate(2025, 9, 10)),
                treinoController.geraTreino(2, "Emagrecimento", getDate(2025, 5, 20), getDate(2025, 11, 20)),
                treinoController.geraTreino(3, "Hipertrofia", getDate(2025, 2, 5), getDate(2025, 8, 5))
        };

        // DivisaoTreino data
        DivisaoTreino[] divisoesTreino = {
            divisaoTreinoController.geraDivisaoTreino(0, "Fortificacao", "Treino segunda sexta sabado"),
            divisaoTreinoController.geraDivisaoTreino(1, "Preparacao para competicao", "Treino intenso, descansa 2 dias"),
            divisaoTreinoController.geraDivisaoTreino(2, "Emagrecimento", "Treino diario com dieta especifica"),
            divisaoTreinoController.geraDivisaoTreino(3, "Hipertrofia", "Treino focado em ganho de massa muscular")
        };

        // DivisaoTreinoMusculo data
        DivisaoTreinoMusculo[] divisoesTreinoMusculo = {
            divisaoTreinoMusculoController.geraDivisaoTreinoMusculo(0, "Fortificacao", "Treino segunda sexta sabado", "Peito"),
            divisaoTreinoMusculoController.geraDivisaoTreinoMusculo(1, "Preparacao para competicao", "Treino intenso, descansa 2 dias", "Perna e Gluteo"),
            divisaoTreinoMusculoController.geraDivisaoTreinoMusculo(2, "Emagrecimento", "Treino diario com dieta especifica", "Braco e Peito"),
            divisaoTreinoMusculoController.geraDivisaoTreinoMusculo(3, "Hipertrofia", "Treino focado em ganho de massa muscular", "Costas e Ombro")
        };

        MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 49.90, getDate(2021, 12, 11), getDate(2022, 12, 10));
        MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 59.90, getDate(2022, 12, 11), getDate(2023, 12, 10));
        MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 69.90, getDate(2023, 12, 11), getDate(2024, 12, 10));
        MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 79.90, getDate(2024, 12, 11), getDate(2025, 12, 10));

        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor(), 0, "PIX");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 2, 1, "Dinheiro");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 3, 2, "Debito automatico");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 2, 3, "PIX");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor(), 4, "Pagamento reccorente");

        MovimentacaoFinanceiraController.cadastrar(1200, 1, "Aluguel do Imovel");
        MovimentacaoFinanceiraController.cadastrar(650, 1, "Campanha de marketing");
        MovimentacaoFinanceiraController.cadastrar(6000, 1, "Aquisicao de Equipamentos");
        MovimentacaoFinanceiraController.cadastrar(7000, 1, "Salario dos Professores");
        MovimentacaoFinanceiraController.cadastrar(6000, 1, "Salario dos Administradores");
        MovimentacaoFinanceiraController.cadastrar(12000, 2, "Investimentos dos socios");
        MovimentacaoFinanceiraController.cadastrar(5000, 2, "Venda de equipamentos");


        PagamentoRecorrenteController.cadastrarPagamentoRecorente(1, "5193 8824 5697 4135", 7);
        PagamentoRecorrenteController.cadastrarPagamentoRecorente(2, "6011 7291 2150 6230", 3);
        PagamentoRecorrenteController.cadastrarPagamentoRecorente(3, "3482 553022 56392", 7);
        PagamentoRecorrenteController.cadastrarPagamentoRecorente(4, "6062 8261 1823 5928", 4);

        PessoaView.alunos = alunos;
        PessoaView.numAlunos = alunos.length;
        PessoaView.professoresInstrutores = professores;
        PessoaView.numProfessoresInstrutores = professores.length;
        PessoaView.administradores[1] = administrador;
        PessoaView.numAdministradores = 2;
        ExercicioView.exercicios = exercicios;
        ExercicioView.numExercicios = exercicios.length;
        ExercicioAplicacaoView.exerciciosAplicacao = exerciciosAplicacao;
        ExercicioAplicacaoView.numExerciciosAplicacao = exerciciosAplicacao.length;
        TreinoView.treinos = treinos;
        TreinoView.numTreinos = treinos.length;
        DivisaoTreinoView.divisoesTreinos = divisoesTreino;
        DivisaoTreinoView.numDivisoesTreino = divisoesTreino.length;
        DivisaoTreinoMusculoView.divisoesTreinoMusculo = divisoesTreinoMusculo;
        DivisaoTreinoMusculoView.numDivisoesTreinoMusculo = divisoesTreinoMusculo.length;

        TreinoAplicacao[] treinosAplicacao = {
                treinoAplicacaoController.geraTreinoAplicacao(0, 0),
                treinoAplicacaoController.geraTreinoAplicacao(1, 1),
                treinoAplicacaoController.geraTreinoAplicacao(2, 2),
        };

        TreinoAplicacaoView.treinosAplicacao = treinosAplicacao;
        TreinoAplicacaoView.numTreinosAplicacao = treinosAplicacao.length;
    }

    private static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // Month is zero-based in Calendar
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

}
