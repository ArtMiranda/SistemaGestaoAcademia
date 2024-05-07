package sgacad.controller;
import java.util.Calendar;
import java.util.Date;

import sgacad.model.Pessoa;


public class AdministradorController {
    

    public Pessoa criarAdministrador(int id, String nome, char sexo, Date nascimento, String login, String senha, String tipoUsuario) {
                Date currentDate = Calendar.getInstance().getTime();
                Pessoa adm = new Pessoa(id, nome, sexo, nascimento, login, senha, tipoUsuario, currentDate, currentDate);
                return adm;

    }
}
