package com.erikaskarda.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.erikaskarda.cursomc.domain.Categoria;
import com.erikaskarda.cursomc.domain.Cidade;
import com.erikaskarda.cursomc.domain.Cliente;
import com.erikaskarda.cursomc.domain.Endereco;
import com.erikaskarda.cursomc.domain.Estado;
import com.erikaskarda.cursomc.domain.Produto;
import com.erikaskarda.cursomc.domain.enums.TipoCliente;
import com.erikaskarda.cursomc.repositories.CategoriaRepository;
import com.erikaskarda.cursomc.repositories.CidadeRepository;
import com.erikaskarda.cursomc.repositories.ClienteRepository;
import com.erikaskarda.cursomc.repositories.EnderecoRepository;
import com.erikaskarda.cursomc.repositories.EstadoRepository;
import com.erikaskarda.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProdutoRepository produtoRepository;	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Erika Skarda", "erikaskarda@yahoo.com.br", "05844464777", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("944846258", "944834385"));
		
		Endereco e1 = new Endereco(null, "Rua do Lago", "51", "Apto 24", "Vila Nair", "04280000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua do Lago", "51", "Apto 25", "Vila Nair", "04280000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
	
}
