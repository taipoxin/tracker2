package by.tiranid.tracker.util;

import org.hibernate.SessionFactory;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.Optimizer;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.tuple.IdentifierProperty;
import org.hibernate.tuple.entity.EntityMetamodel;

import java.util.Collection;


public class SequenceIncrementValidator {
    public SequenceIncrementValidator() {
    }

    public void validateSequenceGeneratorIncrement(SessionFactory sessionFactory) {
        Collection<ClassMetadata> metadatas = sessionFactory.getAllClassMetadata().values();
        metadatas.stream().map((metadata) -> {
            return ((AbstractEntityPersister)metadata).getEntityMetamodel();
        }).forEach(this::validate);
    }

    private void validate(EntityMetamodel entity) {
        IdentifierProperty identifierProperty = entity.getIdentifierProperty();
        if(identifierProperty != null) {
            IdentifierGenerator idGen = identifierProperty.getIdentifierGenerator();
            if(idGen instanceof SequenceStyleGenerator) {
                SequenceStyleGenerator seqGen = (SequenceStyleGenerator)idGen;
                Optimizer optimizer = seqGen.getOptimizer();
                if(optimizer != null && optimizer.getIncrementSize() != 1) {
                    throw new IllegalArgumentException(entity.getName() + " uses pooled sequence generator for " + identifierProperty.getName() + " with increment other than 1. This is a problem, since database sequence increment has to be in sync with hibernate one.");
                }
            }
        }
    }
}
