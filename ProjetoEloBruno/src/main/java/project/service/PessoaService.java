package project.service;

import java.util.InputMismatchException;
import java.util.List;

import org.springframework.stereotype.Service;

import project.controller.dto.AtualizarPessoaRequest;
import project.controller.dto.IncluirPessoaRequest;
import project.exception.PessoaNotFindException;
import project.model.Contato;
import project.model.Pessoa;
import org.springframework.beans.BeanUtils;

import project.repository.ContatoRepository;
import project.repository.PessoaRepository;

@Service
public class PessoaService {
	
	private final PessoaRepository pessoaRepository;
	private final ContatoRepository contatoRepository;

	public PessoaService(PessoaRepository pessoaRepository,
	ContatoRepository contatoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.contatoRepository = contatoRepository;
	}

	public List<Pessoa> listarTodos() {
		return pessoaRepository.findAll();
	}
	
	public Pessoa obterPessoa(Long id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new PessoaNotFindException("Pessoa Inexistente: " + id));
	}
	
	public Pessoa incluirPessoa(IncluirPessoaRequest incluirPessoaRequest) {
        var pessoa = new Pessoa();
		BeanUtils.copyProperties(incluirPessoaRequest, pessoa);
		List<Contato> contatosPessoa = pessoa.getContatos();
		for (int i=0; i<contatosPessoa.size(); i++) {
			Contato contato = pessoa.getContatos().get(i);
			contatoRepository.save(contato);
		}
		if(isCpf(pessoa.getCpf())) {
			pessoaRepository.save(pessoa);	
		} else {
			pessoa.setCpf("CPF INVÁLIDO");
		}
		pessoaRepository.save(pessoa);
        return pessoa;
		
    }
	
	public Pessoa atualizarPesoa(AtualizarPessoaRequest atualizarPessoaRequest) {
		var pessoa = pessoaRepository.findById(atualizarPessoaRequest.getId()).get();

        BeanUtils.copyProperties(atualizarPessoaRequest, pessoa);
        pessoaRepository.save(pessoa);
        return pessoa;
	}
	
	public void excluirPessoa(Long id) {
		pessoaRepository.deleteById(id);
	}

	public Boolean isCpf(String CPF){

		if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }

        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }

}
