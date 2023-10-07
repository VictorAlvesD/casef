package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.TelefoneService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class TelefoneResourceTest {
    @Inject
    TelefoneService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/administradores")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        TelefoneDTO dtoTelefone = new TelefoneDTO(
            "63",
            "984398131"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoTelefone)
            .when().post("/telefones")
            .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "codigoArea", is("63"),
                "numero", is("984398131")
            );
    }

    @Test
    public void testUpdate() {
        TelefoneDTO dto = new TelefoneDTO(
            "63",
            "984398131"
        );

        // inserindo um usuario
        TelefoneResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();

        TelefoneDTO dtoUpdate = new TelefoneDTO(
           "62",
            "984398131"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/telefones/"+ id)
            .then()
            .statusCode(204);

        TelefoneResponseDTO usu = administradorService.findById(id);
        assertThat(usu.codigoArea(), is("62"));
        assertThat(usu.numero(), is("984398131"));

    }

    @Test
    public void testRemoveTelefone() {
        TelefoneDTO dto = new TelefoneDTO(
            "62",
            "984398131"
        );

        TelefoneResponseDTO administradorInserido = administradorService.insert(dto);
        Long idTelefone = administradorInserido.id();

        given()
            .when()
            .get("/felefones/" + idTelefone)
            .then()
            .statusCode(404); 
    }
    
    @Test
    public void testFindById() {
        TelefoneDTO dto = new TelefoneDTO(
            "99",
            "984759685"
        );
    
        // Inserindo um usuário
        TelefoneResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/telefones/{id}", id)
            .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); 
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L; 

        given()
            .when().get("/administradores/{id}", idNaoExistente)
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        String numeroExistente = "98478-3692"; 

        given()
            .when().get("/telefones/search/numero/{numero}", numeroExistente)
            .then()
            .statusCode(200)
            .body("numero[0]", equalTo(numeroExistente)); 
    }

    @Test
    public void testFindByNomeNotFound() {
        String numeroNaoExistente = "00000-888"; 

        given()
            .when().get("/telefones/search/numero/{numero}", numeroNaoExistente)
            .then()
            .statusCode(200)
            .body("$", hasSize(equalTo(0))); 
    }

}
