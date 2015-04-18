package com.andylibrian.pulau.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ProvinsiRepository {

    private final List<Provinsi> provinsiList = new LinkedList<Provinsi>();

    public ProvinsiRepository() {
        InputStream in = getClass().getResourceAsStream("/provinsi.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            for (CSVRecord record : CSVFormat.DEFAULT
                    .withHeader().withSkipHeaderRecord(true).parse(reader)) {

                Provinsi provinsi = new Provinsi(record.get("id"), record.get("name"));
                provinsiList.add(provinsi);
            }
        } catch (IOException iOException) {
        }
    }

    public List<Provinsi> findAll() {
        return provinsiList;
    }
}
