package com.andylibrian.pulau.resources;

import com.andylibrian.pulau.core.Provinsi;
import com.andylibrian.pulau.core.ProvinsiRepository;
import com.codahale.metrics.annotation.Timed;
import com.andylibrian.pulau.core.ResourceNotFoundException;
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

    public ProvinsiResource() {
        final ProvinsiRepository repo = new ProvinsiRepository();
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

    @GET @Path("/{id}")
    public Map<String, Object> findSingle(@PathParam("id") String id) throws ResourceNotFoundException {
        
        Provinsi provinsi = provinsiMap.get(id);
        
        if (provinsi == null) {
            throw new ResourceNotFoundException();
        }

        final Map<String, Object> documentSingleProvinsi = new LinkedHashMap<String, Object>();
        documentSingleProvinsi.put("data", provinsi);
        
        return documentSingleProvinsi;
                
    }
}
