package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.PixResponseDTO;
import br.unitins.topicos1.service.PixService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class PixResourceTest {
    @Inject
    PixService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/pix")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        PixDTO dtoPix = new PixDTO(
            "63984398131"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoPix)
            .when().post("/pix")
            .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "chavePix", is("63984398131")
            );
    }

    @Test
    public void testUpdate() {
        PixDTO dto = new PixDTO(
            "077.511.988-06"
        );

        // inserindo um usuario
        PixResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();

        PixDTO dtoUpdate = new PixDTO(
            "888.511.988-06"
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/pix/"+ id)
            .then()
            .statusCode(204);

        PixResponseDTO usu = administradorService.findById(id);
        assertThat(usu.chavePix(), is("888.511.988-06"));

    }

    @Test
    public void testRemovePix() {
        PixDTO dto = new PixDTO(
            "63984785963"
        );

        PixResponseDTO administradorInserido = administradorService.insert(dto);
        Long idPix = administradorInserido.id();

        given()
            .when()
            .delete("/pix/" + idPix)
            .then()
            .statusCode(204); 

        given()
            .when()
            .get("/pix/" + idPix)
            .then()
            .statusCode(404); 
    }
    
    @Test
    public void testFindById() {
        PixDTO dto = new PixDTO(
            "carlos@gmail.com"
        );
    
        // Inserindo um usuário
        PixResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/pix/{id}", id)
            .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); 
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L; 

        given()
            .when().get("/pix/{id}", idNaoExistente)
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        String chaveExistente = "9998563254"; 

        given()
            .when().get("pix/search/{chave}", chaveExistente)
            .then()
            .statusCode(200); 
    }

}
