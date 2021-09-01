package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoSericeTest {

	private LocacaoService service;
	
//	private static int contador = 0;//definicao do cntador	
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
//		System.out.println("Before");
		service = new LocacaoService();
//		contador++;//incremento
//		System.out.println(contador);//impressao do contador
	}
	
//	@After
//	public void tearDown() {
//		System.out.println("After");
//	}
//	
//	@BeforeClass
//	public static void setupClass() {
//		System.out.println("Before Class");
//		
//	}
//	
//	@AfterClass
//	public static void tearDownClass() {
//		System.out.println("After Class");
//	}

	@Test
	public void teste() throws Exception {
		// cenario		
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

		// verificacao
//		assertThat(locacao.getValor(), is(equalTo(6.0)));
//		assertThat(locacao.getValor(), is(not(6.0)));
//		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(false));

	}

	// Elegante
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// acao
		service.alugarFilme(usuario, filme);
	}

	// Robusta
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		Filme filme = new Filme("Filme 2", 1, 4.0);

		// acao

		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}

	}

	// Nova
	@Test
	public void testeLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		
		// cenario
		Usuario usuario = new Usuario("Usuario 1");	
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		// acao
		service.alugarFilme(usuario, null);	
		
	}

//	@Test
//	public void testLocacao_filmeSemEstoque_2() {
//		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Usuario 1");
//		Filme filme = new Filme("Filme 1", 0, 5.0);
//
//		// acao
//		try {
//			service.alugarFilme(usuario, filme);
//			Assert.fail("Deveria ter lancado uma excessao");
//		} catch (Exception e) {
//			assertThat(e.getMessage(), is("Filme sem estoque"));
//		}
//	}
//	
//	@Test
//	public void testLocacao_filmeSemEstoque_3() throws Exception {
//		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Usuario 1");
//		Filme filme = new Filme("Filme 1", 0, 5.0);		
//
//		exception.expect(Exception.class);
//		exception.expectMessage("Filme sem estoque");
//		
//		// acao
//		service.alugarFilme(usuario, filme);		
//	}

}
