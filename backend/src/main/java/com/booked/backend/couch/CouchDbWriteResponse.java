package com.booked.backend.couch;

public class CouchDbWriteResponse {

    private boolean ok;
    private String id;
    private String rev;

    public boolean isOk() { return ok; }
    public void setOk(boolean ok) { this.ok = ok; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRev() { return rev; }
    public void setRev(String rev) { this.rev = rev; }
}