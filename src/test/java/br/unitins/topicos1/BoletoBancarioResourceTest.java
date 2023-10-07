package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

import br.unitins.topicos1.dto.BoletoBancarioDTO;
import br.unitins.topicos1.dto.BoletoBancarioResponseDTO;
import br.unitins.topicos1.service.BoletoBancarioService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class BoletoBancarioResourceTest {
    @Inject
    BoletoBancarioService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/boletosBancarios")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {

        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        BoletoBancarioDTO dtoBoletoBancario = new BoletoBancarioDTO(
            "Nubank",
            "951478",
            dataVencimento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoBoletoBancario)
            .when().post("/boletosBancarios")
            .then()
            .statusCode(201)
            .body(
                "banco", is("Nubank"),
                "numeroBoleto", is("951478")
            );
    }

    @Test
    public void testUpdate() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        BoletoBancarioDTO dtoBoletoBancario = new BoletoBancarioDTO(
            "Nubank",
            "951478",
            dataVencimento
        );

        // inserindo um usuario
        BoletoBancarioResponseDTO usuarioTest = administradorService.insert(dtoBoletoBancario);
        Long id = usuarioTest.id();

        BoletoBancarioDTO dtoUpdate = new BoletoBancarioDTO(
             "bradesco",
            "951478",
            dataVencimento
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/boletosBancarios/"+ id)
            .then()
            .statusCode(204);

        BoletoBancarioResponseDTO usu = administradorService.findById(id);
        assertThat(usu.banco(), is("bradesco"));

    }

    @Test
    public void testRemoveBoletoBancario() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        BoletoBancarioDTO dtoBoletoBancario = new BoletoBancarioDTO(
            "Nubank",
            "951478",
            dataVencimento
        );

        BoletoBancarioResponseDTO administradorInserido = administradorService.insert(dtoBoletoBancario);
        Long idBoletoBancario = administradorInserido.id();

        given()
            .when()
            .delete("/boletosBancarios/" + idBoletoBancario)
            .then()
            .statusCode(204); // O código 204 indica que a remoção foi bem-sucedida

        given()
            .when()
            .get("/boletosBancarios/" + idBoletoBancario)
            .then()
            .statusCode(404); // O código 404 indica que o administrador não foi encontrado (foi removido com sucesso)
    }
    
    @Test
    public void testFindById() {
        String dataVencimentoStr = "2025-02-03";
        LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);
        BoletoBancarioDTO dtoBoletoBancario = new BoletoBancarioDTO(
            "Nubank",
            "951478",
            dataVencimento
        );
    
        BoletoBancarioResponseDTO usuarioTest = administradorService.insert(dtoBoletoBancario);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/boletosBancarios/{id}", id)
            .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); 
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L; 

        given()
            .when().get("/boletosBancarios/{id}", idNaoExistente)
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNumeroBoleto() {
        String nomeExistente = "36985214789"; 

        given()
            .when().get("boletosBancarios/search/{boleto}", nomeExistente)
            .then()
            .statusCode(200); 
    }
}