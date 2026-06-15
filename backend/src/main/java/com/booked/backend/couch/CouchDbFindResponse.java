package com.booked.backend.couch;

import java.util.List;

public class CouchDbFindResponse<T> {
    private List<T> docs;

    public List<T> getDocs() { return docs; }
    public void setDocs(List<T> docs) { this.docs = docs; }
}