package com.demo.encryptionanddecryption.process;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Optional;

/**
 * @ClassName EnvCopy1
 * @Description
 * @Author H
 * @Date 2021/9/15 14:16
 * @Version 1.0
 */
public class EnvCopy1 {

    StandardEnvironment copy1;

    public EnvCopy1(final ConfigurableEnvironment environment) {
        copy1 = new StandardEnvironment();
        Optional.ofNullable(environment.getPropertySources()).ifPresent(sources -> sources.forEach(ps -> {
            final PropertySource<?> original = ps instanceof EncryptablePropertySource
                    ? ((EncryptablePropertySource) ps).getDelegate()
                    : ps;
            copy1.getPropertySources().addLast(original);
        }));
    }

    public ConfigurableEnvironment get() {
        return copy1;
    }

}
