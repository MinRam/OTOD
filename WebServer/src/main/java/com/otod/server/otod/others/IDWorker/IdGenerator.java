package com.otod.server.otod.others.IDWorker;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

public class IdGenerator implements IdentifierGenerator {
    @Bean
    private SnowFlakeGenerator SnowFlakeGenerator(){
        return new SnowFlakeGenerator(0,0);
    };

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return SnowFlakeGenerator().nextId();
    }
}
