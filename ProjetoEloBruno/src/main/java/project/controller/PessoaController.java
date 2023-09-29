package project.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.controller.dto.AtualizarPessoaRequest;
import project.controller.dto.IncluirPessoaRequest;
import project.controller.dto.IncluirPessoaResponse;
import project.model.Pessoa;
import project.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaController {

	private final PessoaService pessoaService;
	private final ObjectMapper mapper = new ObjectMapper();

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping()
	public ResponseEntity<List<Pessoa>> listar() {
		return new ResponseEntity<>(pessoaService.listarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> obter(@PathVariable("id") Long id) {
		return new ResponseEntity<>(pessoaService.obterPessoa(id), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<IncluirPessoaResponse> incluir(@RequestParam String dadosPessoa) throws IOException {
		final var incluirPessoaRequest = mapper.readValue(dadosPessoa, IncluirPessoaRequest.class);
		var pessoa = pessoaService.incluirPessoa(incluirPessoaRequest);
		var pessoaResponse = new IncluirPessoaResponse();
		BeanUtils.copyProperties(pessoa, pessoaResponse);
		return new ResponseEntity<>(pessoaResponse, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<Pessoa> atualizar(@RequestParam String dadosPessoa) throws IOException {
		final var atualizarPessoaRequest = mapper.readValue(dadosPessoa, AtualizarPessoaRequest.class);
		var pessoa = pessoaService.atualizarPesoa(atualizarPessoaRequest);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		pessoaService.excluirPessoa(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}