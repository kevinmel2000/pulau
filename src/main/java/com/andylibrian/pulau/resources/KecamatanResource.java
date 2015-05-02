package com.andylibrian.pulau.resources;

import com.andylibrian.pulau.core.Kabupaten;
import com.andylibrian.pulau.core.KabupatenRepository;
import com.andylibrian.pulau.core.Kecamatan;
import com.andylibrian.pulau.core.KecamatanRepository;
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

@Path("/kecamatan")
@Produces(MediaType.APPLICATION_JSON)
public class KecamatanResource {

    private final Map<String, Object> documentAllKecamatan;
    private final List<Kecamatan> kecamatanList;
    private final Map<String, Kecamatan> kecamatanMap;

    public KecamatanResource() {
        final KecamatanRepository repo = new KecamatanRepository();

        kecamatanList = repo.findAll();

        kecamatanMap = new HashMap<String, Kecamatan>();
        for (Kecamatan k : kecamatanList) {
            kecamatanMap.put(k.getId(), k);
        }

        documentAllKecamatan = new LinkedHashMap<String, Object>();
        documentAllKecamatan.put("data", kecamatanList);
    }

    @GET
    public Map<String, Object> findAll() {
        return documentAllKecamatan;
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> findSingle(@PathParam("id") String id) throws ResourceNotFoundException {

        final Kecamatan kecamatan = kecamatanMap.get(id);

        if (kecamatan == null) {
            throw new ResourceNotFoundException();
        }

        final Map<String, Object> documentSingleKecamatan = new LinkedHashMap<String, Object>();
        documentSingleKecamatan.put("data", kecamatan);

        return documentSingleKecamatan;

    }
}
