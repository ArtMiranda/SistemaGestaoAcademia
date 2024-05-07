package sgacad.controller;
import java.util.Calendar;
import java.util.Date;

import sgacad.model.Pessoa;


public class ProfessorController {
    

    public Pessoa criarProfessor(int id, String nome, char sexo, Date nascimento, String login, String senha, String tipoUsuario) {
                Date currentDate = Calendar.getInstance().getTime();
                Pessoa prof = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, currentDate, currentDate);
                return prof;

    }
}
