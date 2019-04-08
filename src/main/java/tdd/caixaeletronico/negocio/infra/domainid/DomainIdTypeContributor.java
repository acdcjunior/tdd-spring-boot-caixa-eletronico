package tdd.caixaeletronico.negocio.infra.domainid;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TypeContributor for adding Java8 Date/Time specific Type implementations
 *
 * @author Steve Ebersole
 */
public class DomainIdTypeContributor implements TypeContributor {

	@Override
	public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        System.out.println("INDO");

        try {
            long startTime2 = System.nanoTime();
            List<Class<? extends DomainId>> types = findMyTypes("");
            System.out.println("Classpath scanned in " + (System.nanoTime() - startTime2)/1000000 + " ms");

            types.forEach(t -> {
                typeContributions.contributeType(new DomainIdType<>(t));
            });
            System.out.println("Registered Id UserTypes: " + types);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 50; i++) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        }
	}

    private List<Class<? extends DomainId>> findMyTypes(String basePackage) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackage + "/**/*Id.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

        List<Class<? extends DomainId>> candidates = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                try {
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
                    if (clazz != DomainId.class && DomainId.class.isAssignableFrom(clazz)) {
                        //noinspection unchecked
                        candidates.add((Class<? extends DomainId>) clazz);
                    }
                } catch (ClassNotFoundException | NoClassDefFoundError | ExceptionInInitializerError e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return candidates;
    }

}
