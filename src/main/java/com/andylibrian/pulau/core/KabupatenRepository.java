package com.andylibrian.pulau.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class KabupatenRepository {

    private final List<Kabupaten> kabupatenList = new LinkedList<Kabupaten>();

    public KabupatenRepository() {
        InputStream in = getClass().getResourceAsStream("/kabupaten.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            for (CSVRecord record : CSVFormat.DEFAULT
                    .withHeader().withSkipHeaderRecord(true).parse(reader)) {

                Kabupaten kabupaten = new Kabupaten(record.get("id"), record.get("name"));
                kabupatenList.add(kabupaten);
            }
        } catch (IOException iOException) {
        }
    }

    public List<Kabupaten> findAll() {
        return kabupatenList;
    }

    public List<Kabupaten> findByProvinsi(String provinsiId) {
        return kabupatenList;
    }
}
