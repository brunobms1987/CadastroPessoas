package project.controller.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import project.model.Contato;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarPessoaRequest {
	private Long id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private List<Contato> contatos;	
	
}
