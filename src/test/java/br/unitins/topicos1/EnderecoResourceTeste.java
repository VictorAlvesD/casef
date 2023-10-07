package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.EnderecoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

@QuarkusTest
public class EnderecoResourceTeste {
    @Inject
    EnderecoService administradorService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/enderecos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        EnderecoDTO dtoEndereco = new EnderecoDTO(
            "Rua 7 de setembro",
            "125",
            "",
            "503 Sul",
            "Palmas",
            "77789-963",
            27
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoEndereco)
            .when().post("/enderecos")
            .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "cep", is("77789-963"),
                "estado", equalTo(27)
            );
    }

    @Test
    public void testUpdate() {
        EnderecoDTO dtoEndereco = new EnderecoDTO(
            "Rua 7 de setembro",
            "125",
            "",
            "503 Sul",
            "Palmas",
            "77789-963",
            27
        );

        // inserindo um usuario
        EnderecoResponseDTO usuarioTest = administradorService.insert(dtoEndereco);
        Long id = usuarioTest.id();

        EnderecoDTO dtoUpdate = new EnderecoDTO(
            "Rua 30 de junho",
            "125",
            "Portão Rosa",
            "503 Sul",
            "São Paulo",
            "77789-987",
            25
        );

        given()
            .contentType(ContentType.JSON)
            .body(dtoUpdate)
            .when().put("/enderecos/"+ id)
            .then()
            .statusCode(204);

        EnderecoResponseDTO usu = administradorService.findById(id);
        assertThat(usu.cidade(), is("São Paulo"));
        assertThat(usu.estado(), equalTo(25));

    }

    @Test
    public void testRemoveEndereco() {
        EnderecoDTO dto = new EnderecoDTO(
           "Rua 30 de junho",
            "125",
            "Portão Rosa",
            "503 Sul",
            "São Paulo",
            "77789-987",
            25
        );

        EnderecoResponseDTO administradorInserido = administradorService.insert(dto);
        Long idEndereco = administradorInserido.id();

        given()
            .when()
            .delete("/enderecos/" + idEndereco)
            .then()
            .statusCode(204); // O código 204 indica que a remoção foi bem-sucedida

        given()
            .when()
            .get("/enderecos/" + idEndereco)
            .then()
            .statusCode(404); // O código 404 indica que o administrador não foi encontrado (foi removido com sucesso)
    }
   
    @Test
    public void testFindById() {
        EnderecoDTO dto = new EnderecoDTO(
            "Rua 30 de junho",
            "125",
            "Portão Rosa",
            "503 Sul",
            "São Paulo",
            "77789-987",
            25
        );
    
        // Inserindo um usuário
        EnderecoResponseDTO usuarioTest = administradorService.insert(dto);
        Long id = usuarioTest.id();
    
        given()
            .when().get("/enderecos/{id}", id)
            .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); 
    }
    
    @Test
    public void testFindByIdNotFound() {
        Long idNaoExistente = 9999L; 

        given()
            .when().get("/enderecos/{id}", idNaoExistente)
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByCep() {
        String cepExistente = "70084-885"; 

        given()
            .when().get("/enderecos/search/cep/{cep}", cepExistente)
            .then()
            .statusCode(200)
            .body("cep[0]", equalTo(cepExistente)); 
    }

    @Test
    public void testFindByNomeNotFound() {
        String nomeNaoExistente = "65489-000"; 

        given()
            .when().get("/enderecos/search/cep/{cep}", nomeNaoExistente)
            .then()
            .statusCode(200)
            .body("$", hasSize(equalTo(0))); 
    }

}

