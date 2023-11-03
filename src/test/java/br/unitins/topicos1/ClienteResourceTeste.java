package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.service.CidadeService;
import br.unitins.topicos1.service.ClienteService;
import br.unitins.topicos1.service.EnderecoService;
import br.unitins.topicos1.service.EstadoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@QuarkusTest
public class ClienteResourceTeste {
        @Inject
        ClienteService clienteService;

        @Inject
        CidadeService cidadeService;

        @Inject
        EstadoService estadoService;

        @Inject
        EnderecoService enderecoService;

        @Test
        public void testFindAll() {
                given()
                                .when().get("/clientes")
                                .then()
                                .statusCode(200);
        }

        @Test
        public void testInsert() throws ParseException {
                List<TelefoneDTO> listaTelefone = new ArrayList<TelefoneDTO>();
                listaTelefone.add(new TelefoneDTO(
                                "63",
                                "984398131"));

                List<EnderecoDTO> listaEndereco = new ArrayList<EnderecoDTO>();
                Long idEstado = estadoService.insert(new EstadoDTO("São PAulo", "SP")).id();
                Long idMunicipio = cidadeService.insert(new CidadeDTO("São Paulo", idEstado)).id();

                listaEndereco.add( new EnderecoDTO(
                                "Rua 05",
                                "13",
                                "Portão Rosa",
                                "Norte",
                                "77789-963",
                                idMunicipio)); 

                // Criar uma data de nascimento válida
                Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-03");

                ClienteDTO dtoCliente = new ClienteDTO(
                                "Milly Melo",
                                "pierre@gmail.com",
                                "15987",
                                "159.684.456-02",
                                dataNascimento,
                                listaTelefone,
                                listaEndereco,
                                1);

                given()
                                .contentType(ContentType.JSON)
                                .body(dtoCliente)
                                .when().post("/clientes")
                                .then()
                                .statusCode(201)
                                .body(
                                                "id", notNullValue(),
                                                "nome", is("Milly Melo"),
                                                "email", is("pierre@gmail.com"));
        }

        @Test
        public void testUpdate() throws Exception {
                List<TelefoneDTO> listaTelefone = new ArrayList<TelefoneDTO>();
                listaTelefone.add(new TelefoneDTO(
                                "63",
                                "984398131"));

                List<EnderecoDTO> listaEndereco = new ArrayList<EnderecoDTO>();
                Long idEstado = estadoService.insert(new EstadoDTO("São PAulo", "SP")).id();
                Long idMunicipio = cidadeService.insert(new CidadeDTO("São Paulo", idEstado)).id();

                listaEndereco.add( new EnderecoDTO(
                                "Rua 05",
                                "13",
                                "Portão Rosa",
                                "Norte",
                                "77789-963",
                                idMunicipio)); 

                // Criar uma data de nascimento válida
                Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-03");

                ClienteDTO dtoCliente = new ClienteDTO(
                                "Milly Melo",
                                "alcantara@gmail.com",
                                "15987",
                                "159.684.456-02",
                                dataNascimento,
                                listaTelefone,
                                listaEndereco,
                                1);

                ClienteResponseDTO clienteTest = clienteService.insert(dtoCliente);
                Long id = clienteTest.id();

                ClienteDTO dtoUpdate = new ClienteDTO(
                                "Victor",
                                "victu@gmail.com",
                                "15987",
                                "159.684.456-02",
                                dataNascimento,
                                listaTelefone,
                                listaEndereco,
                                1);
                given()
                                .contentType(ContentType.JSON)
                                .body(dtoUpdate)
                                .when().put("/clientes/" + id)
                                .then()
                                .statusCode(204);

                ClienteResponseDTO usu = clienteService.findById(id);
                assertThat(usu.nome(), is("Victor"));
                assertThat(usu.email(), is("victu@gmail.com"));

        }

        @Test
        public void testRemoveCliente() throws Exception {
                List<TelefoneDTO> listaTelefone = new ArrayList<TelefoneDTO>();
                listaTelefone.add(new TelefoneDTO(
                                "63",
                                "984398131"));

                List<EnderecoDTO> listaEndereco = new ArrayList<EnderecoDTO>();
                Long idEstado = estadoService.insert(new EstadoDTO("São PAulo", "SP")).id();
                Long idMunicipio = cidadeService.insert(new CidadeDTO("São Paulo", idEstado)).id();

                listaEndereco.add( new EnderecoDTO(
                                "Rua 05",
                                "13",
                                "Portão Rosa",
                                "Norte",
                                "77789-963",
                                idMunicipio)); 

                // Criar uma data de nascimento válida
                Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-03");

                ClienteDTO dtoCliente = new ClienteDTO(
                                "Milly Melo",
                                "milly@gmail.com",
                                "15987",
                                "159.684.456-02",
                                dataNascimento,
                                listaTelefone,
                                listaEndereco,
                                1);

                ClienteResponseDTO clienteInserido = clienteService.insert(dtoCliente);
                Long idCliente = clienteInserido.id();

                given()
                                .when()
                                .delete("/clientes/" + idCliente)
                                .then()
                                .statusCode(204);

                given()
                                .when()
                                .get("/clientes/" + idCliente)
                                .then()
                                .statusCode(404);
        }

        @Test
        public void testFindById() throws Exception {
                List<TelefoneDTO> listaTelefone = new ArrayList<TelefoneDTO>();
                listaTelefone.add(new TelefoneDTO(
                                "63",
                                "984398131"));

                List<EnderecoDTO> listaEndereco = new ArrayList<EnderecoDTO>();
                Long idEstado = estadoService.insert(new EstadoDTO("São PAulo", "SP")).id();
                Long idMunicipio = cidadeService.insert(new CidadeDTO("São Paulo", idEstado)).id();

                listaEndereco.add( new EnderecoDTO(
                                "Rua 05",
                                "13",
                                "Portão Rosa",
                                "Norte",
                                "77789-963",
                                idMunicipio)); 

                // Criar uma data de nascimento válida
                Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse("2000-06-03");

                ClienteDTO dtoCliente = new ClienteDTO(
                                "Milly Melo",
                                "milly@gmail.com",
                                "15987",
                                "159.684.456-02",
                                dataNascimento,
                                listaTelefone,
                                listaEndereco,
                                1);

                ClienteResponseDTO usuarioTest = clienteService.insert(dtoCliente);
                Long id = usuarioTest.id();

                given()
                                .when().get("/clientes/{id}", id)
                                .then()
                                .statusCode(200)
                                .body("id", equalTo(id.intValue()));
        }

        @Test
        public void testFindByIdNotFound() {
                Long idNaoExistente = 9999L;

                given()
                                .when().get("/clientes/{id}", idNaoExistente)
                                .then()
                                .statusCode(404);
        }

        @Test
        public void testFindByNome() {
                String nomeExistente = "Victor Alves";

                given()
                                .when().get("/clientes/search/nome/{nome}", nomeExistente)
                                .then()
                                .statusCode(200)
                                .body("nome[0]", equalTo(nomeExistente));
        }

        @Test
        public void testFindByNomeNotFound() {
                String nomeNaoExistente = "Nome Inexistente";

                given()
                                .when().get("/clientes/search/nome/{nome}", nomeNaoExistente)
                                .then()
                                .statusCode(200)
                                .body("$", hasSize(equalTo(0)));
        }

}
