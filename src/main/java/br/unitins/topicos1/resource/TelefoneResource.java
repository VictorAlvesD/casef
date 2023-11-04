package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.service.TelefoneService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @POST
    @RolesAllowed({"User", "Admin"})
    public Response insert(TelefoneDTO dto) {
       TelefoneResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response update(TelefoneDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id) {
        try {
            TelefoneResponseDTO telefone = service.findById(id);
            return Response.ok(telefone).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/search/numero/{numero}")
    @RolesAllowed({"User", "Admin"})
    public Response findByNome(@PathParam("numero") String numero) {
        return Response.ok(service.findByNumero(numero)).build();
    }
}

