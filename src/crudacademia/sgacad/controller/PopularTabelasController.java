package sgacad.controller;

import java.time.LocalDate;
public class PopularTabelasController {

    public static void main(String[] args) {


        PessoaController.criarPessoa("Administradora Padrao", 'F', getDate(1990, 3, 20), "adm", "adm", "Administrador");
        PessoaController.criarPessoa("Professor Padrao", 'M', getDate(1990, 3, 20), "prof", "prof", "Professor/Instrutor");
        PessoaController.criarPessoa("Aluno Padrao", 'M', getDate(1990, 3, 20), "aluno", "aluno", "Aluno");
        PessoaController.criarPessoa("Pablo", 'M', getDate(1990, 3, 20), "pablo", "pablo", "Aluno");
        PessoaController.criarPessoa("Elaine", 'F', getDate(1992, 8, 25), "elaine", "elaine", "Aluno");
        PessoaController.criarPessoa("Alexandre", 'M', getDate(1995, 11, 12), "alexandre", "alexandre", "Aluno");
        PessoaController.criarPessoa("Michelle", 'F', getDate(1988, 4, 5), "michelle", "michelle", "Aluno");
        PessoaController.criarPessoa("Fernando", 'M', getDate(1993, 9, 30), "fernando", "fernando", "Aluno");
        PessoaController.criarPessoa("Nathalie", 'f', getDate(1997, 12, 18), "nathalie", "nathalie", "Aluno");
        PessoaController.criarPessoa("Ricardo", 'M', getDate(1991, 6, 10), "ricardo", "ricardo", "Aluno");
        PessoaController.criarPessoa("Juliana", 'F', getDate(1994, 1, 22), "juliana", "juliana", "Aluno");
        PessoaController.criarPessoa("Rafael", 'M', getDate(1985, 5, 15), "rafael", "rafael", "Professor/Instrutor");
        PessoaController.criarPessoa("Marcela", 'F', getDate(1987, 7, 10), "marcela", "marcela", "Professor/Instrutor");


        ExercicioController.adicionarExercicio("Supino", "Supino com barra");
        ExercicioController.adicionarExercicio("Agachamento", "Agachamento livre");
        ExercicioController.adicionarExercicio("Levantamento Terra", "Levantamento terra com barra");
        ExercicioController.adicionarExercicio("Flexao", "Flexao de braco");
        ExercicioController.adicionarExercicio("Rosca Direta", "Rosca direta com halteres");
        ExercicioController.adicionarExercicio("Cadeira Extensora", "Extensao de pernas na cadeira extensora");
        ExercicioController.adicionarExercicio("Puxada Frontal", "Puxada frontal na barra");
        ExercicioController.adicionarExercicio("Desenvolvimento de Ombros", "Desenvolvimento de ombros com halteres");


        ExercicioAplicacaoController.geraExercicioAplicacao(1, "Supino", "Supino reto com peso");
        ExercicioAplicacaoController.geraExercicioAplicacao(2, "Agachamento", "Agachamento reto com peso");
        ExercicioAplicacaoController.geraExercicioAplicacao(3, "Levantamento Terra", "Levantamento terra com barra pesada");
        ExercicioAplicacaoController.geraExercicioAplicacao(4, "Flexao", "Flexao normal sem peso");
        ExercicioAplicacaoController.geraExercicioAplicacao(5, "Rosca Direta", "Rosca direta com halteres pesados");
        ExercicioAplicacaoController.geraExercicioAplicacao(6, "Cadeira Extensora", "Extensao de pernas com carga");
        ExercicioAplicacaoController.geraExercicioAplicacao(7, "Puxada Frontal", "Puxada frontal com peso");
        ExercicioAplicacaoController.geraExercicioAplicacao(8, "Desenvolvimento de Ombros", "Desenvolvimento de ombros com halteres pesados");

        TreinoController.adicionarTreino("Fortificacao", getDate(2025, 1, 15), getDate(2025, 6, 15));
        TreinoController.adicionarTreino("Preparacao para competicao", getDate(2025, 1, 15), getDate(2025, 9, 10));
        TreinoController.adicionarTreino("Emagrecimento", getDate(2025, 5, 20), getDate(2025, 11, 20));
        TreinoController.adicionarTreino("Hipertrofia", getDate(2025, 1, 15), getDate(2025, 6, 15));


        // DivisaoTreinoView.divisoesTreinos[0] = new DivisaoTreino(0, "Fortificacao", "Treino segunda sexta sabado", currentDate, currentDate);
        // DivisaoTreinoView.divisoesTreinos[1] = new DivisaoTreino(1, "Preparacao para competicao", "Treino intenso, descansa 2 dias", currentDate, currentDate);
        // DivisaoTreinoView.divisoesTreinos[2] = new DivisaoTreino(2, "Emagrecimento", "Treino diario com dieta especifica", currentDate, currentDate);
        // DivisaoTreinoView.divisoesTreinos[3] = new DivisaoTreino(3, "Hipertrofia", "Treino focado em ganho de massa muscular", currentDate, currentDate);

        // DivisaoTreinoView.numDivisoesTreino = 4;

        DivisaoTreinoController.geraDivisaoTreino(1, "Fortificacao", "Treino segunda sexta sabado");
        DivisaoTreinoController.geraDivisaoTreino(2, "Preparacao para competicao", "Treino intenso, descansa 2 dias");
        DivisaoTreinoController.geraDivisaoTreino(3, "Emagrecimento", "Treino diario com dieta especifica");
        DivisaoTreinoController.geraDivisaoTreino(4, "Hipertrofia", "Treino focado em ganho de massa muscular");

        DivisaoTreinoMusculoController.geraOuAtualizaDivisaoTreinoMusculo(1, "Fortificacao", "Treino segunda sexta sabado", "Peito");
        DivisaoTreinoMusculoController.geraOuAtualizaDivisaoTreinoMusculo(2, "Preparacao para competicao", "Treino intenso, descansa 2 dias", "Perna e Gluteo");
        DivisaoTreinoMusculoController.geraOuAtualizaDivisaoTreinoMusculo(3, "Emagrecimento", "Treino diario com dieta especifica", "Braco e Peito");
        DivisaoTreinoMusculoController.geraOuAtualizaDivisaoTreinoMusculo(4, "Hipertrofia", "Treino focado em ganho de massa muscular", "Costas e Ombro");

        // DivisaoTreinoMusculoView.divisoesTreinoMusculo[0] = new DivisaoTreinoMusculo(0, "Fortificacao", "Treino segunda sexta sabado", "Peito", currentDate, currentDate);
        // DivisaoTreinoMusculoView.divisoesTreinoMusculo[1] = new DivisaoTreinoMusculo(1, "Preparacao para competicao", "Treino intenso, descansa 2 dias", "Perna e Gluteo", currentDate, currentDate);
        // DivisaoTreinoMusculoView.divisoesTreinoMusculo[2] = new DivisaoTreinoMusculo(2, "Emagrecimento", "Treino diario com dieta especifica", "Braco e Peito", currentDate, currentDate);
        // DivisaoTreinoMusculoView.divisoesTreinoMusculo[3] = new DivisaoTreinoMusculo(3, "Hipertrofia", "Treino focado em ganho de massa muscular", "Costas e Ombro", currentDate, currentDate);

        // DivisaoTreinoMusculoView.numDivisoesTreinoMusculo = 4;

        // MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 49.90, getDate(2021, 12, 11), getDate(2022, 12, 10));
        // MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 59.90, getDate(2022, 12, 11), getDate(2023, 12, 10));
        // MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 69.90, getDate(2023, 12, 11), getDate(2024, 12, 10));
        // MensalidadeVigenteController.cadastrar(MensalidadeVigenteView.numMensalidades, 79.90, getDate(2024, 12, 11), getDate(2025, 12, 10));

        MensalidadeVigenteController.cadastrarOuAtualizar(1, 49.90, getDate(2021, 12, 11), getDate(2022, 12, 10));
        MensalidadeVigenteController.cadastrarOuAtualizar(2, 59.90, getDate(2022, 12, 11), getDate(2023, 12, 10));
        MensalidadeVigenteController.cadastrarOuAtualizar(3, 69.90, getDate(2023, 12, 11), getDate(2024, 12, 10));
        // MensalidadeVigenteController.cadastrarOuAtualizar(4, 79.90, getDate(2024, 12, 11), getDate(2025, 12, 10));

        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor(), 1, "PIX");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 2, 2, "Dinheiro");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 3, 3, "Debito automatico");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor() * 2, 4, "PIX");
        AlunoPagamentoMensalidadeController.gerarAlunoPagamentoMensalidade(MensalidadeVigenteController.getMensalidadeVigente().getValor(), 5, "Pagamento reccorente");

        MovimentacaoFinanceiraController.cadastrar(1200, 1, "Aluguel do Imovel");
        MovimentacaoFinanceiraController.cadastrar(650, 1, "Campanha de marketing");
        MovimentacaoFinanceiraController.cadastrar(6000, 1, "Aquisicao de Equipamentos");
        MovimentacaoFinanceiraController.cadastrar(7000, 1, "Salario dos Professores");
        MovimentacaoFinanceiraController.cadastrar(6000, 1, "Salario dos Administradores");
        MovimentacaoFinanceiraController.cadastrar(12000, 2, "Investimentos dos socios");
        MovimentacaoFinanceiraController.cadastrar(5000, 2, "Venda de equipamentos");


        PagamentoRecorrenteController.cadastrarPagamentoRecorrente(1, "5193 8824 5697 4135", 7);
        PagamentoRecorrenteController.cadastrarPagamentoRecorrente(2, "6011 7291 2150 6230", 3);
        PagamentoRecorrenteController.cadastrarPagamentoRecorrente(3, "5195 3574 4540 5663", 7);
        PagamentoRecorrenteController.cadastrarPagamentoRecorrente(4, "6062 8205 8700 8502", 4);


        // TreinoAplicacaoView.treinosAplicacao[0] = new TreinoAplicacao(0, "Fortificacao", "Supino", "Supino reto com peso", "Treino segunda sexta sabado", "Peito", getDate(2025, 1, 15), getDate(2025, 6, 15), currentDate, currentDate);
        // TreinoAplicacaoView.treinosAplicacao[1] = new TreinoAplicacao(1, "Preparacao para competicao", "Agachamento", "Agachamento reto com peso", "Treino intenso, descansa 2 dias", "Perna e Gluteo", getDate(2025, 3, 10), getDate(2025, 9, 10), currentDate, currentDate);
        // TreinoAplicacaoView.treinosAplicacao[2] = new TreinoAplicacao(2, "Emagrecimento", "Levantamento Terra", "Levantamento terra com barra pesada", "Treino diario com dieta especifica", "Braco e Peito", getDate(2025, 5, 20), getDate(2025, 11, 20), currentDate, currentDate);

        // TreinoAplicacaoView.numTreinosAplicacao = 3;

        TreinoAplicacaoController.geraTreinoAplicacao(1, 1);
        TreinoAplicacaoController.geraTreinoAplicacao(2, 2);
        TreinoAplicacaoController.geraTreinoAplicacao(3, 3);
        TreinoAplicacaoController.geraTreinoAplicacao(1, 2);

    }

    private static LocalDate getDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
