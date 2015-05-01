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

@Path("/kabupaten")
@Produces(MediaType.APPLICATION_JSON)
public class KabupatenResource {

    private final Map<String, Object> documentAllKabupaten;
    private final List<Kabupaten> kabupatenList;
    private final HashMap<String, Kabupaten> kabupatenMap;

    // private final KecamatanRepository kecamatanRepo;

    public KabupatenResource() {
        final KabupatenRepository repo = new KabupatenRepository();
        
        // kecamatanRepo = new KecamatanRepository();

        kabupatenList = repo.findAll();

        kabupatenMap = new HashMap<String, Kabupaten>();
        for (Kabupaten k : kabupatenList) {
            kabupatenMap.put(k.getId(), k);
        }

        documentAllKabupaten = new LinkedHashMap<String, Object>();
        documentAllKabupaten.put("data", kabupatenList);
    }

    @GET
    public Map<String, Object> findAll() {
        return documentAllKabupaten;
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> findSingle(@PathParam("id") String id) throws ResourceNotFoundException {

        final Kabupaten kabupaten = kabupatenMap.get(id);

        if (kabupaten == null) {
            throw new ResourceNotFoundException();
        }

        final Map<String, Object> documentSingleKabupaten = new LinkedHashMap<String, Object>();
        documentSingleKabupaten.put("data", kabupaten);

        return documentSingleKabupaten;

    }

    @GET
    @Path("/{id}/kecamatan")
    public Map<String, Object> findKecamatan(@PathParam("id") String id) throws ResourceNotFoundException {
        
        final Kabupaten kabupaten = kabupatenMap.get(id);

        if (kabupaten == null) {
            throw new ResourceNotFoundException();
        }

        // final List<Kabupaten> kabupatenList = kabupatenRepo.findByProvinsi(id);

        final Map<String, Object> documentKabupaten = new LinkedHashMap<String, Object>();
        // documentKabupaten.put("data", kabupatenList);

        return documentKabupaten;
    }
}
