package common.dto;

import java.io.Serializable;

public class MesajBackend<T> implements Serializable {
    private String mesaj;
    private String emailUtilizator;
    private TipUtilizator tipUtilizator;
    private T dto;

    public MesajBackend(String mesaj, String emailUtilizator, TipUtilizator tipUtilizator) {
        this.mesaj = mesaj;
        this.emailUtilizator = emailUtilizator;
        this.tipUtilizator = tipUtilizator;
    }

    public String getMesaj() {
        return mesaj;
    }

    public String getEmailUtilizator() {
        return emailUtilizator;
    }

    public TipUtilizator getTipUtilizator() {
        return tipUtilizator;
    }

    public T getDto() {
        return dto;
    }

    public void setDto(T dto) {
        this.dto = dto;
    }
}
