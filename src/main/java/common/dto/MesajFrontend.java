package common.dto;
import java.io.Serializable;

public class MesajFrontend<T> implements Serializable {
    private TipOperatie tipOperatie;
    private T dto;

    public MesajFrontend(TipOperatie tipOperatie, T dto) {
        this.tipOperatie = tipOperatie;
        this.dto = dto;
    }

    public TipOperatie getTipOperatie() {
        return tipOperatie;
    }

    public T getDto() {
        return dto;
    }
}
