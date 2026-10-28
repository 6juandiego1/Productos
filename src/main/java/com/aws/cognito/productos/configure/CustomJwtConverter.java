package com.aws.cognito.productos.configure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<String> groups = source.getClaimAsStringList("cognito:groups");

        if (groups == null) groups = Collections.emptyList();

        Collection<GrantedAuthority> authorities = groups.stream()
                .map(group -> new SimpleGrantedAuthority("ROLE_" + group))
                .collect(Collectors.toList());

        return new JwtAuthenticationToken(source, authorities);
    }
}
