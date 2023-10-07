package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.BoletoBancarioDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.service.PagamentoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class PagamentoResourceTest {
    @Inject
    PagamentoService clienteService;

    @Test
    public void testFindAll() {
        given()
                .when().get("/pagamentos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testInsert() throws ParseException {
        List<PixDTO> listapix = new ArrayList<PixDTO>();
        listapix.add(new PixDTO(
                "63984398131"));

        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimentoCartao = LocalDate.parse(dataVencimentoStr);
        List<CartaoCreditoDTO> listaCartaoCredito = new ArrayList<CartaoCreditoDTO>();
        listaCartaoCredito.add(new CartaoCreditoDTO(
                "Visa",
                "1598753",
                "123",
                dataVencimentoCartao));

        String dataVencimento = "2025-02-10";
        LocalDate dataVencimentoBoleto = LocalDate.parse(dataVencimento);
        List<BoletoBancarioDTO> listaBoleto = new ArrayList<BoletoBancarioDTO>();
        listaBoleto.add(new BoletoBancarioDTO(
                "Nubank",
                "951478",
                dataVencimentoBoleto));

        PagamentoDTO dtoPagamento = new PagamentoDTO(
                "Avista",
                 520.25F,
                listapix,
                listaBoleto,
                listaCartaoCredito);

        given()
                .contentType(ContentType.JSON)
                .body(dtoPagamento)
                .when().post("/pagamentos")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "tipo", is("Avista"),
                        "valor", equalTo(520.25F));
    }

    @Test
    public void testUpdate() throws Exception {
        List<PixDTO> listapix = new ArrayList<PixDTO>();
        listapix.add(new PixDTO(
                "63984398131"));

        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimentoCartao = LocalDate.parse(dataVencimentoStr);
        List<CartaoCreditoDTO> listaCartaoCredito = new ArrayList<CartaoCreditoDTO>();
        listaCartaoCredito.add(new CartaoCreditoDTO(
                "Visa",
                "1598753",
                "123",
                dataVencimentoCartao));

        String dataVencimento = "2025-02-10";
        LocalDate dataVencimentoBoleto = LocalDate.parse(dataVencimento);
        List<BoletoBancarioDTO> listaBoleto = new ArrayList<BoletoBancarioDTO>();
        listaBoleto.add(new BoletoBancarioDTO(
                "Nubank",
                "951478",
                dataVencimentoBoleto));

        PagamentoDTO dtoPagamento = new PagamentoDTO(
                "Avista",
                 520.25F,
                listapix,
                listaBoleto,
                listaCartaoCredito);

        PagamentoResponseDTO clienteTest = clienteService.insert(dtoPagamento);
        Long id = clienteTest.id();

       PagamentoDTO dtoUpdate = new PagamentoDTO(
                "Aprazo",
                 9856.25F,
                listapix,
                listaBoleto,
                listaCartaoCredito);

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when().put("/pagamentos/" + id)
                .then()
                .statusCode(204);

        PagamentoResponseDTO usu = clienteService.findById(id);
        assertThat(usu.tipo(), is("Aprazo"));
        assertThat(usu.valor(), equalTo(9856.25F));

    }

    @Test
    public void testRemovePagamento() throws Exception {
        List<PixDTO> listapix = new ArrayList<PixDTO>();
        listapix.add(new PixDTO(
                "63984398131"));

        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimentoCartao = LocalDate.parse(dataVencimentoStr);
        List<CartaoCreditoDTO> listaCartaoCredito = new ArrayList<CartaoCreditoDTO>();
        listaCartaoCredito.add(new CartaoCreditoDTO(
                "Visa",
                "1598753",
                "123",
                dataVencimentoCartao));

        String dataVencimento = "2025-02-10";
        LocalDate dataVencimentoBoleto = LocalDate.parse(dataVencimento);
        List<BoletoBancarioDTO> listaBoleto = new ArrayList<BoletoBancarioDTO>();
        listaBoleto.add(new BoletoBancarioDTO(
                "Nubank",
                "951478",
                dataVencimentoBoleto));

        PagamentoDTO dtoPagamento = new PagamentoDTO(
                "Avista",
                 520.25F,
                listapix,
                listaBoleto,
                listaCartaoCredito);

        PagamentoResponseDTO clienteInserido = clienteService.insert(dtoPagamento);
        Long idPagamento = clienteInserido.id();

        given()
                .when()
                .delete("/pagamentos/" + idPagamento)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/pagamentos/" + idPagamento)
                .then()
                .statusCode(404);
    }

    @Test
    public void testFindById() throws Exception {
        List<PixDTO> listapix = new ArrayList<PixDTO>();
        listapix.add(new PixDTO(
                "63984398131"));

        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimentoCartao = LocalDate.parse(dataVencimentoStr);
        List<CartaoCreditoDTO> listaCartaoCredito = new ArrayList<CartaoCreditoDTO>();
        listaCartaoCredito.add(new CartaoCreditoDTO(
                "Visa",
                "1598753",
                "123",
                dataVencimentoCartao));

        String dataVencimento = "2025-02-10";
        LocalDate dataVencimentoBoleto = LocalDate.parse(dataVencimento);
        List<BoletoBancarioDTO> listaBoleto = new ArrayList<BoletoBancarioDTO>();
        listaBoleto.add(new BoletoBancarioDTO(
                "Nubank",
                "951478",
                dataVencimentoBoleto));

        PagamentoDTO dtoPagamento = new PagamentoDTO(
                "Avista",
                 520.25F,
                listapix,
                listaBoleto,
                listaCartaoCredito);

        PagamentoResponseDTO usuarioTest = clienteService.insert(dtoPagamento);
        Long id = usuarioTest.id();

        given()
                .when().get("/pagamentos/{id}", id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.intValue()));
    }

    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L;

        given()
                .when().get("/pagamentos/{id}", idNaoExistente)
                .then()
                .statusCode(404);
    }

    @Test
    public void testFindByTipo() {
        String nomeExistente = "Avista";
        given()
                .when().get("pagamentos/search/{tipo}", nomeExistente)
                .then()
                .statusCode(200);
    }

}
