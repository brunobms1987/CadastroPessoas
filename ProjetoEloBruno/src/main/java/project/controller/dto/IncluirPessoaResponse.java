package project.controller.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import project.model.Contato;

@Data
public class IncluirPessoaResponse {
	private Long id;
	private String nome;
	private Date dataNascimento;
	private List<Contato> contatos;	

}
