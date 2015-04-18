package com.andylibrian.pulau.resources;

import com.andylibrian.pulau.core.Provinsi;
import com.andylibrian.pulau.core.ProvinsiRepository;
import com.codahale.metrics.annotation.Timed;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/provinsi")
@Produces(MediaType.APPLICATION_JSON)
public class ProvinsiResource {

    private final Map<String, Object> documentAllProvinsi;
    private final List<Provinsi> provinsiList;

    public ProvinsiResource() {
        final ProvinsiRepository repo = new ProvinsiRepository();
        provinsiList = repo.findAll();

        documentAllProvinsi = new LinkedHashMap<String, Object>();
        documentAllProvinsi.put("data", provinsiList);
    }

    @GET
    @Timed
    public Map<String, Object> findAll() {
        return documentAllProvinsi;
    }
}
