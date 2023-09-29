package project.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.model.Contato;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncluirPessoaRequest {
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private List<Contato> contatos;
	
}
