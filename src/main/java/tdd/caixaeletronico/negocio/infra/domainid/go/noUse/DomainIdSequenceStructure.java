package tdd.caixaeletronico.negocio.infra.domainid.go.noUse;

import org.hibernate.AssertionFailure;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.model.relational.QualifiedName;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IntegralDataTypeHolder;
import org.hibernate.id.enhanced.AccessCallback;
import org.hibernate.id.enhanced.SequenceStructure;
import org.hibernate.internal.CoreMessageLogger;
import org.jboss.logging.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


class DomainIdSequenceStructure extends SequenceStructure {

	private static final CoreMessageLogger LOG = Logger.getMessageLogger(
			CoreMessageLogger.class,
			DomainIdSequenceStructure.class.getName()
	);

	private final Class numberType;

	private String sql;
	private int accessCounter;

    DomainIdSequenceStructure(JdbcEnvironment jdbcEnvironment, QualifiedName qualifiedSequenceName, int initialValue, int incrementSize, Class numberType) {
        super(jdbcEnvironment, qualifiedSequenceName, initialValue, incrementSize, numberType);
		this.numberType = numberType;
	}

	@Override
	public int getTimesAccessed() {
		return accessCounter;
	}

	@Override
	public AccessCallback buildCallback(final SharedSessionContractImplementor session) {
		if ( sql == null ) {
			throw new AssertionFailure( "SequenceStyleGenerator's SequenceStructure was not properly initialized" );
		}

		return new AccessCallback() {
			@Override
			public IntegralDataTypeHolder getNextValue() {
				accessCounter++;
				try {
					final PreparedStatement st = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( sql );
					try {
						final ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().extract( st );
						try {
							rs.next();
                            //noinspection unchecked
                            final IntegralDataTypeHolder value = new DomainIdBasicHolder<>( numberType ); // ONLY CHANGED LINE FROM ORIGINAL
							value.initialize( rs, 1 );
							if ( LOG.isDebugEnabled() ) {
								LOG.debugf( "Sequence value obtained: %s", value.makeValue() );
							}
							return value;
						}
						finally {
							try {
								session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( rs, st );
							}
							catch( Throwable ignore ) {
								// intentionally empty
							}
						}
					}
					finally {
						session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( st );
						session.getJdbcCoordinator().afterStatementExecution();
					}

				}
				catch ( SQLException sqle) {
					throw session.getJdbcServices().getSqlExceptionHelper().convert(
							sqle,
							"could not get next sequence value",
							sql
					);
				}
			}

			@Override
			public String getTenantIdentifier() {
				return session.getTenantIdentifier();
			}
		};
	}

	@Override
	public void registerExportables(Database database) {
		buildSequence( database );
		this.sql = database.getJdbcEnvironment().getDialect().getSequenceNextValString( sequenceName );
	}

}
