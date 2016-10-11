/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.rest.devmedia.webservices;

import io.github.jass2125.rest.devmedia.dao.NotaDao;
import io.github.jass2125.rest.devmedia.entidades.Nota;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Anderson Souza
 */
@Path("/notas")
public class NotasService {

    private NotaDao dao;
    private static final String UTF8 = ";charset=utf-8";

    @PostConstruct
    public void init() {
        dao = new NotaDao();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON + UTF8)
    public List<Nota> listarNotas() {
        List<Nota> listaDeNotas = null;
        try {
            listaDeNotas = dao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeNotas;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON + UTF8)
    public String addNota(Nota nota) {
        String mensagem = "";
        System.out.println(nota.getTitulo());
        try {
            dao.addNota(nota);
            mensagem = "Nota adicionada com sucesso!!";
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar nova nota!!";
            e.printStackTrace();
        }
        return mensagem;
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON + UTF8)
    public Nota buscaPorId(@PathParam("id") int idNota) {
        Nota nota = null;
        try {
            nota = dao.buscaPorId(idNota);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nota;
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON + UTF8)
    @Produces(MediaType.TEXT_PLAIN)
    public String editar(Nota nota) {
        String mensagem = "";
        try {
            dao.editNota(nota);
            mensagem = "Nota atualizada com sucesso!!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao atualizar a nota!!";
        }
        return mensagem;
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON + UTF8)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarPorId(@PathParam("id") Long id, Nota nota) {
        String mensagem = "";
        try {
            dao.editNota(nota, id);
            mensagem = "Nota atualizada com sucesso!!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao atualizar a nota!!";
        }
        return mensagem;
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON + UTF8)
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") Long id) {
        String mensagem = "";
        try {
            dao.deleteNota(id);
            mensagem = "Nota apagada com sucesso!!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao apagad a nota!!";
        }
        return mensagem;
    }

}
