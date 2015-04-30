package com.andylibrian.pulau.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class KabupatenRepository {

    private final List<Kabupaten> kabupatenList = new LinkedList<Kabupaten>();
    private final SortedMap<String, List<Kabupaten>> kabupatenMap = new TreeMap<String, List<Kabupaten>>();

    public KabupatenRepository() {
        final InputStream in = getClass().getResourceAsStream("/kabupaten.csv");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
        final Comparator c = new NaturalOrderComparator();

        final Comparator<Kabupaten> comparator = new Comparator<Kabupaten>() {
            public int compare(Kabupaten c1, Kabupaten c2) {
                return c.compare(c1.getName(), c2.getName());
            }
        };

        try {
            for (CSVRecord record : CSVFormat.DEFAULT
                    .withHeader().withSkipHeaderRecord(true).parse(reader)) {

                final String idProvinsi = record.get("id_provinsi");

                Kabupaten kabupaten = new Kabupaten(record.get("id"), record.get("name"), idProvinsi);
                kabupatenList.add(kabupaten);

                if (!kabupatenMap.containsKey(idProvinsi)) {
                    kabupatenMap.put(idProvinsi, new LinkedList<Kabupaten>());
                }

                kabupatenMap.get(idProvinsi).add(kabupaten);

                for (Map.Entry<String, List<Kabupaten>> m : kabupatenMap.entrySet()) {
                    Collections.sort(m.getValue(), comparator);
                }
            }
        } catch (IOException iOException) {
        }
    }

    public List<Kabupaten> findAll() {
        return kabupatenList;
    }

    public List<Kabupaten> findByProvinsi(String provinsiId) {
        return kabupatenMap.get(provinsiId);
    }
}
