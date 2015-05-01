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

public class KecamatanRepository {

    private final List<Kecamatan> kecamatanList = new LinkedList<Kecamatan>();
    private final SortedMap<String, List<Kecamatan>> kecamatanMap = new TreeMap<String, List<Kecamatan>>();

    public KecamatanRepository() {
        final InputStream in = getClass().getResourceAsStream("/kecamatan.csv");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
        final Comparator c = new NaturalOrderComparator();

        final Comparator<Kecamatan> comparator = new Comparator<Kecamatan>() {
            public int compare(Kecamatan c1, Kecamatan c2) {
                return c.compare(c1.getName(), c2.getName());
            }
        };

        try {
            for (CSVRecord record : CSVFormat.DEFAULT
                    .withHeader().withSkipHeaderRecord(true).parse(reader)) {

                final String idKabupaten = record.get("id_kabupaten");

                Kecamatan kecamatan = new Kecamatan(record.get("id"), record.get("name"), idKabupaten);
                kecamatanList.add(kecamatan);

                if (!kecamatanMap.containsKey(idKabupaten)) {
                    kecamatanMap.put(idKabupaten, new LinkedList<Kecamatan>());
                }

                kecamatanMap.get(idKabupaten).add(kecamatan);

                for (Map.Entry<String, List<Kecamatan>> m : kecamatanMap.entrySet()) {
                    Collections.sort(m.getValue(), comparator);
                }
            }
        } catch (IOException iOException) {
        }
    }

    public List<Kecamatan> findAll() {
        return kecamatanList;
    }

    public List<Kecamatan> findByKabupaten(String idKabupaten) {
        return kecamatanMap.get(idKabupaten);
    }
}
