package tdd.caixaeletronico.negocio.cliente;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;

public class ClienteId implements Serializable {

    private final long id;

    public ClienteId(long id) {
        this.id = id;
    }

    public long toLong() {
        return id;
    }

    @Override
    public String toString() {
        return "ClienteId{" +
            "id=" + id +
            '}';
    }
}
