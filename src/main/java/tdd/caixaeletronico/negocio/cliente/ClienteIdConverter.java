package tdd.caixaeletronico.negocio.cliente;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ClienteIdConverter implements AttributeConverter<ClienteId, Long> {

    @Override
    public Long convertToDatabaseColumn(ClienteId attribute) {
        if (attribute == null) return null;
        return attribute.toLong();
    }

    @Override
    public ClienteId convertToEntityAttribute(Long dbData) {
        if (dbData == null) return null;
        return new ClienteId(dbData);
    }

}
