package tdd.caixaeletronico.negocio.cliente;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class ClienteKey implements Serializable {

    @Column
    @GeneratedValue
    private Long id;

    public ClienteKey() {
    }

    public ClienteKey(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClienteKey{" +
            "id=" + id +
            '}';
    }
}
/*

public static class Key {
    @Column(name = "ID", nullable = false)
    @Convert(converter = MyObjectConverter.class)
    private MyObject id;
//getter/setter
}

    @EmbeddedId
    private Key key*/
