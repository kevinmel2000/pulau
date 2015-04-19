package com.andylibrian.pulau.resources;

import com.andylibrian.pulau.core.Kabupaten;
import com.andylibrian.pulau.core.KabupatenRepository;
import com.andylibrian.pulau.core.Provinsi;
import com.andylibrian.pulau.core.ProvinsiRepository;
import com.andylibrian.pulau.core.ResourceNotFoundException;
import com.codahale.metrics.annotation.Timed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("/provinsi")
@Produces(MediaType.APPLICATION_JSON)
public class ProvinsiResource {

    private final Map<String, Object> documentAllProvinsi;
    private final List<Provinsi> provinsiList;
    private final HashMap<String, Provinsi> provinsiMap;

    private final KabupatenRepository kabupatenRepo;

    public ProvinsiResource() {
        final ProvinsiRepository repo = new ProvinsiRepository();
        kabupatenRepo = new KabupatenRepository();

        provinsiList = repo.findAll();

        provinsiMap = new HashMap<String, Provinsi>();
        for (Provinsi p : provinsiList) {
            provinsiMap.put(p.getId(), p);
        }

        documentAllProvinsi = new LinkedHashMap<String, Object>();
        documentAllProvinsi.put("data", provinsiList);
    }

    @GET
    public Map<String, Object> findAll() {
        return documentAllProvinsi;
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> findSingle(@PathParam("id") String id) throws ResourceNotFoundException {

        final Provinsi provinsi = provinsiMap.get(id);

        if (provinsi == null) {
            throw new ResourceNotFoundException();
        }

        final Map<String, Object> documentSingleProvinsi = new LinkedHashMap<String, Object>();
        documentSingleProvinsi.put("data", provinsi);

        return documentSingleProvinsi;

    }

    @GET
    @Path("/{id}/kabupaten")
    public Map<String, Object> findKabupaten(@PathParam("id") String id) throws ResourceNotFoundException {
        final Provinsi provinsi = provinsiMap.get(id);

        if (provinsi == null) {
            throw new ResourceNotFoundException();
        }

        final List<Kabupaten> kabupatenList = kabupatenRepo.findByProvinsi(id);

        final Map<String, Object> documentKabupaten = new LinkedHashMap<String, Object>();
        documentKabupaten.put("data", kabupatenList);

        return documentKabupaten;
    }
}
