package net.binis.example.service.generator;

import lombok.extern.slf4j.Slf4j;
import net.binis.example.db.entity.BaseEntity;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

//WARNING: Don't use for production!

@Slf4j
public class IdGenerator implements IdentifierGenerator {

    private static long lastTime = System.nanoTime();

    public Long generate(Class<?> cls) {
        //Generate class specific id!
        synchronized (IdGenerator.class) {
            long time = System.nanoTime();
            if (lastTime == time) {
                time++;
            }
            lastTime = time;
            return time;
        }
    }

    public Long generate(final SharedSessionContractImplementor session, final Object object) {
        log.debug("Generating id for " + object.getClass());

        if (object instanceof BaseEntity) {
            return this.generate(object.getClass());
        } else {
            throw new IllegalArgumentException(object.getClass().getName() + " is not a subclass of BaseEntity!");
        }
    }

}