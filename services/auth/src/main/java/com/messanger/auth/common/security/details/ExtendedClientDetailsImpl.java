package com.messanger.auth.common.security.details;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

@Setter
@SuppressWarnings("deprecation")
@EqualsAndHashCode(callSuper = true)
public class ExtendedClientDetailsImpl extends BaseClientDetails implements ExtendedClientDetails {
    private static final long serialVersionUID = -7075981807028175593L;

    private boolean enabled;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "ExtendedClientDetailsImpl [clientId=" + getClientId() + " enabled=" + isEnabled() + "]";
    }
}
