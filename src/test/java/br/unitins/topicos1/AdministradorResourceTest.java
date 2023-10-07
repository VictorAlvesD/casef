package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.AdministradorService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class AdministradorResourceTest {
    @Inject
    AdministradorService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/administradores")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        AdministradorDTO dtoAdministrador = new AdministradorDTO(
            "Milly Melo",
            "milly@gmail.com",
            "15987",
            "159.684.456-02",
            951
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoAdministrador)
            .when().post("/administradores")
            .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "nome", is("Milly Melo"),
                "matricula", equalTo(951)
            );
    }

    @Test
    public void testUpdate() {
        AdministradorDTO dto = new AdministradorDTO(
            "Milly Melo",
            "milly@gmail.com",
            "15987",
            "159.684.456-02",
            951
        );

        // inserindo um usuario
        AdministradorResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();

        AdministradorDTO dtoUpdate = new AdministradorDTO(
            "Milly Melo",
            "melo@gmail.com",
            "15987",
            "159.684.456-02",
            100
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/administradores/"+ id)
            .then()
            .statusCode(204);

        AdministradorResponseDTO usu = administradorService.findById(id);
        assertThat(usu.nome(), is("Milly Melo"));
        assertThat(usu.matricula(), equalTo(100));

    }

    @Test
    public void testRemoveAdministrador() {
        AdministradorDTO dto = new AdministradorDTO(
            "Milly Melo",
            "milly@gmail.com",
            "15987",
            "159.684.456-02",
            951
        );

        AdministradorResponseDTO administradorInserido = administradorService.insert(dto);
        Long idAdministrador = administradorInserido.id();

        given()
            .when()
            .delete("/administradores/" + idAdministrador)
            .then()
            .statusCode(204); // O código 204 indica que a remoção foi bem-sucedida

        given()
            .when()
            .get("/administradores/" + idAdministrador)
            .then()
            .statusCode(404); // O código 404 indica que o administrador não foi encontrado (foi removido com sucesso)
    }
    
    @Test
    public void testFindById() {
        AdministradorDTO dto = new AdministradorDTO(
            "Milly Melo",
            "milly@gmail.com",
            "15987",
            "159.684.456-02",
            951
        );
    
        // Inserindo um usuário
        AdministradorResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/administradores/{id}", id)
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
        String nomeExistente = "Victor Alves"; 

        given()
            .when().get("/administradores/search/nome/{nome}", nomeExistente)
            .then()
            .statusCode(200)
            .body("nome[0]", equalTo(nomeExistente)); 
    }

    @Test
    public void testFindByNomeNotFound() {
        String nomeNaoExistente = "Nome Inexistente"; 

        given()
            .when().get("/administradores/search/nome/{nome}", nomeNaoExistente)
            .then()
            .statusCode(200)
            .body("$", hasSize(equalTo(0))); 
    }

}
