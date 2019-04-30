package tdd.caixaeletronico.negocio.infra.domainid.go.noUse;

import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGeneratorHelper;
import org.hibernate.id.IntegralDataTypeHolder;
import tdd.caixaeletronico.negocio.infra.domainid.DomainId;


class DomainIdBasicHolder<T extends DomainId> extends IdentifierGeneratorHelper.BasicHolder {

    private final Class<T> exactType;

    DomainIdBasicHolder(Class<T> exactType) {
        super(Long.class);
        this.exactType = exactType;
    }

    @Override
    public IntegralDataTypeHolder copy() {
        DomainIdBasicHolder copy = new DomainIdBasicHolder<>(exactType);
        copy.initialize(this.getActualLongValue());
        return copy;
    }

    @Override
    public Number makeValue() {
        checkInitialized();
        return getActualLongValue();
    }

    private void checkInitialized() {
        if ( getActualLongValue() == Long.MIN_VALUE ) {
            throw new IdentifierGenerationException( "integral holder was not initialized" );
        }
    }

    @Override
    public String toString() {
        return "DomainIdBasicHolder[" + exactType.getName() + "[" + getActualLongValue() + "]]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomainIdBasicHolder that = (DomainIdBasicHolder) o;

        return getActualLongValue() == that.getActualLongValue();
    }

}
