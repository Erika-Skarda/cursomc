package com.erikaskarda.cursomc;

import java.text.SimpleDateFormat;
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
import com.erikaskarda.cursomc.domain.ItemPedido;
import com.erikaskarda.cursomc.domain.Pagamento;
import com.erikaskarda.cursomc.domain.PagamentoComBoleto;
import com.erikaskarda.cursomc.domain.PagamentoComCartao;
import com.erikaskarda.cursomc.domain.Pedido;
import com.erikaskarda.cursomc.domain.Produto;
import com.erikaskarda.cursomc.domain.enums.EstadoPagamento;
import com.erikaskarda.cursomc.domain.enums.TipoCliente;
import com.erikaskarda.cursomc.repositories.CategoriaRepository;
import com.erikaskarda.cursomc.repositories.CidadeRepository;
import com.erikaskarda.cursomc.repositories.ClienteRepository;
import com.erikaskarda.cursomc.repositories.EnderecoRepository;
import com.erikaskarda.cursomc.repositories.EstadoRepository;
import com.erikaskarda.cursomc.repositories.ItemPedidoRepository;
import com.erikaskarda.cursomc.repositories.PagamentoRepository;
import com.erikaskarda.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("21/06/2021 17:06"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/06/2021 17:00"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("21/06/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1,  800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
}
