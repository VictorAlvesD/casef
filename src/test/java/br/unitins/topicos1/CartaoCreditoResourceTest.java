package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoCreditoResponseDTO;
import br.unitins.topicos1.service.CartaoCreditoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class CartaoCreditoResourceTest {
    @Inject
    CartaoCreditoService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/cartaoCredito")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        CartaoCreditoDTO dtoCartaoCredito = new CartaoCreditoDTO(
            "Visa",
            "1598753",
            "123",
            dataVencimento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoCartaoCredito)
            .when().post("/cartaoCredito")
            .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "bandeira", is("Visa"),
                "numeroCartao", is("1598753")
            );
    }

    @Test
    public void testUpdate() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        CartaoCreditoDTO dto = new CartaoCreditoDTO(
            "Visa",
            "1598753",
            "123",
            dataVencimento
        );

        CartaoCreditoResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();

        CartaoCreditoDTO dtoUpdate = new CartaoCreditoDTO(
             "Visa",
            "1598753",
            "123",
            dataVencimento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/cartaoCredito/"+ id)
            .then()
            .statusCode(204);

        CartaoCreditoResponseDTO usu = administradorService.findById(id);
        assertThat(usu.bandeira(), is("Visa"));
        assertThat(usu.numeroCartao(), is("1598753"));

    }

    @Test
    public void testRemoveCartaoCredito() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        CartaoCreditoDTO dto = new CartaoCreditoDTO(
             "Visa",
            "1598753",
            "123",
            dataVencimento
        );

        CartaoCreditoResponseDTO administradorInserido = administradorService.insert(dto);
        Long idCartaoCredito = administradorInserido.id();

        given()
            .when()
            .delete("/cartaoCredito/" + idCartaoCredito)
            .then()
            .statusCode(204); // O código 204 indica que a remoção foi bem-sucedida

        given()
            .when()
            .get("/cartaoCredito/" + idCartaoCredito)
            .then()
            .statusCode(404); // O código 404 indica que o administrador não foi encontrado (foi removido com sucesso)
    }
    
    @Test
    public void testFindById() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        CartaoCreditoDTO dto = new CartaoCreditoDTO(
             "Visa",
            "1598753",
            "123",
            dataVencimento
        );
    
        // Inserindo um usuário
        CartaoCreditoResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/cartaoCredito/{id}", id)
            .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); 
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L; 

        given()
            .when().get("/cartaoCredito/{id}", idNaoExistente)
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        String nomeExistente = "Visa"; 

        given()
            .when().get("cartaoCredito/search/{bandeira}", nomeExistente)
            .then()
            .statusCode(200)
            .body("bandeira[0]", equalTo(nomeExistente)); 
    }

    
}
