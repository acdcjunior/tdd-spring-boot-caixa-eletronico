package tdd.caixaeletronico.negocio.cliente;

import javax.persistence.*;
import java.io.Serializable;

public class ClienteDoisPK implements Serializable {

//    @Column(name = "ID")
    @Convert(converter = ClienteIdConverter.class)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="cliente_dois_sequ")
//    @SequenceGenerator(name="cliente_dois_sequ", sequenceName="cliente_dois_sequ", allocationSize=1)
    private ClienteId id;

    public ClienteDoisPK() {
    }

    public ClienteDoisPK(long id) {
        this.id = new ClienteId(id);
//        this.id = id;
    }

}
