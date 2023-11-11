package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import br.unitins.topicos1.dto.BoletoBancarioDTO;
import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.LoginDTO;
import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.CidadeService;
import br.unitins.topicos1.service.ClienteService;
import br.unitins.topicos1.service.EnderecoService;
import br.unitins.topicos1.service.EstadoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    ClienteService clienteService;

    @Inject
    CidadeService cidadeService;

    @Inject
    EstadoService estadoService;

    @Inject
    EnderecoService enderecoService;

    private String token;
    @BeforeEach
    public void setUp() {
        var auth = new LoginDTO("victor@unitins.br", "123");

        Response response = (Response) given()
                .contentType("application/json")
                .body(auth)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.header("Authorization");
    }

    @Test
    public void testFindAll() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/pedidos")
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

        PedidoDTO pedidoDTO = new PedidoDTO(dtoPagamento);

        given()
        .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(pedidoDTO)
                .when().post("/pedidos")
                .then()
                .log().all()
                .statusCode(201);
    }

}