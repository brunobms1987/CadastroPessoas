package project.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue
	@Nonnull
	private Long id;
	
	@Nonnull
	private String nome;
	
	@Nonnull
	private String cpf;
	
	@Nonnull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@OneToMany(targetEntity=Contato.class, fetch=FetchType.EAGER)
    @JoinColumn(name="pessoa_id")
	@Nonnull
	private List<Contato> contatos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
		
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
}
