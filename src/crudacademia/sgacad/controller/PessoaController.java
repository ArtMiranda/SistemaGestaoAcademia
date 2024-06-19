package sgacad.controller;

import java.time.LocalDate;
import sgacad.view.PessoaView;
import sgacad.model.Pessoa;

public class PessoaController {

    public Pessoa criarPessoa(int id, String nome, char sexo, LocalDate nascimento, String login, String senha,
            String tipoUsuario) {
        LocalDate currentDate = LocalDate.now();
        Pessoa pessoa = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, currentDate, currentDate);
        return pessoa;
    }


    public static Pessoa getAlunoById(int id){
        for (int i = 0; i < PessoaView.alunos.length; i++) {
            if (PessoaView.alunos[i].getId() == id) {
                return PessoaView.alunos[i];
            }
        }
        return null;
    }
}
