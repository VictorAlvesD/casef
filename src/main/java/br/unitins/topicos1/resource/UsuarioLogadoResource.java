package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuario() {
        // obtendo o login pelo token jwt
        String login = jwt.getSubject();
        return Response.ok(usuarioService.findByLogin(login)).build();
    }

    @PATCH
    @Transactional
    @Path("/updateSenha/{novaSenha}")
    @RolesAllowed({ "User", "Admin" })
    public Response novaSenha(@PathParam("novaSenha") String senha) {
        // Obtendo o login pelo token JWT
    String login = jwt.getSubject();

    try {
        // Chame o método no serviço para atualizar a senha
        usuarioService.updatePassword(login, senha);
        return Response.ok("Senha atualizada com sucesso "+usuarioService.findByLogin(login)).build();
    } catch (Exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar a senha: " + e.getMessage()).build();
    }

    }


}